package cc.cassian.bigger_fish.entity;

import cc.cassian.bigger_fish.registry.BiggerFishEntityTypes;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import cc.cassian.bigger_fish.registry.BiggerFishMobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class LeechEntity extends ThrowableItemProjectile {

    public LeechEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public LeechEntity(Level level, double x, double y, double z) {
        super(BiggerFishEntityTypes.LEECH.get(), x, y, z, level);
    }

    public LeechEntity(Level level, Player player) {
        super(BiggerFishEntityTypes.LEECH.get(), player, level);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
         if (result.getEntity() instanceof LivingEntity living && living.isAffectedByPotions()) {
             living.addEffect(new MobEffectInstance(BiggerFishMobEffects.LEECHED, 600));
         }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return BiggerFishItems.LEECH.get();
    }
}
