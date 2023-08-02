package moe.gensoukyo.mcgproject.registry;

import moe.gensoukyo.mcgproject.MCGProject;
import moe.gensoukyo.mcgproject.entity.model.projectile.KnifeProjectileModel;
import moe.gensoukyo.mcgproject.entity.renderer.projectile.KnifeProjectileRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MCGProject.MOD_ID, value = Dist.CLIENT)
public class RenderRegistry {

    //透明度处理不再需要mod操作，仅需在模型json文件中加入一行"render_type": "cutout"

    @SubscribeEvent//注册实体模型
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(KnifeProjectileModel.KNIFE_PROJECTILE_LAYER, KnifeProjectileModel::createBodyLayer);
    }

    @SubscribeEvent//注册实体渲染器
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegistry.KNIFE_PROJECTILE.get(), KnifeProjectileRenderer::new);
    }
}
