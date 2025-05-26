package cc.cassian.bigger_fish.neoforge;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.Tags;

public class PlatformMethodsImpl {
    public static boolean isFishingRod(ItemStack stack) {
        return stack.is(Tags.Items.TOOLS_FISHING_ROD);
    }
}
