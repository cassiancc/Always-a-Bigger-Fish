package cc.cassian.bigger_fish.compat.eiv.fishing;

import cc.cassian.bigger_fish.BiggerFishMod;
import de.crafty.eiv.common.api.recipe.EivRecipeType;
import de.crafty.eiv.common.api.recipe.IEivServerRecipe;
import net.minecraft.nbt.CompoundTag;

public class FishingServerRecipe implements IEivServerRecipe {


        //Create a server recipe type (the id does not have to match your client side viewtype id)
        public static final EivRecipeType<FishingServerRecipe> TYPE = EivRecipeType.register(
                BiggerFishMod.of("fishing"),
                () -> new FishingServerRecipe()
        );

        @Override
        public void writeToTag(CompoundTag tag) {

        }

        @Override
        public void loadFromTag(CompoundTag tag) {

        }

        @Override
        public EivRecipeType<? extends FishingServerRecipe> getRecipeType() {
            return TYPE;
        }
}
