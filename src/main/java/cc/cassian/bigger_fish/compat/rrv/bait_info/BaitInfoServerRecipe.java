package cc.cassian.bigger_fish.compat.rrv.bait_info;
//? if >1.21.4 {
/*import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.compat.rrv.fishing.FishingServerRecipe;
import cc.cassian.rrv.api.recipe.ReliableServerRecipeType;

public class BaitInfoServerRecipe extends FishingServerRecipe {

        //Create a server recipe type (the id does not have to match your client side viewtype id)
        public static final ReliableServerRecipeType<BaitInfoServerRecipe> TYPE = ReliableServerRecipeType.register(
                BiggerFishMod.of("bait_info"),
                BaitInfoServerRecipe::new
        );

        @Override
        public ReliableServerRecipeType<? extends BaitInfoServerRecipe> getRecipeType() {
            return TYPE;
        }
}
*///?}