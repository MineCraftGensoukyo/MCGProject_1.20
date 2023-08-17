package moe.gensoukyo.mcgproject;

import com.mojang.logging.LogUtils;
import moe.gensoukyo.mcgproject.Command.GensouChestCommand;
import moe.gensoukyo.mcgproject.block.*;
import moe.gensoukyo.mcgproject.item.MCGMaterialItems;
import moe.gensoukyo.mcgproject.item.MCGPropItems;
import moe.gensoukyo.mcgproject.mixin.mixinInterface.PlayerAccess;
import moe.gensoukyo.mcgproject.registry.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
@Mod(MCGProject.MOD_ID)
@Mod.EventBusSubscriber(modid = MCGProject.MOD_ID)
public class MCGProject {
    public final static String MOD_ID = "mcgproject";
    public static final Logger LOGGER = LogUtils.getLogger();//log输出
    public MCGProject(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();//获取Mod总线备用
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;//获取Forge总线备用
        //加载mod新增物品和方块至内存（？）
        MCGMaterialItems.preregister();
        MCGPropItems.preregister();
        MCGNatureBlocks.preregister();
        MCGStructureBlocks.preregister();
        //注册新增物品、方块、创造模式物品栏和实体
        ItemRegistry.ITEMS.register(modEventBus);
        BlockRegistry.BLOCKS.register(modEventBus);
        TabRegistry.MCG_TABS.register(modEventBus);
        EntityRegistry.ENTITIES.register(modEventBus);
        //注册gui
        MenuRegistry.CONTAINERS.register(modEventBus);
        //注册Mod自身
        MinecraftForge.EVENT_BUS.register(this);

        //注册按键事件
        //modEventBus.addListener(HotKeyRegistry::onClientInit);
        //注册实体模型
        modEventBus.addListener(RenderRegistry::onRegisterLayers);
        //注册实体渲染器
        modEventBus.addListener(RenderRegistry::registerRenderers);
        //注册数据包
        //modEventBus.addListener(PacketRegistry::init);
        //为可变色方块定义染色逻辑
        //modEventBus.addListener(BlockColorRegistry::blockColorHandle);

    }
    @SubscribeEvent
    public static void welcomeLogin(PlayerEvent.PlayerLoggedInEvent event){
         Player player = event.getEntity();
        player.sendSystemMessage(Component.translatable("Welcome!"));
    }
    @SubscribeEvent
    public static void registerCommands (RegisterCommandsEvent event) {
        GensouChestCommand.register(event.getDispatcher());
    }
    @SubscribeEvent
    public static void onPlayerCloned (PlayerEvent.Clone event) {
        ((PlayerAccess)event.getEntity()).setContainerList(((PlayerAccess)event.getOriginal()).getContainerList());
        ((PlayerAccess)event.getEntity()).setContainerID(((PlayerAccess)event.getOriginal()).getGensouchestID());
    }
}
