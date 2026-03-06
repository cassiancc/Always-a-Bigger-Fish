package cc.cassian.bigger_fish.compat.rrv.bait;
//? if >1.21.10 {
import cc.cassian.bigger_fish.compat.rrv.fishing.FishingClientRecipe;
import cc.cassian.bigger_fish.compat.rrv.fishing.FishingClientRecipeType;
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

public class BaitUsageClientRecipe implements ReliableClientRecipe {
    private final SlotContent output;
    private final String translationKey;

    //You can design your constructor to suit your needs
    public BaitUsageClientRecipe(TagKey<Item> output) {

        //Define your inputs and outputs here
        this.output = SlotContent.of(output);
        this.translationKey = "tag." + output.location().toLanguageKey() + ".description";

    }

    //You can design your constructor to suit your needs
    public BaitUsageClientRecipe(Item itemSupplier, String id) {
        //Define your inputs and outputs here
        this.output = SlotContent.of(itemSupplier);
        this.translationKey = id;
    }

    @Override
    public ReliableClientRecipeType getViewType() {
        return BaitUsageClientRecipeType.INSTANCE; //Here you need your type's instance you created before
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        //Tell EIV which SlotContent belongs to which of your previously defined slots
        slotFillContext.bindSlot(0, this.output);
    }

    @Override
    public void renderRecipe(RecipeViewScreen screen, RecipePosition recipePosition, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.drawWordWrap(Minecraft.getInstance().font, FormattedText.of(I18n.get(translationKey)), 5, 5, BaitUsageClientRecipeType.INSTANCE.getDisplayWidth()-8, ARGB.opaque(1842204), false);
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(this.output); //Return all of your inputs here
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(); //Return all of your outputs here
    }
}
//?}