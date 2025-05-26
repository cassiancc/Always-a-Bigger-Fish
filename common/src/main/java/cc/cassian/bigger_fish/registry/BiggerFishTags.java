package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.BiggerFishMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class BiggerFishTags {
    public static TagKey<Item> BAIT = itemTagKey("bait");

    public static TagKey<Item> itemTagKey(String id) {
        return TagKey.create(Registries.ITEM, BiggerFishMod.of(id));
    }
}
