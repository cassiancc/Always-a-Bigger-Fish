package cc.cassian.bigger_fish;

import cc.cassian.bigger_fish.compat.CompostCompat;
import cc.cassian.bigger_fish.compat.ModCompat;
import cc.cassian.bigger_fish.compat.eiv.EivIntegration;
import cc.cassian.bigger_fish.config.ModConfig;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class BiggerFishMod {
    public static final String MOD_ID = "bigger_fish";
    public static final Logger LOGGER = LogManager.getLogger("Always a Bigger Fish");;
    public static final ModConfig CONFIG = ModConfig.createToml(ModConfig.configPath(), "", MOD_ID, ModConfig.class);

    public static void init() {
        // Write common init code here.

        // Load optional compatibility
        if (ModCompat.COMPOST) {
            CompostCompat.register();
        }
        if (ModCompat.EIV) {
            EivIntegration.hideStacks();
        }
    }

    public static ResourceLocation of(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
