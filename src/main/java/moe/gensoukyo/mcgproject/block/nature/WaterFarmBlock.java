package moe.gensoukyo.mcgproject.block.nature;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class WaterFarmBlock extends FarmBlock {

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 13.0D, 16.0D);

    public WaterFarmBlock(Properties pProperties) {
        super(pProperties);
        //默认是带水的
        this.registerDefaultState(this.stateDefinition.any().setValue(MOISTURE, 7));
    }


    @Override//重写形状，否则方块高度会高于水平面
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override//重写逻辑，土地仍会变干，但不会变回泥土了
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        int i = pState.getValue(MOISTURE);//获取湿润度
        if (!isNearWater(pLevel, pPos) && !pLevel.isRainingAt(pPos.above())) {//不在水附近或没在下雨
            if (i > 0) {//渐渐变干
                pLevel.setBlock(pPos, pState.setValue(MOISTURE, i - 1), 2);
            }
        } else if (i < 7) {//有水，变湿
            pLevel.setBlock(pPos, pState.setValue(MOISTURE, 7), 2);
        }
    }

    @Override//删去踩踏后变回泥土的特性，不过似乎连带着带来了免疫掉落伤害的效果
    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {}

    //这是完全从耕地方块搬来的，private方法继承不过来，啧
    private static boolean isNearWater(LevelReader pLevel, BlockPos pPos) {
        for (BlockPos blockpos : BlockPos.betweenClosed(pPos.offset(-4, -1, -4), pPos.offset(4, 1, 4))) {
            if (pLevel.getFluidState(blockpos).is(FluidTags.WATER)) {
                return true;
            }
        }
        return net.minecraftforge.common.FarmlandWaterManager.hasBlockWaterTicket(pLevel, pPos);
    }

}

    
