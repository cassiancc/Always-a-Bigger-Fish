package cc.cassian.bigger_fish.compat.eiv;

import cc.cassian.bigger_fish.registry.BiggerFishItems;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import de.crafty.eiv.common.api.IExtendedItemViewIntegration;
import de.crafty.eiv.common.api.recipe.ItemView;
import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class EivIntegration implements IExtendedItemViewIntegration {
    @Override
    public void onIntegrationInitialize() {
        //For the server
        ItemView.addRecipeProvider(list -> {
            //Here you can add all your server recipes
            list.add(new FishingServerRecipe());
            list.add(new LavaFishingServerRecipe());
        });

        //For the client
        ItemView.registerRecipeWrapper(FishingServerRecipe.TYPE,  modRecipe -> {

            //Here you tell EIV how to process incoming server recipes
            //Requires you to return a list of client-side view-recipes (IEivViewRecipe)

            ArrayList<FishingViewRecipe> recipes = new ArrayList<>();
            for (TagKey<Item> itemTagKey : BiggerFishTags.FISHING_TAGS_FOR_DISPLAY) {
                recipes.add(new FishingViewRecipe(itemTagKey));
            }
            return recipes;
        });
        ItemView.registerRecipeWrapper(LavaFishingServerRecipe.TYPE,
                modRecipe -> List.of(new LavaFishingViewRecipe(BiggerFishTags.LAVA_FISH)));
    }

    public static void hideStacks() {
        for (DeferredSupplier<Item> item : BiggerFishItems.HIDDEN_FOOD) {
            ItemView.excludeItem(item.get());
        }
    }
}
