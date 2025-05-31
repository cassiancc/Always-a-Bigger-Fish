package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.BiggerFishMod;
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

import static cc.cassian.bigger_fish.BiggerFishMod.MOD_ID;

public class BiggerFishItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
    public static ArrayList<DeferredSupplier<Item>> FISH = new ArrayList<>();

    // Fish
    public static DeferredSupplier<Item> BASS = createFish("bass");
    public static DeferredSupplier<Item> BLUEGILL = createFish("bluegill");
    public static DeferredSupplier<Item> BREAM = createFish("bream");
    public static DeferredSupplier<Item> BUTTERFLYFISH = createFish("butterflyfish");
    public static DeferredSupplier<Item> CATFISH = createFish("catfish");
    public static DeferredSupplier<Item> CAPELIN = createFish("capelin");
    public static DeferredSupplier<Item> CARP = createFish("carp");
    public static DeferredSupplier<Item> CHAR = createFish("char");
    public static DeferredSupplier<Item> CLINGFISH = createFish("clingfish");
    public static DeferredSupplier<Item> FLOUNDER = createFish("flounder");
    public static DeferredSupplier<Item> GAR = createFish("gar");
    public static DeferredSupplier<Item> GOLDEYE = createFish("goldeye");
    public static DeferredSupplier<Item> GOLDFISH = createFish("goldfish");
    public static DeferredSupplier<Item> GROUPER = createFish("grouper");
    public static DeferredSupplier<Item> HADDOCK = createFish("haddock");
    public static DeferredSupplier<Item> HERRING = createFish("herring");
    public static DeferredSupplier<Item> KOI = createFish("koi");
    public static DeferredSupplier<Item> MACKEREL = createFish("mackerel");
    public static DeferredSupplier<Item> PERCH = createFish("perch");
    public static DeferredSupplier<Item> PIKE = createFish("pike");
    public static DeferredSupplier<Item> RAINBOW_TROUT = createFish("rainbow_trout");
    public static DeferredSupplier<Item> ROACH = createFish("roach");
    public static DeferredSupplier<Item> RUDD = createFish("rudd");
    public static DeferredSupplier<Item> SARDINE = createFish("sardine");
    public static DeferredSupplier<Item> SHAD = createFish("shad");
    public static DeferredSupplier<Item> SOULFISH = createFish("soulfish");
    public static DeferredSupplier<Item> SURGEONFISH = createFish("surgeonfish");
    public static DeferredSupplier<Item> SWORDFISH = createFish("swordfish");
    public static DeferredSupplier<Item> TILAPIA = createFish("tilapia");
    public static DeferredSupplier<Item> TROUT = createFish("trout");
    public static DeferredSupplier<Item> TUNA = createFish("tuna");
    public static DeferredSupplier<Item> TWAIT_SHAD = createFish("twait_shad");
    public static DeferredSupplier<Item> WALLEYE = createFish("walleye");

    // Bait
    public static DeferredSupplier<Item> WORM = ITEMS.register("worm", ()->new Item(properties("worm").arch$tab(CreativeModeTabs.INGREDIENTS)));
    public static DeferredSupplier<Item> LEECH = ITEMS.register("leech", ()->new LeechItem(properties("leech").arch$tab(CreativeModeTabs.INGREDIENTS)));

    // Food
    public static DeferredSupplier<Item> FRIED_FISH = createFood("fried_fish", 5, 0.6f);
    public static DeferredSupplier<Item> FISH_KEBAB = createFood("fish_kebab", 7, 0.6f);
    public static DeferredSupplier<Item> FISH_STEW = createFood("fish_stew", 7, 0.6f);
    public static DeferredSupplier<Item> FISH_TACO = createFood("fish_taco", 7, 0.6f);
    public static DeferredSupplier<Item> SASHIMI = createFood("sashimi", 5, 0.6f);
    public static DeferredSupplier<Item> SUSHI = createFood("sushi", 5, 0.6f);

    // Tools
    public static DeferredSupplier<Item> COPPER_ROD = ITEMS.register("copper_rod", ()->new BaitedRodItem(properties("copper_rod").stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY)));
    public static DeferredSupplier<Item> NETHERITE_ROD = ITEMS.register("netherite_rod", ()->new BaitedRodItem(properties("netherite_rod").stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY)));

    // JUNK
    public static DeferredSupplier<Item> CAN = createItem("can");
    public static DeferredSupplier<Item> FISH_BONES = createItem("fish_bones");

    private static DeferredSupplier<Item> createItem(String id) {
        return registerItem(id, properties(id).arch$tab(CreativeModeTabs.INGREDIENTS));
    }

    private static DeferredSupplier<Item> createFish(String id) {
        DeferredSupplier<Item> fish = registerItem(id, properties(id).food(Foods.COD));
        FISH.add(fish);
        return fish;
    }

    private static DeferredSupplier<Item> createFood(String id, int nutrition, float saturation) {
        DeferredSupplier<Item> fish = registerItem(id, properties(id).food(new FoodProperties(nutrition, saturation, false)));
        FISH.add(fish);
        return fish;
    }

    private static DeferredSupplier<Item> registerItem(String id, Item.Properties properties) {
        return ITEMS.register(id, () -> new Item(properties));
    }

    private static Item.Properties properties(String id) {
        return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, BiggerFishMod.of(id)));
    }
}
