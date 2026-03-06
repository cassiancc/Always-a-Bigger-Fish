package cc.cassian.bigger_fish.client;

import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import cc.cassian.bigger_fish.registry.FishSize;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomModelData;
import org.jspecify.annotations.Nullable;
import timmychips.modefiteitemdefinitions.property.handler.RangePropertyHandler;
import timmychips.modefiteitemdefinitions.property.type.codec.RangeDispatchDefinition;

public class SizeProperty implements RangePropertyHandler {

	@Override
	public float getValue(ItemStack itemStack, LivingEntity livingEntity, RangeDispatchDefinition.Definition definition) {
		return itemStack.getOrDefault(BiggerFishComponentTypes.SIZE.get(), FishSize.ZERO).size();
	}
}
