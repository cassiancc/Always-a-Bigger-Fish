package cc.cassian.bigger_fish.fabric;

import net.fabricmc.api.ModInitializer;

import cc.cassian.bigger_fish.BiggerFishMod;

public final class BiggerFishFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        BiggerFishMod.init();
    }
}
