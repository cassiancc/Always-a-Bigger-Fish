package cc.cassian.bigger_fish.blocks.entity;

import cc.cassian.bigger_fish.registry.BiggerFishBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;


public class FishTrapBlockEntity extends FishContainerBlockEntity {

	public FishTrapBlockEntity(BlockPos pos, BlockState state) {
		super(BiggerFishBlockEntityTypes.FISH_TRAP_BLOCK_ENTITY, pos, state);
	}
}