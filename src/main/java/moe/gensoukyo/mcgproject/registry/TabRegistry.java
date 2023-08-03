package moe.gensoukyo.mcgproject.registry;

import moe.gensoukyo.mcgproject.MCGProject;
import moe.gensoukyo.mcgproject.block.MCGNatureBlocks;
import moe.gensoukyo.mcgproject.block.MCGStructureBlocks;
import moe.gensoukyo.mcgproject.item.MCGMaterialItems;
import moe.gensoukyo.mcgproject.item.MCGPropItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TabRegistry {//创造模式物品栏
    public static final DeferredRegister<CreativeModeTab> MCG_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MCGProject.MOD_ID);
    //MCG:结构
    public static final RegistryObject<CreativeModeTab> MCG_STRUCTURE = MCG_TABS.register("mcg_structure", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> new ItemStack(MCGStructureBlocks.NAMAKO.get()))//图标，要求ItemStack
            .displayItems((parameters, output) -> {//这里填属于此类的方块或物品
                output.accept(MCGStructureBlocks.NAMAKO.get());
                output.accept(MCGStructureBlocks.TATAMI.get());
                output.accept(MCGStructureBlocks.TATAMI_NO_SIDE.get());
            }).build());
    //MCG:自然
    public static final RegistryObject<CreativeModeTab> MCG_NATURE = MCG_TABS.register("mcg_nature", () -> CreativeModeTab.builder()
            .withTabsBefore(MCG_STRUCTURE.getKey())
            .icon(() -> new ItemStack(MCGNatureBlocks.IVY.get()))
            .displayItems((parameters, output) -> {
                output.accept(MCGNatureBlocks.IVY.get());
                output.accept(MCGNatureBlocks.CLOVER.get());
                output.accept(MCGNatureBlocks.LUCKY_CLOVER.get());
                output.accept(MCGNatureBlocks.PADDY.get());
                output.accept(MCGNatureBlocks.PADDY_SPARSE.get());
                output.accept(MCGNatureBlocks.PADDY_DENSE.get());
                output.accept(MCGNatureBlocks.WATER_FARMLAND.get());
                output.accept(MCGNatureBlocks.BAMBOO.get());
                output.accept(MCGNatureBlocks.BAMBOO_BIG_LEAVES.get());
                output.accept(MCGNatureBlocks.BAMBOO_SMALL_LEAVES.get());
                output.accept(MCGNatureBlocks.BAMBOO_SHOOT_FRESH.get());
                output.accept(MCGNatureBlocks.BAMBOO_SHOOT_OLD.get());
                output.accept(MCGNatureBlocks.IRIS.get());
                output.accept(MCGNatureBlocks.FLOWER_GRAY_FIELD_SPEEDWELL.get());
                output.accept(MCGNatureBlocks.ARROWHEAD.get());
                output.accept(MCGNatureBlocks.ARROWHEAD_DENSE.get());
                output.accept(MCGNatureBlocks.SILVERGRASS_HIGH.get());
                output.accept(MCGNatureBlocks.SILVERGRASS_DENSE.get());
            }).build());
    //MCG:道具
    public static final RegistryObject<CreativeModeTab> MCG_PROP = MCG_TABS.register("mcg_prop", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> MCGPropItems.TESTER.get().getDefaultInstance())//图标，要求ItemStack
            .displayItems((parameters, output) -> {//这里填属于此类的方块或物品
                output.accept(MCGPropItems.TESTER.get());
            }).build());
    //MCG:材料
    public static final RegistryObject<CreativeModeTab> MCG_MATERIAL = MCG_TABS.register("mcg_material", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> MCGMaterialItems.MCGITEM1.get().getDefaultInstance())//图标，要求ItemStack
            .displayItems((parameters, output) -> {//这里填属于此类的方块或物品
                output.accept(MCGMaterialItems.MCGITEM1.get());
            }).build());
}
