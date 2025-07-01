package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.Platform;
import cc.cassian.bigger_fish.effect.LeechedMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class BiggerFishMobEffects {
    public static final Holder<MobEffect> LEECHED = Platform.REGISTRY.registerMobEffect("leeched", ()-> new LeechedMobEffect(MobEffectCategory.HARMFUL, 10968927));

    public static void touch() {

    }
}
