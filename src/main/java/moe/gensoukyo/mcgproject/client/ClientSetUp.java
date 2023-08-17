package moe.gensoukyo.mcgproject.client;

import moe.gensoukyo.mcgproject.registry.MenuRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetUp {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent evt) {
        System.out.println("renderScreens");
        MenuRegistry.renderScreens();
    }
}