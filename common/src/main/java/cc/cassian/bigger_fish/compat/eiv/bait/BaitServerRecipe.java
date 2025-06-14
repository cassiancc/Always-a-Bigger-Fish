package cc.cassian.bigger_fish.compat.eiv.bait;

import cc.cassian.bigger_fish.BiggerFishMod;
import de.crafty.eiv.common.api.recipe.EivRecipeType;
import de.crafty.eiv.common.api.recipe.IEivServerRecipe;
import net.minecraft.nbt.CompoundTag;

public class BaitServerRecipe implements IEivServerRecipe {


        //Create a server recipe type (the id does not have to match your client side viewtype id)
        public static final EivRecipeType<BaitServerRecipe> TYPE = EivRecipeType.register(
                BiggerFishMod.of("bait"),
                () -> new BaitServerRecipe()
        );

        @Override
        public void writeToTag(CompoundTag tag) {

        }

        @Override
        public void loadFromTag(CompoundTag tag) {

        }

        @Override
        public EivRecipeType<? extends BaitServerRecipe> getRecipeType() {
            return TYPE;
        }
}
