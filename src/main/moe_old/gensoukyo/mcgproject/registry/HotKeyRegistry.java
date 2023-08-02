package moe.gensoukyo.mcgproject.registry;

import com.mojang.blaze3d.platform.InputConstants;
import moe.gensoukyo.mcgproject.MCGProject;
import moe.gensoukyo.mcgproject.network.PacketLockTarget;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MCGProject.MOD_ID, value = Dist.CLIENT)
public class HotKeyRegistry {

    //本地化用的键
    public static final String KEY_CATEGORIES_MCGPROJECT = "key.categories.mcgproject";
    public static final String KEY_LOCK_TARGET = "key.mcgproject.locktarget";

    public static KeyMapping lockTargetKeyMapping;

    public static void init() {
        lockTargetKeyMapping = new KeyMapping(KEY_LOCK_TARGET, KeyConflictContext.IN_GAME,
                InputConstants.getKey("key.keyboard.q"), KEY_CATEGORIES_MCGPROJECT);
        ClientRegistry.registerKeyBinding(lockTargetKeyMapping);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        if (lockTargetKeyMapping.consumeClick()) {//锁定目标键被按下时
            PacketRegistry.sendToServer(new PacketLockTarget());//发包给服务器处理
        }
    }

    @SubscribeEvent
    public static void onClientInit(FMLClientSetupEvent event){
        MinecraftForge.EVENT_BUS.addListener(HotKeyRegistry::onKeyInput);
        init();
    }
}
