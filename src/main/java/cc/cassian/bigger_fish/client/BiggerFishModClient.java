package cc.cassian.bigger_fish.client;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Objects;

public class BiggerFishModClient {
    public static void addTooltip(ItemStack stack, List<Component> list) {
        MutableComponent fishSizeTooltip = getFishSizeTooltip(stack);
        if (fishSizeTooltip != null)
            list.add(fishSizeTooltip);
        addBaitUsageTooltip(stack, list);
    }

    private static void addBaitUsageTooltip(ItemStack stack, List<Component> list) {
        if (BiggerFishMod.CONFIG.tooltip.baitUsageTooltip.value()) {
            if (BiggerFishMod.CONFIG.tooltip.showBaitUsageAlways.value() || ModHelpers.hasShiftDown())
                if (stack.has(BiggerFishComponentTypes.FISHING_LOOT.get())) {
                    list.add(Component.translatable("fishing."+ Identifier.parse(Objects.requireNonNull(stack.get(BiggerFishComponentTypes.FISHING_LOOT.get()))).toLanguageKey().replace("/", ".")));
                }
        }
    }

    public static MutableComponent getFishSizeTooltip(ItemStack stack) {
        if (BiggerFishMod.CONFIG.tooltip.fishSizeTooltip.value()) {
            if (stack.has(BiggerFishComponentTypes.SIZE.get())) {
                if (BiggerFishMod.CONFIG.tooltip.showFishSizesAlways.value() || ModHelpers.hasShiftDown())
                    return Component.translatable("component.bigger_fish.size", ModHelpers.getFishSize(stack), ModHelpers.getUnit());
            }
        }
        return null;
    }
}
