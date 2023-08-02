package moe.gensoukyo.mcgproject.registry;

import moe.gensoukyo.mcgproject.MCGProject;
import moe.gensoukyo.mcgproject.entity.entity.projectile.KnifeProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MCGProject.MOD_ID);
//-------------------------------------------------
    public static final RegistryObject<EntityType<KnifeProjectile>> KNIFE_PROJECTILE = ENTITIES.register("knife_projectile",
        ()-> EntityType.Builder.<KnifeProjectile>of(KnifeProjectile::new, MobCategory.MISC)
                .sized(0.5f, 0.5f)
                .clientTrackingRange(4)
                .updateInterval(1)
                .build("knife_projectile"));
}
