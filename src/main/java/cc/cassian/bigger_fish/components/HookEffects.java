package cc.cassian.bigger_fish.components;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.helpers.ModHelpers;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public record HookEffects(String effect) implements TooltipProvider {
	public static final Codec<HookEffects> CODEC = Codec.STRING.xmap(HookEffects::new, HookEffects::effect);
	public static final StreamCodec<ByteBuf, HookEffects> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.STRING_UTF8, HookEffects::effect, HookEffects::new);
	public static final HookEffects VANILLA = new HookEffects("vanilla");
	public static final HookEffects TREASURE = new HookEffects("treasure");
	public static final HookEffects COPPER = new HookEffects("copper");
	public static final HookEffects NETHERITE = new HookEffects("netherite");
	public static final HookEffects GRAPPLING = new HookEffects("grappling");
	public static final HookEffects STICKY_GRAPPLING = new HookEffects("sticky_grappling");

	public static HookEffects parse(String treasure) {
        return new HookEffects(treasure);
    }

	@Override
	public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag tooltipFlag, DataComponentGetter components) {
		if (BiggerFishMod.CONFIG.tooltip.baitUsageTooltip.value()) {
			if (BiggerFishMod.CONFIG.tooltip.showBaitUsageAlways.value() || ModHelpers.hasShiftDown(tooltipFlag)) {
				String key = "fishing.bigger_fish." + effect;
				if (Language.getInstance().has(key)) {
					consumer.accept(Component.translatable(key));
				}
			}
		}
	}
}
