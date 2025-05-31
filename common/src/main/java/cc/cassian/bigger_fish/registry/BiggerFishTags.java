package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.BiggerFishMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class BiggerFishTags {
    public static final TagKey<Item> BAIT = itemTagKey("bait");
    public static final TagKey<Item> REQUIRES_MINIGAME_TO_CATCH = itemTagKey("requires_minigame_to_catch");
    public static final TagKey<Item> CAN_FISH_IN_LAVA = itemTagKey("can_fish_in_lava");;
    public static final TagKey<Item> COLD_FRESHWATER_FISH = itemTagKey("cold_freshwater_fish");
    public static final TagKey<Item> COLD_SALTWATER_FISH = itemTagKey("cold_saltwater_fish");
    public static final TagKey<Item> DEEP_DARK_FISH = itemTagKey("deep_dark_fish");
    public static final TagKey<Item> HOT_FRESHWATER_FISH = itemTagKey("hot_freshwater_fish");
    public static final TagKey<Item> HOT_SALTWATER_FISH = itemTagKey("hot_saltwater_fish");
    public static final TagKey<Item> SHROOMY_FISH = itemTagKey("shroomy_fish");
    public static final TagKey<Item> TEMPERATE_FRESHWATER_FISH = itemTagKey("temperate_freshwater_fish");
    public static final TagKey<Item> TEMPERATE_SALTWATER_FISH = itemTagKey("temperate_saltwater_fish");
    public static final TagKey<Item> FISH = itemTagKey("fish");

    public static TagKey<Item> itemTagKey(String id) {
        return TagKey.create(Registries.ITEM, BiggerFishMod.of(id));
    }
}
