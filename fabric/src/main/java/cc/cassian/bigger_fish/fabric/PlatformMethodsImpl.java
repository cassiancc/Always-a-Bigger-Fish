package cc.cassian.bigger_fish.fabric;

import cc.cassian.bigger_fish.registry.BiggerFishItems;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.world.item.ItemStack;

public class PlatformMethodsImpl {
    public static boolean isFishingRod(ItemStack stack) {
        return stack.is(ConventionalItemTags.FISHING_ROD_TOOLS);
    }
}
