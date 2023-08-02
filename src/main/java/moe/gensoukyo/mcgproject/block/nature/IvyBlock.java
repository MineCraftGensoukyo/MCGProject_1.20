package moe.gensoukyo.mcgproject.block.nature;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class IvyBlock extends Block implements net.minecraftforge.common.IForgeShearable {
    public static final BooleanProperty UP = PipeBlock.UP;
    public static final BooleanProperty NORTH = PipeBlock.NORTH;
    public static final BooleanProperty EAST = PipeBlock.EAST;
    public static final BooleanProperty SOUTH = PipeBlock.SOUTH;
    public static final BooleanProperty WEST = PipeBlock.WEST;
    public static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = PipeBlock.PROPERTY_BY_DIRECTION.entrySet().stream().filter((p_57886_) -> {
        return p_57886_.getKey() != Direction.DOWN;
    }).collect(Util.toMap());
    private static final VoxelShape UP_AABB = Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape WEST_AABB = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
    private static final VoxelShape EAST_AABB = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
    private static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
    private final Map<BlockState, VoxelShape> shapesCache;
    //基本全都是从藤蔓VineBlock.java抄来的
    public IvyBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(UP, Boolean.FALSE).setValue(NORTH, Boolean.FALSE).setValue(EAST, Boolean.FALSE).setValue(SOUTH, Boolean.FALSE).setValue(WEST, Boolean.FALSE));
        this.shapesCache = ImmutableMap.copyOf(this.stateDefinition.getPossibleStates().stream().collect(Collectors.toMap(Function.identity(), IvyBlock::calculateShape)));
    }

    private static VoxelShape calculateShape(BlockState p_57906_) {
        VoxelShape voxelshape = Shapes.empty();
        if (p_57906_.getValue(UP)) {
            voxelshape = UP_AABB;
        }

        if (p_57906_.getValue(NORTH)) {
            voxelshape = Shapes.or(voxelshape, NORTH_AABB);
        }

        if (p_57906_.getValue(SOUTH)) {
            voxelshape = Shapes.or(voxelshape, SOUTH_AABB);
        }

        if (p_57906_.getValue(EAST)) {
            voxelshape = Shapes.or(voxelshape, EAST_AABB);
        }

        if (p_57906_.getValue(WEST)) {
            voxelshape = Shapes.or(voxelshape, WEST_AABB);
        }

        return voxelshape.isEmpty() ? Shapes.block() : voxelshape;
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return this.shapesCache.get(pState);
    }

    private boolean hasFaces(BlockState pState) {
        return this.countFaces(pState) > 0;
    }

    private int countFaces(BlockState pState) {
        int i = 0;

        for(BooleanProperty booleanproperty : PROPERTY_BY_DIRECTION.values()) {
            if (pState.getValue(booleanproperty)) {
                ++i;
            }
        }

        return i;
    }

    public static boolean isAcceptableNeighbour(BlockGetter pBlockReader, BlockPos pNeighborPos, Direction pAttachedFace) {
        BlockState blockstate = pBlockReader.getBlockState(pNeighborPos);
        return Block.isFaceFull(blockstate.getCollisionShape(pBlockReader, pNeighborPos), pAttachedFace.getOpposite());
    }

    private BlockState getUpdatedState(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.above();
        if (pState.getValue(UP)) {
            pState = pState.setValue(UP, isAcceptableNeighbour(pLevel, blockpos, Direction.DOWN));
        }

        for(Direction direction : Direction.Plane.HORIZONTAL) {
            BooleanProperty booleanproperty = getPropertyForFace(direction);
            if (pState.getValue(booleanproperty)) {
                pState = pState.setValue(booleanproperty, true);
            }
        }

        return pState;
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pFacing == Direction.DOWN) {
            return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
        } else {
            BlockState blockstate = this.getUpdatedState(pState, pLevel, pCurrentPos);
            return !this.hasFaces(blockstate) ? Blocks.AIR.defaultBlockState() : blockstate;
        }
    }

    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        BlockState blockstate = pUseContext.getLevel().getBlockState(pUseContext.getClickedPos());
        if (blockstate.is(this)) {
            return this.countFaces(blockstate) < PROPERTY_BY_DIRECTION.size();
        } else {
            return super.canBeReplaced(pState, pUseContext);
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos());
        boolean flag = blockstate.is(this);//判断是放置了新方块还是更新已有方块
        BlockState blockstate1 = flag ? blockstate : this.defaultBlockState();//更新已有方块则先复制原有方块状态，否则新建一个默认状态
        for (Direction direction : pContext.getNearestLookingDirections()) {//抄的藤蔓VinBlock.java代码
            if (direction != Direction.DOWN) {
                BooleanProperty booleanproperty = getPropertyForFace(direction);//获取要更新的朝向
                boolean flag1 = flag && blockstate.getValue(booleanproperty);
                if (!flag1){//新放置爬山虎或已有位置对应面无爬山虎
                    return blockstate1.setValue(booleanproperty, Boolean.TRUE);//则根据玩家朝向为对应面添加爬山虎
                }
            }
        }

        return flag ? blockstate1 : null;
    }
    //注册方块状态
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(UP, NORTH, EAST, SOUTH, WEST);
    }
    //不知道有什么用，或许用于使用命令放置方块时，总之抄过来
    public BlockState rotate(BlockState pState, Rotation pRotate) {
        switch(pRotate) {
            case CLOCKWISE_180:
                return pState.setValue(NORTH, pState.getValue(SOUTH)).setValue(EAST, pState.getValue(WEST)).setValue(SOUTH, pState.getValue(NORTH)).setValue(WEST, pState.getValue(EAST));
            case COUNTERCLOCKWISE_90:
                return pState.setValue(NORTH, pState.getValue(EAST)).setValue(EAST, pState.getValue(SOUTH)).setValue(SOUTH, pState.getValue(WEST)).setValue(WEST, pState.getValue(NORTH));
            case CLOCKWISE_90:
                return pState.setValue(NORTH, pState.getValue(WEST)).setValue(EAST, pState.getValue(NORTH)).setValue(SOUTH, pState.getValue(EAST)).setValue(WEST, pState.getValue(SOUTH));
            default:
                return pState;
        }
    }
    //不知道有什么用，或许用于使用命令放置方块时，总之抄过来
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        switch(pMirror) {
            case LEFT_RIGHT:
                return pState.setValue(NORTH, pState.getValue(SOUTH)).setValue(SOUTH, pState.getValue(NORTH));
            case FRONT_BACK:
                return pState.setValue(EAST, pState.getValue(WEST)).setValue(WEST, pState.getValue(EAST));
            default:
                return super.mirror(pState, pMirror);
        }
    }
    //抄
    public static BooleanProperty getPropertyForFace(Direction pFace) {
        return PROPERTY_BY_DIRECTION.get(pFace);
    }
}
