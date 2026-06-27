package cc.cassian.bigger_fish.compat.iteminteractions;

import cc.cassian.bigger_fish.BiggerFishMod;
import fuzs.iteminteractions.common.api.v2.world.item.storage.ItemStorage;
import fuzs.iteminteractions.common.api.v2.world.item.storage.ItemStorageType;
import fuzs.puzzleslib.common.api.init.v3.registry.RegistryManager;
import net.minecraft.core.Holder;

public class ItemInteractionsCompat {
    static final RegistryManager REGISTRIES = RegistryManager.from(BiggerFishMod.MOD_ID);
    public static final Holder.Reference<ItemStorageType<?>>
            BAITED_ROD_ITEM_CONTENTS_PROVIDER_TYPE = REGISTRIES.register(
            ItemStorage.REGISTRY_KEY,
            "baited_rod",
            () -> new ItemStorageType<>(BaitedRodProvider.CODEC));
    public static final Holder.Reference<ItemStorageType<?>>
            FISH_BARREL_ITEM_CONTENTS_PROVIDER_TYPE = REGISTRIES.register(
            ItemStorage.REGISTRY_KEY,
            "fish_barrel",
            () -> new ItemStorageType<>(FishBarrelProvider.CODEC));

    public static void touch() {

    }
}