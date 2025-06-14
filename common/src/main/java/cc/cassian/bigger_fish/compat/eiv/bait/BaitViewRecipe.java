package cc.cassian.bigger_fish.compat.eiv.bait;

import cc.cassian.bigger_fish.compat.eiv.fishing.FishingViewRecipe;
import de.crafty.eiv.common.api.recipe.IEivRecipeViewType;
import de.crafty.eiv.common.recipe.inventory.SlotContent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class BaitViewRecipe extends FishingViewRecipe {
    private final SlotContent output;

    public BaitViewRecipe(TagKey<Item> output) {
        super(output);
        this.output = SlotContent.of(output);
    }

    @Override
    public IEivRecipeViewType getViewType() {
        return BaitFishingViewType.INSTANCE; //Here you need your type's instance you created before
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(this.output); //Return all of your outputs here
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(); //Return all of your outputs here
    }
}
