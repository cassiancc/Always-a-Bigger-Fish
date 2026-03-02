package cc.cassian.bigger_fish.mixin;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientBundleTooltip;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStackTemplate;
import org.apache.commons.lang3.math.Fraction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(ClientBundleTooltip.class)
public interface ClientBundleTooltipAccessor {

	@Invoker
	List<ItemStackTemplate> callGetShownItems(final int amountOfItemsToShow);

	@Invoker
	static boolean callShouldRenderSurplusText(final boolean isOverflowing, final int column, final int row) {
		throw new UnsupportedOperationException();
	}

	@Invoker
	static boolean callShouldRenderItemSlot(final List<? extends ItemInstance> shownItems, final int slotNumber) {
		throw new UnsupportedOperationException();
	}

	@Invoker
	int callGetAmountOfHiddenItems(final List<ItemStackTemplate> shownItems);

	@Invoker
	void callRenderSlot(
		final int slotNumber,
		final int drawX,
		final int drawY,
		final List<ItemStackTemplate> shownItems,
		final int slotIndex,
		final Font font,
		final GuiGraphics graphics
	);

	@Invoker
	static void callRenderCount(final int drawX, final int drawY, final int hiddenItemCount, final Font font, final GuiGraphics graphics) {
		throw new UnsupportedOperationException();
	}

	@Invoker
	static void callDrawProgressbar(final int x, final int y, final Font font, final GuiGraphics graphics, final Fraction weight) {
		throw new UnsupportedOperationException();
	}

	@Invoker
	int callGridSizeY();

	@Invoker
	static int callGetContentXOffset(final int tooltipWidth) {
		throw new UnsupportedOperationException();
	}

	@Invoker
	int callItemGridHeight();
}
