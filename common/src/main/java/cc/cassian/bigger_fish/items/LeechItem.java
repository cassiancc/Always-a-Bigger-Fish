package cc.cassian.bigger_fish.items;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class LeechItem extends Item {
    public LeechItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        Player player = useOnContext.getPlayer();
        var result = this.calculateHitResult(player);
        if (player instanceof ServerPlayer serverPlayer && result.getType() == HitResult.Type.ENTITY) {
           if (result instanceof EntityHitResult hitResult) {
               // TODO unique effect
               if (hitResult.getEntity() instanceof LivingEntity livingEntity && livingEntity.isAffectedByPotions()) {
                   livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 5, 1, true, true));
               }
           }
        }
        useOnContext.getItemInHand().consume(1, player);

        return InteractionResult.PASS;
    }

    private HitResult calculateHitResult(Player player) {
        return ProjectileUtil.getHitResultOnViewVector(player, EntitySelector.CAN_BE_PICKED, player.blockInteractionRange());
    }
}
