package cc.cassian.bigger_fish.client;

import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomModelData;
import org.jspecify.annotations.Nullable;

public class SizeProperty implements RangeSelectItemModelProperty {
	public static final MapCodec<SizeProperty> MAP_CODEC = MapCodec.unit(new SizeProperty());
	@Override
	public float get(ItemStack itemStack, @Nullable ClientLevel level, @Nullable ItemOwner owner, int seed) {
		return itemStack.getOrDefault(BiggerFishComponentTypes.SIZE.get(), 0.0f);
	}

	@Override
	public MapCodec<SizeProperty> type() {
		return MAP_CODEC;
	}
}
