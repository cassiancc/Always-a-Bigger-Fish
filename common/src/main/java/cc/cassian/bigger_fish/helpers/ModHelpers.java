package cc.cassian.bigger_fish.helpers;

import cc.cassian.bigger_fish.config.ModConfig;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomModelData;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static cc.cassian.bigger_fish.BiggerFishMod.MOD_ID;

public class ModHelpers {

    /**
     * Automatically generate translation keys for config options.
     */
    public static Component fieldName(Field field) {
        return Component.translatable("config.%s.config.%s".formatted(MOD_ID, field.getName()));
    }

    /**
     * Automatically generate translation keys for config tooltips. Relies on custom tooltip wrapping.
     */
    public static Component fieldTooltip(Field field) {
        String tooltipKey = "config.%s.config.%s.tooltip".formatted(MOD_ID, field.getName());
        return Component.translatable(tooltipKey);
    }

    /**
     * Get the current value of a config field.
     */
    @SuppressWarnings("unchecked")
    public static <T> T fieldGet(Object instance, Field field) {
        try {
            return (T) field.get(instance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Set a config field.
     */
    public static <T> Consumer<T> fieldSetter(Object instance, Field field) {
        return t -> {
            try {
                field.set(instance, t);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static Float getRandomFishSize(Entity hook) {
        var random = hook.getRandom();
        var size = random.nextIntBetweenInclusive(1, 15)*Math.log(random.nextIntBetweenInclusive(1, 160));
        if (random.nextIntBetweenInclusive(0, 100) > 60) {
            size = size*.5;
        }
        return (float) (Math.round(size * 10d) / 10d);
    }

    public static ItemStack setRandomFishSize(ItemStack itemStack, Entity hook) {
        var size = ModHelpers.getRandomFishSize(hook);
        itemStack.set(BiggerFishComponentTypes.SIZE.get(), size);
        itemStack.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(List.of(size), List.of(), List.of(), List.of()));
        return itemStack;
    }

    public static String getUnit() {
        if (ModConfig.get().centimeters) {
            return I18n.get("component.bigger_fish.size.cm");
        } else {
            return I18n.get("component.bigger_fish.size.inch");
        }
    }

    public static String getFishSize(ItemStack stack) {
        Float size = stack.get(BiggerFishComponentTypes.SIZE.get());
        if (size == null) return "0";
        if (ModConfig.get().centimeters) {
            return "%s".formatted(Math.round(size * 2.54 * 10d) / 10d);
        } else {
            return "%s".formatted(size);
        }
    }

    public static List<ItemStack> toCollection(ArrayList<DeferredSupplier<Item>> fish) {
        List<ItemStack> list = new ArrayList<>();
        for (DeferredSupplier<Item> itemDeferredSupplier : fish) {
            list.add(itemDeferredSupplier.get().getDefaultInstance());
        }
        return list;

    }
}
