package cc.cassian.bigger_fish.compat.rrv.fishing;
//? if >1.21.10 {
/*import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.rrv.api.recipe.ReliableServerRecipe;
import cc.cassian.rrv.api.recipe.ReliableServerRecipeType;
import net.minecraft.nbt.CompoundTag;

public class FishingServerRecipe implements ReliableServerRecipe {


        //Create a server recipe type (the id does not have to match your client side viewtype id)
        public static final ReliableServerRecipeType<FishingServerRecipe> TYPE = ReliableServerRecipeType.register(
                BiggerFishMod.of("fishing"),
                FishingServerRecipe::new
        );

        @Override
        public void writeToTag(CompoundTag tag) {

        }

        @Override
        public void loadFromTag(CompoundTag tag) {

        }

        @Override
        public ReliableServerRecipeType<? extends FishingServerRecipe> getRecipeType() {
            return TYPE;
        }
}
*///?}