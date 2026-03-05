package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.blocks.FishBarrelBlock;
import cc.cassian.bigger_fish.blocks.FishTrapBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class BiggerFishBlocks {
	public static final Block FISH_BARREL = register("fish_barrel", FishBarrelBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL));
	public static final Block FISH_TRAP = register("fish_trap", FishTrapBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL).randomTicks().noOcclusion());


	private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings) {
		// Create a registry key for the block
		ResourceKey<Block> blockKey = key(name);
		// Create the block instance
		Block block = blockFactory.apply(settings.setId(blockKey));

		return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
	}

	private static ResourceKey<Block> key(String name) {
		return ResourceKey.create(Registries.BLOCK, BiggerFishMod.of(name));
	}

	public static void touch() {

	}
}
