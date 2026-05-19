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
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class FishBarrelBlock extends FishContainerBlock {
	public static final MapCodec<FishBarrelBlock> CODEC = simpleCodec(FishBarrelBlock::new);
	private static final VoxelShape INSIDE_SHAPE = box(2.0F, 4.0F, 2.0F, 14.0F, 16.0F, 14.0F);
	protected static final VoxelShape SHAPE = Shapes.join(Shapes.block(), Shapes.or(box(0.0F, 0.0F, 0.0F, 16.0F, 0.0F, 12.0F), box(4.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F), box(2.0F, 0.0F, 2.0F, 16.0F, 0.0F, 16.0F), INSIDE_SHAPE), BooleanOp.ONLY_FIRST);

	@Override
	public MapCodec<FishBarrelBlock> codec() {
		return CODEC;
	}

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
