package cc.cassian.bigger_fish.items;

import cc.cassian.bigger_fish.tooltip.FishContainerTooltip;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.item.component.TooltipDisplay;
import org.apache.commons.lang3.math.Fraction;

import java.util.Optional;
import java.util.function.Predicate;

import static cc.cassian.bigger_fish.mixin.BundleItemAccessor.invokeGetWeightSafe;
import static net.minecraft.util.ARGB.colorFromFloat;

public class FishContainer {

	static int FULL_BAR_COLOR = colorFromFloat(1.0F, 0.44F, 1.0F, 0.33F);
	static int BAR_COLOR = colorFromFloat(1.0F, 0.44F, 0.53F, 1.0F);

	static boolean overrideStackedOnOther(ItemStack rod, Slot slot, ClickAction action, Player player, Predicate<ItemStack> stackPredicate) {
		BundleContents bundleContents = rod.get(DataComponents.BUNDLE_CONTENTS);
		if (bundleContents == null) {
			return false;
		} else {
			ItemStack other = slot.getItem();
			BundleContents.Mutable mutable = new BundleContents.Mutable(bundleContents);
			if (isPrimary(action) && stackPredicate.test(other)) {
				if (mutable.tryTransfer(slot, player) > 0) {
					playInsertSound(player);
				} else {
					playInsertFailSound(player);
				}

				rod.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
				broadcastChangesOnContainerMenu(player);
				return true;
			} else if (isSecondary(action) && other.isEmpty()) {
				ItemStack itemStack2 = mutable.removeOne();
				if (itemStack2 != null) {
					ItemStack itemStack3 = slot.safeInsert(itemStack2);
					if (itemStack3.getCount() > 0) {
						mutable.tryInsert(itemStack3);
					} else {
						playRemoveOneSound(player);
					}
				}

				rod.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
				broadcastChangesOnContainerMenu(player);
				return true;
			} else {
				return false;
			}
		}
	}

	static boolean overrideOtherStackedOnMe(ItemStack rod, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access, Predicate<ItemStack> stackPredicate) {
		if (isPrimary(action) && other.isEmpty()) {
			toggleSelectedItem(rod, -1);
			return false;
		} else {
			BundleContents bundleContents = rod.get(DataComponents.BUNDLE_CONTENTS);
			if (bundleContents == null) {
				return false;
			} else {
				BundleContents.Mutable mutable = new BundleContents.Mutable(bundleContents);
				if (isPrimary(action) && stackPredicate.test(other)) {
					if (slot.allowModification(player) && mutable.tryInsert(other) > 0) {
						playInsertSound(player);
					} else {
						playInsertFailSound(player);
					}

					rod.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
					broadcastChangesOnContainerMenu(player);
					return true;
				} else if (isSecondary(action) && other.isEmpty()) {
					if (slot.allowModification(player)) {
						ItemStack itemStack = mutable.removeOne();
						if (itemStack != null) {
							playRemoveOneSound(player);
							access.set(itemStack);
						}
					}

					rod.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
					broadcastChangesOnContainerMenu(player);
					return true;
				} else {
					toggleSelectedItem(rod, -1);
					return false;
				}
			}
		}
	}

	static void toggleSelectedItem(ItemStack bundle, int selectedItem) {
		BundleContents bundleContents = bundle.get(DataComponents.BUNDLE_CONTENTS);
		if (bundleContents != null) {
			BundleContents.Mutable mutable = new BundleContents.Mutable(bundleContents);
			mutable.toggleSelectedItem(selectedItem);
			bundle.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
		}
	}

	static Optional<TooltipComponent> getTooltipImage(ItemStack stack, MutableComponent translatable) {
		TooltipDisplay tooltipDisplay = stack.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT);
		return !tooltipDisplay.shows(DataComponents.BUNDLE_CONTENTS)
				? Optional.empty()
				: Optional.ofNullable(stack.get(DataComponents.BUNDLE_CONTENTS)).map((BundleContents contents) -> new FishContainerTooltip(contents, translatable));
	}

	private static void playInsertFailSound(Entity entity) {
		entity.playSound(SoundEvents.BUNDLE_INSERT_FAIL, 1.0F, 1.0F);
	}

	static boolean isBarVisible(ItemStack stack) {
		BundleContents bundleContents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
		return bundleContents.weight().getOrThrow().compareTo(Fraction.ZERO) > 0;
	}

	static int getBarWidth(final ItemStack stack) {
		BundleContents contents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
		return Math.min(1 + Mth.mulAndTruncate(invokeGetWeightSafe(contents), 12), 13);
	}

	static int getBarColor(ItemStack stack) {
		BundleContents bundleContents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
		return invokeGetWeightSafe(bundleContents).compareTo(Fraction.ONE) >= 0 ? FULL_BAR_COLOR : BAR_COLOR;
	}

	static void onDestroyed(final ItemEntity entity) {
		BundleContents contents = entity.getItem().get(DataComponents.BUNDLE_CONTENTS);
		if (contents != null) {
			entity.getItem().set(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
			ItemUtils.onContainerDestroyed(entity, contents.itemCopyStream());
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
