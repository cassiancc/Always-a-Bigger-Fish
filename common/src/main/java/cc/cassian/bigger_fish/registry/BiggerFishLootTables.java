package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.BiggerFishMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class BiggerFishLootTables {
    public static ResourceKey<LootTable> FISHING = ResourceKey.create(Registries.LOOT_TABLE, BiggerFishMod.of("gameplay/fishing"));
    public static ResourceKey<LootTable> LAVA_FISHING = ResourceKey.create(Registries.LOOT_TABLE, BiggerFishMod.of("gameplay/lava_fishing"));
}
