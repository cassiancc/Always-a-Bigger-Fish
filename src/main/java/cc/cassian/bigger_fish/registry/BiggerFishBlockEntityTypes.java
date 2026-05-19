package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.blocks.entity.FishBarrelBlockEntity;
import cc.cassian.bigger_fish.blocks.entity.FishTrapBlockEntity;
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
			Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, BiggerFishMod.of("fish_barrel_block_entity"),
					BlockEntityType.Builder.of(FishBarrelBlockEntity::new,
							BiggerFishBlocks.FISH_BARREL).build(null)
			);

	public static final BlockEntityType<FishTrapBlockEntity> FISH_TRAP_BLOCK_ENTITY =
			Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, BiggerFishMod.of("fish_trap_block_entity"),
					BlockEntityType.Builder.of(FishTrapBlockEntity::new,
							BiggerFishBlocks.FISH_TRAP).build(null)
			);

	public static void touch() {

	}
}