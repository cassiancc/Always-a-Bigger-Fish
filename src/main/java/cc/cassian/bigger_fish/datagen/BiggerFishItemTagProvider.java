package cc.cassian.bigger_fish.datagen;

//? fabric {

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static cc.cassian.bigger_fish.BiggerFishMod.of;
import static cc.cassian.bigger_fish.registry.BiggerFishItems.*;
import static cc.cassian.bigger_fish.registry.BiggerFishTags.*;
import static net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags.*;
import static net.minecraft.tags.ItemTags.*;
import static net.minecraft.world.item.Items.*;

public class BiggerFishItemTagProvider extends FabricTagProvider.ItemTagProvider {
	public BiggerFishItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tagBuilder(COOKED_FISH_FOODS)
				.add(FRIED_FISH);
		tagBuilder(RAW_FISH_FOODS)
				.addOptionalTag(BiggerFishTags.FISH);
		tagBuilder(FISHING_ROD_TOOLS)
				.add(COPPER_ROD);
		tagBuilder(FISH_CONTAINERS)
				.add(FISH_BARREL);
		tagBuilder(PICKS_UP_FISH)
				.add(COPPER_ROD)
				.add(FISH_BARREL)
				.add(FISH_TRAP);
		tagBuilder(BONES)
				.add(FISH_BONES);
		tagBuilder(HIDDEN_FROM_RECIPE_VIEWERS)
				.add(COPPER_HOOK);
		tagBuilder(DURABILITY_ENCHANTABLE)
				.add(DIAMOND_HOOK.get(), NETHERITE_HOOK.get(), COPPER_ROD.get());
		tagBuilder(FISHING_ENCHANTABLE)
				.add(COPPER_ROD);
		tagBuilder(CAT_FOOD)
				.addOptionalTag(BiggerFishTags.FISH);
		tagBuilder(CHICKEN_FOOD)
				.add(WORM);
		tagBuilder(FISHES)
				.addOptionalTag(BiggerFishTags.FISH);
		tagBuilder(ALLOWED_IN_BAITED_ROD)
				.addOptionalTag(BAIT)
				.addOptionalTag(HOOKS);
		tagBuilder(ALLOWED_IN_FISH_BARREL)
				.addOptionalTag(RAW_FISH_FOODS)
				.addOptionalTag(BiggerFishTags.FISH)
				.addOptionalTag(TREASURE)
				.addOptionalTag(JUNK);
		tagBuilder(ATTRACTS_TREASURE)
				.add(DIAMOND_HOOK);
		tagBuilder(BAIT)
				.addOptionalTag(TIER_ONE_BAIT)
				.addOptionalTag(TIER_TWO_BAIT)
				.addOptionalTag(TIER_THREE_BAIT);
		BiggerFishItems.FISH.forEach(itemSupplier -> {
			tagBuilder(BiggerFishTags.FISH).add(itemSupplier);
		});
		tagBuilder(BRACKISH_CAVE_FISH).addOptionalTag(TIER_ONE_BRACKISH_CAVE_FISH).addOptionalTag(TIER_TWO_BRACKISH_CAVE_FISH).addOptionalTag(TIER_THREE_BRACKISH_CAVE_FISH);
		tagBuilder(BRACKISH_FISH).addOptionalTag(TIER_ONE_BRACKISH_FISH).addOptionalTag(TIER_TWO_BRACKISH_FISH).addOptionalTag(TIER_THREE_BRACKISH_FISH);
		tagBuilder(CAVE_FISH).addOptionalTag(TIER_ONE_CAVE_FISH).addOptionalTag(TIER_TWO_CAVE_FISH).addOptionalTag(TIER_THREE_CAVE_FISH);
		tagBuilder(COLD_FRESHWATER_FISH).addOptionalTag(TIER_ONE_COLD_FRESHWATER_FISH).addOptionalTag(TIER_TWO_COLD_FRESHWATER_FISH).addOptionalTag(TIER_THREE_COLD_FRESHWATER_FISH);
		tagBuilder(COLD_SALTWATER_FISH).addOptionalTag(TIER_ONE_COLD_SALTWATER_FISH).addOptionalTag(TIER_TWO_COLD_SALTWATER_FISH).addOptionalTag(TIER_THREE_COLD_SALTWATER_FISH);
		tagBuilder(COSMOPOLITAN_FRESHWATER_FISH).addOptionalTag(TIER_ONE_COSMOPOLITAN_FRESHWATER_FISH).addOptionalTag(TIER_TWO_COSMOPOLITAN_FRESHWATER_FISH).addOptionalTag(TIER_THREE_COSMOPOLITAN_FRESHWATER_FISH);
		tagBuilder(COSMOPOLITAN_SALTWATER_FISH).addOptionalTag(TIER_ONE_COSMOPOLITAN_SALTWATER_FISH).addOptionalTag(TIER_TWO_COSMOPOLITAN_SALTWATER_FISH).addOptionalTag(TIER_THREE_COSMOPOLITAN_SALTWATER_FISH);
		tagBuilder(DEEP_DARK_FISH).add(
				SCULKFISH.get(),
				ANGLER_SCULKFISH.get(),
				SENSOR_EEL.get(),
				WARDING_SQUID.get())
				.addOptional(fishery("echofin"))
				.addOptional(fishery("sculkamander")
		);
		tagBuilder(HOOKS).add(
						DIAMOND_HOOK.get(),
						NETHERITE_HOOK.get()
		);
		tagBuilder(HOT_FRESHWATER_FISH).addOptionalTag(TIER_ONE_HOT_FRESHWATER_FISH).addOptionalTag(TIER_TWO_HOT_FRESHWATER_FISH).addOptionalTag(TIER_THREE_HOT_FRESHWATER_FISH);
		tagBuilder(HOT_SALTWATER_FISH).addOptionalTag(TIER_ONE_HOT_SALTWATER_FISH).addOptionalTag(TIER_TWO_HOT_SALTWATER_FISH).addOptionalTag(TIER_THREE_HOT_SALTWATER_FISH);

