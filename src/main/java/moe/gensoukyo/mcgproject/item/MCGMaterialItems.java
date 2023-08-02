package moe.gensoukyo.mcgproject.item;

import moe.gensoukyo.mcgproject.registry.ItemRegistry;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class MCGMaterialItems{//这里都是些没有特殊功能的物品
    public static void preregister(){}
    //物品------------------------------------------------------------------------------------
    //临时物品1
    public static final RegistryObject<Item> MCGITEM1 = ItemRegistry.registerItem("mcgitem1",
            ()->new Item(new Item.Properties()));
}
