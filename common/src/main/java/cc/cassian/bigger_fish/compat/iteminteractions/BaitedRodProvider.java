package cc.cassian.bigger_fish.compat.iteminteractions;

import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.iteminteractions.api.v1.DyeBackedColor;
import fuzs.iteminteractions.api.v1.provider.impl.BundleProvider;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class BaitedRodProvider extends BundleProvider {
    public static final MapCodec<BundleProvider> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(capacityMultiplierCodec(), backgroundColorCodec(), disallowedItemsCodec())
                    .apply(instance,
                            (Integer capacityMultiplier, Optional<DyeBackedColor> dyeColor, HolderSet<Item> disallowedItems) ->
                                    new BaitedRodProvider(capacityMultiplier, dyeColor.orElse(null)).disallowedItems(
                                            disallowedItems)
                    ));

    public BaitedRodProvider() {
        super(DyeBackedColor.fromDyeColor(DyeColor.ORANGE));
    }

    public BaitedRodProvider(Integer capacityMultiplier, DyeBackedColor dyeBackedColor) {
        super(capacityMultiplier, DyeBackedColor.fromDyeColor(DyeColor.ORANGE));
    }

    @Override
    public boolean isItemAllowedInContainer(ItemStack stackToAdd) {
        return ModHelpers.isAllowedInBaitedRod(stackToAdd);
    }

    @Override
    public boolean canAddItem(ItemStack containerStack, ItemStack stackToAdd, Player player) {
        return super.canAddItem(containerStack, stackToAdd, player);
    }

    @Override
    public Type getType() {
        return ItemInteractionsCompat.BAITED_ROD_ITEM_CONTENTS_PROVIDER_TYPE.value();
    }
}