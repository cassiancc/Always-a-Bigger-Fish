package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.blocks.entity.FishBarrelBlockEntity;
//? fabric {
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
//?}
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Set;
import java.util.function.Supplier;

public class BiggerFishBlockEntityTypes {
	public static final BlockEntityType<FishBarrelBlockEntity> FISH_BARREL_BLOCK_ENTITY =
			Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, BiggerFishMod.of("cauldron_block_entity"),
					//? if fabric {
					FabricBlockEntityTypeBuilder.create(FishBarrelBlockEntity::new,
							BiggerFishBlocks.FISH_BARREL).build()
					//?} else {
                    /*new BlockEntityType<>(FishBarrelBlockEntity::new,
                            BiggerFishBlocks.FISH_BARREL)
                    *///?}
			);

	public static void touch() {

	}
}