package cc.cassian.bigger_fish.minigame;

import cc.cassian.bigger_fish.registry.BiggerFishItems;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class MinigameLayer implements LayeredDraw.Layer {
    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        var mc = Minecraft.getInstance();
        if (mc.player != null && mc.player.getMainHandItem().is(BiggerFishItems.COPPER_ROD.get())) {
            int rectangleX = Minecraft.getInstance().getWindow().getGuiScaledWidth()-100;
            int rectangleY = 110;
            int rectangleWidth = 10;
            int rectangleHeight = 100;
            // x1, y1, x2, y2, color
            guiGraphics.fill(rectangleX, rectangleY, rectangleX + rectangleWidth, rectangleY + rectangleHeight, 0xFF0000FF);
        }
    }
}
