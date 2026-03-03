package cc.cassian.bigger_fish.blocks;

import cc.cassian.bigger_fish.CommonEvents;
import cc.cassian.bigger_fish.blocks.entity.FishBarrelBlockEntity;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

import java.util.List;

import static net.minecraft.world.level.block.ShulkerBoxBlock.CONTENTS;

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
		return InteractionResult.PASS;
	}


	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new FishBarrelBlockEntity(blockPos, blockState);
	}

	@Override
	public BlockState playerWillDestroy(final Level level, final BlockPos pos, final BlockState state, final Player player) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (blockEntity instanceof FishBarrelBlockEntity fishBarrelBlockEntity) {
			if (!level.isClientSide() && player.preventsBlockDrops() && !fishBarrelBlockEntity.isEmpty()) {
				ItemStack itemStack = new ItemStack(state.getBlock());
				itemStack.applyComponents(blockEntity.collectComponents());
				ItemEntity entity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, itemStack);
				entity.setDefaultPickUpDelay();
				level.addFreshEntity(entity);
			} else {
//				fishBarrelBlockEntity.unpackLootTable(player);
			}
		}

		return super.playerWillDestroy(level, pos, state, player);
	}

	@Override
	protected List<ItemStack> getDrops(final BlockState state, net.minecraft.world.level.storage.loot.LootParams.Builder params) {
		BlockEntity blockEntity = params.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
		if (blockEntity instanceof FishBarrelBlockEntity fishBarrelBlockEntity) {
			params = params.withDynamicDrop(CONTENTS, output -> {
				for (int i = 0; i < fishBarrelBlockEntity.getContainerSize(); i++) {
					output.accept(fishBarrelBlockEntity.getItem(i));
				}
			});
		}

		return super.getDrops(state, params);
	}

	@Override
	protected void affectNeighborsAfterRemoval(final BlockState state, final ServerLevel level, final BlockPos pos, final boolean movedByPiston) {
		Containers.updateNeighboursAfterDestroy(state, level, pos);
	}
}
