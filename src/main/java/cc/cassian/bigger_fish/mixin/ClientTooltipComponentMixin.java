package cc.cassian.bigger_fish.mixin;

import cc.cassian.bigger_fish.tooltip.ClientBaitedRodTooltip;
import cc.cassian.bigger_fish.tooltip.BaitedRodTooltip;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.component.BundleContents;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClientTooltipComponent.class)
public interface ClientTooltipComponentMixin {
    @WrapMethod(method = "create(Lnet/minecraft/world/inventory/tooltip/TooltipComponent;)Lnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipComponent;")
    private static ClientTooltipComponent guardRiskyCalculation(TooltipComponent visualTooltipComponent, Operation<ClientTooltipComponent> original) {
        if (visualTooltipComponent instanceof BaitedRodTooltip(
                BundleContents contents
        )) {
            return new ClientBaitedRodTooltip(contents);
        } else return original.call(visualTooltipComponent);
    }
}
