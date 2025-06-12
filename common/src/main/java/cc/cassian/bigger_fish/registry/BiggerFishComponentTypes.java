package cc.cassian.bigger_fish.registry;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;

import java.util.function.Supplier;

import static cc.cassian.bigger_fish.registry.CommonRegistry.registerComponentType;

public class BiggerFishComponentTypes {

    public static Supplier<DataComponentType<Float>> SIZE = registerComponentType("size",
            (builder) -> builder.persistent(ExtraCodecs.POSITIVE_FLOAT).networkSynchronized(ByteBufCodecs.FLOAT));

    public static void touch() {

    }
}
