package cc.cassian.bigger_fish.blocks.entity;

import cc.cassian.bigger_fish.blocks.FishContainerBlock;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public abstract class FishContainerBlockEntity extends BlockEntity implements WorldlyContainer {

	public static final int CAPACITY = 64;
	private final ArrayList<ItemStack> items = new ArrayList<>(CAPACITY);
	@Override
	public void setChanged() {
		if (level != null)
			level.setBlockAndUpdate(worldPosition, getBlockState().cycle(FishContainerBlock.BOOP));
		super.setChanged();
	}

	public FishContainerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Override
	@Nullable
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		return saveWithoutMetadata(registries);
	}

	@Override
	protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		Optional<List<ItemStack>> inventory = read(tag, registries, "inventory", ItemStack.CODEC.listOf());
		items.clear();
		inventory.ifPresent(items::addAll);
	}

	private Optional<List<ItemStack>> read(CompoundTag tag, HolderLookup.Provider registries, String inventory, Codec<List<ItemStack>> listCodec) {
		var c = listCodec.decode(registries.createSerializationContext(NbtOps.INSTANCE), tag.get(inventory));
		if (c.result().isPresent()) {
			return Optional.of(c.getOrThrow().getFirst());
		} else return Optional.empty();
	}

	private void store(CompoundTag tag, HolderLookup.Provider registries, String inventory, Codec<List<ItemStack>> listCodec, List<ItemStack> items) {
		tag.put(inventory, listCodec.encodeStart(registries.createSerializationContext(NbtOps.INSTANCE), items).getOrThrow());
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		if (!items.isEmpty()) {
			items.removeIf(ItemStack::isEmpty);
			store(tag, registries, "inventory", ItemStack.CODEC.listOf(), items);
		} else {
			store(tag, registries, "inventory", ItemStack.CODEC.listOf(), List.of());
		}
		super.saveAdditional(tag, registries);
	}

	@Override
	protected void applyImplicitComponents(DataComponentInput components) {
		this.items.clear();
		components.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).items().forEach(itemStack->{
			for (int i = 0; i < itemStack.getCount(); i++) {
				this.items.add(itemStack.copyWithCount(1));
			}
		});
		setChanged();
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder components) {
		BundleContents.Mutable mutable = new BundleContents.Mutable(BundleContents.EMPTY);
		for (ItemStack item : items) {
			mutable.tryInsert(item.copy());
		}
		components.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
	}

	public ItemInteractionResult insert(ItemStack itemStack) {
		// insert as inventory
		if (hasSpace(itemStack.getCount())) {
			int i;
			for (i = 0; i < itemStack.getCount(); i++) {
				items.add(itemStack.copyWithCount(1));
			}

			itemStack.setCount(itemStack.getCount() - i);
			setChanged();
			return ItemInteractionResult.SUCCESS;
		}
		return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	}

	public ItemStack retrieve() {
		return items.removeFirst();
	}

	public List<ItemStack> getItems() {
		return items;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return new int[CAPACITY];
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) {
		return false;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return index <= items.size() - 1;
	}

	@Override
	public int getContainerSize() {
		return CAPACITY;
	}

	/**
	 * Checks if there are no items in this Trap.
	 */
	@Override
	public boolean isEmpty() {
		return items.isEmpty();
	}

	/**
	 * Checks if there is space to put items in this Trap.
	 */
	public boolean hasSpace(int size) {
		return (items.size() + size) <= getContainerSize();
	}

	@Override
	public ItemStack getItem(int slot) {
		if (items.size()>slot)
			return items.get(slot);
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		ItemStack remove1 = items.remove(slot);
		setChanged();
		return remove1;
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return items.remove(slot);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		items.set(slot, stack);
	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

	@Override
	public void clearContent() {
		items.clear();
	}
}