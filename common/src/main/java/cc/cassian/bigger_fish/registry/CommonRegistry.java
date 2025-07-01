package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.BiggerFishMod;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class CommonRegistry {

    @ExpectPlatform
    public static <T> Supplier<DataComponentType<T>> registerComponentType(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Item> Supplier<T> registerItem(String name, Supplier<T> supplier) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> supplier) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends EntityType<?>> Supplier<T> registerEntity(String name, Supplier<T> supplier) {
        throw new AssertionError();
    }

    public static Supplier<SoundEvent> registerSoundEvent(String name) {
        return registerSoundEvent(name, ()->SoundEvent.createVariableRangeEvent(BiggerFishMod.of(name)));
    }

    @ExpectPlatform
    public static Supplier<SoundEvent> registerSoundEvent(String name, Supplier<SoundEvent> supplier) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Holder<MobEffect> registerMobEffect(String sucked, Supplier<MobEffect> suckedMobEffect) {
        throw new AssertionError();
    }
}