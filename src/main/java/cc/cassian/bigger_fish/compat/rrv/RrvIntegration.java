package cc.cassian.bigger_fish.compat.rrv;
//? if >1.21.10 {
import cc.cassian.bigger_fish.compat.rrv.bait.BaitUsageClientRecipe;
import cc.cassian.bigger_fish.compat.rrv.fishing.FishingClientRecipe;
import cc.cassian.bigger_fish.compat.rrv.lava_fishing.LavaFishingClientRecipe;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import cc.cassian.rrv.api.ReliableRecipeViewerClientPlugin;
import cc.cassian.rrv.api.ReliableRecipeViewerPlugin;
import cc.cassian.rrv.api.recipe.ItemView;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class RrvIntegration implements ReliableRecipeViewerClientPlugin {
    @Override
    public void onIntegrationInitialize() {

        //For the client
        // Fishing
        ItemView.addClientRecipeProvider(recipes -> {
			for (TagKey<Item> itemTagKey : BiggerFishTags.FISHING_TAGS_FOR_DISPLAY) {
				recipes.add(new FishingClientRecipe(itemTagKey));
			}
			recipes.add(new LavaFishingClientRecipe(BiggerFishTags.LAVA_FISH));
			for (TagKey<Item> itemTagKey : BiggerFishTags.BAIT_TAGS_FOR_DISPLAY) {
				recipes.add(new BaitUsageClientRecipe(itemTagKey));
			}
			recipes.add(new BaitUsageClientRecipe(BiggerFishItems.NETHERITE_HOOK, "tag.bigger_fish.attracts_lava_fish.description"));
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