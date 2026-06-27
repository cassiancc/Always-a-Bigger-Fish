package cc.cassian.bigger_fish.compat.iteminteractions;

import cc.cassian.bigger_fish.helpers.ModHelpers;
import com.google.common.base.Preconditions;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.iteminteractions.common.api.v2.world.item.DyeBackedColor;
import fuzs.iteminteractions.common.api.v2.world.item.storage.BundleContentsStorage;
import fuzs.iteminteractions.common.api.v2.world.item.storage.ItemStorageType;
import fuzs.iteminteractions.common.api.v2.world.item.storage.StorageOptions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class BaitedRodProvider extends BundleContentsStorage {
    public static final MapCodec<BaitedRodProvider> CODEC = RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(capacityMultiplierCodec(), itemContentsCodec())
                .apply(instance, BaitedRodProvider::new);
    });

    public BaitedRodProvider(int capacityMultiplier, StorageOptions storageOptions) {
        super(capacityMultiplier, storageOptions);
    }

    @Override
    public boolean canAddItem(ItemStack containerStack, ItemStack stackToAdd, Player player) {
        return ModHelpers.isAllowedInBaitedRod(stackToAdd) && super.canAddItem(containerStack, stackToAdd, player);
    }

    @Override
    public ItemStorageType<?> getType() {
        return ItemInteractionsCompat.BAITED_ROD_ITEM_CONTENTS_PROVIDER_TYPE.value();
    }
}