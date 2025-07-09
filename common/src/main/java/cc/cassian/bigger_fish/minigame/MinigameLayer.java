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
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.HumanoidArm;

public class MinigameLayer implements LayeredDraw.Layer {
    public boolean moveBackwards = false;
    public int moveBackwardsLastSwitched = 0;
    public int difficulty = 2;

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        var mc = Minecraft.getInstance();
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
            guiGraphics.blitSprite(RenderType::guiTextured, BiggerFishMod.of("minigame"),
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
            }

            int rectangleHeight = height-2;

            // x1, y1, x2, y2, color
            guiGraphics.fill(x+1, y+1, x + rectangleWidth, y + rectangleHeight, ARGB.color(200, 100, 0, 0));
            guiGraphics.drawString(mc.font, String.valueOf(tick), 5, 5, -1);
        }
    }
}
