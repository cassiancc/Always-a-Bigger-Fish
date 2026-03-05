package cc.cassian.bigger_fish.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public abstract class FishContainerBlockEntity extends BlockEntity implements WorldlyContainer {

	public static final int CAPACITY = 64;
	private final ArrayList<ItemStack> items = new ArrayList<>(CAPACITY);

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
	public void loadAdditional(ValueInput tag) {
		super.loadAdditional(tag);
		Optional<List<ItemStack>> inventory = tag.read("inventory", ItemStack.CODEC.listOf());
		items.clear();
		inventory.ifPresent(items::addAll);
	}

	@Override
	public void saveAdditional(ValueOutput tag) {
		if (!items.isEmpty()) {
			items.removeIf(ItemStack::isEmpty);
			tag.store("inventory", ItemStack.CODEC.listOf(), items);
		} else {
			tag.store("inventory", ItemStack.CODEC.listOf(), List.of());
		}
		super.saveAdditional(tag);
	}

	@Override
	protected void applyImplicitComponents(DataComponentGetter components) {
		this.items.clear();
		components.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).items().forEach(s->{
			ItemStack itemStack = s.create();
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

	public InteractionResult insert(ItemStack itemStack) {
		// insert as inventory
		if (hasSpace(itemStack.getCount())) {
			int i;
			for (i = 0; i < itemStack.getCount(); i++) {
				items.add(itemStack.copyWithCount(1));
			}

			itemStack.setCount(itemStack.getCount() - i);
			setChanged();
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
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
		return true;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return true;
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
		return items.remove(slot);
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

	@Override
	public void preRemoveSideEffects(BlockPos pos, BlockState state) {

	}
}