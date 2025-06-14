package cc.cassian.bigger_fish.compat.eiv.lava_fishing;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.compat.eiv.fishing.FishingViewType;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class LavaFishingViewType extends FishingViewType {
    protected static final LavaFishingViewType INSTANCE = new LavaFishingViewType();

    @Override
    public Component getDisplayName() {
        return Component.translatable("category.bigger_fish.lava_fishing");
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return BiggerFishMod.of("textures/gui/lava_fishing.png");
    }

    @Override
    public ResourceLocation getId() {
        return BiggerFishMod.of("lava_fishing");
    }

    @Override
    public ItemStack getIcon() {
        return BiggerFishItems.NETHERITE_ROD.get().getDefaultInstance();
    }

    @Override
    public List<ItemStack> getCraftReferences() {
        return List.of(BiggerFishItems.NETHERITE_ROD.get().getDefaultInstance()); //Return a list of blocks/items that can be used to process your recipes (e.g. for Smelting it would be the furnace)
    }
}