		tagBuilder(JUNK).add(LILY_PAD, LEATHER, LEATHER_BOOTS, BONE, STRING, FISHING_ROD, BOWL, STICK, TRIPWIRE_HOOK, ROTTEN_FLESH, BAMBOO, FISH_BONES.get(), CAN.get());
		tagBuilder(TREASURE).add(NAME_TAG, SADDLE, BOW, FISHING_ROD, NAUTILUS_SHELL);


		tagBuilder(LAVA_FISH).add(CINDER_EEL.get(), FIRE_BASS.get(), FIRE_MACKEREL.get(), LAVA_JELLYFISH.get(), LAVASHOE_CRAB.get())
				.addOptional(fishofthieves("jellyfish"))
				.addOptional(fishofthieves("ghast_brood"))
				.addOptional(fishofthieves("soul_leech"))
		;
		// bait
		tagBuilder(TIER_ONE_BAIT).add(WORM.get())
				.addOptional(aquaculture("worm"))
				.addOptional(fishofthieves("earthworms"))
				.addOptional(of("tide", "bait"))
		;
		tagBuilder(TIER_TWO_BAIT).add(LEECH.get())
				.addOptional(fishofthieves("grubs"))
				.addOptional(aquaculture("leech"))
				.addOptional(fishofthieves("leeches"))
		;
		tagBuilder(convention("foods/raw_fish")).add(LEECH.get())
				.addOptional(aquaculture("minnow"))
				.add(SARDINE.get())
		;
		tagBuilder(REQUIRES_MINIGAME_TO_CATCH).add(COPPER_ROD.get());
		tagBuilder(SHROOMY_FISH)
				.addOptional(aquaculture("brown_shrooma"))
				.addOptional(aquaculture("red_shrooma"));
		tagBuilder(TEMPERATE_FRESHWATER_FISH).addOptionalTag(TIER_ONE_TEMPERATE_FRESHWATER_FISH).addOptionalTag(TIER_TWO_TEMPERATE_FRESHWATER_FISH).addOptionalTag(TIER_THREE_TEMPERATE_FRESHWATER_FISH);
		tagBuilder(TEMPERATE_SALTWATER_FISH).addOptionalTag(TIER_ONE_TEMPERATE_SALTWATER_FISH).addOptionalTag(TIER_TWO_TEMPERATE_SALTWATER_FISH).addOptionalTag(TIER_THREE_TEMPERATE_SALTWATER_FISH);
		// tier one
		tagBuilder(TIER_ONE_BRACKISH_CAVE_FISH).add(
				SHORTFIN_MOLLY.get(),
				CICHLID.get()
		);
		tagBuilder(TIER_ONE_BRACKISH_FISH).add(
				BRACKISH_MUDSKIPPER.get(),
				BRACKISH_GOBY.get()
		);
		tagBuilder(TIER_ONE_CAVE_FISH).add(
				BLIND_CAVEFISH.get(),
				CAVE_PUPFISH.get())
				.addOptional(fishery("pale_bass"))
				.addOptional(fishery("salamander"))
		;
		tagBuilder(TIER_ONE_COLD_FRESHWATER_FISH)
				.addOptionalTag(TIER_ONE_COSMOPOLITAN_FRESHWATER_FISH)
				.add(GOLDEYE.get());
		tagBuilder(TIER_ONE_COLD_SALTWATER_FISH)
				.addOptionalTag(TIER_ONE_COSMOPOLITAN_FRESHWATER_FISH)
				.add(CAPELIN.get(),
					POLAR_COD.get())
				.addOptional(aquaculture("atlantic_cod"))
				.addOptional(aquaculture("pink_salmon"))
				.addOptional(aquaculture("pacific_halibut"))
				.addOptional(aquaculture("atlantic_halibut"))
		;
		tagBuilder(TIER_ONE_COSMOPOLITAN_FRESHWATER_FISH)
				.add(BREAM.get(),
					CARP.get(),
					TROUT.get(),
					SALMON)
				.addOptional(aquaculture("carp"))
				.addOptional(aquaculture("minnow"))
		;
		tagBuilder(TIER_ONE_COSMOPOLITAN_SALTWATER_FISH)
				.add(HERRING.get(),
					COD,
					SALMON)
				.addOptional(aquaculture("atlantic_herring"))
		;
		tagBuilder(TIER_ONE_FISH)
				.addOptionalTag(TIER_ONE_COSMOPOLITAN_SALTWATER_FISH)
				.addOptionalTag(TIER_ONE_COSMOPOLITAN_FRESHWATER_FISH)
				.addOptionalTag(TIER_ONE_COLD_FRESHWATER_FISH)
				.addOptionalTag(TIER_ONE_COLD_SALTWATER_FISH)
				.addOptionalTag(TIER_ONE_HOT_FRESHWATER_FISH)
				.addOptionalTag(TIER_ONE_HOT_SALTWATER_FISH)
				.addOptionalTag(TIER_ONE_TEMPERATE_FRESHWATER_FISH)
				.addOptionalTag(TIER_ONE_TEMPERATE_SALTWATER_FISH)
				.addOptionalTag(TIER_ONE_CAVE_FISH)
				.addOptionalTag(TIER_ONE_BRACKISH_CAVE_FISH)
				.addOptionalTag(TIER_ONE_BRACKISH_FISH)
		;
		tagBuilder(TIER_ONE_HOT_FRESHWATER_FISH)
				.addOptionalTag(TIER_ONE_COSMOPOLITAN_FRESHWATER_FISH)
				.add(CATFISH.get(),
					PIRANHA.get())
				.addOptional(aquaculture("bayad"))
				.addOptional(aquaculture("catfish"))
				.addOptional(aquaculture("piranha"))
				.addOptional(fishofthieves("splashtail"))
				.addOptional(fishofthieves("pondie"))
		;
		tagBuilder(TIER_ONE_HOT_SALTWATER_FISH)
				.addOptionalTag(TIER_ONE_COSMOPOLITAN_SALTWATER_FISH)
				.add(SARDINE.get(),
					FLOUNDER.get(),
					PUFFERFISH,
					TROPICAL_FISH)
				.addOptional(fishofthieves("splashtail"))
				.addOptional(fishofthieves("islehopper"))
				.addOptional(fishofthieves("plentifin"))
		;
		tagBuilder(TIER_ONE_TEMPERATE_FRESHWATER_FISH)
				.addOptionalTag(TIER_ONE_COSMOPOLITAN_FRESHWATER_FISH)
				.add(GOLDFISH.get(),
					BASS.get(),
					BLUEGILL.get())
				.addOptional(fishery("bluegill"))
				.addOptional(fishery("leafskimmer"))
				.addOptional(fishofthieves("splashtail"))
				.addOptional(fishofthieves("pondie"))
		;
		tagBuilder(TIER_ONE_TEMPERATE_SALTWATER_FISH)
				.addOptionalTag(TIER_ONE_COSMOPOLITAN_SALTWATER_FISH)
				.add(STARFISH.get(),
					MACKEREL.get(),
					STURGEON.get())
				.addOptional(fishofthieves("splashtail"))
				.addOptional(fishofthieves("plentifin"))
		;
		// tier two
		tagBuilder(TIER_TWO_FISH)
				.addOptionalTag(TIER_TWO_COSMOPOLITAN_SALTWATER_FISH)
				.addOptionalTag(TIER_TWO_COSMOPOLITAN_FRESHWATER_FISH)
				.addOptionalTag(TIER_TWO_COLD_FRESHWATER_FISH)
				.addOptionalTag(TIER_TWO_COLD_SALTWATER_FISH)
				.addOptionalTag(TIER_TWO_HOT_FRESHWATER_FISH)
				.addOptionalTag(TIER_TWO_HOT_SALTWATER_FISH)
				.addOptionalTag(TIER_TWO_TEMPERATE_FRESHWATER_FISH)
				.addOptionalTag(TIER_TWO_TEMPERATE_SALTWATER_FISH)
				.addOptionalTag(TIER_TWO_CAVE_FISH)
				.addOptionalTag(TIER_TWO_BRACKISH_CAVE_FISH)
				.addOptionalTag(TIER_TWO_BRACKISH_FISH)
		;
		tagBuilder(TIER_TWO_BRACKISH_CAVE_FISH)
				.add(BLUE_BLANQUILLO.get(), BRACKISH_GOBY.get())
		;
		tagBuilder(TIER_TWO_BRACKISH_FISH)
				.add(GREEN_CHROMIDE.get(), BRACKISH_TIGERFISH.get(), MANGROVE_MOONY.get())
		;
		tagBuilder(TIER_TWO_CAVE_FISH)
				.add(NORTHERN_CAVEFISH.get(), RED_CAVEFISH.get(), WHITE_CAVEFISH.get())
		;
		tagBuilder(TIER_TWO_COLD_FRESHWATER_FISH)
				.addOptionalTag(TIER_TWO_COSMOPOLITAN_FRESHWATER_FISH)
				.add(RUDD)
				.add(PIKE)
				.addOptional(of("upgrade_aquatic", "pike"))
				.addOptional(aquaculture("muskellunge"))
		;
		tagBuilder(TIER_TWO_COLD_SALTWATER_FISH)
				.addOptionalTag(TIER_TWO_COSMOPOLITAN_SALTWATER_FISH)
				.add(CHAR)
				.add(HADDOCK)
				.addOptional(aquaculture("pollock"))
		;
		tagBuilder(TIER_TWO_COSMOPOLITAN_FRESHWATER_FISH)
				.add(KOI)
				.add(SHAD)
		;
		tagBuilder(TIER_TWO_COSMOPOLITAN_SALTWATER_FISH)
				.add(TARPON)
				.add(JELLYFISH)
				.addOptional(fishery("red_snapper"))
		;
		tagBuilder(TIER_TWO_HOT_FRESHWATER_FISH)
				.addOptionalTag(TIER_TWO_COSMOPOLITAN_FRESHWATER_FISH)
				.add(GAR.get(), PERCH.get(), TILAPIA.get())
				.addOptional(aquaculture("gar"))
				.addOptional(aquaculture("perch"))
				.addOptional(aquaculture("capitaine"))
				.addOptional(aquaculture("boulti"))
				.addOptional(aquaculture("synodontis"))
				.addOptional(aquaculture("arapaima"))
				.addOptional(aquaculture("tambaqui"))
				.addOptional(of("upgrade_aquatic", "perch"))
		;
		tagBuilder(TIER_TWO_HOT_SALTWATER_FISH)
				.addOptionalTag(TIER_TWO_COSMOPOLITAN_SALTWATER_FISH)
				.add(BUTTERFLYFISH)
				.add(SURGEONFISH)
				.add(STINGRAY)
				.addOptional(fishery("sunfish"))
				.addOptional(of("upgrade_aquatic", "lionfish"))
		;
		tagBuilder(TIER_TWO_TEMPERATE_FRESHWATER_FISH)
				.addOptionalTag(TIER_TWO_COSMOPOLITAN_FRESHWATER_FISH)
				.add(DARTER)
				.add(RAINBOW_TROUT)
				.addOptional(aquaculture("rainbow_trout"))
				.addOptional(aquaculture("brown_trout"))
				.addOptional(aquaculture("smallmouth_bass"))
		;
		tagBuilder(TIER_TWO_TEMPERATE_SALTWATER_FISH)
				.addOptionalTag(TIER_TWO_COSMOPOLITAN_SALTWATER_FISH)
				.add(CLINGFISH)
				.addOptional(fishofthieves("islehopper"))
				.addOptional(fishery("sunfish"))
		;

