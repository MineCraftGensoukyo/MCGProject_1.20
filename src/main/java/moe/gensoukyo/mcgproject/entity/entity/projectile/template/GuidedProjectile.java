package moe.gensoukyo.mcgproject.entity.entity.projectile.template;

import moe.gensoukyo.mcgproject.entity.entity.projectile.KnifeProjectile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

//有简易制导功能的投射物模板
public abstract class GuidedProjectile extends BasicProjectile {

    @Nullable
    private Entity target;//投射物当前锁定的目标
    private float trackSpeed = 0;//跟踪能力，rad/tick

    //在世界的0，0，0处生成投射物
    protected GuidedProjectile(EntityType<? extends BasicProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    //根据坐标生成投射物
    protected GuidedProjectile(EntityType<? extends BasicProjectile> pEntityType, double pX, double pY, double pZ, Level pLevel){
        super(pEntityType,pX,pY,pZ,pLevel);
    }

    //在发射者的位置生成投射物
    protected GuidedProjectile(EntityType<? extends BasicProjectile> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType,pShooter,pLevel);
    }

    @Override
    public void tick() {
        track();
        if (this.level().isClientSide) {
            this.level().addParticle(ParticleTypes.END_ROD, true, this.getX(), this.getY() + 0.15D, this.getZ(), 0.0D, 0.0D, 0.0D);
        }
        super.tick();
    }

    protected void track(){//简单的追踪目标当前位置，不考虑其速度

        if(this.getTarget()!=null) {
            Vec3 v = this.getDeltaMovement();//获取速度方向
            float speed = (float) v.length();//获取速度大小
            v= v.normalize();//速度方向单位化
            //获取目标方向
            Vec3 tgt = this.getTarget().getPosition(1).subtract(this.getPosition(1)).normalize();
            Vec3 aim;//创建新的瞄准方向
            float deltaA = (float) Math.acos(v.dot(tgt));//计算角度差（弧度制）
            if(Math.abs(deltaA)<=this.getTrackSpeed()){//目标方向在角度限制之内则
                aim = tgt;//直接转向目标
            }
            else{//否则应用罗德里格旋转公式
                Vec3 axis = v.cross(tgt);//获取旋转轴
                Vec3 aim1 = v.scale(Math.cos(-getTrackSpeed()));//第一项
                Vec3 aim2 = axis.scale(v.dot(axis)*(1-Math.cos(-getTrackSpeed())));//第二项
                Vec3 aim3 = v.cross(axis).scale(Math.sin(-getTrackSpeed()));//第三项
                aim =aim1.add(aim2).add(aim3);//向目标旋转限定角度
            }
            this.setDeltaMovement(aim.scale(speed));
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        this.setTarget(null);
        super.onHitEntity(pResult);
    }

    public Entity getTarget() {
        return target;
    }

    public void setTarget(LivingEntity target) {
        this.target = target;
    }

    public float getTrackSpeed() {
        return trackSpeed;
    }

    //跟踪角速度，degree/s
    public void setTrackSpeed(float trackSpeed) {
        this.trackSpeed = (float) (trackSpeed*Math.PI/180/20);//角度转弧度，秒转tick
    }

}
