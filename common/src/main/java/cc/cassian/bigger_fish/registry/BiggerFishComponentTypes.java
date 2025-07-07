package cc.cassian.bigger_fish.registry;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;

import java.util.function.Supplier;

import static cc.cassian.bigger_fish.registry.CommonRegistry.registerComponentType;

public class BiggerFishComponentTypes {

    public static Supplier<DataComponentType<Float>> SIZE = registerComponentType("size",
            (builder) -> builder.persistent(ExtraCodecs.POSITIVE_FLOAT).networkSynchronized(ByteBufCodecs.FLOAT));
    public static Supplier<DataComponentType<String>> FISHING_LOOT = registerComponentType("fishing_loot_table",
            (builder) -> builder.persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.STRING_UTF8));

    public static void touch() {

    }
}
