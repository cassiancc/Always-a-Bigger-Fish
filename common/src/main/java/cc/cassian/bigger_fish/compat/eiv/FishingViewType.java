package cc.cassian.bigger_fish.compat.eiv;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import de.crafty.eiv.common.api.recipe.IEivRecipeViewType;
import de.crafty.eiv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class FishingViewType implements IEivRecipeViewType {
    //Create an instance of your viewtype here
    //Relevant for next steps
    protected static final FishingViewType INSTANCE = new FishingViewType();


    @Override
    public Component getDisplayName() {
        return Component.translatable("category.bigger_fish.fishing"); //This is the name of your viewtype displayed later in the recipe-view
    }

    @Override
    public int getDisplayWidth() {
        return 100; //The width of your type's gui texture
    }

    @Override
    public int getDisplayHeight() {
        return 90; //The height of your type's gui texture
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return BiggerFishMod.of("textures/gui/fishing.png"); //Your type's gui texture

    }

    @Override
    public int getSlotCount() {
        return 1; //The amount of slots one of your type's recipes requires (all slots including results)
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        //Tell EIV where your slots are located by calling slotDefinition.addItemSlot();
        //NOTE: Slot position is relative to your gui texture

        slotDefinition.addItemSlot(0, 40, 65);

    }

    @Override
    public ResourceLocation getId() {
        return BiggerFishMod.of("fishing"); //A unique id for your viewtype
    }

    @Override
    public ItemStack getIcon() {
        return BiggerFishItems.COPPER_ROD.get().getDefaultInstance(); //The icon displayed in the recipe-view
    }

    @Override
    public List<ItemStack> getCraftReferences() {
        return List.of(Items.FISHING_ROD.getDefaultInstance(), BiggerFishItems.COPPER_ROD.get().getDefaultInstance(), BiggerFishItems.NETHERITE_ROD.get().getDefaultInstance()); //Return a list of blocks/items that can be used to process your recipes (e.g. for Smelting it would be the furnace)
    }
}
