package cc.cassian.bigger_fish.items;

import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;

import java.util.List;

import static net.minecraft.util.FastColor.ARGB32.colorFromFloat;

public class BaitedRodItem extends FishingRodItem {
    public BaitedRodItem(Properties properties) {
        super(properties);
    }

    private static final int FULL_BAR_COLOR = colorFromFloat(1.0F, 0.44F, 1.0F, 0.33F);
    private static final int BAR_COLOR = colorFromFloat(1.0F, 0.44F, 0.53F, 1.0F);

    public boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction action, Player player) {
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
                } else if (itemStack.getItem().canFitInsideContainerItems()  && itemStack.is(BiggerFishTags.ALLOWED_IN_BAITED_ROD)) {
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

    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
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
                } else if (other.is(BiggerFishTags.ALLOWED_IN_BAITED_ROD)) {
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

    @Override
    public boolean isBarVisible(ItemStack stack) {
        if (stack.has(DataComponents.MAX_DAMAGE)) {
            return super.isBarVisible(stack);
        } else {
            BundleContents bundleContents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
            return bundleContents.weight().compareTo(Fraction.ZERO) > 0;
        }
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        if (stack.has(DataComponents.MAX_DAMAGE)) {
            return super.getBarWidth(stack);
        } else {
            BundleContents bundleContents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
            return Math.min(1 + Mth.mulAndTruncate(bundleContents.weight(), 12), 13);
        }
    }

    @Override
    public int getBarColor(ItemStack stack) {
        if (stack.has(DataComponents.MAX_DAMAGE)) {
            return super.getBarColor(stack);
        } else {
            BundleContents bundleContents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
            return bundleContents.weight().compareTo(Fraction.ONE) >= 0 ? FULL_BAR_COLOR : BAR_COLOR;
        }
    }


    @Override
    public void onDestroyed(ItemEntity itemEntity) {
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

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        var contents = stack.get(DataComponents.BUNDLE_CONTENTS);
        if (contents == null || contents.isEmpty()) {
            tooltipComponents.add(Component.translatable("item.bigger_fish.baited_rod.empty.description"));
        }
    }
}
