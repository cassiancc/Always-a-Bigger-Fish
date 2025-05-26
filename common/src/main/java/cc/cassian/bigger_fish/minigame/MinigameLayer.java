package cc.cassian.bigger_fish.minigame;

import cc.cassian.bigger_fish.registry.BiggerFishItems;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;

public class MinigameLayer implements LayeredDraw.Layer {
    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        var mc = Minecraft.getInstance();
        var player = mc.player;
        if (player == null) return;;
        var mainhand = player.getMainHandItem().is(BiggerFishItems.COPPER_ROD.get());
        var offhand = player.getOffhandItem().is(BiggerFishItems.COPPER_ROD.get());
        if (mainhand || offhand) {
            int y = guiGraphics.guiHeight() - 74;
            int x = guiGraphics.guiWidth() / 2 + 97;
            if (mainhand) {
                x = guiGraphics.guiWidth() / 2 - 117;
            }

            guiGraphics.blitSprite(RenderType::guiTextured, ResourceLocation.withDefaultNamespace("hud/hotbar_selection"), x, y, 18, 72);
        }
    }
}
