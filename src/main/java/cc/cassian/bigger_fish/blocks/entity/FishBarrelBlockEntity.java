package cc.cassian.bigger_fish.blocks.entity;

import cc.cassian.bigger_fish.registry.BiggerFishBlockEntityTypes;
import net.minecraft.core.*;
import net.minecraft.world.level.block.state.BlockState;


public class FishBarrelBlockEntity extends FishContainerBlockEntity {
	public FishBarrelBlockEntity(BlockPos pos, BlockState state) {
		super(BiggerFishBlockEntityTypes.FISH_BARREL_BLOCK_ENTITY, pos, state);
	}
}