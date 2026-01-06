package cc.cassian.bigger_fish.compat.rrv.lava_fishing;
//? if >1.21.4 {
import cc.cassian.bigger_fish.compat.rrv.fishing.FishingViewRecipe;
import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class LavaFishingViewRecipe extends FishingViewRecipe {
    public LavaFishingViewRecipe(TagKey<Item> output) {
        super(output);
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(SlotContent.of(Blocks.LAVA.asItem())); //Return all of your inputs here
    }

    @Override
    public ReliableClientRecipeType getViewType() {
        return LavaFishingViewType.INSTANCE; //Here you need your type's instance you created before
    }
}
//?}