package cc.cassian.bigger_fish.compat;

import  cc.cassian.bigger_fish.Platform;

public class ModCompat {
    public static final boolean EIV = Platform.INSTANCE.isModLoaded("eiv");
    public static final boolean FARMERS_DELIGHT = Platform.INSTANCE.isModLoaded("farmersdelight");
    public static final boolean COMPOST = Platform.INSTANCE.isModLoaded("compost");
    public static final boolean ITEMINTERACTIONS = Platform.INSTANCE.isModLoaded("iteminteractions");
}
