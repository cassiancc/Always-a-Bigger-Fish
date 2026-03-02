package cc.cassian.bigger_fish.client.tooltip;

import cc.cassian.bigger_fish.client.BiggerFishModClient;
import cc.cassian.bigger_fish.mixin.ClientBundleTooltipAccessor;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import com.mojang.serialization.DataResult;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientBundleTooltip;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.DefaultTooltipPositioner;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;

import java.util.ArrayList;
import java.util.List;

import static cc.cassian.bigger_fish.mixin.ClientBundleTooltipAccessor.*;

public class ClientBaitedRodTooltip extends ClientBundleTooltip {

    private final ClientBundleTooltipAccessor original;

    public ClientBaitedRodTooltip(BundleContents contents) {
        super(contents);
        this.contents = contents;
        this.original = (ClientBundleTooltipAccessor) this;
    }

    private static final Component FISH_CONTAINER_EMPTY_DESCRIPTION = Component.translatable("item.bigger_fish.baited_rod.empty.description");
    private final BundleContents contents;

    @Override
    public void renderImage(final Font font, final int x, final int y, final int w, final int h, final GuiGraphics graphics) {
        DataResult<Fraction> weight = this.contents.weight();
        if (!weight.isError()) {
            if (this.contents.isEmpty()) {
                renderEmptyFishContainerTooltip(font, x, y, w, h, graphics);
            } else {
                this.renderFishContainerWithItemsTooltip(font, x, y, w, h, graphics, weight.getOrThrow());
            }
        }
    }

    private static void renderEmptyFishContainerTooltip(final Font font, final int x, final int y, final int w, final int h, final GuiGraphics graphics) {
        int left = x + callGetContentXOffset(w);
        drawEmptyFishContainerDescriptionText(left, y, font, graphics);
        callDrawProgressbar(left, y + getEmptyFishContainerDescriptionTextHeight(font) + 4, font, graphics, Fraction.ZERO);
    }

    private void renderFishContainerWithItemsTooltip(
            final Font font, final int x, final int y, final int w, final int h, final GuiGraphics graphics, final Fraction weight
    ) {
        boolean isOverflowing = this.contents.size() > 12;
        List<ItemStackTemplate> shownItems = original.callGetShownItems(this.contents.getNumberOfItemsToShow());
        int xStartPos = x + callGetContentXOffset(w) + 96;
        int yStartPos = y + original.callGridSizeY() * 24;
        int slotNumber = 1;

        for (int rowNumber = 1; rowNumber <= original.callGridSizeY(); rowNumber++) {
            for (int columnNumber = 1; columnNumber <= 4; columnNumber++) {
                int drawX = xStartPos - columnNumber * 24;
                int drawY = yStartPos - rowNumber * 24;
                if (callShouldRenderSurplusText(isOverflowing, columnNumber, rowNumber)) {
                    callRenderCount(drawX, drawY, original.callGetAmountOfHiddenItems(shownItems), font, graphics);
                } else if (callShouldRenderItemSlot(shownItems, slotNumber)) {
                    original.callRenderSlot(slotNumber, drawX, drawY, shownItems, slotNumber, font, graphics);
                    slotNumber++;
                }
            }
        }

        this.drawSelectedItemTooltip(font, graphics, x, y, w);
        callDrawProgressbar(x + callGetContentXOffset(w), y + original.callItemGridHeight() + 4, font, graphics, weight);
    }

    private void drawSelectedItemTooltip(final Font font, final GuiGraphics graphics, final int x, final int y, final int w) {
        ItemStackTemplate selectedItem = this.contents.getSelectedItem();
        if (selectedItem != null) {
            ArrayList<ClientTooltipComponent> tooltip = new ArrayList<>();
            ItemStack itemStack = selectedItem.create();
            // name
            Component selectedItemName = itemStack.getStyledHoverName();
            int textWidth = font.width(selectedItemName.getVisualOrderText());
            int centerTooltip = x + w / 2 - 12;
            ClientTooltipComponent selectedItemNameTooltip = ClientTooltipComponent.create(selectedItemName.getVisualOrderText());
            tooltip.add(selectedItemNameTooltip);
            // fish size
            MutableComponent fishSizeTooltip = BiggerFishModClient.getFishSizeTooltip(itemStack.get(BiggerFishComponentTypes.SIZE.get()));
            if (fishSizeTooltip != null) {
				ClientTooltipComponent fishSizeClientTooltip = ClientTooltipComponent.create(fishSizeTooltip.getVisualOrderText());
                tooltip.add(fishSizeClientTooltip);
			}
            graphics.renderTooltip(
                    font,
                    tooltip,
                    centerTooltip - textWidth / 2,
                    y - (tooltip.size() * 15),
                    DefaultTooltipPositioner.INSTANCE,
                    itemStack.get(DataComponents.TOOLTIP_STYLE)
            );
        }
    }

    private static void drawEmptyFishContainerDescriptionText(final int x, final int y, final Font font, final GuiGraphics graphics) {
        graphics.drawWordWrap(font, FISH_CONTAINER_EMPTY_DESCRIPTION, x, y, 96, -5592406);
    }

    private static int getEmptyFishContainerDescriptionTextHeight(final Font font) {
        return font.split(FISH_CONTAINER_EMPTY_DESCRIPTION, 96).size() * 9;
    }
}
