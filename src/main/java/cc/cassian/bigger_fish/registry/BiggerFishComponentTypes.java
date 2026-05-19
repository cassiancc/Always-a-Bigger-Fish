package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.components.FishSize;
import cc.cassian.bigger_fish.components.FishingLoot;
import cc.cassian.bigger_fish.components.HookEffects;
import com.mojang.datafixers.types.templates.Hook;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;

import java.util.function.Supplier;

import static cc.cassian.bigger_fish.registry.CommonRegistry.registerComponentType;

public class BiggerFishComponentTypes {

    public static final Supplier<DataComponentType<FishSize>> SIZE = registerComponentType("size",
            (builder) -> builder.persistent(FishSize.CODEC).networkSynchronized(FishSize.STREAM_CODEC));

    public static final Supplier<DataComponentType<FishingLoot>> FISHING_LOOT = registerComponentType("fishing_loot_table",
            (builder) -> builder.persistent(FishingLoot.CODEC).networkSynchronized(FishingLoot.STREAM_CODEC));

    public static final Supplier<DataComponentType<HookEffects>> HOOK_EFFECTS = registerComponentType("hook_effects",
            (builder) -> builder.persistent(HookEffects.CODEC).networkSynchronized(HookEffects.STREAM_CODEC));

    public static void touch() {

    }
}
