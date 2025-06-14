package cc.cassian.bigger_fish.compat.eiv.lava_fishing;

import cc.cassian.bigger_fish.compat.eiv.fishing.FishingViewRecipe;
import de.crafty.eiv.common.api.recipe.IEivRecipeViewType;
import de.crafty.eiv.common.recipe.inventory.SlotContent;
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
    public IEivRecipeViewType getViewType() {
        return LavaFishingViewType.INSTANCE; //Here you need your type's instance you created before
    }
}
