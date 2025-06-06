package cc.cassian.bigger_fish.compat.eiv;

import cc.cassian.bigger_fish.registry.BiggerFishItems;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import de.crafty.eiv.common.api.IExtendedItemViewIntegration;
import de.crafty.eiv.common.api.recipe.ItemView;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

import java.util.List;

public class EivIntegration implements IExtendedItemViewIntegration {
    @Override
    public void onIntegrationInitialize() {
        //For the server
        ItemView.addRecipeProvider(list -> {
            //Here you can add all your server recipes
            list.add(new FishingServerRecipe());
        });

        //For the client
        ItemView.registerRecipeWrapper(FishingServerRecipe.TYPE,  modRecipe -> {

            //Here you tell EIV how to process incoming server recipes
            //Requires you to return a list of client-side view-recipes (IEivViewRecipe)

            return List.of(
                    new FishingViewRecipe(BiggerFishTags.COLD_FRESHWATER_FISH),
                    new FishingViewRecipe(BiggerFishTags.COLD_SALTWATER_FISH),
                    new FishingViewRecipe(BiggerFishTags.TEMPERATE_FRESHWATER_FISH),
                    new FishingViewRecipe(BiggerFishTags.TEMPERATE_SALTWATER_FISH),
                    new FishingViewRecipe(BiggerFishTags.HOT_FRESHWATER_FISH),
                    new FishingViewRecipe(BiggerFishTags.HOT_SALTWATER_FISH),
                    new FishingViewRecipe(BiggerFishTags.CAVE_FISH),
                    new FishingViewRecipe(BiggerFishTags.DEEP_DARK_FISH),
                    new FishingViewRecipe(BiggerFishTags.JUNK),
                    new FishingViewRecipe(BiggerFishTags.TREASURE)
            );
        });

    }
}
