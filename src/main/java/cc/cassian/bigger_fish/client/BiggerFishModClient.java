package cc.cassian.bigger_fish.client;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.client.tooltip.ClientBaitedRodTooltip;
import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import cc.cassian.bigger_fish.registry.FishSize;
import cc.cassian.bigger_fish.tooltip.BaitedRodTooltip;
import cc.cassian.bigger_fish.tooltip.FishBarrelTooltip;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class BiggerFishModClient {

    public static void addBaitUsageTooltip(ItemStack stack, List<Component> list) {
        if (BiggerFishMod.CONFIG.tooltip.baitUsageTooltip.value()) {
            if (BiggerFishMod.CONFIG.tooltip.showBaitUsageAlways.value() || ModHelpers.hasShiftDown())
                if (stack.has(BiggerFishComponentTypes.FISHING_LOOT.get())) {
                    list.add(Component.translatable("fishing."+ Identifier.parse(Objects.requireNonNull(stack.get(BiggerFishComponentTypes.FISHING_LOOT.get()))).toLanguageKey().replace("/", ".")));
                }
        }
    }

    public static MutableComponent getFishSizeTooltip(FishSize size) {
		if (BiggerFishMod.CONFIG.tooltip.fishSizeTooltip.value() && size != null && (BiggerFishMod.CONFIG.tooltip.showFishSizesAlways.value() || ModHelpers.hasShiftDown()))
			return Component.translatable("component.bigger_fish.size", ModHelpers.getFishSize(size), ModHelpers.getUnit());
        return null;
    }

    public static ClientBaitedRodTooltip getClientBaitedRodTooltip(TooltipComponent visualTooltipComponent) {
        if (visualTooltipComponent instanceof BaitedRodTooltip(BundleContents contents)) {
            return new ClientBaitedRodTooltip(contents, Component.translatable("item.bigger_fish.baited_rod.empty.description"));
        } else if (visualTooltipComponent instanceof FishBarrelTooltip(BundleContents contents)) {
            return new ClientBaitedRodTooltip(contents, Component.translatable("item.bigger_fish.fish_barrel.empty.description"));
        } else return null;
    }
}
