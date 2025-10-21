package cc.cassian.bigger_fish.compat.eiv.lava_fishing;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.compat.eiv.fishing.FishingServerRecipe;
import de.crafty.eiv.common.api.recipe.EivRecipeType;
import de.crafty.eiv.common.api.recipe.IEivServerRecipe;
import net.minecraft.nbt.CompoundTag;

public class LavaFishingServerRecipe extends FishingServerRecipe {


        //Create a server recipe type (the id does not have to match your client side viewtype id)
        public static final EivRecipeType<LavaFishingServerRecipe> TYPE = EivRecipeType.register(
                BiggerFishMod.of("lava_fishing"),
                LavaFishingServerRecipe::new
        );

        @Override
        public EivRecipeType<? extends LavaFishingServerRecipe> getRecipeType() {
            return TYPE;
        }
}
