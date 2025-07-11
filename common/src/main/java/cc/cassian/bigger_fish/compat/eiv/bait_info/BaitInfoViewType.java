package cc.cassian.bigger_fish.compat.eiv.bait_info;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.compat.eiv.fishing.FishingViewType;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class BaitInfoViewType extends FishingViewType {
    protected static final BaitInfoViewType INSTANCE = new BaitInfoViewType();

    @Override
    public Component getDisplayName() {
        return Component.translatable("category.bigger_fish.bait");
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return BiggerFishMod.of("textures/gui/bait.png");
    }

    @Override
    public ResourceLocation getId() {
        return BiggerFishMod.of("bait_info");
    }

    @Override
    public ItemStack getIcon() {
        return BiggerFishItems.WORM.get().getDefaultInstance();
    }

    @Override
    public List<ItemStack> getCraftReferences() {
        return List.of(); //Return a list of blocks/items that can be used to process your recipes (e.g. for Smelting it would be the furnace)
    }
}
