package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.compat.ModCompat;
import cc.cassian.bigger_fish.items.BaitedRodItem;
import cc.cassian.bigger_fish.items.LeechItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.BundleContents;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BiggerFishItems {
    public static ArrayList<Supplier<Item>> FISH = new ArrayList<>();
    public static ArrayList<Supplier<Item>> HIDDEN_FOOD = new ArrayList<>();

    // Fish
    public static Supplier<Item> ARAPAIMA = createFish("arapaima");
    public static Supplier<Item> BASS = createFish("bass");
    public static Supplier<Item> BLUE_BLANQUILLO = createFish("blue_blanquillo");
    public static Supplier<Item> BLUEGILL = createFish("bluegill");
    public static Supplier<Item> BOWFIN = createFish("bowfin");
    public static Supplier<Item> BRACKISH_GOBY = createFish("brackish_goby");
    public static Supplier<Item> BRACKISH_MUDSKIPPER = createFish("brackish_mudskipper");
    public static Supplier<Item> BRACKISH_TIGERFISH = createFish("brackish_tigerfish");
    public static Supplier<Item> BREAM = createFish("bream");
    public static Supplier<Item> BUTTERFLYFISH = createFish("butterflyfish");
    public static Supplier<Item> CATFISH = createFish("catfish");
    public static Supplier<Item> CAPELIN = createFish("capelin");
    public static Supplier<Item> CARP = createFish("carp");
    public static Supplier<Item> CHAR = createFish("char");
    public static Supplier<Item> CICHLID = createFish("cichlid");
    public static Supplier<Item> CLINGFISH = createFish("clingfish");
    public static Supplier<Item> DARTER = createFish("darter");
    public static Supplier<Item> DRIPSTONE_GARRA = createFish("dripstone_garra");
    public static Supplier<Item> FLOUNDER = createFish("flounder");
    public static Supplier<Item> GAR = createFish("gar");
    public static Supplier<Item> GOLDEYE = createFish("goldeye");
    public static Supplier<Item> GOLDFISH = createFish("goldfish");
    public static Supplier<Item> GREEN_CHROMIDE = createFish("green_chromide");
    public static Supplier<Item> GROUPER = createFish("grouper");
    public static Supplier<Item> HADDOCK = createFish("haddock");
    public static Supplier<Item> HERRING = createFish("herring");
    public static Supplier<Item> KOI = createFish("koi");
    public static Supplier<Item> KNIFEFISH = createFish("knifefish");
    public static Supplier<Item> LOACH = createFish("loach");
    public static Supplier<Item> MACKEREL = createFish("mackerel");
    public static Supplier<Item> MANGROVE_MOONY = createFish("mangrove_moony");
    public static Supplier<Item> OARFISH = createFish("oarfish");
    public static Supplier<Item> PACU = createFish("pacu");
    public static Supplier<Item> PERCH = createFish("perch");
    public static Supplier<Item> PIKE = createFish("pike");
    public static Supplier<Item> PIRANHA = createFish("piranha");
    public static Supplier<Item> POLAR_COD = createFish("polar_cod");
    public static Supplier<Item> RAINBOW_TROUT = createFish("rainbow_trout");
    public static Supplier<Item> ROACH = createFish("roach");
    public static Supplier<Item> RUDD = createFish("rudd");
    public static Supplier<Item> SARDINE = createFish("sardine");
    public static Supplier<Item> SHAD = createFish("shad");
    public static Supplier<Item> SHARK_CATFISH = createFish("shark_catfish");
    public static Supplier<Item> SCULKFISH = createFish("sculkfish");
    public static Supplier<Item> SHORTFIN_MOLLY = createFish("shortfin_molly");
    public static Supplier<Item> SPINY_LUMPSUCKER = createFish("spiny_lumpsucker");
    public static Supplier<Item> STURGEON = createFish("sturgeon");
    public static Supplier<Item> SURGEONFISH = createFish("surgeonfish");
    public static Supplier<Item> SWORDFISH = createFish("swordfish");
    public static Supplier<Item> TARPON = createFish("tarpon");
    public static Supplier<Item> TILAPIA = createFish("tilapia");
    public static Supplier<Item> TROUT = createFish("trout");
    public static Supplier<Item> TUNA = createFish("tuna");
    public static Supplier<Item> TWOHORN_SCULPIN = createFish("twohorn_sculpin");
    public static Supplier<Item> WALLEYE = createFish("walleye");
    public static Supplier<Item> WHITE_SUCKER = createFish("white_sucker");

    // Lava fish
    public static Supplier<Item> CINDER_EEL = createFish("cinder_eel", true);
    public static Supplier<Item> FIRE_BASS = createFish("fire_bass", true);
    public static Supplier<Item> FIRE_MACKEREL = createFish("fire_mackerel", true);
    public static Supplier<Item> LAVA_JELLYFISH = createFish("lava_jellyfish", true);

    // Cave fish
    public static Supplier<Item> BLIND_CAVEFISH = createFish("blind_cavefish");
    public static Supplier<Item> CAVE_ANGEL_FISH = createFish("cave_angel_fish");
    public static Supplier<Item> CAVE_PUPFISH = createFish("cave_pupfish");
    public static Supplier<Item> NORTHERN_CAVEFISH = createFish("northern_cavefish");
    public static Supplier<Item> RED_CAVEFISH = createFish("red_cavefish");
    public static Supplier<Item> WHITE_CAVEFISH = createFish("white_cavefish");

    // Bait
    public static Supplier<Item> WORM = createItem("worm", new Item.Properties().component(BiggerFishComponentTypes.FISHING_LOOT.get(), "bigger_fish:gameplay/tier_one_fishing"));
    public static Supplier<Item> LEECH = CommonRegistry.registerItem("leech", ()->new LeechItem(properties("leech").component(BiggerFishComponentTypes.FISHING_LOOT.get(), "bigger_fish:gameplay/tier_two_fishing")));

    // Food
    public static Supplier<Item> FRIED_FISH = createFood("fried_fish", 5, 0.6f);
    public static Supplier<Item> FISH_KEBAB = createFood("fish_kebab", 7, 0.6f);
    public static Supplier<Item> FISH_STEW = createFood("fish_stew", 7, 0.6f);
    public static Supplier<Item> CANNED_FISH = createFood("canned_fish", 5, 0.6f);
    public static Supplier<Item> FISH_FINGERS = createFood("fish_fingers", 5, 0.6f, true);
    public static Supplier<Item> FISH_TACO = createFood("fish_taco", 7, 0.6f, true);

    public static Supplier<Item> SASHIMI = createFood("sashimi", 5, 0.6f, true);
    public static Supplier<Item> SUSHI = createFood("sushi", 5, 0.6f);

    // Tools
    public static Supplier<Item> COPPER_ROD = CommonRegistry.registerItem("copper_rod", ()->new BaitedRodItem(getCopperRodProperties()));
    public static Supplier<Item> NETHERITE_ROD = CommonRegistry.registerItem("netherite_rod", ()->new FishingRodItem(properties("netherite_rod").durability(2031).stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).fireResistant()));
    public static Supplier<Item> DIAMOND_HOOK = createItem("diamond_hook", new Item.Properties().repairable(ItemTags.DIAMOND_TOOL_MATERIALS).component(BiggerFishComponentTypes.FISHING_LOOT.get(), "bigger_fish:gameplay/treasure_fishing").durability(64));

    // JUNK
    public static Supplier<Item> CAN = createItem("can");
    public static Supplier<Item> FISH_BONES = createItem("fish_bones");

    public static List<Supplier<Item>> INGREDIENTS = List.of(WORM, LEECH, CAN, FISH_BONES, DIAMOND_HOOK);

    private static Supplier<Item> createItem(String id) {
        return registerItem(id, properties(id));
    }

    private static Supplier<Item> createItem(String id, Item.Properties properties) {
        return registerItem(id, properties.setId(ResourceKey.create(Registries.ITEM, BiggerFishMod.of(id))));
    }

    private static Supplier<Item> createFish(String id) {
        Supplier<Item> fish = registerItem(id, properties(id).food(Foods.COD).component(BiggerFishComponentTypes.FISHING_LOOT.get(), "bigger_fish:gameplay/tier_three_fishing"));
        FISH.add(fish);
        return fish;
    }

    private static Supplier<Item> createFish(String id, boolean fireproof) {
        if (!fireproof) {
            return createFish(id);
        } else {
            Supplier<Item> fish = registerItem(id, properties(id).component(BiggerFishComponentTypes.FISHING_LOOT.get(), "bigger_fish:gameplay/tier_three_fishing").fireResistant().food(Foods.COD));
            FISH.add(fish);
            return fish;
        }
    }

    private static Supplier<Item> createFood(String id, int nutrition, float saturation) {
        return createFood(id, nutrition, saturation, false);
    }

    private static Supplier<Item> createFood(String id, int nutrition, float saturation, boolean requiresFarmersDelight) {
        Supplier<Item> fish = registerItem(id, properties(id).food(new FoodProperties(nutrition, saturation, false)));
        if (!requiresFarmersDelight || ModCompat.FARMERS_DELIGHT) {
            FISH.add(fish);
        } else {
            HIDDEN_FOOD.add(fish);
        }
        return fish;
    }

    private static Supplier<Item> registerItem(String id, Item.Properties properties) {
        return CommonRegistry.registerItem(id, () -> new Item(properties));
    }

    private static Item.Properties properties(String id) {
        return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, BiggerFishMod.of(id)));
    }

    private static Item.Properties getCopperRodProperties() {
        var properties = properties("copper_rod").stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        if (BiggerFishMod.CONFIG.gameplay.baitedRodsHaveDurability.value()) {
            properties = properties.durability(191);
        }
        return properties;
    }

    public static void touch() {

    }
}
