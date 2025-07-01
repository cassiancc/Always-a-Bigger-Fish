package cc.cassian.bigger_fish.compat.emi;

import dev.emi.emi.api.recipe.EmiInfoRecipe;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FishingEmiRecipe extends EmiInfoRecipe {
    public FishingEmiRecipe(List<EmiIngredient> stacks, List<Component> text, @Nullable ResourceLocation id) {
        super(stacks, text, id);
    }
}
