package cc.cassian.bigger_fish.compat;

import cc.cassian.bigger_fish.Platform;

public class ModCompat {
    public static final boolean EIV = Platform.METHODS.isModLoaded("eiv");
    public static final boolean FARMERS_DELIGHT = Platform.METHODS.isModLoaded("farmersdelight");
    public static final boolean COMPOST = Platform.METHODS.isModLoaded("compost");
}
