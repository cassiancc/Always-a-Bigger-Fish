package cc.cassian.bigger_fish.minigame;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.HumanoidArm;

import java.awt.*;

public class MinigameLayer {

    public static void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        boolean moveBackwards = false;
        int moveBackwardsLastSwitched = 0;
        int difficulty = 2;
        var mc = Minecraft.getInstance();
        if (mc.options.hideGui) return;
        var player = mc.player;
        if (player == null) return;;
        var mainhand = player.getMainHandItem().is(BiggerFishTags.REQUIRES_MINIGAME_TO_CATCH);
        var offhand = player.getOffhandItem().is(BiggerFishTags.REQUIRES_MINIGAME_TO_CATCH);
        if (mainhand || offhand) {
            int x = 120;
            int y = guiGraphics.guiHeight() - 60;
            int width = 185;
            int height = 15;

            // background
            guiGraphics.blitSprite(BiggerFishMod.of("minigame"),
                    x, y, width, height);

            int tickCount= mc.player.tickCount;
            int difficultySpeed = 185;
            int difficultyMultiplier = 1;
            if (difficulty == 2) {
                difficultySpeed = 92;
                difficultyMultiplier = 2;
            } else if (difficulty == 3) {
                difficultySpeed = 46;
                difficultyMultiplier = 4;
            }

            int tick = tickCount%difficultySpeed;
            int rectangleWidth = tick*difficultyMultiplier;
            if (tick == (difficultySpeed-2) && (tickCount-moveBackwardsLastSwitched > 10)) {
                moveBackwards = !moveBackwards;
                moveBackwardsLastSwitched = tickCount;
            }
            if (moveBackwards) {
                rectangleWidth = 180-rectangleWidth;
                if (rectangleWidth < 10) {
                    rectangleWidth = 180;
                }
            }

            int rectangleHeight = height-2;

            // x1, y1, x2, y2, color
            guiGraphics.fill(x+1, y+1, x + rectangleWidth, y + rectangleHeight, FastColor.ARGB32.color(200, 100, 0, 0));
            guiGraphics.drawString(mc.font, String.valueOf(tick), 5, 5, -1);
        }
    }
}
