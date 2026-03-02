package cc.cassian.bigger_fish.blocks.entity;

import cc.cassian.bigger_fish.registry.BiggerFishBlockEntityTypes;
import net.minecraft.core.*;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
import oshi.util.tuples.Pair;

import java.util.*;


public class FishBarrelBlockEntity extends BlockEntity implements WorldlyContainer {

	public static final int CAPACITY = 64;
	private final ArrayList<ItemStack> items = new ArrayList<>(CAPACITY);

	public FishBarrelBlockEntity(BlockPos pos, BlockState state) {
		super(BiggerFishBlockEntityTypes.FISH_BARREL_BLOCK_ENTITY, pos, state);
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
		Optional<List<ItemStack>> inventory = tag.read("barrel.inventory", ItemStack.CODEC.listOf());
		items.clear();
		inventory.ifPresent(items::addAll);
	}

	@Override
	public void saveAdditional(ValueOutput tag) {
		if (!items.isEmpty()) {
			items.removeIf(ItemStack::isEmpty);
			tag.store("barrel.inventory", ItemStack.CODEC.listOf(), items);
		} else {
			tag.store("barrel.inventory", ItemStack.CODEC.listOf(), List.of());
		}
		super.saveAdditional(tag);
	}

	@Override
	protected void applyImplicitComponents(DataComponentGetter components) {
		components.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).items().forEach(s->{
			ItemStack itemStack = s.create();
			for (int i = 0; i < itemStack.getCount(); i++) {
				this.items.add(itemStack.copyWithCount(1));
			}
		});
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
	 * Checks if there are no items in this Barrel.
	 */
	@Override
	public boolean isEmpty() {
		return items.isEmpty();
	}

	/**
	 * Checks if there is space to put items in this Barrel.
	 */
	public boolean hasSpace(int size) {
		return (items.size() + size) < getContainerSize();
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
}