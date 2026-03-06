package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.compat.ModCompat;
import cc.cassian.bigger_fish.items.BaitedRodItem;
import cc.cassian.bigger_fish.items.FishBarrelItem;
import cc.cassian.bigger_fish.items.FishTrapItem;
import cc.cassian.bigger_fish.items.LeechItem;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.BundleContents;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static cc.cassian.bigger_fish.registry.BiggerFishTags.*;

public class BiggerFishItems {
    public static final ArrayList<Item> FISH = new ArrayList<>();
    public static final ArrayList<Item> HIDDEN_FOOD = new ArrayList<>();
	public static final ArrayList<Item> FOOD = new ArrayList<>();

    // Fish
    public static final Item ARAPAIMA = createFish("arapaima");
    public static final Item BASS = createFish("bass");
    public static final Item BLUE_BLANQUILLO = createFish("blue_blanquillo");
    public static final Item BLUEGILL = createFish("bluegill");
    public static final Item BOWFIN = createFish("bowfin");
    public static final Item BRACKISH_GOBY = createFish("brackish_goby");
    public static final Item BRACKISH_MUDSKIPPER = createFish("brackish_mudskipper");
    public static final Item BRACKISH_TIGERFISH = createFish("brackish_tigerfish");
    public static final Item BREAM = createFish("bream");
    public static final Item BUTTERFLYFISH = createFish("butterflyfish");
    public static final Item CATFISH = createFish("catfish");
    public static final Item CAPELIN = createFish("capelin");
    public static final Item CARP = createFish("carp");
    public static final Item CHAR = createFish("char");
    public static final Item CICHLID = createFish("cichlid");
    public static final Item CLINGFISH = createFish("clingfish");
    public static final Item DARTER = createFish("darter");
    public static final Item DRIPSTONE_GARRA = createFish("dripstone_garra");
    public static final Item FLOUNDER = createFish("flounder");
    public static final Item GAR = createFish("gar");
    public static final Item GOLDEYE = createFish("goldeye");
    public static final Item GOLDFISH = createFish("goldfish");
    public static final Item GREAT_WHITE_SHARK = createFish("great_white_shark");
    public static final Item GREEN_CHROMIDE = createFish("green_chromide");
    public static final Item GROUPER = createFish("grouper");
    public static final Item HADDOCK = createFish("haddock");
    public static final Item HAMMERHEAD_SHARK = createFish("hammerhead_shark");
    public static final Item HERRING = createFish("herring");
    public static final Item JELLYFISH = createFish("jellyfish");
    public static final Item KOI = createFish("koi");
    public static final Item KNIFEFISH = createFish("knifefish");
    public static final Item LOACH = createFish("loach");
    public static final Item MACKEREL = createFish("mackerel");
    public static final Item MANGROVE_MOONY = createFish("mangrove_moony");
    public static final Item OARFISH = createFish("oarfish");
    public static final Item PACU = createFish("pacu");
    public static final Item PERCH = createFish("perch");
    public static final Item PIKE = createFish("pike");
    public static final Item PIRANHA = createFish("piranha");
    public static final Item POLAR_COD = createFish("polar_cod");
    public static final Item RAINBOW_TROUT = createFish("rainbow_trout");
    public static final Item ROACH = createFish("roach");
    public static final Item RUDD = createFish("rudd");
    public static final Item SARDINE = createFish("sardine");
    public static final Item SHAD = createFish("shad");
    public static final Item SHARK_CATFISH = createFish("shark_catfish");
    public static final Item SHORTFIN_MOLLY = createFish("shortfin_molly");
    public static final Item SPINY_LUMPSUCKER = createFish("spiny_lumpsucker");
    public static final Item STARFISH = createFish("starfish");
    public static final Item STINGRAY = createFish("stingray");
    public static final Item STURGEON = createFish("sturgeon");
    public static final Item SURGEONFISH = createFish("surgeonfish");
    public static final Item SWORDFISH = createFish("swordfish");
    public static final Item TARPON = createFish("tarpon");
    public static final Item TILAPIA = createFish("tilapia");
    public static final Item TROUT = createFish("trout");
    public static final Item TUNA = createFish("tuna");
    public static final Item TWOHORN_SCULPIN = createFish("twohorn_sculpin");
    public static final Item WALLEYE = createFish("walleye");
    public static final Item WHALE_SHARK = createFish("whale_shark");
    public static final Item WHITE_SUCKER = createFish("white_sucker");

    // Deep Dark Fish
    public static final Item SCULKFISH = createFish("sculkfish");
    public static final Item SENSOR_EEL = createFish("sensor_eel");
    public static final Item ANGLER_SCULKFISH = createFish("angler_sculkfish");
    public static final Item WARDING_SQUID = createFish("warding_squid");

    // Lava fish
    public static final Item CINDER_EEL = createFish("cinder_eel", true);
    public static final Item FIRE_BASS = createFish("fire_bass", true);
    public static final Item FIRE_MACKEREL = createFish("fire_mackerel", true);
    public static final Item LAVA_JELLYFISH = createFish("lava_jellyfish", true);
    public static final Item LAVASHOE_CRAB = createFish("lavashoe_crab", true);

