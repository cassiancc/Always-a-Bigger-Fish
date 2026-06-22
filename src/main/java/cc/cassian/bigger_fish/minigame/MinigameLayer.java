package cc.cassian.bigger_fish.minigame;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
//? if >1.21.6 {
import net.minecraft.client.renderer.RenderPipelines;
import static net.minecraft.util.ARGB.color;
//?} else {
/*import static net.minecraft.util.FastColor.ABGR32.color;
*///?}


public class MinigameLayer {
    public boolean moveBackwards = false;
    public int moveBackwardsLastSwitched = 0;
    public int difficulty = 2;

//    @Override
    public void render(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker) {
        var mc = Minecraft.getInstance();
        if (mc.gui.hud.isHidden()) return;
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
            guiGraphics.blitSprite(
                    //? if >1.21.6 {
                    RenderPipelines.GUI_TEXTURED,
                    //?}
                    BiggerFishMod.of("minigame"),
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
            guiGraphics.fill(x+1, y+1, x + rectangleWidth, y + rectangleHeight, color(200, 100, 0, 0));
            guiGraphics.text(mc.font, String.valueOf(tick), 5, 5, -1);
        }
    }
}
