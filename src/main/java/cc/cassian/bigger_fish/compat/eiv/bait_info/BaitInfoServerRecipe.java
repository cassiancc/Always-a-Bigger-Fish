package cc.cassian.bigger_fish.compat.eiv.bait_info;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.compat.eiv.fishing.FishingServerRecipe;
import de.crafty.eiv.common.api.recipe.EivRecipeType;
import de.crafty.eiv.common.api.recipe.IEivServerRecipe;
import net.minecraft.nbt.CompoundTag;

public class BaitInfoServerRecipe extends FishingServerRecipe {

        //Create a server recipe type (the id does not have to match your client side viewtype id)
        public static final EivRecipeType<BaitInfoServerRecipe> TYPE = EivRecipeType.register(
                BiggerFishMod.of("bait_info"),
                BaitInfoServerRecipe::new
        );

        @Override
        public EivRecipeType<? extends BaitInfoServerRecipe> getRecipeType() {
            return TYPE;
        }
}
