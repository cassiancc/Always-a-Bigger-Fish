package cc.cassian.bigger_fish.compat.rrv.bait;
//? if >1.21.4 {
import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.compat.rrv.fishing.FishingClientRecipeType;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class BaitUsageClientRecipeType extends FishingClientRecipeType {
    protected static final BaitUsageClientRecipeType INSTANCE = new BaitUsageClientRecipeType();

    @Override
    public Identifier getId() {
        return BiggerFishMod.of("bait_usage");
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("category.bigger_fish.bait");
    }

    @Override
    public Identifier getGuiTexture() {
        return BiggerFishMod.of("textures/gui/bait.png");
    }

    @Override
    public ItemStack getIcon() {
        return BiggerFishItems.WORM.getDefaultInstance();
    }

    @Override
    public List<ItemStack> getCraftReferences() {
        return List.of(); //Return a list of blocks/items that can be used to process your recipes (e.g. for Smelting it would be the furnace)
    }

}
//?}