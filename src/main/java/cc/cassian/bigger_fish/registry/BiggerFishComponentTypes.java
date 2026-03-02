package cc.cassian.bigger_fish.registry;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;

import java.util.function.Supplier;

import static cc.cassian.bigger_fish.registry.CommonRegistry.registerComponentType;

public class BiggerFishComponentTypes {

    public static Supplier<DataComponentType<FishSize>> SIZE = registerComponentType("size",
            (builder) -> builder.persistent(FishSize.CODEC).networkSynchronized(FishSize.STREAM_CODEC));

    public static Supplier<DataComponentType<String>> FISHING_LOOT = registerComponentType("fishing_loot_table",
            (builder) -> builder.persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.STRING_UTF8));

    public static Supplier<DataComponentType<String>> HOOK_EFFECTS = registerComponentType("hook_effects",
            (builder) -> builder.persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.STRING_UTF8));

    public static void touch() {

    }
}
