package cc.cassian.bigger_fish.datagen;

//? fabric {

import cc.cassian.bigger_fish.registry.BiggerFishBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BiggerFishBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
	public BiggerFishBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, registryLookupFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE).add(BiggerFishBlocks.FISH_BARREL).add(BiggerFishBlocks.FISH_TRAP);
		valueLookupBuilder(ConventionalBlockTags.WOODEN_BARRELS).add(BiggerFishBlocks.FISH_BARREL);
	}
}

//?}