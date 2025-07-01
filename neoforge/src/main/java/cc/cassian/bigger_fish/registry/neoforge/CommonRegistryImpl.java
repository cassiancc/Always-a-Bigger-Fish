package cc.cassian.bigger_fish.registry.neoforge;

import cc.cassian.bigger_fish.registry.ICommonRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static cc.cassian.bigger_fish.BiggerFishMod.MOD_ID;

public class CommonRegistryImpl implements ICommonRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MOD_ID);
    public static final DeferredRegister<DataComponentType<?>> COMPONENT_TYPES = DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MOD_ID);
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MOD_ID);

    public static void register(IEventBus eventBus) {
        CommonRegistryImpl.ITEMS.register(eventBus);
        CommonRegistryImpl.COMPONENT_TYPES.register(eventBus);
        CommonRegistryImpl.ENTITY_TYPES.register(eventBus);
        CommonRegistryImpl.SOUND_EVENTS.register(eventBus);
        CommonRegistryImpl.MOB_EFFECTS.register(eventBus);
    }

    public <T> Supplier<DataComponentType<T>> registerComponentType(String id, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return COMPONENT_TYPES.register(id, () -> (builderOperator.apply(DataComponentType.builder())).build());
    }

    public <B extends Item> Supplier<B> registerItem(String name, Supplier<B> supplier) {
        return ITEMS.register(name, supplier);
    }

    public <B extends Block> Supplier<B> registerBlock(String name, Supplier<B> supplier) {
        return BLOCKS.register(name, supplier);
    }

    public <T extends EntityType<?>> Supplier<T> registerEntity(String name, Supplier<T> supplier) {
        return ENTITY_TYPES.register(name, supplier);
    }

    public Supplier<SoundEvent> registerSoundEvent(String name, Supplier<SoundEvent> supplier) {
        return SOUND_EVENTS.register(name, supplier);
    }

    public Holder<MobEffect> registerMobEffect(String name, Supplier<MobEffect> supplier) {
        return MOB_EFFECTS.register(name, supplier);
    }
}
