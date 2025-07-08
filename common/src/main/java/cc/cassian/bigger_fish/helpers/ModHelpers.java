package cc.cassian.bigger_fish.helpers;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.config.ModConfig;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;
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
import java.util.function.Supplier;

import static cc.cassian.bigger_fish.BiggerFishMod.MOD_ID;

public class ModHelpers {

    /**
     * Automatically generate translation keys for config options.
     */
    public static Component fieldName(TrackedValue<?> field) {
        return Component.translatable("config.%s.%s".formatted(MOD_ID, toSnakeCase(field.key().toString())));
    }

    public static String toSnakeCase(String field) {
        return field.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }

    /**
     * Automatically generate translation keys for config tooltips. Relies on custom tooltip wrapping.
     */

    public static Component fieldTooltip(TrackedValue<?> field) {
        String tooltipKey = "config.%s.%s.tooltip".formatted(MOD_ID, toSnakeCase(field.key().toString()));
        if (I18n.exists(tooltipKey))
            return Component.translatable(tooltipKey);
        return Component.empty();
    }

    /**
     * Set a config field.
     */
    public static void fieldSetter(boolean instance, TrackedValue<Boolean> field) {
        field.setValue(instance);
    }
    public static void fieldSetter(Integer instance, TrackedValue<Integer> field) {
        field.setValue(instance);
    }
    public static void fieldSetter(String instance, TrackedValue<String> field) {
        field.setValue(instance);
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
        if (BiggerFishMod.CONFIG.tooltip.centimeters.value()) {
            return I18n.get("component.bigger_fish.size.cm");
        } else {
            return I18n.get("component.bigger_fish.size.inch");
        }
    }

    public static String getFishSize(ItemStack stack) {
        Float size = stack.get(BiggerFishComponentTypes.SIZE.get());
        if (size == null) return "0";
        if (BiggerFishMod.CONFIG.tooltip.centimeters.value()) {
            return "%s".formatted(Math.round(size * 2.54 * 10d) / 10d);
        } else {
            return "%s".formatted(size);
        }
    }

    public static List<ItemStack> toCollection(List<Supplier<Item>> fish) {
        List<ItemStack> list = new ArrayList<>();
        for (Supplier<Item> itemDeferredSupplier : fish) {
            list.add(itemDeferredSupplier.get().getDefaultInstance());
        }
        return list;

    }

    public static boolean isAllowedInBaitedRod(ItemStack stack) {
        return stack.is(BiggerFishTags.ALLOWED_IN_BAITED_ROD) || stack.has(BiggerFishComponentTypes.FISHING_LOOT.get());
    }
}