    // Cave fish
    public static final Item BLIND_CAVEFISH = createFish("blind_cavefish");
    public static final Item CAVE_ANGEL_FISH = createFish("cave_angel_fish");
    public static final Item CAVE_PUPFISH = createFish("cave_pupfish");
    public static final Item NORTHERN_CAVEFISH = createFish("northern_cavefish");
    public static final Item RED_CAVEFISH = createFish("red_cavefish");
    public static final Item WHITE_CAVEFISH = createFish("white_cavefish");

    // Bait
    public static final Item WORM = createItem("worm", new Item.Properties().component(BiggerFishComponentTypes.FISHING_LOOT.get(), "bigger_fish:gameplay/tier_one_fishing"));
    public static final Item LEECH = CommonRegistry.registerItem("leech", ()->new LeechItem(properties("leech")
//            .useCooldown(0.5F)
            .component(BiggerFishComponentTypes.FISHING_LOOT.get(), "bigger_fish:gameplay/tier_two_fishing")));

    // Food
    public static final Item FRIED_FISH = createFood("fried_fish", 5, 0.6f);
    public static final Item FISH_KEBAB = createFood("fish_kebab", 7, 0.6f);
    public static final Item FISH_STEW = createFood("fish_stew", 7, 0.6f);
    public static final Item CANNED_FISH = createFood("canned_fish", 5, 0.6f);
    public static final Item FISH_FINGERS = createFood("fish_fingers", 5, 0.6f, true);
    public static final Item FISH_TACO = createFood("fish_taco", 7, 0.6f, true);

    public static final Item SASHIMI = createFood("sashimi", 5, 0.6f, true);
    public static final Item SUSHI = createFood("sushi", 5, 0.6f);

    // Tools
    public static final Item COPPER_ROD = CommonRegistry.registerItem("copper_rod", ()->new BaitedRodItem(getCopperRodProperties()));

    public static final Item COPPER_HOOK = createItem("copper_hook", new Item.Properties()
//            .repairable(COPPER_TOOL_MATERIALS)
            .component(BiggerFishComponentTypes.HOOK_EFFECTS.get(), "copper")
            .component(BiggerFishComponentTypes.FISHING_LOOT.get(), "bigger_fish:gameplay/tier_one_fishing")
            .durability(64));

    public static final Item DIAMOND_HOOK = createItem("diamond_hook", Item::new, new Item.Properties()
//            .repairable(DIAMOND_TOOL_MATERIALS)
            .component(BiggerFishComponentTypes.HOOK_EFFECTS.get(), "treasure")
            .component(BiggerFishComponentTypes.FISHING_LOOT.get(), "bigger_fish:gameplay/treasure_fishing")
            .durability(128));

    public static final Item NETHERITE_HOOK = createItem("netherite_hook", new Item.Properties()
//            .repairable(NETHERITE_TOOL_MATERIALS)
            .component(BiggerFishComponentTypes.HOOK_EFFECTS.get(), "netherite")
            .durability(512));

    public static Item FISH_BARREL = createItem("fish_barrel", FishBarrelItem::new, new Item.Properties().component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).stacksTo(1));
    public static Item FISH_TRAP = createItem("fish_trap", FishTrapItem::new, new Item.Properties().component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).stacksTo(1));


    // JUNK
    public static final Item CAN = createItem("can");
    public static final Item FISH_BONES = createItem("fish_bones");

    public static List<Item> INGREDIENTS = List.of(WORM, LEECH, CAN, FISH_BONES);
    public static List<Item> TOOLS = List.of(COPPER_ROD, DIAMOND_HOOK, NETHERITE_HOOK, FISH_BARREL, FISH_TRAP);

    private static Item createItem(String id) {
        return registerItem(id, properties(id));
    }

    public static <T extends Item> T createItem(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<Item> itemKey = key(name);

        // Create the item instance.
        T item = itemFactory.apply(settings);

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    private static Item createItem(String id, Item.Properties properties) {
        return registerItem(id, properties
        );
    }

    private static ResourceKey<Item> key(String name) {
        return ResourceKey.create(Registries.ITEM, BiggerFishMod.of(name));
    }

    private static Item createFish(String id) {
        Item fish = registerItem(id, properties(id).food(Foods.COD).component(BiggerFishComponentTypes.FISHING_LOOT.get(), "bigger_fish:gameplay/tier_three_fishing"));
        FISH.add(fish);
        return fish;
    }

    private static Item createFish(String id, boolean fireproof) {
        if (!fireproof) {
            return createFish(id);
        } else {
            Item fish = registerItem(id, properties(id).component(BiggerFishComponentTypes.FISHING_LOOT.get(), "bigger_fish:gameplay/tier_three_fishing").fireResistant().food(Foods.COD));
            FISH.add(fish);
            return fish;
        }
    }

    private static Item createFood(String id, int nutrition, float saturation) {
        return createFood(id, nutrition, saturation, false);
    }

    private static Item createFood(String id, int nutrition, float saturation, boolean requiresFarmersDelight) {
        Item fish = registerItem(id, properties(id).food(new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation).build()));
        if (!requiresFarmersDelight || ModCompat.FARMERS_DELIGHT) {
            FOOD.add(fish);
        } else {
            HIDDEN_FOOD.add(fish);
        }
        return fish;
    }

    private static Item registerItem(String id, Item.Properties properties) {
        return CommonRegistry.registerItem(id, () -> new Item(properties));
    }

    private static Item.Properties properties(String id) {
        return new Item.Properties();
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
