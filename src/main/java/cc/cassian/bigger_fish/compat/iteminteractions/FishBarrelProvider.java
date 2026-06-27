package cc.cassian.bigger_fish.compat.iteminteractions;

import cc.cassian.bigger_fish.registry.BiggerFishTags;
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

public class FishBarrelProvider extends BundleContentsStorage {
    public static final MapCodec<BaitedRodProvider> CODEC = RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(capacityMultiplierCodec(), itemContentsCodec())
                .apply(instance, BaitedRodProvider::new);
    });

    public FishBarrelProvider(int capacityMultiplier, StorageOptions storageOptions) {
        super(capacityMultiplier, storageOptions);
    }

    @Override
    public boolean canAddItem(ItemStack containerStack, ItemStack stackToAdd, Player player) {
        return stackToAdd.is(BiggerFishTags.ALLOWED_IN_FISH_BARREL) && super.canAddItem(containerStack, stackToAdd, player);
    }

    @Override
    public ItemStorageType<?> getType() {
        return ItemInteractionsCompat.FISH_BARREL_ITEM_CONTENTS_PROVIDER_TYPE.value();
    }
}