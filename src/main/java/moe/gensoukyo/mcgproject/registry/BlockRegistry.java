package moe.gensoukyo.mcgproject.registry;

import moe.gensoukyo.mcgproject.MCGProject;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockRegistry {
    private BlockRegistry(){}
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MCGProject.MOD_ID);
    //方块注册器-----------------------------
    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> registeredBlock = BLOCKS.register(name,block);
        registerBlockItem(name,registeredBlock);
        return registeredBlock;
    }
    public static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block, CreativeModeTab tab){
        return BLOCKS.register(name,block);
    }
    //方块物品注册器---------------------------------------
    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ItemRegistry.ITEMS.register(name,()-> new BlockItem(block.get(),new Item.Properties()));
    }
}
