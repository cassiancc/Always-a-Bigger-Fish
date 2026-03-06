package cc.cassian.bigger_fish.items;

import cc.cassian.bigger_fish.tooltip.FishContainerTooltip;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;

import java.util.Optional;
import java.util.function.Predicate;

import static net.minecraft.util.FastColor.ARGB32.colorFromFloat;

public class FishContainer {

	static int FULL_BAR_COLOR = colorFromFloat(1.0F, 0.44F, 1.0F, 0.33F);
	static int BAR_COLOR = colorFromFloat(1.0F, 0.44F, 0.53F, 1.0F);

	static boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction action, Player player, Predicate<ItemStack> itemStackPredicate) {
		if (action != ClickAction.SECONDARY) {
			return false;
		} else {
			BundleContents bundleContents = stack.get(DataComponents.BUNDLE_CONTENTS);
			if (bundleContents == null) {
				return false;
			} else {
				ItemStack itemStack = slot.getItem();
				BundleContents.Mutable mutable = new BundleContents.Mutable(bundleContents);
				if (itemStack.isEmpty()) {
					playRemoveOneSound(player);
					ItemStack itemStack2 = mutable.removeOne();
					if (itemStack2 != null) {
						ItemStack itemStack3 = slot.safeInsert(itemStack2);
						mutable.tryInsert(itemStack3);
					}
				} else if (itemStack.getItem().canFitInsideContainerItems() && itemStackPredicate.test(itemStack)) {
					int i = mutable.tryTransfer(slot, player);
					if (i > 0) {
						playInsertSound(player);
					}
				}

				stack.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
				return true;
			}
		}
	}

	static boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access, Predicate<ItemStack> predicate) {
		if (action == ClickAction.SECONDARY && slot.allowModification(player)) {
			BundleContents bundleContents = stack.get(DataComponents.BUNDLE_CONTENTS);
			if (bundleContents == null) {
				return false;
			} else {
				BundleContents.Mutable mutable = new BundleContents.Mutable(bundleContents);
				if (other.isEmpty()) {
					ItemStack itemStack = mutable.removeOne();
					if (itemStack != null) {
						playRemoveOneSound(player);
						access.set(itemStack);
					}
				} else if (predicate.test(other)) {
					int i = mutable.tryInsert(other);
					if (i > 0) {
						playInsertSound(player);
					}
				}

				stack.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
				return true;
			}
		} else {
			return false;
		}
	}


	static Optional<TooltipComponent> getTooltipImage(ItemStack stack, MutableComponent translatable) {
		return Optional.ofNullable(stack.get(DataComponents.BUNDLE_CONTENTS)).map((BundleContents contents) -> new FishContainerTooltip(contents, translatable));
	}

	static boolean isBarVisible(ItemStack stack) {
		BundleContents bundleContents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
		return bundleContents.weight().compareTo(Fraction.ZERO) > 0;
	}

	static int getBarWidth(final ItemStack stack) {
		BundleContents contents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
		return Math.min(1 + Mth.mulAndTruncate(contents.weight(), 12), 13);
	}

	static int getBarColor(ItemStack stack) {
		BundleContents bundleContents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
		return bundleContents.weight().compareTo(Fraction.ONE) >= 0 ? FULL_BAR_COLOR : BAR_COLOR;
	}

	static void onDestroyed(ItemEntity itemEntity) {
		BundleContents bundleContents = itemEntity.getItem().get(DataComponents.BUNDLE_CONTENTS);
		if (bundleContents != null) {
			itemEntity.getItem().set(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
			ItemUtils.onContainerDestroyed(itemEntity, bundleContents.itemsCopy());
		}
	}

	private static void playRemoveOneSound(Entity entity) {
		entity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
	}

	private static void playInsertSound(Entity entity) {
		entity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
	}

	private static void broadcastChangesOnContainerMenu(Player player) {
		AbstractContainerMenu abstractContainerMenu = player.containerMenu;
		if (abstractContainerMenu != null) {
			abstractContainerMenu.slotsChanged(player.getInventory());
		}
	}

	private static boolean isPrimary(ClickAction action) {
		return action == ClickAction.PRIMARY;
	}

	private static boolean isSecondary(ClickAction action) {
		return action == ClickAction.SECONDARY;
	}
}
