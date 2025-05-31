package cc.cassian.bigger_fish.tooltip;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.component.BundleContents;

public record BaitedRodTooltip(BundleContents contents) implements TooltipComponent {
}