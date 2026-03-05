package cc.cassian.bigger_fish.items;

import cc.cassian.bigger_fish.helpers.ModHelpers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class BaitedRodItem extends FishingRodItem {
    public BaitedRodItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack rod, Slot slot, ClickAction action, Player player) {
        return FishContainer.overrideStackedOnOther(rod, slot, action, player, ModHelpers::isAllowedInBaitedRod);
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack rod, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        return FishContainer.overrideOtherStackedOnMe(rod, other, slot, action, player, access, ModHelpers::isAllowedInBaitedRod);
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return FishContainer.getTooltipImage(stack);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        if (stack.has(DataComponents.MAX_DAMAGE)) {
            return super.isBarVisible(stack);
        } else {
            return FishContainer.isBarVisible(stack);
        }
    }

    @Override
    public int getBarWidth(final ItemStack stack) {
        return FishContainer.getBarWidth(stack);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        if (stack.has(DataComponents.MAX_DAMAGE)) {
            return stack.getBarColor();
        } else {
            return FishContainer.getBarColor(stack);
        }
    }

    public void onDestroyed(final ItemEntity entity) {
        FishContainer.onDestroyed(entity);
    }
}
