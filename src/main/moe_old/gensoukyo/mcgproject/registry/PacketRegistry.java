package moe.gensoukyo.mcgproject.registry;

import moe.gensoukyo.mcgproject.MCGProject;
import moe.gensoukyo.mcgproject.network.PacketLockTarget;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod.EventBusSubscriber(modid = MCGProject.MOD_ID)
public class PacketRegistry {
    private static SimpleChannel CHANNEL;

    // Every packet needs a unique ID (unique for this channel)
    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event){
        PacketRegistry.register();
    }

    public static void register() {

        // Make the channel. If needed you can do version checking here
        CHANNEL = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(MCGProject.MOD_ID, "network"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        // Register all our packets. We only have one right now. The new message has a unique ID, an indication
        // of how it is going to be used (from client to server) and ways to encode and decode it. Finally 'handle'
        // will actually execute when the packet is received
        CHANNEL.messageBuilder(PacketLockTarget.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PacketLockTarget::new)
                .encoder(PacketLockTarget::toBytes)
                .consumer(PacketLockTarget::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        CHANNEL.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}

