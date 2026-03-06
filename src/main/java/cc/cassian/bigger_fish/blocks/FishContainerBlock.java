package cc.cassian.bigger_fish.blocks;

import cc.cassian.bigger_fish.CommonEvents;
import cc.cassian.bigger_fish.blocks.entity.FishContainerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;
import java.util.function.Predicate;

import static net.minecraft.world.level.block.ShulkerBoxBlock.CONTENTS;

public abstract class FishContainerBlock extends Block implements EntityBlock {

	public Predicate<ItemStack> allowedItems;
	public static final BooleanProperty BOOP = BooleanProperty.create("boop");

	public FishContainerBlock(final Properties properties, Predicate<ItemStack> allowedItems) {
		super(properties);
		this.allowedItems = allowedItems;
		this.registerDefaultState(this.defaultBlockState().setValue(BOOP, false));
	}

	@Override
	protected ItemInteractionResult useItemOn(
			ItemStack itemStack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult
	) {
		if (itemStack.isEmpty() && interactionHand.equals(InteractionHand.MAIN_HAND)) return mapResult(useWithoutItem(blockState, level, pos, player, blockHitResult));
		if (level.getBlockEntity(pos) instanceof FishContainerBlockEntity fishContainerBlockEntity) {
			if (allowedItems.test(itemStack))
				return fishContainerBlockEntity.insert(itemStack);
			else if (interactionHand.equals(InteractionHand.MAIN_HAND)) return mapResult(useWithoutItem(blockState, level, pos, player, blockHitResult));
		}
		return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	}

	private ItemInteractionResult mapResult(InteractionResult interactionResult) {
		return switch (interactionResult) {
			case SUCCESS, SUCCESS_NO_ITEM_USED -> ItemInteractionResult.SUCCESS;
			case CONSUME, CONSUME_PARTIAL -> ItemInteractionResult.CONSUME;
			case PASS -> ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
			case FAIL -> ItemInteractionResult.FAIL;
		};
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos pos, Player player, BlockHitResult blockHitResult) {
		if (level.getBlockEntity(pos) instanceof FishContainerBlockEntity fishContainer) {
			if (!fishContainer.isEmpty()) {
				CommonEvents.giveToPlayer(player, null, level, pos, blockHitResult.getDirection(), fishContainer.retrieve());
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.PASS;
	}

	@Override
	public BlockState playerWillDestroy(final Level level, final BlockPos pos, final BlockState state, final Player player) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (blockEntity instanceof FishContainerBlockEntity fishContainerBlockEntity) {
			if (!level.isClientSide() && !fishContainerBlockEntity.isEmpty()) {
				ItemStack itemStack = new ItemStack(state.getBlock());
				itemStack.applyComponents(blockEntity.collectComponents());
				ItemEntity entity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, itemStack);
				entity.setDefaultPickUpDelay();
				level.addFreshEntity(entity);
			} else {
//				fishContainerBlockEntity.unpackLootTable(player);
			}
		}

		return super.playerWillDestroy(level, pos, state, player);
	}

	@Override
	protected List<ItemStack> getDrops(final BlockState state, net.minecraft.world.level.storage.loot.LootParams.Builder params) {
		BlockEntity blockEntity = params.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
		if (blockEntity instanceof FishContainerBlockEntity containerBlockEntity) {
			params = params.withDynamicDrop(CONTENTS, output -> {
				for (int i = 0; i < containerBlockEntity.getContainerSize(); i++) {
					output.accept(containerBlockEntity.getItem(i));
				}
			});
		}

		return super.getDrops(state, params);
	}

	@Override
	protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
		Containers.dropContentsOnDestroy(state, newState, level, pos);
	}

	@Override
	protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BOOP);
	}
}
