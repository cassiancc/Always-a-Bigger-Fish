package cc.cassian.bigger_fish.compat.eiv.bait;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.compat.eiv.bait_info.BaitInfoServerRecipe;
import cc.cassian.bigger_fish.compat.eiv.bait_info.BaitInfoViewType;
import cc.cassian.bigger_fish.compat.eiv.fishing.FishingServerRecipe;
import de.crafty.eiv.common.api.recipe.EivRecipeType;
import de.crafty.eiv.common.api.recipe.IEivServerRecipe;
import net.minecraft.nbt.CompoundTag;

public class BaitUsageServerRecipe extends FishingServerRecipe {

        //Create a server recipe type (the id does not have to match your client side viewtype id)
        public static final EivRecipeType<BaitUsageServerRecipe> TYPE = EivRecipeType.register(
                BiggerFishMod.of("bait_usage"),
                BaitUsageServerRecipe::new
        );

        @Override
        public EivRecipeType<? extends BaitUsageServerRecipe> getRecipeType() {
            return TYPE;
        }
}
