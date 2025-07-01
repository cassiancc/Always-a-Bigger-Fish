package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.Platform;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public interface ICommonRegistry {


     <T> Supplier<DataComponentType<T>> registerComponentType(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator);


    <T extends Item> Supplier<T> registerItem(String name, Supplier<T> supplier);


    <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> supplier);


    <T extends EntityType<?>> Supplier<T> registerEntity(String name, Supplier<T> supplier);

    static Supplier<SoundEvent> registerSoundEvent(String name) {
        return Platform.REGISTRY.registerSoundEvent(name, ()->SoundEvent.createVariableRangeEvent(BiggerFishMod.of(name)));
    }

    
     Supplier<SoundEvent> registerSoundEvent(String name, Supplier<SoundEvent> supplier);


    Holder<MobEffect> registerMobEffect(String sucked, Supplier<MobEffect> suckedMobEffect);
}