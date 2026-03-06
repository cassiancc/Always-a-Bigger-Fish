package cc.cassian.bigger_fish.compat.rrv.fishing;
//? if >1.21.10 {
/*import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class FishingViewType implements ReliableClientRecipeType {

    protected static final FishingViewType INSTANCE = new FishingViewType();

    @Override
    public Component getDisplayName() {
        return Component.translatable("category.bigger_fish.fishing");
    }

    @Override
    public int getDisplayWidth() {
        return 140;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }

    @Override
    public Identifier getGuiTexture() {
        return BiggerFishMod.of("textures/gui/fishing.png");
    }

    @Override
    public int getSlotCount() {
        return 1;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        //Tell EIV where your slots are located by calling slotDefinition.addItemSlot();
        //NOTE: Slot position is relative to your gui texture

        slotDefinition.addItemSlot(0, (getDisplayWidth()/2)-5, 65);

    }

    @Override
    public Identifier getId() {
        return BiggerFishMod.of("fishing");
    }

    @Override
    public ItemStack getIcon() {
        return BiggerFishItems.COPPER_ROD.get().getDefaultInstance();
    }

    @Override
    public List<ItemStack> getCraftReferences() {
        return List.of(Items.FISHING_ROD.getDefaultInstance(), BiggerFishItems.COPPER_ROD.get().getDefaultInstance()); //Return a list of blocks/items that can be used to process your recipes (e.g. for Smelting it would be the furnace)
    }
}
*///?}