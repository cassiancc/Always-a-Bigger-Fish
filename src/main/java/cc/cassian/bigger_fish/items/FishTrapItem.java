package cc.cassian.bigger_fish.items;

import cc.cassian.bigger_fish.registry.BiggerFishBlocks;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class FishTrapItem extends BlockItem {
	public FishTrapItem(Properties properties) {
		super(BiggerFishBlocks.FISH_TRAP, properties);
	}

	@Override
	public boolean overrideStackedOnOther(ItemStack rod, Slot slot, ClickAction action, Player player) {
		return FishContainer.overrideStackedOnOther(rod, slot, action, player, stack -> stack.is(BiggerFishTags.FISH));
	}

	@Override
	public boolean overrideOtherStackedOnMe(ItemStack rod, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
		return FishContainer.overrideOtherStackedOnMe(rod, other, slot, action, player, access, stack -> stack.is(BiggerFishTags.FISH));
	}

	@Override
	public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
		return FishContainer.getTooltipImage(stack, Component.translatable("item.bigger_fish.fish_trap.empty.description"));
	}

	@Override
	public boolean isBarVisible(ItemStack stack) {
		return FishContainer.isBarVisible(stack);
	}

	@Override
	public int getBarWidth(final ItemStack stack) {
		return FishContainer.getBarWidth(stack);
	}

	@Override
	public int getBarColor(ItemStack stack) {
		return FishContainer.getBarColor(stack);
	}

	public void onDestroyed(final ItemEntity entity) {
		FishContainer.onDestroyed(entity);
	}
}
