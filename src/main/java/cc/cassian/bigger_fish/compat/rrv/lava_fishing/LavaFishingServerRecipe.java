package cc.cassian.bigger_fish.compat.rrv.lava_fishing;
//? if >1.21.10 {
/*import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.compat.rrv.fishing.FishingServerRecipe;
import cc.cassian.rrv.api.recipe.ReliableServerRecipeType;

public class LavaFishingServerRecipe extends FishingServerRecipe {


        //Create a server recipe type (the id does not have to match your client side viewtype id)
        public static final ReliableServerRecipeType<LavaFishingServerRecipe> TYPE = ReliableServerRecipeType.register(
                BiggerFishMod.of("lava_fishing"),
                LavaFishingServerRecipe::new
        );

        @Override
        public ReliableServerRecipeType<? extends LavaFishingServerRecipe> getRecipeType() {
            return TYPE;
        }
}
*///?}