package cc.cassian.bigger_fish;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.ItemStack;

public class PlatformMethods {

    @ExpectPlatform
    public static boolean isFishingRod(ItemStack stack) {
        throw new AssertionError();
    }


}
