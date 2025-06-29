package cc.cassian.bigger_fish.compat.eiv.bait_info;

import cc.cassian.bigger_fish.compat.eiv.fishing.FishingViewRecipe;
import de.crafty.eiv.common.api.recipe.IEivRecipeViewType;
import de.crafty.eiv.common.recipe.inventory.SlotContent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.function.Supplier;

public class BaitInfoViewRecipe extends FishingViewRecipe {

    public BaitInfoViewRecipe(TagKey<Item> output) {
        super(output);
    }

    public BaitInfoViewRecipe(Supplier<Item> item, ResourceLocation output) {
        super(item, output);
    }

    @Override
    public IEivRecipeViewType getViewType() {
        return BaitInfoViewType.INSTANCE; //Here you need your type's instance you created before
    }
}
