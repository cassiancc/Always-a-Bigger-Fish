package cc.cassian.bigger_fish.helpers;

import net.minecraft.network.chat.Component;

import java.lang.reflect.Field;
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

}
