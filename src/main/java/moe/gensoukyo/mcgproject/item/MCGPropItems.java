package moe.gensoukyo.mcgproject.item;

import moe.gensoukyo.mcgproject.item.prop.TesterItem;
import moe.gensoukyo.mcgproject.registry.ItemRegistry;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class MCGPropItems {//这里是有特殊功能的物品,具体实现请去相应软件包查找(moe.gensoukyo.mcgproject.item.prop)
    public static void preregister(){}
    //物品------------------------------------------------------------------------------------
    //测试物品
    public static final RegistryObject<Item> TESTER = ItemRegistry.registerItem("tester",
            ()->new TesterItem(new Item.Properties()));
}
