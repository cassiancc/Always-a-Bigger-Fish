package cc.cassian.bigger_fish.compat.rrv.fishing;
//? if >1.21.10 {
import cc.cassian.rrv.api.recipe.ReliableClientRecipe;
import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewScreen;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ARGB;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.function.Supplier;

public class FishingViewRecipe implements ReliableClientRecipe {
    private final SlotContent output;
    private final String translationKey;


    //You can design your constructor to suit your needs
    public FishingViewRecipe(TagKey<Item> output) {

        //Define your inputs and outputs here
        this.output = SlotContent.of(output);
        this.translationKey = "tag." + output.location().toLanguageKey() + ".description";

    }

    //You can design your constructor to suit your needs
    public FishingViewRecipe(Supplier<Item> itemSupplier, Identifier id) {

        //Define your inputs and outputs here
        this.output = SlotContent.of(itemSupplier.get());
        this.translationKey = "item." + id.toLanguageKey() + ".description";

    }

    @Override
    public ReliableClientRecipeType getViewType() {
        return FishingViewType.INSTANCE; //Here you need your type's instance you created before
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {

        //Tell EIV which SlotContent belongs to which of your previously defined slots
        slotFillContext.bindSlot(0, this.output);

    }

    @Override
    public void renderRecipe(RecipeViewScreen screen, RecipePosition recipePosition, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.drawWordWrap(Minecraft.getInstance().font, FormattedText.of(I18n.get(translationKey)), 5, 5, FishingViewType.INSTANCE.getDisplayWidth()-8, ARGB.opaque(1842204), false);
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(SlotContent.of(Blocks.WATER.asItem())); //Return all of your inputs here
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(this.output); //Return all of your outputs here
    }
}
//?}