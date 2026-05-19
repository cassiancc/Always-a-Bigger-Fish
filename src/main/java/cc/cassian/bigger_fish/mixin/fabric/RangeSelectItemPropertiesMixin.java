package cc.cassian.bigger_fish.mixin.fabric;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.client.SizeProperty;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import timmychips.modefiteitemdefinitions.property.handler.RangePropertyHandler;
import timmychips.modefiteitemdefinitions.property.registry.RangePropertyRegistry;

import java.util.Map;

@Mixin(RangePropertyRegistry.class)
public class RangeSelectItemPropertiesMixin {
	@Shadow
	@Final
	private static Map<ResourceLocation, RangePropertyHandler> HANDLERS;

	@Inject(method = "init", at = @At(value = "RETURN"))
	private static void registerSize(CallbackInfo ci) {
		HANDLERS.put(BiggerFishMod.of("size"), new SizeProperty());
	}
}
