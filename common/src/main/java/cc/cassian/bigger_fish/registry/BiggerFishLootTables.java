package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.BiggerFishMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class BiggerFishLootTables {
    public static ResourceKey<LootTable> FISHING = createLootTable("fishing");
    public static ResourceKey<LootTable> LAVA_FISHING = createLootTable("lava_fishing");
    public static ResourceKey<LootTable> CAVE_FISHING = createLootTable("cave_fishing");

    private static ResourceKey<LootTable> createLootTable(String id) {
        return ResourceKey.create(Registries.LOOT_TABLE, BiggerFishMod.of("gameplay/"+id));
    }

}
