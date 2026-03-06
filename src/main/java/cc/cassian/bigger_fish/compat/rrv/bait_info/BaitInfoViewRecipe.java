package cc.cassian.bigger_fish.compat.rrv.bait_info;
//? if >1.21.4 {
/*import cc.cassian.bigger_fish.compat.rrv.fishing.FishingViewRecipe;
import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class BaitInfoViewRecipe extends FishingViewRecipe {

    public BaitInfoViewRecipe(TagKey<Item> output) {
        super(output);
    }

    public BaitInfoViewRecipe(Supplier<Item> item, Identifier output) {
        super(item, output);
    }

    @Override
    public ReliableClientRecipeType getViewType() {
        return BaitInfoViewType.INSTANCE; //Here you need your type's instance you created before
    }
}
*///?}