package moe.gensoukyo.mcgproject.registry;

import moe.gensoukyo.mcgproject.MCGProject;
import moe.gensoukyo.mcgproject.entity.model.projectile.KnifeProjectileModel;
import moe.gensoukyo.mcgproject.entity.renderer.projectile.KnifeProjectileRenderer;
import moe.gensoukyo.mcgproject.geckolib.renderer.entity.ReplacedPlayerRenderer;
import moe.gensoukyo.mcgproject.mixininterface.IMixinLockTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MCGProject.MOD_ID, value = Dist.CLIENT)
public class RenderRegistry {

    @SubscribeEvent//方块透明度的处理
    public static void registerBlockRender(final FMLClientSetupEvent event){
        ItemBlockRenderTypes.setRenderLayer(MCGNatureBlocks.IVY.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MCGNatureBlocks.CLOVER.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MCGNatureBlocks.LUCKY_CLOVER.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MCGNatureBlocks.PADDY.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MCGNatureBlocks.PADDY_DENSE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MCGNatureBlocks.PADDY_SPARSE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MCGNatureBlocks.WATER_FARMLAND.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(MCGNatureBlocks.BAMBOO.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MCGNatureBlocks.BAMBOO_BIG_LEAVES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MCGNatureBlocks.BAMBOO_SMALL_LEAVES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MCGNatureBlocks.BAMBOO_SHOOT_FRESH.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MCGNatureBlocks.BAMBOO_SHOOT_OLD.get(), RenderType.cutoutMipped());
    }

    @SubscribeEvent//注册实体模型
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(KnifeProjectileModel.KNIFE_PROJECTILE_LAYER, KnifeProjectileModel::createBodyLayer);
    }

    @SubscribeEvent//注册实体渲染器
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegistry.KNIFE_PROJECTILE.get(), KnifeProjectileRenderer::new);
    }

    /*@SubscribeEvent//替换玩家模型
    public static void registerPlayerRenderers(final RenderPlayerEvent.Pre event) {
        //以下代码来自Yes Steve Mod
        EntityRenderDispatcher dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
        EntityModelSet entityModels = Minecraft.getInstance().getEntityModels();
        Font font = Minecraft.getInstance().font;
        EntityRendererProvider.Context context = new EntityRendererProvider.Context(dispatcher,itemRenderer,resourceManager,entityModels,font);
        //以上代码来自Yes Steve Mod
        //new一个geckolib的玩家渲染器
        ReplacedPlayerRenderer renderer = new ReplacedPlayerRenderer(context);
        //调用Geckolib提供的渲染方法
        renderer.render(event.getPlayer(), event.getPlayer().getYRot(), event.getPartialTick(), event.getPoseStack(), event.getMultiBufferSource(), event.getPackedLight());
        event.setCanceled(true);//移除原版玩家渲染
    }*/
}
