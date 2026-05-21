package cc.cassian.bigger_fish.components;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.helpers.ModHelpers;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public record FishingLoot(Identifier lootTable) implements TooltipProvider {
	public static final Codec<FishingLoot> CODEC = Identifier.CODEC.xmap(FishingLoot::new, FishingLoot::lootTable);
	public static final StreamCodec<ByteBuf, FishingLoot> STREAM_CODEC = StreamCodec.composite(Identifier.STREAM_CODEC, FishingLoot::lootTable, FishingLoot::new);

    public static FishingLoot parse(String s) {
        return new FishingLoot(Identifier.parse(s));
    }

	@Override
	public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
		if (BiggerFishMod.CONFIG.tooltip.baitUsageTooltip.value()) {
			if (BiggerFishMod.CONFIG.tooltip.showBaitUsageAlways.value() || ModHelpers.hasShiftDown())
				consumer.accept(Component.translatable("fishing."+ lootTable.toLanguageKey().replace("/", ".")));
		}
	}
}
