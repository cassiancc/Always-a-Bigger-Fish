package cc.cassian.bigger_fish.neoforge;

import net.neoforged.fml.common.Mod;

import cc.cassian.bigger_fish.BiggerFishMod;

@Mod(BiggerFishMod.MOD_ID)
public final class BiggerFishNeoForge {
    public BiggerFishNeoForge() {
        // Run our common setup.
        BiggerFishMod.init();
    }
}
