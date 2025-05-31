package cc.cassian.bigger_fish.mixin;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.tooltip.ClientBaitedRodTooltip;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientBundleTooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientBundleTooltip.class)
public class ClientBundleTooltipMixin {
    @Inject(method = "getProgressBarTexture",
            at = @At(value = "RETURN"), cancellable = true)
    private void baitedRodFullnessIsGood(CallbackInfoReturnable<ResourceLocation> cir) {
        var bundle = (ClientBundleTooltip) (Object) this;
        if (bundle instanceof ClientBaitedRodTooltip && cir.getReturnValue().equals(ResourceLocation.withDefaultNamespace("container/bundle/bundle_progressbar_full"))) {
            cir.setReturnValue(BiggerFishMod.of("container/copper_rod/copper_rod_progressbar_full"));
        }
    }

    @WrapOperation(method = "renderEmptyBundleTooltip",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/tooltip/ClientBundleTooltip;drawEmptyBundleDescriptionText(IILnet/minecraft/client/gui/Font;Lnet/minecraft/client/gui/GuiGraphics;)V"))
    private void baitedRodsTakeBait(int x, int y, Font font, GuiGraphics guiGraphics, Operation<Void> original) {
        var bundle = (ClientBundleTooltip) (Object) this;
        if (bundle instanceof ClientBaitedRodTooltip) {
            guiGraphics.drawWordWrap(font, Component.translatable("item.bigger_fish.baited_rod.empty.description"), x, y, 96, 11184810);
        } else
            original.call(x, y, font, guiGraphics);
    }
}
