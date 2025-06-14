package cc.cassian.bigger_fish.compat.eiv.lava_fishing;

import cc.cassian.bigger_fish.BiggerFishMod;
import de.crafty.eiv.common.api.recipe.EivRecipeType;
import de.crafty.eiv.common.api.recipe.IEivServerRecipe;
import net.minecraft.nbt.CompoundTag;

public class LavaFishingServerRecipe implements IEivServerRecipe {


        //Create a server recipe type (the id does not have to match your client side viewtype id)
        public static final EivRecipeType<LavaFishingServerRecipe> TYPE = EivRecipeType.register(
                BiggerFishMod.of("lava_fishing"),
                () -> new LavaFishingServerRecipe()
        );

        @Override
        public void writeToTag(CompoundTag tag) {

        }

        @Override
        public void loadFromTag(CompoundTag tag) {

        }

        @Override
        public EivRecipeType<? extends LavaFishingServerRecipe> getRecipeType() {
            return TYPE;
        }
}
