package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.compat.ModCompat;
import cc.cassian.bigger_fish.items.BaitedRodItem;
import cc.cassian.bigger_fish.items.LeechItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.BundleContents;

import java.util.ArrayList;
import java.util.List;

import static cc.cassian.bigger_fish.BiggerFishMod.MOD_ID;

public class BiggerFishItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
    public static ArrayList<DeferredSupplier<Item>> FISH = new ArrayList<>();
    public static ArrayList<DeferredSupplier<Item>> HIDDEN_FOOD = new ArrayList<>();

    // Fish
    public static DeferredSupplier<Item> ARAPAIMA = createFish("arapaima");
    public static DeferredSupplier<Item> BASS = createFish("bass");
    public static DeferredSupplier<Item> BLUE_BLANQUILLO = createFish("blue_blanquillo");
    public static DeferredSupplier<Item> BLUEGILL = createFish("bluegill");
    public static DeferredSupplier<Item> BOWFIN = createFish("bowfin");
    public static DeferredSupplier<Item> BRACKISH_GOBY = createFish("brackish_goby");
    public static DeferredSupplier<Item> BRACKISH_MUDSKIPPER = createFish("brackish_mudskipper");
    public static DeferredSupplier<Item> BRACKISH_TIGERFISH = createFish("brackish_tigerfish");
    public static DeferredSupplier<Item> BREAM = createFish("bream");
    public static DeferredSupplier<Item> BUTTERFLYFISH = createFish("butterflyfish");
    public static DeferredSupplier<Item> CATFISH = createFish("catfish");
    public static DeferredSupplier<Item> CAPELIN = createFish("capelin");
    public static DeferredSupplier<Item> CARP = createFish("carp");
    public static DeferredSupplier<Item> CHAR = createFish("char");
    public static DeferredSupplier<Item> CICHLID = createFish("cichlid");
    public static DeferredSupplier<Item> CLINGFISH = createFish("clingfish");
    public static DeferredSupplier<Item> DARTER = createFish("darter");
    public static DeferredSupplier<Item> DRIPSTONE_GARRA = createFish("dripstone_garra");
    public static DeferredSupplier<Item> FLOUNDER = createFish("flounder");
    public static DeferredSupplier<Item> GAR = createFish("gar");
    public static DeferredSupplier<Item> GOLDEYE = createFish("goldeye");
    public static DeferredSupplier<Item> GOLDFISH = createFish("goldfish");
    public static DeferredSupplier<Item> GREEN_CHROMIDE = createFish("green_chromide");
    public static DeferredSupplier<Item> GROUPER = createFish("grouper");
    public static DeferredSupplier<Item> HADDOCK = createFish("haddock");
    public static DeferredSupplier<Item> HERRING = createFish("herring");
    public static DeferredSupplier<Item> KOI = createFish("koi");
    public static DeferredSupplier<Item> KNIFEFISH = createFish("knifefish");
    public static DeferredSupplier<Item> LOACH = createFish("loach");
    public static DeferredSupplier<Item> MACKEREL = createFish("mackerel");
    public static DeferredSupplier<Item> MANGROVE_MOONY = createFish("mangrove_moony");
    public static DeferredSupplier<Item> OARFISH = createFish("oarfish");
    public static DeferredSupplier<Item> PACU = createFish("pacu");
    public static DeferredSupplier<Item> PERCH = createFish("perch");
    public static DeferredSupplier<Item> PIKE = createFish("pike");
    public static DeferredSupplier<Item> PIRANHA = createFish("piranha");
    public static DeferredSupplier<Item> POLAR_COD = createFish("polar_cod");
    public static DeferredSupplier<Item> RAINBOW_TROUT = createFish("rainbow_trout");
    public static DeferredSupplier<Item> ROACH = createFish("roach");
    public static DeferredSupplier<Item> RUDD = createFish("rudd");
    public static DeferredSupplier<Item> SARDINE = createFish("sardine");
    public static DeferredSupplier<Item> SHAD = createFish("shad");
    public static DeferredSupplier<Item> SHARK_CATFISH = createFish("shark_catfish");
    public static DeferredSupplier<Item> SCULKFISH = createFish("sculkfish");
    public static DeferredSupplier<Item> SHORTFIN_MOLLY = createFish("shortfin_molly");
    public static DeferredSupplier<Item> SPINY_LUMPSUCKER = createFish("spiny_lumpsucker");
    public static DeferredSupplier<Item> STURGEON = createFish("sturgeon");
    public static DeferredSupplier<Item> SURGEONFISH = createFish("surgeonfish");
    public static DeferredSupplier<Item> SWORDFISH = createFish("swordfish");
    public static DeferredSupplier<Item> TARPON = createFish("tarpon");
    public static DeferredSupplier<Item> TILAPIA = createFish("tilapia");
    public static DeferredSupplier<Item> TROUT = createFish("trout");
    public static DeferredSupplier<Item> TUNA = createFish("tuna");
    public static DeferredSupplier<Item> TWOHORN_SCULPIN = createFish("twohorn_sculpin");
    public static DeferredSupplier<Item> WALLEYE = createFish("walleye");
    public static DeferredSupplier<Item> WHITE_SUCKER = createFish("white_sucker");

    // Lava fish
    public static DeferredSupplier<Item> CINDER_EEL = createFish("cinder_eel", true);
    public static DeferredSupplier<Item> FIRE_BASS = createFish("fire_bass", true);
    public static DeferredSupplier<Item> FIRE_MACKEREL = createFish("fire_mackerel", true);
    public static DeferredSupplier<Item> LAVA_JELLYFISH = createFish("lava_jellyfish", true);

    // Cave fish
    public static DeferredSupplier<Item> BLIND_CAVEFISH = createFish("blind_cavefish");
    public static DeferredSupplier<Item> CAVE_ANGEL_FISH = createFish("cave_angel_fish");
    public static DeferredSupplier<Item> CAVE_PUPFISH = createFish("cave_pupfish");
    public static DeferredSupplier<Item> NORTHERN_CAVEFISH = createFish("northern_cavefish");
    public static DeferredSupplier<Item> RED_CAVEFISH = createFish("red_cavefish");
    public static DeferredSupplier<Item> WHITE_CAVEFISH = createFish("white_cavefish");

    // Bait
    public static DeferredSupplier<Item> WORM = ITEMS.register("worm", ()->new Item(properties("worm")));
    public static DeferredSupplier<Item> LEECH = ITEMS.register("leech", ()->new LeechItem(properties("leech")));

    // Food
    public static DeferredSupplier<Item> FRIED_FISH = createFood("fried_fish", 5, 0.6f);
    public static DeferredSupplier<Item> FISH_KEBAB = createFood("fish_kebab", 7, 0.6f);
    public static DeferredSupplier<Item> FISH_STEW = createFood("fish_stew", 7, 0.6f);
    public static DeferredSupplier<Item> FISH_FINGERS = createFood("fish_fingers", 5, 0.6f, true);
    public static DeferredSupplier<Item> FISH_TACO = createFood("fish_taco", 7, 0.6f, true);

    public static DeferredSupplier<Item> SASHIMI = createFood("sashimi", 5, 0.6f, true);
    public static DeferredSupplier<Item> SUSHI = createFood("sushi", 5, 0.6f);

    // Tools
    public static DeferredSupplier<Item> COPPER_ROD = ITEMS.register("copper_rod", ()->new BaitedRodItem(properties("copper_rod").stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY)));
    public static DeferredSupplier<Item> NETHERITE_ROD = ITEMS.register("netherite_rod", ()->new BaitedRodItem(properties("netherite_rod").stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).fireResistant()));

    // JUNK
    public static DeferredSupplier<Item> CAN = createItem("can");
    public static DeferredSupplier<Item> FISH_BONES = createItem("fish_bones");

    public static List<DeferredSupplier<Item>> INGREDIENTS = List.of(WORM, LEECH, CAN, FISH_BONES);

    private static DeferredSupplier<Item> createItem(String id) {
        return registerItem(id, properties(id));
    }

    private static DeferredSupplier<Item> createFish(String id) {
        DeferredSupplier<Item> fish = registerItem(id, properties(id).food(Foods.COD));
        FISH.add(fish);
        return fish;
    }

    private static DeferredSupplier<Item> createFish(String id, boolean fireproof) {
        if (!fireproof) {
            return createFish(id);
        } else {
            DeferredSupplier<Item> fish = registerItem(id, properties(id).fireResistant().food(Foods.COD));
            FISH.add(fish);
            return fish;
        }
    }

    private static DeferredSupplier<Item> createFood(String id, int nutrition, float saturation) {
        return createFood(id, nutrition, saturation, false);
    }

    private static DeferredSupplier<Item> createFood(String id, int nutrition, float saturation, boolean requiresFarmersDelight) {
        DeferredSupplier<Item> fish = registerItem(id, properties(id).food(new FoodProperties(nutrition, saturation, false)));
        if (!requiresFarmersDelight || ModCompat.FARMERS_DELIGHT) {
            FISH.add(fish);
        } else {
            HIDDEN_FOOD.add(fish);
        }
        return fish;
    }

    private static DeferredSupplier<Item> registerItem(String id, Item.Properties properties) {
        return ITEMS.register(id, () -> new Item(properties));
    }

    private static Item.Properties properties(String id) {
        return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, BiggerFishMod.of(id)));
    }
}
