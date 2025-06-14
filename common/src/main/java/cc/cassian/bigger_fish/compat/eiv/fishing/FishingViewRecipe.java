package cc.cassian.bigger_fish.compat.eiv.fishing;

import de.crafty.eiv.common.api.recipe.IEivRecipeViewType;
import de.crafty.eiv.common.api.recipe.IEivViewRecipe;
import de.crafty.eiv.common.recipe.inventory.RecipeViewMenu;
import de.crafty.eiv.common.recipe.inventory.RecipeViewScreen;
import de.crafty.eiv.common.recipe.inventory.SlotContent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class FishingViewRecipe implements IEivViewRecipe {
    private final SlotContent output;
    private final String translationKey;


    //You can design your constructor to suit your needs
    public FishingViewRecipe(TagKey<Item> output) {

        //Define your inputs and outputs here
        this.output = SlotContent.of(output);
        this.translationKey = "tag."+ output.location().toLanguageKey() + ".eiv";

    }

    @Override
    public IEivRecipeViewType getViewType() {
        return FishingViewType.INSTANCE; //Here you need your type's instance you created before
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {

        //Tell EIV which SlotContent belongs to which of your previously defined slots
        slotFillContext.bindSlot(0, this.output);

    }

    @Override
    public void renderRecipe(RecipeViewScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.drawWordWrap(Minecraft.getInstance().font, FormattedText.of(I18n.get(translationKey)), 5, 5, 112, 1842204, false);
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
