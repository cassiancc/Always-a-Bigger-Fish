package cc.cassian.bigger_fish.datagen;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.KelpBlock;

import java.util.concurrent.CompletableFuture;

import static cc.cassian.bigger_fish.registry.BiggerFishItems.*;
import static net.minecraft.world.item.Items.KELP;
import static net.minecraft.world.item.Items.TROPICAL_FISH;

public class BiggerFishRecipeProvider extends FabricRecipeProvider {
	public BiggerFishRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
		return new RecipeProvider(registryLookup, exporter) {
			@Override
			public void buildRecipes() {
				HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

				shapeless(RecipeCategory.FOOD, SUSHI)
						.requires(Items.DRIED_KELP)
						.requires(ConventionalItemTags.RAW_FISH_FOODS)
						.unlockedBy(getHasName(Items.DRIED_KELP), has(Items.DRIED_KELP))
						.save(output);

				shapeless(RecipeCategory.MISC, Items.BONE_MEAL)
						.requires(FISH_BONES)
						.unlockedBy(getHasName(FISH_BONES), has(FISH_BONES))
						.save(output, "bone_meal_from_fish_bones");

				foodSmelting(
						Ingredient.of(itemLookup.getOrThrow(BiggerFishTags.FISH)),
						FRIED_FISH,
						0.35F, 200,
						"fried_fish");
				foodSmelting(Ingredient.of(TROPICAL_FISH),
						FRIED_FISH,
						0.35F, 200,
						"tropical_fish");

				shapeless(RecipeCategory.MISC, CANNED_FISH)
					.requires(ConventionalItemTags.COOKED_FISH_FOODS)
					.requires(CAN)
					.unlockedBy(getHasName(CAN), has(CAN))
					.save(output);

				SimpleCookingRecipeBuilder.smelting(
						Ingredient.of(CAN),
						RecipeCategory.MISC,
						CookingBookCategory.MISC,
						Items.IRON_NUGGET,
						0.3F, 200)
						.unlockedBy(getHasName(CAN), this.has(CAN))
						.save(output, "iron_nugget_from_can");
				SimpleCookingRecipeBuilder.blasting(
						Ingredient.of(CAN),
						RecipeCategory.MISC,
						CookingBookCategory.MISC,
						Items.IRON_NUGGET,
						0.3F, 200)
						.unlockedBy(getHasName(CAN), this.has(CAN))
						.save(output, "iron_nugget_from_can_blasting");
				this.shaped(RecipeCategory.TOOLS, FISH_BARREL, 1)
						.define('P', ItemTags.PLANKS)
						.define('S', ItemTags.WOODEN_SLABS)
						.pattern("P P")
						.pattern("P P")
						.pattern("PSP")
						.unlockedBy("has_planks", this.has(ItemTags.PLANKS))
						.unlockedBy("has_wood_slab", this.has(ItemTags.WOODEN_SLABS))
						.save(this.output);

				this.shaped(RecipeCategory.TOOLS, FISH_TRAP, 1)
						.define('P', ConventionalItemTags.WOODEN_RODS)
						.define('C', Items.COBWEB)
						.define('S', ItemTags.WOODEN_SLABS)
						.pattern("P P")
						.pattern("PCP")
						.pattern("PSP")
						.unlockedBy("has_cobweb", this.has(Items.COBWEB))
						.save(this.output);

				this.shaped(RecipeCategory.TOOLS, COPPER_ROD)
						.define('#', Items.COPPER_INGOT)
						.define('X', Items.STRING)
						.pattern("  #")
						.pattern(" #X")
						.pattern("# X")
						.unlockedBy(getHasName(Items.STRING), this.has(Items.FISHING_ROD))
						.save(this.output);

				this.shaped(RecipeCategory.TOOLS, DIAMOND_HOOK)
						.define('C', Items.DIAMOND)
						.pattern(" C")
						.pattern("CC")
						.unlockedBy(getHasName(Items.DIAMOND), this.has(Items.DIAMOND))
						.save(this.output);

				this.shaped(RecipeCategory.TOOLS, NETHERITE_HOOK)
						.define('C', Items.NETHERITE_SCRAP)
						.pattern(" C")
						.pattern("CC")
						.unlockedBy(getHasName(Items.NETHERITE_SCRAP), this.has(Items.NETHERITE_SCRAP))
						.save(this.output);

				this.shapeless(RecipeCategory.FOOD, FISH_KEBAB)
						.requires(Items.STICK)
						.requires(ConventionalItemTags.COOKED_FISH_FOODS)
						.unlockedBy("has_fish", this.has(ConventionalItemTags.COOKED_FISH_FOODS))
						.save(this.output);

				this.shapeless(RecipeCategory.FOOD, FISH_STEW)
						.requires(Items.BOWL)
						.requires(Items.CARROT)
						.requires(Items.POTATO)
						.requires(ConventionalItemTags.COOKED_FISH_FOODS)
						.unlockedBy("has_fish", this.has(ConventionalItemTags.COOKED_FISH_FOODS))
						.save(this.output);

			}

			private void foodSmelting(Ingredient ingredient, Item result, float experience, int cookingTime, String s) {
				SimpleCookingRecipeBuilder.smelting(ingredient, RecipeCategory.FOOD, CookingBookCategory.FOOD, result, experience, cookingTime).unlockedBy("has_fish", this.has(BiggerFishTags.FISH)).save(this.output, s);
				SimpleCookingRecipeBuilder.campfireCooking(ingredient, RecipeCategory.FOOD, result, experience, cookingTime).unlockedBy("has_fish", this.has(BiggerFishTags.FISH)).save(this.output, s +"_campfire_cooking");
				SimpleCookingRecipeBuilder.smoking(ingredient, RecipeCategory.FOOD, result, experience, cookingTime).unlockedBy("has_fish", this.has(BiggerFishTags.FISH)).save(this.output, s+"_smoking");
			}
		};
	}

	@Override
	public String getName() {
		return "Always a Bigger Fish";
	}
}
