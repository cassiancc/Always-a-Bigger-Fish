package cc.cassian.bigger_fish.compat.rrv.bait;
//? if >1.21.10 {
/*import cc.cassian.bigger_fish.compat.rrv.fishing.FishingViewRecipe;
import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.resources.Identifier;
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

    public BaitUsageViewRecipe(Supplier<Item> itemSupplier, Identifier input) {
        super(itemSupplier, input);
        this.input = SlotContent.of(itemSupplier.get());
    }

    @Override
    public ReliableClientRecipeType getViewType() {
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
*///?}