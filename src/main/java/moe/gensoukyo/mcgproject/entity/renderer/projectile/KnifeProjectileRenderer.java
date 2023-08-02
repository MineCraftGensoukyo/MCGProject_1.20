package moe.gensoukyo.mcgproject.entity.renderer.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import moe.gensoukyo.mcgproject.MCGProject;
import moe.gensoukyo.mcgproject.entity.entity.projectile.KnifeProjectile;
import moe.gensoukyo.mcgproject.entity.model.projectile.KnifeProjectileModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KnifeProjectileRenderer extends EntityRenderer<KnifeProjectile> {

    final KnifeProjectileModel model;

    public KnifeProjectileRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager);
        this.model = new KnifeProjectileModel(renderManager.bakeLayer(KnifeProjectileModel.KNIFE_PROJECTILE_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(KnifeProjectile pEntity) {
        return new ResourceLocation(MCGProject.MOD_ID,"textures/entity/projectile/knife_projectile.png");
    }

    @Override
    public void render(KnifeProjectile pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.pushPose();//渲染相关内容要在这之下
        //让模型根据实体朝向实时进行旋转,尚未移植到1.20.1
        /*pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.yRotO, pEntity.getYRot())+180F));
        pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot())));*/
        //修正模型和实际碰撞箱之间的偏移
        pMatrixStack.translate(0,-1.25,0);
        //绘制模型
        this.model.renderToBuffer(pMatrixStack,pBuffer.getBuffer(this.model.renderType(this.getTextureLocation(pEntity))),pPackedLight, OverlayTexture.NO_OVERLAY,1f,1f,1f,1f);
        pMatrixStack.popPose();//渲染相关内容要在这之上
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
