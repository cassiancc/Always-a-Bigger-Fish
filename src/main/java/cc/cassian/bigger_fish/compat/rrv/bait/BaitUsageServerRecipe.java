package cc.cassian.bigger_fish.compat.rrv.bait;
//? if >1.21.10 {
/*import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.compat.rrv.fishing.FishingServerRecipe;
import cc.cassian.rrv.api.recipe.ReliableServerRecipeType;

public class BaitUsageServerRecipe extends FishingServerRecipe {

        //Create a server recipe type (the id does not have to match your client side viewtype id)
        public static final ReliableServerRecipeType<BaitUsageServerRecipe> TYPE = ReliableServerRecipeType.register(
                BiggerFishMod.of("bait_usage"),
                BaitUsageServerRecipe::new
        );

        @Override
        public ReliableServerRecipeType<? extends BaitUsageServerRecipe> getRecipeType() {
            return TYPE;
        }
}
*///?}