package cc.cassian.bigger_fish.compat.eiv.bait;

import cc.cassian.bigger_fish.compat.eiv.fishing.FishingViewRecipe;
import de.crafty.eiv.common.api.recipe.IEivRecipeViewType;
import de.crafty.eiv.common.recipe.inventory.SlotContent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.function.Supplier;

public class BaitUsageViewRecipe extends FishingViewRecipe {
    private final SlotContent input;

    public BaitUsageViewRecipe(TagKey<Item> input) {
        super(input);
        this.input = SlotContent.of(input);
    }

    public BaitUsageViewRecipe(Supplier<Item> itemSupplier, ResourceLocation input) {
        super(itemSupplier, input);
        this.input = SlotContent.of(itemSupplier.get());
    }

    @Override
    public IEivRecipeViewType getViewType() {
        return BaitUsageViewType.INSTANCE; //Here you need your type's instance you created before
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(this.input); //Return all of your outputs here
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(); //Return all of your outputs here
    }
}
