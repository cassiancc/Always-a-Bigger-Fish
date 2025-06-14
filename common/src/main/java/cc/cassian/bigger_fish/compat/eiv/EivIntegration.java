package cc.cassian.bigger_fish.compat.eiv;

import cc.cassian.bigger_fish.compat.eiv.bait.BaitServerRecipe;
import cc.cassian.bigger_fish.compat.eiv.bait.BaitViewRecipe;
import cc.cassian.bigger_fish.compat.eiv.fishing.FishingServerRecipe;
import cc.cassian.bigger_fish.compat.eiv.fishing.FishingViewRecipe;
import cc.cassian.bigger_fish.compat.eiv.lava_fishing.LavaFishingServerRecipe;
import cc.cassian.bigger_fish.compat.eiv.lava_fishing.LavaFishingViewRecipe;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import de.crafty.eiv.common.api.IExtendedItemViewIntegration;
import de.crafty.eiv.common.api.recipe.ItemView;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class EivIntegration implements IExtendedItemViewIntegration {
    @Override
    public void onIntegrationInitialize() {
        //For the server
        ItemView.addRecipeProvider(list -> {
            //Here you can add all your server recipes
            list.add(new FishingServerRecipe());
            list.add(new LavaFishingServerRecipe());
            list.add(new BaitServerRecipe());
        });

        //For the client
        // Fishing
        ItemView.registerRecipeWrapper(FishingServerRecipe.TYPE,  modRecipe -> {

            //Here you tell EIV how to process incoming server recipes
            //Requires you to return a list of client-side view-recipes (IEivViewRecipe)

            ArrayList<FishingViewRecipe> recipes = new ArrayList<>();
            for (TagKey<Item> itemTagKey : BiggerFishTags.FISHING_TAGS_FOR_DISPLAY) {
                recipes.add(new FishingViewRecipe(itemTagKey));
            }
            return recipes;
        });

        // Lava Fishing
        ItemView.registerRecipeWrapper(LavaFishingServerRecipe.TYPE,
                modRecipe -> List.of(new LavaFishingViewRecipe(BiggerFishTags.LAVA_FISH)));

        // Bait
        ItemView.registerRecipeWrapper(BaitServerRecipe.TYPE,
                modRecipe -> List.of(
                        new BaitViewRecipe(BiggerFishTags.TIER_ONE_BAIT),
                        new BaitViewRecipe(BiggerFishTags.TIER_TWO_BAIT),
                        new BaitViewRecipe(BiggerFishTags.TIER_THREE_BAIT)
                ));
    }

    public static void hideStacks() {
        for (Supplier<Item> item : BiggerFishItems.HIDDEN_FOOD) {
            ItemView.excludeItem(item.get());
        }
    }
}
