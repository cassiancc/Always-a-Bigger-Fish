package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.BiggerFishMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.List;

public class BiggerFishTags {

    public static final TagKey<Item> TIER_ONE_BAIT = itemTagKey("tier_one_bait");
    public static final TagKey<Item> TIER_TWO_BAIT = itemTagKey("tier_two_bait");
    public static final TagKey<Item> TIER_THREE_BAIT = itemTagKey("tier_three_bait");
    public static final TagKey<Item> BAIT = itemTagKey("bait");

    public static final TagKey<Item> HOOKS = itemTagKey("hooks");
    public static final TagKey<Item> ATTRACTS_TREASURE = itemTagKey("attracts_treasure");

    public static final TagKey<Item> ALLOWED_IN_BAITED_ROD = itemTagKey("allowed_in_baited_rod");

    public static final TagKey<Item> REQUIRES_MINIGAME_TO_CATCH = itemTagKey("requires_minigame_to_catch");
    public static final TagKey<Item> CAN_FISH_IN_LAVA = itemTagKey("can_fish_in_lava");;
    public static final TagKey<Item> CATCHES_BIGGER_FISH = itemTagKey("catches_bigger_fish");

    public static final TagKey<Item> TIER_ONE_COLD_FRESHWATER_FISH = itemTagKey("tier_one_cold_freshwater_fish");
    public static final TagKey<Item> TIER_TWO_COLD_FRESHWATER_FISH = itemTagKey("tier_two_cold_freshwater_fish");
    public static final TagKey<Item> TIER_THREE_COLD_FRESHWATER_FISH = itemTagKey("tier_three_cold_freshwater_fish");

    public static final TagKey<Item> TIER_ONE_COLD_SALTWATER_FISH = itemTagKey("tier_one_cold_saltwater_fish");
    public static final TagKey<Item> TIER_TWO_COLD_SALTWATER_FISH = itemTagKey("tier_two_cold_saltwater_fish");
    public static final TagKey<Item> TIER_THREE_COLD_SALTWATER_FISH = itemTagKey("tier_three_cold_saltwater_fish");

    public static final TagKey<Item> TIER_ONE_TEMPERATE_FRESHWATER_FISH = itemTagKey("tier_one_temperate_freshwater_fish");
    public static final TagKey<Item> TIER_TWO_TEMPERATE_FRESHWATER_FISH = itemTagKey("tier_two_temperate_freshwater_fish");
    public static final TagKey<Item> TIER_THREE_TEMPERATE_FRESHWATER_FISH = itemTagKey("tier_three_temperate_freshwater_fish");

    public static final TagKey<Item> TIER_ONE_TEMPERATE_SALTWATER_FISH = itemTagKey("tier_one_temperate_saltwater_fish");
    public static final TagKey<Item> TIER_TWO_TEMPERATE_SALTWATER_FISH = itemTagKey("tier_two_temperate_saltwater_fish");
    public static final TagKey<Item> TIER_THREE_TEMPERATE_SALTWATER_FISH = itemTagKey("tier_three_temperate_saltwater_fish");

    public static final TagKey<Item> TIER_ONE_HOT_FRESHWATER_FISH = itemTagKey("tier_one_hot_freshwater_fish");
    public static final TagKey<Item> TIER_TWO_HOT_FRESHWATER_FISH = itemTagKey("tier_two_hot_freshwater_fish");
    public static final TagKey<Item> TIER_THREE_HOT_FRESHWATER_FISH = itemTagKey("tier_three_hot_freshwater_fish");

    public static final TagKey<Item> TIER_ONE_HOT_SALTWATER_FISH = itemTagKey("tier_one_hot_saltwater_fish");
    public static final TagKey<Item> TIER_TWO_HOT_SALTWATER_FISH = itemTagKey("tier_two_hot_saltwater_fish");
    public static final TagKey<Item> TIER_THREE_HOT_SALTWATER_FISH = itemTagKey("tier_three_hot_saltwater_fish");

    public static final TagKey<Item> TIER_ONE_BRACKISH_FISH = itemTagKey("tier_one_brackish_fish");
    public static final TagKey<Item> TIER_TWO_BRACKISH_FISH = itemTagKey("tier_two_brackish_fish");
    public static final TagKey<Item> TIER_THREE_BRACKISH_FISH = itemTagKey("tier_three_brackish_fish");

    public static final TagKey<Item> SHROOMY_FISH = itemTagKey("shroomy_fish");
    public static final TagKey<Item> DEEP_DARK_FISH = itemTagKey("deep_dark_fish");

    public static final TagKey<Item> TIER_ONE_CAVE_FISH = itemTagKey("tier_one_cave_fish");
    public static final TagKey<Item> TIER_TWO_CAVE_FISH = itemTagKey("tier_two_cave_fish");
    public static final TagKey<Item> TIER_THREE_CAVE_FISH = itemTagKey("tier_three_cave_fish");

    public static final TagKey<Item> TIER_ONE_BRACKISH_CAVE_FISH = itemTagKey("tier_one_brackish_cave_fish");
    public static final TagKey<Item> TIER_TWO_BRACKISH_CAVE_FISH = itemTagKey("tier_two_brackish_cave_fish");
    public static final TagKey<Item> TIER_THREE_BRACKISH_CAVE_FISH = itemTagKey("tier_three_brackish_cave_fish");

    public static final TagKey<Item> LAVA_FISH = itemTagKey("lava_fish");

    public static final TagKey<Item> JUNK = itemTagKey("junk");
    public static final TagKey<Item> TREASURE = itemTagKey("treasure");

    public static final List<TagKey<Item>> FISHING_TAGS_FOR_DISPLAY = List.of(
            TIER_ONE_COLD_FRESHWATER_FISH, TIER_TWO_COLD_FRESHWATER_FISH, TIER_THREE_COLD_FRESHWATER_FISH,
            TIER_ONE_COLD_SALTWATER_FISH, TIER_TWO_COLD_SALTWATER_FISH, TIER_THREE_COLD_SALTWATER_FISH,
            TIER_ONE_TEMPERATE_FRESHWATER_FISH, TIER_TWO_TEMPERATE_FRESHWATER_FISH, TIER_THREE_TEMPERATE_FRESHWATER_FISH,
            TIER_ONE_TEMPERATE_SALTWATER_FISH, TIER_TWO_TEMPERATE_SALTWATER_FISH, TIER_THREE_TEMPERATE_SALTWATER_FISH,
            TIER_ONE_HOT_FRESHWATER_FISH, TIER_TWO_HOT_FRESHWATER_FISH, TIER_THREE_HOT_FRESHWATER_FISH,
            TIER_ONE_HOT_SALTWATER_FISH, TIER_TWO_HOT_SALTWATER_FISH, TIER_THREE_HOT_SALTWATER_FISH,
            TIER_ONE_BRACKISH_FISH, TIER_TWO_BRACKISH_FISH, TIER_THREE_BRACKISH_FISH,
            DEEP_DARK_FISH, SHROOMY_FISH,
            TIER_ONE_CAVE_FISH, TIER_TWO_CAVE_FISH, TIER_THREE_CAVE_FISH,
            TIER_ONE_BRACKISH_CAVE_FISH, TIER_TWO_BRACKISH_CAVE_FISH, TIER_THREE_BRACKISH_CAVE_FISH,
            JUNK, TREASURE
    );

    public static TagKey<Item> itemTagKey(String id) {
        return TagKey.create(Registries.ITEM, BiggerFishMod.of(id));
    }
}
