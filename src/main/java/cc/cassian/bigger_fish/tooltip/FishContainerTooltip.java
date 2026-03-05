package cc.cassian.bigger_fish.tooltip;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.component.BundleContents;

public record FishContainerTooltip(BundleContents contents, MutableComponent translatable) implements TooltipComponent {
}