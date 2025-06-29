package cc.cassian.bigger_fish.compat.eiv.bait;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.compat.eiv.bait_info.BaitInfoViewRecipe;
import cc.cassian.bigger_fish.compat.eiv.bait_info.BaitInfoViewType;
import cc.cassian.bigger_fish.compat.eiv.fishing.FishingViewType;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class BaitUsageViewType extends BaitInfoViewType {
    protected static final BaitUsageViewType INSTANCE = new BaitUsageViewType();

    @Override
    public ResourceLocation getId() {
        return BiggerFishMod.of("bait_usage");
    }

}
