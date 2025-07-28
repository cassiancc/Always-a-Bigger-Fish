package cc.cassian.bigger_fish.minigame;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;

public class MinigameLayer implements LayeredDraw.Layer {
    public static boolean moveBackwards = false;
    public static int moveBackwardsLastSwitched = 0;
    public static int difficulty = 2;
    public static boolean minigameActive = false;
    public static int timesSwitched = 0;

    public static void startMinigame(LootTable instance, LootParams params, FishingHook hook, Player player) {
        ObjectArrayList<ItemStack> randomItems = instance.getRandomItems(params);
        minigameActive = true;
        for (ItemStack itemStack : randomItems) {
            if (itemStack.is(BiggerFishTags.TIER_THREE_FISH)) {
                difficulty = 3;
            } else if (itemStack.is(BiggerFishTags.TIER_THREE_FISH)) {
                difficulty = 2;
            } else {
                difficulty = 1;
            }
            BiggerFishMod.LOGGER.info("Minigame active - catching" + itemStack.getItemName());
        }
//        winMinigame(randomItems, hook, player);
    }

    public static void winMinigame(ObjectArrayList<ItemStack> randomItems, FishingHook hook, Player player) {
        for (ItemStack itemStack : randomItems) {
            ItemEntity itemEntity = new ItemEntity(hook.level(), hook.getX(), hook.getY(), hook.getZ(), itemStack);
            double d = player.getX() - hook.getX();
            double e = player.getY() - hook.getY();
            double f = player.getZ() - hook.getZ();
            double g = 0.1;
            itemEntity.setDeltaMovement(d * g, e * g + Math.sqrt(Math.sqrt(d * d + e * e + f * f)) * 0.08, f * g);
            hook.level().addFreshEntity(itemEntity);
            player.level().addFreshEntity(new ExperienceOrb(player.level(), player.getX(), player.getY() + 0.5, player.getZ() + 0.5, hook.getRandom().nextInt(6) + 1));
            if (itemStack.is(ItemTags.FISHES)) {
                player.awardStat(Stats.FISH_CAUGHT, 1);
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        if (minigameActive) {
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
                    timesSwitched++;
                }
                if (moveBackwards) {
                    rectangleWidth = 180-rectangleWidth;
                    if (rectangleWidth < 10) {
                        rectangleWidth = 180;
                    }
                }
                if (timesSwitched > 2) {
                    minigameActive = false;
                }

                int rectangleHeight = height-2;

                // x1, y1, x2, y2, color
                guiGraphics.fill(x+1, y+1, x + rectangleWidth, y + rectangleHeight, ARGB.color(200, 100, 0, 0));
                guiGraphics.drawString(mc.font, String.valueOf(tick), 5, 5, -1);
            }
        }
    }
}
