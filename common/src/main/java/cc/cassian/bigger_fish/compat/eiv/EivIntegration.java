package cc.cassian.bigger_fish.compat.eiv;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.compat.eiv.bait.BaitUsageServerRecipe;
import cc.cassian.bigger_fish.compat.eiv.bait.BaitUsageViewRecipe;
import cc.cassian.bigger_fish.compat.eiv.bait_info.BaitInfoServerRecipe;
import cc.cassian.bigger_fish.compat.eiv.bait_info.BaitInfoViewRecipe;
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
            list.add(new BaitInfoServerRecipe());
            list.add(new BaitUsageServerRecipe());
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
                modRecipe -> List.of(new LavaFishingViewRecipe(BiggerFishTags.LAVA_FISH)
        ));

        // Bait
        ItemView.registerRecipeWrapper(BaitInfoServerRecipe.TYPE,
                modRecipe -> List.of(
                        new BaitInfoViewRecipe(BiggerFishItems.WORM, BiggerFishMod.of("worm"))
        ));

        ItemView.registerRecipeWrapper(BaitUsageServerRecipe.TYPE,
                modRecipe -> {

                //Here you tell EIV how to process incoming server recipes
                //Requires you to return a list of client-side view-recipes (IEivViewRecipe)

                ArrayList<FishingViewRecipe> recipes = new ArrayList<>();
                for (TagKey<Item> itemTagKey : BiggerFishTags.BAIT_TAGS_FOR_DISPLAY) {
                    recipes.add(new BaitUsageViewRecipe(itemTagKey));
                }
                return recipes;
            });
    }

    public static void hideStacks() {
        for (Supplier<Item> item : BiggerFishItems.HIDDEN_FOOD) {
            ItemView.excludeItem(item.get());
        }
        ItemView.excludeItem(BiggerFishItems.COPPER_HOOK.get());
    }
}
