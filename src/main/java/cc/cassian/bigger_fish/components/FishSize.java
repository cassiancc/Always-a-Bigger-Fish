package cc.cassian.bigger_fish.components;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.client.BiggerFishModClient;
import cc.cassian.bigger_fish.helpers.ModHelpers;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public record FishSize(float size) implements TooltipProvider {
	public static final Codec<FishSize> CODEC = ExtraCodecs.POSITIVE_FLOAT.xmap(FishSize::new, FishSize::size);
	public static final StreamCodec<ByteBuf, FishSize> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.FLOAT, FishSize::size, FishSize::new);
	public static final FishSize ZERO = new FishSize(0);

	@Override
	public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag tooltipFlag) {
		if (BiggerFishMod.CONFIG.tooltip.fishSizeTooltip.value() && (BiggerFishMod.CONFIG.tooltip.showFishSizesAlways.value() || ModHelpers.hasShiftDown()))
			consumer.accept(Component.translatable("component.bigger_fish.size", ModHelpers.getFishSize(this), ModHelpers.getUnit()));
	}
}
