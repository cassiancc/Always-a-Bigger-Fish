package cc.cassian.bigger_fish;

import cc.cassian.bigger_fish.compat.ModCompat;
//? if >1.21.4 {
import cc.cassian.bigger_fish.compat.eiv.EivIntegration;
//?}
import cc.cassian.bigger_fish.compat.iteminteractions.ItemInteractionsCompat;
import cc.cassian.bigger_fish.config.ModConfig;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class BiggerFishMod {
    public static final String MOD_ID = "bigger_fish";
    public static final Logger LOGGER = LogManager.getLogger("Always a Bigger Fish");;
    public static final ModConfig CONFIG = ModConfig.createToml(Platform.INSTANCE.getConfigFolder(), "", MOD_ID, ModConfig.class);

    public static void init() {
        // Write common init code here.

        // Load optional compatibility
        //? if >1.21.4 {
        if (ModCompat.EIV) {
            EivIntegration.hideStacks();
        }
        //?}
        if (ModCompat.ITEMINTERACTIONS) {
            ItemInteractionsCompat.touch();
        }
    }

    public static ResourceLocation of(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
