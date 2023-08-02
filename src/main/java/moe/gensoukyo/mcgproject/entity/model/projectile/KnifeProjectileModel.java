package moe.gensoukyo.mcgproject.entity.model.projectile;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import moe.gensoukyo.mcgproject.MCGProject;
import moe.gensoukyo.mcgproject.entity.entity.projectile.KnifeProjectile;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

//这是用Blockbench制作的！选模组版实体！
public class KnifeProjectileModel extends EntityModel<KnifeProjectile> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation KNIFE_PROJECTILE_LAYER = new ModelLayerLocation(new ResourceLocation(MCGProject.MOD_ID, "knife_projectile"), "main");
	private final ModelPart Root;

	public KnifeProjectileModel(ModelPart root) {
		this.Root = root.getChild("Root");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Root = partdefinition.addOrReplaceChild("Root", CubeListBuilder.create().texOffs(6, 3).addBox(-0.5F, 1.125F, 0.9914F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(-0.5F, -3.125F, 0.9914F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.5F, -1.0F, 0.9914F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 18).addBox(-0.5F, -0.5F, 3.9914F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = Root.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(14, 23).addBox(-0.5F, 0.25F, -7.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5663F, -0.0945F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r2 = Root.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(1, 2).addBox(-0.5F, -1.0F, -13.0F, 1.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5663F, 0.1305F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r3 = Root.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(10, 12).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0071F, 4.0097F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r4 = Root.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(1, 12).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0071F, 4.0097F, 0.48F, 0.0F, 0.0F));

		PartDefinition cube_r5 = Root.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(14, 23).addBox(-0.5F, -2.25F, -7.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5578F, -0.225F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r6 = Root.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(1, 2).addBox(-0.5F, -1.0F, -13.0F, 1.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5578F, 0.0F, -0.1309F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(KnifeProjectile entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}