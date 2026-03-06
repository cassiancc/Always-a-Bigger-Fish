package cc.cassian.bigger_fish.compat.iteminteractions;

import cc.cassian.bigger_fish.BiggerFishMod;
import fuzs.iteminteractions.api.v1.provider.ItemContentsProvider;
import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import net.minecraft.core.Holder;

public class ItemInteractionsCompat {
    static final RegistryManager REGISTRIES = RegistryManager.from(BiggerFishMod.MOD_ID);
    public static final Holder.Reference<ItemContentsProvider.Type<?>>
            BAITED_ROD_ITEM_CONTENTS_PROVIDER_TYPE = REGISTRIES.register(
            ItemContentsProvider.REGISTRY_KEY,
            "baited_rod",
            () -> new ItemContentsProvider.Type<>(BaitedRodProvider.CODEC));
    public static final Holder.Reference<ItemContentsProvider.Type<?>>
            FISH_BARREL_ITEM_CONTENTS_PROVIDER_TYPE = REGISTRIES.register(
            ItemContentsProvider.REGISTRY_KEY,
            "fish_barrel",
            () -> new ItemContentsProvider.Type<>(FishBarrelProvider.CODEC));

    public static void touch() {

    }
}