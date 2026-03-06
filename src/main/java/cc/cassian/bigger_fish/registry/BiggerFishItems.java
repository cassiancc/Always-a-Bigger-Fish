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
    public static final ArrayList<Supplier<Item>> FISH = new ArrayList<>();
    public static final ArrayList<Supplier<Item>> HIDDEN_FOOD = new ArrayList<>();
	public static final ArrayList<Supplier<Item>> FOOD = new ArrayList<>();

    // Fish
    public static final Supplier<Item> ARAPAIMA = createFish("arapaima");
    public static final Supplier<Item> BASS = createFish("bass");
    public static final Supplier<Item> BLUE_BLANQUILLO = createFish("blue_blanquillo");
    public static final Supplier<Item> BLUEGILL = createFish("bluegill");
    public static final Supplier<Item> BOWFIN = createFish("bowfin");
    public static final Supplier<Item> BRACKISH_GOBY = createFish("brackish_goby");
    public static final Supplier<Item> BRACKISH_MUDSKIPPER = createFish("brackish_mudskipper");
    public static final Supplier<Item> BRACKISH_TIGERFISH = createFish("brackish_tigerfish");
    public static final Supplier<Item> BREAM = createFish("bream");
    public static final Supplier<Item> BUTTERFLYFISH = createFish("butterflyfish");
    public static final Supplier<Item> CATFISH = createFish("catfish");
    public static final Supplier<Item> CAPELIN = createFish("capelin");
    public static final Supplier<Item> CARP = createFish("carp");
    public static final Supplier<Item> CHAR = createFish("char");
    public static final Supplier<Item> CICHLID = createFish("cichlid");
    public static final Supplier<Item> CLINGFISH = createFish("clingfish");
    public static final Supplier<Item> DARTER = createFish("darter");
    public static final Supplier<Item> DRIPSTONE_GARRA = createFish("dripstone_garra");
    public static final Supplier<Item> FLOUNDER = createFish("flounder");
    public static final Supplier<Item> GAR = createFish("gar");
    public static final Supplier<Item> GOLDEYE = createFish("goldeye");
    public static final Supplier<Item> GOLDFISH = createFish("goldfish");
    public static final Supplier<Item> GREAT_WHITE_SHARK = createFish("great_white_shark");
    public static final Supplier<Item> GREEN_CHROMIDE = createFish("green_chromide");
    public static final Supplier<Item> GROUPER = createFish("grouper");
    public static final Supplier<Item> HADDOCK = createFish("haddock");
    public static final Supplier<Item> HAMMERHEAD_SHARK = createFish("hammerhead_shark");
    public static final Supplier<Item> HERRING = createFish("herring");
    public static final Supplier<Item> JELLYFISH = createFish("jellyfish");
    public static final Supplier<Item> KOI = createFish("koi");
    public static final Supplier<Item> KNIFEFISH = createFish("knifefish");
    public static final Supplier<Item> LOACH = createFish("loach");
    public static final Supplier<Item> MACKEREL = createFish("mackerel");
    public static final Supplier<Item> MANGROVE_MOONY = createFish("mangrove_moony");
    public static final Supplier<Item> OARFISH = createFish("oarfish");
    public static final Supplier<Item> PACU = createFish("pacu");
    public static final Supplier<Item> PERCH = createFish("perch");
    public static final Supplier<Item> PIKE = createFish("pike");
    public static final Supplier<Item> PIRANHA = createFish("piranha");
    public static final Supplier<Item> POLAR_COD = createFish("polar_cod");
    public static final Supplier<Item> RAINBOW_TROUT = createFish("rainbow_trout");
    public static final Supplier<Item> ROACH = createFish("roach");
    public static final Supplier<Item> RUDD = createFish("rudd");
    public static final Supplier<Item> SARDINE = createFish("sardine");
    public static final Supplier<Item> SHAD = createFish("shad");
    public static final Supplier<Item> SHARK_CATFISH = createFish("shark_catfish");
    public static final Supplier<Item> SHORTFIN_MOLLY = createFish("shortfin_molly");
    public static final Supplier<Item> SPINY_LUMPSUCKER = createFish("spiny_lumpsucker");
    public static final Supplier<Item> STARFISH = createFish("starfish");
    public static final Supplier<Item> STINGRAY = createFish("stingray");
    public static final Supplier<Item> STURGEON = createFish("sturgeon");
    public static final Supplier<Item> SURGEONFISH = createFish("surgeonfish");
    public static final Supplier<Item> SWORDFISH = createFish("swordfish");
    public static final Supplier<Item> TARPON = createFish("tarpon");
    public static final Supplier<Item> TILAPIA = createFish("tilapia");
    public static final Supplier<Item> TROUT = createFish("trout");
    public static final Supplier<Item> TUNA = createFish("tuna");
    public static final Supplier<Item> TWOHORN_SCULPIN = createFish("twohorn_sculpin");
    public static final Supplier<Item> WALLEYE = createFish("walleye");
    public static final Supplier<Item> WHALE_SHARK = createFish("whale_shark");
    public static final Supplier<Item> WHITE_SUCKER = createFish("white_sucker");

    // Deep Dark Fish
    public static final Supplier<Item> SCULKFISH = createFish("sculkfish");
    public static final Supplier<Item> SENSOR_EEL = createFish("sensor_eel");
    public static final Supplier<Item> ANGLER_SCULKFISH = createFish("angler_sculkfish");
    public static final Supplier<Item> WARDING_SQUID = createFish("warding_squid");

    // Lava fish
    public static final Supplier<Item> CINDER_EEL = createFish("cinder_eel", true);
    public static final Supplier<Item> FIRE_BASS = createFish("fire_bass", true);
    public static final Supplier<Item> FIRE_MACKEREL = createFish("fire_mackerel", true);
    public static final Supplier<Item> LAVA_JELLYFISH = createFish("lava_jellyfish", true);
    public static final Supplier<Item> LAVASHOE_CRAB = createFish("lavashoe_crab", true);

    // Cave fish
    public static final Supplier<Item> BLIND_CAVEFISH = createFish("blind_cavefish");
    public static final Supplier<Item> CAVE_ANGEL_FISH = createFish("cave_angel_fish");
    public static final Supplier<Item> CAVE_PUPFISH = createFish("cave_pupfish");
    public static final Supplier<Item> NORTHERN_CAVEFISH = createFish("northern_cavefish");
    public static final Supplier<Item> RED_CAVEFISH = createFish("red_cavefish");
    public static final Supplier<Item> WHITE_CAVEFISH = createFish("white_cavefish");

    // Bait
    public static final Supplier<Item> WORM = createItem("worm", new Item.Properties().component(BiggerFishComponentTypes.FISHING_LOOT.get(), "bigger_fish:gameplay/tier_one_fishing"));
    public static final Supplier<Item> LEECH = CommonRegistry.registerItem("leech", ()->new LeechItem(properties("leech")
//            .useCooldown(0.5F)
            .component(BiggerFishComponentTypes.FISHING_LOOT.get(), "bigger_fish:gameplay/tier_two_fishing")));

    // Food
    public static final Supplier<Item> FRIED_FISH = createFood("fried_fish", 5, 0.6f);
    public static final Supplier<Item> FISH_KEBAB = createFood("fish_kebab", 7, 0.6f);
    public static final Supplier<Item> FISH_STEW = createFood("fish_stew", 7, 0.6f);
    public static final Supplier<Item> CANNED_FISH = createFood("canned_fish", 5, 0.6f);
    public static final Supplier<Item> FISH_FINGERS = createFood("fish_fingers", 5, 0.6f, true);
    public static final Supplier<Item> FISH_TACO = createFood("fish_taco", 7, 0.6f, true);

    public static final Supplier<Item> SASHIMI = createFood("sashimi", 5, 0.6f, true);
    public static final Supplier<Item> SUSHI = createFood("sushi", 5, 0.6f);

    // Tools
    public static final Supplier<Item> COPPER_ROD = CommonRegistry.registerItem("copper_rod", ()->new BaitedRodItem(getCopperRodProperties()));

    public static final Supplier<Item> COPPER_HOOK = createItem("copper_hook", new Item.Properties()
//            .repairable(COPPER_TOOL_MATERIALS)
            .component(BiggerFishComponentTypes.HOOK_EFFECTS.get(), "copper")
            .component(BiggerFishComponentTypes.FISHING_LOOT.get(), "bigger_fish:gameplay/tier_one_fishing")
            .durability(64));

    public static final Supplier<Item> DIAMOND_HOOK = createItem("diamond_hook", Item::new, new Item.Properties()
//            .repairable(DIAMOND_TOOL_MATERIALS)
            .component(BiggerFishComponentTypes.HOOK_EFFECTS.get(), "treasure")
            .component(BiggerFishComponentTypes.FISHING_LOOT.get(), "bigger_fish:gameplay/treasure_fishing")
            .durability(128));

    public static final Supplier<Item> NETHERITE_HOOK = createItem("netherite_hook", new Item.Properties()
//            .repairable(NETHERITE_TOOL_MATERIALS)
            .component(BiggerFishComponentTypes.HOOK_EFFECTS.get(), "netherite")
            .durability(512));

    public static Item FISH_BARREL = createItem("fish_barrel", FishBarrelItem::new, new Item.Properties().component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).stacksTo(1));
    public static Item FISH_TRAP = createItem("fish_trap", FishTrapItem::new, new Item.Properties().component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).stacksTo(1));


    // JUNK
    public static final Supplier<Item> CAN = createItem("can");
    public static final Supplier<Item> FISH_BONES = createItem("fish_bones");

    public static List<Supplier<Item>> INGREDIENTS = List.of(WORM, LEECH, CAN, FISH_BONES);
    public static List<Supplier<Item>> TOOLS = List.of(COPPER_ROD, DIAMOND_HOOK, NETHERITE_HOOK, ()-> FISH_BARREL, ()->FISH_TRAP);

    private static Supplier<Item> createItem(String id) {
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

    private static Supplier<Item> createItem(String id, Item.Properties properties) {
        return registerItem(id, properties
        );
    }

    private static ResourceKey<Item> key(String name) {
        return ResourceKey.create(Registries.ITEM, BiggerFishMod.of(name));
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
        Supplier<Item> fish = registerItem(id, properties(id).food(new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation).build()));
        if (!requiresFarmersDelight || ModCompat.FARMERS_DELIGHT) {
            FOOD.add(fish);
        } else {
            HIDDEN_FOOD.add(fish);
        }
        return fish;
    }

    private static Supplier<Item> registerItem(String id, Item.Properties properties) {
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
