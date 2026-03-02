package cc.cassian.bigger_fish.items;

import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.mixin.BundleItemAccessor;
import cc.cassian.bigger_fish.tooltip.BaitedRodTooltip;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import static net.minecraft.util.ARGB.colorFromFloat;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BundleContents;

import org.apache.commons.lang3.math.Fraction;

import java.util.Optional;

public class BaitedRodItem extends FishingRodItem {
    public BaitedRodItem(Properties properties) {
        super(properties);
    }

    private static final int FULL_BAR_COLOR = colorFromFloat(1.0F, 0.44F, 1.0F, 0.33F);
    private static final int BAR_COLOR = colorFromFloat(1.0F, 0.44F, 0.53F, 1.0F);

    @Override
    public boolean overrideStackedOnOther(ItemStack rod, Slot slot, ClickAction action, Player player) {
        BundleContents bundleContents = rod.get(DataComponents.BUNDLE_CONTENTS);
        if (bundleContents == null) {
            return false;
        } else {
            ItemStack other = slot.getItem();
            BundleContents.Mutable mutable = new BundleContents.Mutable(bundleContents);
            if (action == ClickAction.PRIMARY && ModHelpers.isAllowedInBaitedRod(other)) {
                if (mutable.tryTransfer(slot, player) > 0) {
                    playInsertSound(player);
                } else {
                    playInsertFailSound(player);
                }

                rod.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
                this.broadcastChangesOnContainerMenu(player);
                return true;
            } else if (action == ClickAction.SECONDARY && other.isEmpty()) {
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
                this.broadcastChangesOnContainerMenu(player);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack rod, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        if (action == ClickAction.PRIMARY && other.isEmpty()) {
            toggleSelectedItem(rod, -1);
            return false;
        } else {
            BundleContents bundleContents = rod.get(DataComponents.BUNDLE_CONTENTS);
            if (bundleContents == null) {
                return false;
            } else {
                BundleContents.Mutable mutable = new BundleContents.Mutable(bundleContents);
                if (action == ClickAction.PRIMARY && ModHelpers.isAllowedInBaitedRod(other)) {
                    if (slot.allowModification(player) && mutable.tryInsert(other) > 0) {
                        playInsertSound(player);
                    } else {
                        playInsertFailSound(player);
                    }

                    rod.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
                    this.broadcastChangesOnContainerMenu(player);
                    return true;
                } else if (action == ClickAction.SECONDARY && other.isEmpty()) {
                    if (slot.allowModification(player)) {
                        ItemStack itemStack = mutable.removeOne();
                        if (itemStack != null) {
                            playRemoveOneSound(player);
                            access.set(itemStack);
                        }
                    }

                    rod.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
                    this.broadcastChangesOnContainerMenu(player);
                    return true;
                } else {
                    toggleSelectedItem(rod, -1);
                    return false;
                }
            }
        }
    }
    public static void toggleSelectedItem(ItemStack bundle, int selectedItem) {
        BundleContents bundleContents = bundle.get(DataComponents.BUNDLE_CONTENTS);
        if (bundleContents != null) {
            BundleContents.Mutable mutable = new BundleContents.Mutable(bundleContents);
            mutable.toggleSelectedItem(selectedItem);
            bundle.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
        }
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        TooltipDisplay tooltipDisplay = stack.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT);
        return !tooltipDisplay.shows(DataComponents.BUNDLE_CONTENTS)
                ? Optional.empty()
                : Optional.ofNullable(stack.get(DataComponents.BUNDLE_CONTENTS)).map(BaitedRodTooltip::new);
    }

    private static void playInsertFailSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_INSERT_FAIL, 1.0F, 1.0F);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        if (stack.has(DataComponents.MAX_DAMAGE)) {
            return super.isBarVisible(stack);
        } else {
            BundleContents bundleContents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
            return bundleContents.weight().getOrThrow().compareTo(Fraction.ZERO) > 0;
        }
    }

    @Override
    public int getBarWidth(final ItemStack stack) {
        BundleContents contents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        return Math.min(1 + Mth.mulAndTruncate(getWeightSafe(contents), 12), 13);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        if (stack.has(DataComponents.MAX_DAMAGE)) {
            return super.getBarColor(stack);
        } else {
            BundleContents bundleContents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
            return getWeightSafe(bundleContents).compareTo(Fraction.ONE) >= 0 ? FULL_BAR_COLOR : BAR_COLOR;
        }
    }

	private Fraction getWeightSafe(BundleContents bundleContents) {
		return BundleItemAccessor.invokeGetWeightSafe(bundleContents);
	}

    public void onDestroyed(final ItemEntity entity) {
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

    private void broadcastChangesOnContainerMenu(Player player) {
        AbstractContainerMenu abstractContainerMenu = player.containerMenu;
        if (abstractContainerMenu != null) {
            abstractContainerMenu.slotsChanged(player.getInventory());
        }
    }
}