		// tier three
		tagBuilder(TIER_THREE_FISH)
				.addOptionalTag(TIER_THREE_COSMOPOLITAN_SALTWATER_FISH)
				.addOptionalTag(TIER_THREE_COSMOPOLITAN_FRESHWATER_FISH)
				.addOptionalTag(TIER_THREE_COLD_FRESHWATER_FISH)
				.addOptionalTag(TIER_THREE_COLD_SALTWATER_FISH)
				.addOptionalTag(TIER_THREE_HOT_FRESHWATER_FISH)
				.addOptionalTag(TIER_THREE_HOT_SALTWATER_FISH)
				.addOptionalTag(TIER_THREE_TEMPERATE_FRESHWATER_FISH)
				.addOptionalTag(TIER_THREE_TEMPERATE_SALTWATER_FISH)
				.addOptionalTag(TIER_THREE_CAVE_FISH)
				.addOptionalTag(TIER_THREE_BRACKISH_CAVE_FISH)
				.addOptionalTag(TIER_THREE_BRACKISH_FISH)
		;
		tagBuilder(TIER_THREE_BRACKISH_FISH)
				.add(KNIFEFISH)
				.add(SHARK_CATFISH)
		;
		tagBuilder(TIER_THREE_BRACKISH_CAVE_FISH)
				.add(DRIPSTONE_GARRA)
		;
		tagBuilder(TIER_THREE_CAVE_FISH)
				.add(CAVE_ANGEL_FISH)
				.addOptional(of("toothless_blindcat"))
				.addOptional(fishery("ghostfish"))
		;
		tagBuilder(TIER_THREE_COLD_FRESHWATER_FISH)
				.addOptionalTag(TIER_THREE_COSMOPOLITAN_FRESHWATER_FISH)
				.add(
					WALLEYE.get(),
					WHITE_SUCKER.get())
				.addOptional(fishery("walleye"))
		;
		tagBuilder(TIER_THREE_COLD_SALTWATER_FISH)
				.addOptionalTag(TIER_THREE_COSMOPOLITAN_SALTWATER_FISH)
				.add(
					SPINY_LUMPSUCKER.get(),
					TWOHORN_SCULPIN.get())
				.addOptional(aquaculture("blackfish"))
				.addOptional(fishery("anglerfish"))
				.addOptional(fishofthieves("battlegill"))
				.addOptional(fishofthieves("wrecker"))
		;
		tagBuilder(TIER_THREE_COSMOPOLITAN_FRESHWATER_FISH)
				.add(KOI)
				.addOptional(fishery("largemouth_bass"))
		;
		tagBuilder(TIER_THREE_COSMOPOLITAN_SALTWATER_FISH)
				.add(GREAT_WHITE_SHARK)
		;
		tagBuilder(TIER_THREE_HOT_FRESHWATER_FISH)
				.addOptionalTag(TIER_THREE_COSMOPOLITAN_FRESHWATER_FISH)
				.add(
					ARAPAIMA.get(),
					ROACH.get(),
					PACU.get())
				.addOptional(fishofthieves("stormfish"))
				.addOptional(fishofthieves("wildsplash"))
				.addOptional(fishofthieves("crab_claw"))
				.addOptional(fishofthieves("crayfish"))
		;
		tagBuilder(TIER_THREE_HOT_SALTWATER_FISH)
				.addOptionalTag(TIER_THREE_COSMOPOLITAN_SALTWATER_FISH)
				.add(
					SWORDFISH.get(),
					GROUPER.get(),
					TUNA.get())
				.addOptional(aquaculture("red_grouper"))
				.addOptional(aquaculture("tuna"))
				.addOptional(fishery("tuna"))
				.addOptional(fishofthieves("devilfish"))
		;
		tagBuilder(TIER_THREE_TEMPERATE_FRESHWATER_FISH)
				.addOptionalTag(TIER_THREE_COSMOPOLITAN_FRESHWATER_FISH)
				.add(
					BOWFIN.get(),
					LOACH.get(),
					TUNA.get())
				.addOptional(fishery("branch_eel"))
		;
		tagBuilder(TIER_THREE_TEMPERATE_SALTWATER_FISH)
				.addOptionalTag(TIER_THREE_COSMOPOLITAN_SALTWATER_FISH)
				.add(
					OARFISH.get(),
					HAMMERHEAD_SHARK.get(),
					WHALE_SHARK.get())
				.addOptional(fishofthieves("ancientscale"))
				.addOptional(fishofthieves("devilfish"))
				.addOptional(of("upgrade_aquatic", "lionfish"))
		;

	}

	public class BiggerFishTagBuilder {
		//? if >1.21.2 {
		/*private TagAppender<Item> valueLookupBuilder;
		*///?} else {
		private FabricTagProvider<Item>.FabricTagBuilder valueLookupBuilder;
		//?}

		private TagBuilder rawBuilder;

		public BiggerFishTagBuilder(TagKey<Item> tag) {
			//? if >1.21.2 {
			/*this.valueLookupBuilder = valueLookupBuilder(tag);
			*///?} else {
			this.valueLookupBuilder = getOrCreateTagBuilder(tag);
			//?}

			this.rawBuilder = getOrCreateRawBuilder(tag);
		}

		public BiggerFishTagBuilder add(Item item) {
			valueLookupBuilder = valueLookupBuilder.add(item);
			return this;
		}

		public BiggerFishTagBuilder add(Supplier<Item> item) {
			valueLookupBuilder = valueLookupBuilder.add(item.get());
			return this;
		}

		public BiggerFishTagBuilder addOptionalTag(TagKey<Item> itemTagKey) {
			valueLookupBuilder = valueLookupBuilder.addOptionalTag(itemTagKey);
			return this;
		}

		public BiggerFishTagBuilder add(Item... items) {
			valueLookupBuilder = valueLookupBuilder.add(items);
			return this;
		}

		public BiggerFishTagBuilder addOptional(Identifier item) {
			rawBuilder = rawBuilder.addOptionalElement(item);
			return this;
		}
	}

	private BiggerFishTagBuilder tagBuilder(TagKey<Item> tag) {
		return new BiggerFishTagBuilder(tag);
	}

	private Identifier aquaculture(String id) {
		return of("aquaculture", id);
	}

	private Identifier fishofthieves(String id) {
		return of("fishofthieves", id);
	}

	private Identifier fishery(String id) {
		return of("fishery", id);
	}

	private TagKey<Item> convention(String id) {
		return TagKey.create(Registries.ITEM, of("c", id));
	}

	private TagKey<Item> tag(String id) {
		return TagKey.create(Registries.ITEM, of(id));
	}
}
//?}

