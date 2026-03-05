package cc.cassian.bigger_fish.blocks;

import cc.cassian.bigger_fish.blocks.entity.FishBarrelBlockEntity;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class FishBarrelBlock extends FishContainerBlock {
	public static final MapCodec<FishBarrelBlock> CODEC = simpleCodec(FishBarrelBlock::new);
	private static final int HOLE_WIDTH = 12;
	private static final VoxelShape SHAPE = Block.column(HOLE_WIDTH, Math.clamp(1, 2, 16), 16.0);

	@Override
	public MapCodec<FishBarrelBlock> codec() {
		return CODEC;
	}

	public FishBarrelBlock(final Properties properties) {
		super(properties, (stack -> stack.is(BiggerFishTags.FISH)));
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
