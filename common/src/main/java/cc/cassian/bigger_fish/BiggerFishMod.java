package cc.cassian.bigger_fish;

import cc.cassian.bigger_fish.registry.BiggerFishItems;
import net.minecraft.resources.ResourceLocation;

public final class BiggerFishMod {
    public static final String MOD_ID = "bigger_fish";

    public static void init() {
        // Write common init code here.
        BiggerFishItems.ITEMS.register();
    }

    public static ResourceLocation of(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
