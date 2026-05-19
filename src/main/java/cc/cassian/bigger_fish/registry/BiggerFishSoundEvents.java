package cc.cassian.bigger_fish.registry;

import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

import static cc.cassian.bigger_fish.registry.CommonRegistry.registerSoundEvent;

public class BiggerFishSoundEvents {
    public static final SoundEvent LEECH_THROW = registerSoundEvent("entity.leech.throw");
    public static final SoundEvent GRAPPLING_HOOK_TIGHTEN = registerSoundEvent("entity.player.grappling_hook.tighten");

    public static void touch() {

    }
}
