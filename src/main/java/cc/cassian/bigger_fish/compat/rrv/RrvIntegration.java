package cc.cassian.bigger_fish.compat.rrv;
//? if >1.21.10 {
import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.compat.rrv.bait.BaitUsageServerRecipe;
import cc.cassian.bigger_fish.compat.rrv.bait.BaitUsageClientRecipe;
import cc.cassian.bigger_fish.compat.rrv.fishing.FishingServerRecipe;
import cc.cassian.bigger_fish.compat.rrv.fishing.FishingClientRecipe;
import cc.cassian.bigger_fish.compat.rrv.lava_fishing.LavaFishingServerRecipe;
import cc.cassian.bigger_fish.compat.rrv.lava_fishing.LavaFishingClientRecipe;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import cc.cassian.rrv.api.ReliableRecipeViewerPlugin;
import cc.cassian.rrv.api.recipe.ItemView;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class RrvIntegration implements ReliableRecipeViewerPlugin {
    @Override
    public void onIntegrationInitialize() {
        //For the server
        ItemView.addServerRecipeProvider(list -> {
            //Here you can add all your server recipes
            list.add(new FishingServerRecipe());
            list.add(new LavaFishingServerRecipe());
            list.add(new BaitUsageServerRecipe());
        });

        //For the client

        // Fishing
        ItemView.addClientRecipeWrapper(FishingServerRecipe.TYPE, modRecipe -> {
			ArrayList<FishingClientRecipe> recipes = new ArrayList<>();
			for (TagKey<Item> itemTagKey : BiggerFishTags.FISHING_TAGS_FOR_DISPLAY) {
				recipes.add(new FishingClientRecipe(itemTagKey));
			}
			return recipes;
		});

        // Lava Fishing
        ItemView.addClientRecipeWrapper(LavaFishingServerRecipe.TYPE,
				modRecipe -> List.of(new LavaFishingClientRecipe(BiggerFishTags.LAVA_FISH)
				));

        ItemView.addClientRecipeWrapper(BaitUsageServerRecipe.TYPE,
				modRecipe -> {
					ArrayList<BaitUsageClientRecipe> recipes = new ArrayList<>();
					for (TagKey<Item> itemTagKey : BiggerFishTags.BAIT_TAGS_FOR_DISPLAY) {
						recipes.add(new BaitUsageClientRecipe(itemTagKey));
					}
					recipes.add(new BaitUsageClientRecipe(BiggerFishItems.NETHERITE_HOOK, "tag.bigger_fish.attracts_lava_fish.description"));
					return recipes;
				});
        hideStacks();
    }

    public static void hideStacks() {
        for (Item item : BiggerFishItems.HIDDEN_FOOD) {
            ItemView.excludeItem(item);
        }
        ItemView.excludeItem(BiggerFishItems.COPPER_HOOK);
    }
}
//?}