package cc.cassian.bigger_fish.mixin.fabric;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.client.SizeProperty;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.item.properties.numeric.CustomModelDataProperty;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperties;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RangeSelectItemModelProperties.class)
public class RangeSelectItemPropertiesMixin {
	@Shadow
	@Final
	public static ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends RangeSelectItemModelProperty>> ID_MAPPER;

	@Inject(method = "bootstrap", at = @At(value = "RETURN"))
	private static void registerSize(CallbackInfo ci) {
		ID_MAPPER.put(BiggerFishMod.of("size"), SizeProperty.MAP_CODEC);
	}
}
