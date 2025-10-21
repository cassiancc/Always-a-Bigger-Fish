package cc.cassian.bigger_fish.effect;

import cc.cassian.bigger_fish.registry.BiggerFishMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class LeechedMobEffect extends MobEffect {
    public LeechedMobEffect(MobEffectCategory mobEffectCategory, int i) {
        super(mobEffectCategory, i);
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        if (entity.getHealth() > 1.0F) {
            entity.hurtServer(level, entity.damageSources().magic(), 1.0F);
            if (entity.getRandom().nextBoolean() && entity.getActiveEffects().size() > 1) {
                List<MobEffectInstance> activeEffects = entity.getActiveEffects().stream().toList();
                var effect = getRandomEffectToRemove(activeEffects, entity);
                if (effect != null)
                    entity.removeEffect(effect);
            }
        }

        return true;
    }

    private Holder<MobEffect> getRandomEffectToRemove(List<MobEffectInstance> activeEffects, LivingEntity entity) {
        Holder<MobEffect> effect = activeEffects.get(entity.getRandom().nextInt(0, activeEffects.size())).getEffect();
        if (!effect.equals(BiggerFishMobEffects.LEECHED)) {
            return effect;
        }
        return null;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int i = 50 >> amplifier;
        return i == 0 || duration % i == 0;
    }
}
