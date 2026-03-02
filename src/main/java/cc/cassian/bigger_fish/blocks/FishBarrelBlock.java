package cc.cassian.bigger_fish.blocks;

import cc.cassian.bigger_fish.CommonEvents;
import cc.cassian.bigger_fish.blocks.entity.FishBarrelBlockEntity;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class FishBarrelBlock extends Block implements EntityBlock {
	public static final MapCodec<FishBarrelBlock> CODEC = simpleCodec(FishBarrelBlock::new);
	private static final int HOLE_WIDTH = 12;
	private static final VoxelShape SHAPE = Block.column(HOLE_WIDTH, Math.clamp(1, 2, 16), 16.0);

	@Override
	public MapCodec<FishBarrelBlock> codec() {
		return CODEC;
	}

	public FishBarrelBlock(final Properties properties) {
		super(properties);
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
	protected InteractionResult useItemOn(
			ItemStack itemStack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult
	) {
		if (itemStack.isEmpty()) return useWithoutItem(blockState, level, pos, player, blockHitResult);
		if (level.getBlockEntity(pos) instanceof FishBarrelBlockEntity cauldronBlockEntity && itemStack.is(BiggerFishTags.FISH)) {
			return cauldronBlockEntity.insert(itemStack);
		}
		return InteractionResult.PASS;
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos pos, Player player, BlockHitResult blockHitResult) {
		if (level.getBlockEntity(pos) instanceof FishBarrelBlockEntity cauldronBlockEntity) {
			if (!cauldronBlockEntity.isEmpty()) {
				CommonEvents.giveToPlayer(player, null, level, pos, blockHitResult.getDirection(), cauldronBlockEntity.retrieve());
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.SUCCESS_SERVER;
	}


	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new FishBarrelBlockEntity(blockPos, blockState);
	}
}
