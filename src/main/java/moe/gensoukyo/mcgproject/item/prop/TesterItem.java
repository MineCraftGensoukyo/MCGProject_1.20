package moe.gensoukyo.mcgproject.item.prop;

//import moe.gensoukyo.mcgproject.entity.entity.projectile.KnifeProjectile;
//import moe.gensoukyo.mcgproject.mixininterface.IMixinLockTarget;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class TesterItem extends Item {
    public TesterItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        /*if (!pLevel.isClientSide) {
            for (int i = 0; i <5; i++) {//i为投射物数量
                KnifeProjectile knife = new KnifeProjectile(pLevel, pPlayer);//创建
                knife.setTrackSpeed(90);
                knife.shootFromRotation(pPlayer,pPlayer.getXRot(), pPlayer.getYRot(), 0F, 1F, 60F);//根据头朝向发射
                LivingEntity tgt = ((IMixinLockTarget)pPlayer).getTarget();
                knife.setTarget(tgt);//录入目标
                pLevel.addFreshEntity(knife);
            }
        }*/
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
