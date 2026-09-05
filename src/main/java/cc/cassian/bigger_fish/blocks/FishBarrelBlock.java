package cc.cassian.bigger_fish.blocks;

import cc.cassian.bigger_fish.blocks.entity.FishBarrelBlockEntity;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Util;
import net.minecraft.world.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class FishBarrelBlock extends FishContainerBlock {
	private static final VoxelShape SHAPE_INSIDE = Block.column(12.0, 4.0, 16.0);
	protected static final VoxelShape SHAPE = Util.make(
			() -> Shapes.join(
					Shapes.block(),
					Shapes.or(Block.column(16.0, 8.0, 0.0, 0.0), Block.column(8.0, 16.0, 0.0, 0.0), Block.column(16.0, 0.0, 0.0), SHAPE_INSIDE),
					BooleanOp.ONLY_FIRST
			)
	);

	public FishBarrelBlock(final Properties properties) {
		super(properties, (stack -> stack.is(BiggerFishTags.ALLOWED_IN_FISH_BARREL)));
	}

	@Override
	protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected VoxelShape getInteractionShape(final BlockState state, final BlockGetter level, final BlockPos pos) {
		return Shapes.block();
	}

	@Override
	protected VoxelShape getCollisionShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
		return SHAPE;
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
		return new FishBarrelBlockEntity(worldPosition, blockState);
	}
}
