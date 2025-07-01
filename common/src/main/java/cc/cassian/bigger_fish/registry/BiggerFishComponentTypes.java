package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.Platform;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;

import java.util.function.Supplier;

public class BiggerFishComponentTypes {

    public static Supplier<DataComponentType<Float>> SIZE = Platform.REGISTRY.registerComponentType("size",
            (builder) -> builder.persistent(ExtraCodecs.POSITIVE_FLOAT).networkSynchronized(ByteBufCodecs.FLOAT));

    public static void touch() {

    }
}
