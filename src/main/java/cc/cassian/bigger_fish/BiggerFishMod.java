package cc.cassian.bigger_fish;

import cc.cassian.bigger_fish.compat.ModCompat;
//? if >1.21.4 {
/*import cc.cassian.bigger_fish.compat.rrv.RrvIntegration;
*///?}
import cc.cassian.bigger_fish.compat.iteminteractions.ItemInteractionsCompat;
import cc.cassian.bigger_fish.config.ModConfig;
import net.minecraft.resources.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class BiggerFishMod {
    public static final String MOD_ID = "bigger_fish";
    public static final Logger LOGGER = LogManager.getLogger("Always a Bigger Fish");;
    public static final ModConfig CONFIG = ModConfig.createToml(Platform.INSTANCE.getConfigFolder(), "", MOD_ID, ModConfig.class);

    public static void init() {
        // Write common init code here.

        // Load optional compatibility
        if (ModCompat.ITEMINTERACTIONS) {
            ItemInteractionsCompat.touch();
        }
    }

    public static Identifier of(String path) {
        return of(MOD_ID, path);
    }

    public static Identifier of(String namespace, String path) {
        return Identifier.fromNamespaceAndPath(namespace, path);
    }
}
