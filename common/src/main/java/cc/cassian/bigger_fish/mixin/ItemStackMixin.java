package cc.cassian.bigger_fish.mixin;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.config.ModConfig;
import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(method = "addDetailsToTooltip", at = @At(value = "HEAD"))
    private void randomizedFish(Item.TooltipContext context, TooltipDisplay tooltipDisplay, Player player, TooltipFlag tooltipFlag, Consumer<Component> tooltipAdder, CallbackInfo ci) {
        var stack = (ItemStack) (Object) this;
        if (BiggerFishMod.CONFIG.tooltip.fishSizeTooltip.value()) {
            if (stack.has(BiggerFishComponentTypes.SIZE.get()) && tooltipDisplay.shows(BiggerFishComponentTypes.SIZE.get())) {
                if (BiggerFishMod.CONFIG.tooltip.showFishSizesAlways.value() || Screen.hasShiftDown())
                    tooltipAdder.accept(Component.translatable("component.bigger_fish.size", ModHelpers.getFishSize(stack), ModHelpers.getUnit()));
            }
        }
        if (BiggerFishMod.CONFIG.tooltip.baitUsageTooltip.value()) {
            if (BiggerFishMod.CONFIG.tooltip.showBaitUsageAlways.value() || Screen.hasShiftDown())
                if (stack.has(BiggerFishComponentTypes.FISHING_LOOT.get()) && tooltipDisplay.shows(BiggerFishComponentTypes.FISHING_LOOT.get())) {
                tooltipAdder.accept(Component.translatable("fishing."+ ResourceLocation.parse(Objects.requireNonNull(stack.get(BiggerFishComponentTypes.FISHING_LOOT.get()))).toLanguageKey().replace("/", ".")));
            }
        }
    }
}
