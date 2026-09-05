package cc.cassian.bigger_fish.blocks;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.Platform;
import cc.cassian.bigger_fish.blocks.entity.FishTrapBlockEntity;
import cc.cassian.bigger_fish.helpers.ModHelpers;
import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class FishTrapBlock extends FishContainerBlock implements SimpleWaterloggedBlock {
	private static final VoxelShape SHAPE_INSIDE = Block.column(12.0, 4.0, 16.0);
	protected static final VoxelShape SHAPE = Util.make(
			() -> Shapes.join(
					Shapes.block(),
					Shapes.or(Block.column(16.0, 8.0, 0.0, 0.0), Block.column(8.0, 16.0, 0.0, 0.0), Block.column(16.0, 0.0, 0.0), SHAPE_INSIDE),
					BooleanOp.ONLY_FIRST
			)
	);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private int nearbyBlocks = 0;
	private int nearbyWaterBlocks = 0;

	public FishTrapBlock(final Properties properties) {
		super(properties, (_)->false);
		this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false).setValue(BOOP, false));
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
	public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new FishTrapBlockEntity(blockPos, blockState);
	}

	@Override
	protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (level.getBlockEntity(pos) instanceof FishTrapBlockEntity fishTrapBlockEntity && state.getFluidState().is(Fluids.WATER)) {
			recountWaterBlocks(level, pos);
			float nextRandom = random.nextFloat();
			float chanceToContinue = (float) this.nearbyWaterBlocks / this.nearbyBlocks;
			boolean willContinue = nextRandom <= chanceToContinue;
			if (!willContinue) return;

			LootTable lootTable = ModHelpers.fish(level.getServer().reloadableRegistries(), ItemStack.EMPTY, false, true);
			if (lootTable == null) return;
			LootParams params = new LootParams.Builder(level)
					.withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
					.withParameter(LootContextParams.TOOL, this.asItem().getDefaultInstance())
					.withLuck(0)
					.create(LootContextParamSets.FISHING);
			ObjectArrayList<ItemStack> randomItems = lootTable.getRandomItems(params);
			randomItems.forEach(itemStack -> fishTrapBlockEntity.insert(ModHelpers.setRandomFishSize(itemStack, level.getRandom())));
		}

	}

	private void recountWaterBlocks(Level level, BlockPos pos) {
		AtomicInteger blocks = new AtomicInteger();
		AtomicInteger waterBlocks = new AtomicInteger();
		Stream<BlockState> blockStates = level.getBlockStates(new AABB(Vec3.atCenterOf(pos.above().east().north()), Vec3.atCenterOf(pos.below().west().south())));
		blockStates.forEach(blockState -> {
			blocks.getAndIncrement();
			if (blockState.getFluidState().is(Fluids.WATER)) {
				waterBlocks.getAndIncrement();
			}
		});
		this.nearbyBlocks = blocks.get();
		this.nearbyWaterBlocks = waterBlocks.get();
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(final BlockPlaceContext context) {
		FluidState replacedFluidState = context.getLevel().getFluidState(context.getClickedPos());
		return super.getStateForPlacement(context).setValue(WATERLOGGED, replacedFluidState.is(Fluids.WATER));
	}

	@Override
	protected BlockState updateShape(
			final BlockState state,
			final LevelReader level,
			final ScheduledTickAccess ticks,
			final BlockPos pos,
			final Direction directionToNeighbour,
			final BlockPos neighbourPos,
			final BlockState neighbourState,
			final RandomSource random
	) {
		if (state.getValue(WATERLOGGED)) {
			ticks.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		return super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
	}

	@Override
	protected FluidState getFluidState(final BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(true) : super.getFluidState(state);
	}

	@Override
	protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED).add(BOOP);
	}
}
