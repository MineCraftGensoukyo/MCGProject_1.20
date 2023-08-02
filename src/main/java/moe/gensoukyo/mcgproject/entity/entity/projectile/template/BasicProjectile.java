package moe.gensoukyo.mcgproject.entity.entity.projectile.template;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.minecraftforge.network.NetworkHooks;

//投射物基础模板
public abstract class BasicProjectile extends Projectile {

    private static final EntityDataAccessor<Byte> PIERCE_LEVEL = SynchedEntityData.defineId(BasicProjectile.class, EntityDataSerializers.BYTE);
    private int max_life = 200;
    private int life;
    public int health = 1;
    public float drag = 0;//阻力(TODO)
    public Vec3 acceleration = new Vec3(0,0,0);//加速度(TODO)

    //在世界的0，0，0处生成投射物
    protected BasicProjectile(EntityType<? extends BasicProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
        this.noPhysics=false;
    }

    //根据坐标生成投射物
    protected BasicProjectile(EntityType<? extends BasicProjectile> pEntityType, double pX, double pY, double pZ, Level pLevel){
        this(pEntityType, pLevel);
        this.setPos(pX, pY, pZ);
    }

    //在发射者的位置生成投射物
    protected BasicProjectile(EntityType<? extends BasicProjectile> pEntityType, LivingEntity pShooter, Level pLevel) {
        this(pEntityType, pShooter.getX(), pShooter.getEyeY() - (double)0.3F, pShooter.getZ(), pLevel);
        this.setOwner(pShooter);
    }

    @Override
    public void tick(){
        //检查是否有命中
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
            this.onHit(hitresult);//有命中则调用对应方法
        }
        tickDespawn();//寿命增长，超过最大寿命则清除
        Vec3 v = this.getDeltaMovement();
        if (!this.isNoGravity()) {//重力
            this.setDeltaMovement(v.x, v.y - 0.02, v.z);
        }
        this.move();
        super.tick();
    }

    //运动
    public void move(){

        //位移
        Vec3 vec3 = this.getDeltaMovement();
        double dx = vec3.x;
        double dy = vec3.y;
        double dz = vec3.z;

        double x = this.getX() + dx;
        double y = this.getY() + dy;
        double z = this.getZ() + dz;

        this.setPos(x, y, z);

        //旋转，朝向速度方向
        double d0 = vec3.horizontalDistance();
        this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
        this.setXRot((float)(Mth.atan2(vec3.y, d0) * (double)(180F / (float)Math.PI)));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if(this.getOwner()!=pResult.getEntity()){
            byte pierce = this.getPierceLevel();
            if(pierce > 0){
                this.setPierceLevel((byte) (pierce-1));
            }
            else{
                this.despawn();
            }
            super.onHitEntity(pResult);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        this.despawn();
    }

    //消灭投射物，或许可以由某些技能调用
    public void despawn(){
        this.remove(RemovalReason.KILLED);
    }

    //寿命检测，超时则移除投射物实体
    protected void tickDespawn() {
        ++this.life;
        if (this.life >= max_life) {
            this.discard();
        }
    }

    public boolean hurt(DamageSource pSource, float pAmount) {
        if (!this.level().isClientSide) {
            this.health -= pAmount;
            if (this.health <= 0){
                ((ServerLevel)this.level()).sendParticles(ParticleTypes.CRIT, this.getX(), this.getY(), this.getZ(), 15, 0.2D, 0.2D, 0.2D, 0.0D);
                this.discard();
            }
        }
        return true;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(PIERCE_LEVEL, (byte)0);
    }

    public byte getPierceLevel() {
        return this.entityData.get(PIERCE_LEVEL);
    }

    public void setPierceLevel(byte pPierceLevel) {
        this.entityData.set(PIERCE_LEVEL, pPierceLevel);
    }

    public int getMax_life() {
        return max_life;
    }

    public void setMax_life(int max_life) {
        this.max_life = max_life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
