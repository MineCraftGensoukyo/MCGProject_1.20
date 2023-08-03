package moe.gensoukyo.mcgproject.block;

import moe.gensoukyo.mcgproject.block.nature.BambooBlock;
import moe.gensoukyo.mcgproject.block.nature.IvyBlock;
import moe.gensoukyo.mcgproject.block.nature.WaterFarmBlock;
import moe.gensoukyo.mcgproject.registry.BlockRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.RegistryObject;

public class MCGNatureBlocks{
    public static void preregister(){}
    //方块----------------------------------------------------
    //爬山虎
    public static final RegistryObject<Block> IVY = BlockRegistry.registerBlock("ivy",
            ()->new IvyBlock(BlockBehaviour.Properties.of().noCollission().sound(SoundType.GRASS)));
    //三叶草
    public static final RegistryObject<Block> CLOVER = BlockRegistry.registerBlock("clover",
            ()->new Block(BlockBehaviour.Properties.of().noCollission().sound(SoundType.GRASS)));
    //带四叶草三叶草
    public static final RegistryObject<Block> LUCKY_CLOVER = BlockRegistry.registerBlock("lucky_clover",
            ()->new Block(BlockBehaviour.Properties.of().noCollission().sound(SoundType.GRASS)));
    //水稻苗
    public static final RegistryObject<Block> PADDY = BlockRegistry.registerBlock("paddy",
            ()->new Block(BlockBehaviour.Properties.of().noCollission().noOcclusion().sound(SoundType.CROP)));
    //稀疏水稻苗
    public static final RegistryObject<Block> PADDY_SPARSE = BlockRegistry.registerBlock("paddy_sparse",
            ()->new Block(BlockBehaviour.Properties.of().noCollission().noOcclusion().sound(SoundType.CROP)));
    //密植水稻苗
    public static final RegistryObject<Block> PADDY_DENSE = BlockRegistry.registerBlock("paddy_dense",
            ()->new Block(BlockBehaviour.Properties.of().noCollission().noOcclusion().sound(SoundType.CROP)));
    //水田
    public static final RegistryObject<Block> WATER_FARMLAND = BlockRegistry.registerBlock("water_farmland",
            ()->new WaterFarmBlock(BlockBehaviour.Properties.of().randomTicks().strength(0.6F).sound(SoundType.GRAVEL)));
    //竹子_光杆
    public static final RegistryObject<Block> BAMBOO = BlockRegistry.registerBlock("bamboo",
            ()->new BambooBlock(BlockBehaviour.Properties.of().sound(SoundType.BAMBOO).dynamicShape().noOcclusion().randomTicks()));
    //竹子_大叶
    public static final RegistryObject<Block> BAMBOO_BIG_LEAVES = BlockRegistry.registerBlock("bamboo_big_leaves",
            ()->new BambooBlock(BlockBehaviour.Properties.of().sound(SoundType.BAMBOO).dynamicShape().noOcclusion().randomTicks()));
    //竹子_小叶
    public static final RegistryObject<Block> BAMBOO_SMALL_LEAVES = BlockRegistry.registerBlock("bamboo_small_leaves",
            ()->new BambooBlock(BlockBehaviour.Properties.of().sound(SoundType.BAMBOO).dynamicShape().noOcclusion().randomTicks()));
    //嫩竹笋
    public static final RegistryObject<Block> BAMBOO_SHOOT_FRESH = BlockRegistry.registerBlock("bamboo_shoot_fresh",
            ()->new Block(BlockBehaviour.Properties.of().noCollission().noOcclusion().sound(SoundType.BAMBOO_SAPLING)));
    //老竹笋
    public static final RegistryObject<Block> BAMBOO_SHOOT_OLD = BlockRegistry.registerBlock("bamboo_shoot_old",
            ()->new Block(BlockBehaviour.Properties.of().noCollission().noOcclusion().sound(SoundType.BAMBOO_SAPLING)));

    // 鸢尾
    public static final RegistryObject<Block> IRIS = BlockRegistry.registerBlock("iris",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));

    // 婆婆纳
    public static final RegistryObject<Block> FLOWER_GRAY_FIELD_SPEEDWELL = BlockRegistry.registerBlock("flower_gray_field_speedwell",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));

    // 慈姑
    public static final RegistryObject<Block> ARROWHEAD = BlockRegistry.registerBlock("arrowhead",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));

    // 茂密慈姑
    public static final RegistryObject<Block> ARROWHEAD_DENSE = BlockRegistry.registerBlock("arrowhead_dense",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));

    // 芒草
    public static final RegistryObject<Block> SILVERGRASS_DENSE = BlockRegistry.registerBlock("silvergrass_dense",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));

    // 高芒草
    public static final RegistryObject<Block> SILVERGRASS_HIGH = BlockRegistry.registerBlock("silvergrass_high",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
}
