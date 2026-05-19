package cc.cassian.bigger_fish.client;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.client.tooltip.ClientFishContainerTooltip;
import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import cc.cassian.bigger_fish.components.FishSize;
import cc.cassian.bigger_fish.tooltip.FishContainerTooltip;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class BiggerFishModClient {

    public static ClientFishContainerTooltip getClientBaitedRodTooltip(TooltipComponent visualTooltipComponent) {
        if (visualTooltipComponent instanceof FishContainerTooltip(BundleContents contents, MutableComponent translatable)) {
            return new ClientFishContainerTooltip(contents, translatable);
        } else return null;
    }

    public static void addTooltips(ItemStack itemStack, Item.TooltipContext tooltipContext, TooltipFlag tooltipFlag, List<Component> list) {
        var components = List.of(BiggerFishComponentTypes.HOOK_EFFECTS, BiggerFishComponentTypes.FISHING_LOOT, BiggerFishComponentTypes.SIZE);
        for (Supplier<? extends DataComponentType<?>> component : components) {
            if (itemStack.has(component.get())) {
                ((TooltipProvider) Objects.requireNonNull(itemStack.get(component.get()))).addToTooltip(tooltipContext, component1->list.add(1, component1.copy().withStyle(ChatFormatting.GRAY)), tooltipFlag);
            }
            ItemStack baitFromRod = ModHelpers.getBaitFromRod(itemStack);
            if (baitFromRod != null && baitFromRod.has(component.get())) {
                ((TooltipProvider) Objects.requireNonNull(baitFromRod.get(component.get()))).addToTooltip(tooltipContext, component1->list.add(1, component1.copy().withStyle(ChatFormatting.GRAY)), tooltipFlag);
            }
        }
    }
}
