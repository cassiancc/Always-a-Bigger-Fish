package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.items.CopperRodItem;
import cc.cassian.bigger_fish.items.LeechItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.BundleContents;

import static cc.cassian.bigger_fish.BiggerFishMod.MOD_ID;

public class BiggerFishItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);

    public static DeferredSupplier<Item> BASS = createFish("bass");
    public static DeferredSupplier<Item> BLUEGILL = createFish("bluegill");
    public static DeferredSupplier<Item> CARP = createFish("carp");
    public static DeferredSupplier<Item> FLOUNDER = createFish("flounder");
    public static DeferredSupplier<Item> GOLDFISH = createFish("goldfish");
    public static DeferredSupplier<Item> GROUPER = createFish("grouper");
    public static DeferredSupplier<Item> HERRING = createFish("herring");
    public static DeferredSupplier<Item> KOI = createFish("koi");
    public static DeferredSupplier<Item> MACKEREL = createFish("mackerel");
    public static DeferredSupplier<Item> PERCH = createFish("perch");
    public static DeferredSupplier<Item> SARDINE = createFish("sardine");
    public static DeferredSupplier<Item> SOULFISH = createFish("soulfish");
    public static DeferredSupplier<Item> SWORDFISH = createFish("swordfish");
    public static DeferredSupplier<Item> TILAPIA = createFish("tilapia");
    public static DeferredSupplier<Item> TROUT = createFish("trout");
    public static DeferredSupplier<Item> TUNA = createFish("tuna");

    public static DeferredSupplier<Item> WORM = createFish("worm");
    public static DeferredSupplier<Item> LEECH = ITEMS.register("leech", ()->new LeechItem(properties("leech").arch$tab(CreativeModeTabs.FOOD_AND_DRINKS)));

    public static DeferredSupplier<Item> COPPER_ROD = ITEMS.register("copper_rod", ()->new CopperRodItem(properties("copper_rod").stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES)));

    private static DeferredSupplier<Item> createFish(String id) {
        return ITEMS.register(id, ()-> new Item(properties(id)));
    }

    private static Item.Properties properties(String id) {
        return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, BiggerFishMod.of(id))).arch$tab(CreativeModeTabs.FOOD_AND_DRINKS);
    }
}
