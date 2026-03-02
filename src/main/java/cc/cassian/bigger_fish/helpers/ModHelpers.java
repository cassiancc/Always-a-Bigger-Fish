package cc.cassian.bigger_fish.helpers;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.Platform;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import cc.cassian.bigger_fish.registry.FishSize;
import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;
//? if >1.21.9 {
import net.minecraft.client.Minecraft;
//?} else
/*import net.minecraft.client.gui.screens.Screen;*/
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.component.CustomModelData;

import java.util.ArrayList;
import java.util.List;
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

    public static FishSize getRandomFishSize(Entity hook) {
        var random = hook.getRandom();
        var size = random.nextIntBetweenInclusive(1, 15)*Math.log(random.nextIntBetweenInclusive(1, 160));
        if (random.nextIntBetweenInclusive(0, 100) > 60) {
            size = size*.5;
        }
        return new FishSize((float) (Math.round(size * 10d) / 10d));
    }

    public static ItemStack setRandomFishSize(ItemStack itemStack, Entity hook) {
        itemStack.set(BiggerFishComponentTypes.SIZE.get(), ModHelpers.getRandomFishSize(hook));
        return itemStack;
    }

    public static String getUnit() {
        if (BiggerFishMod.CONFIG.tooltip.centimeters.value()) {
            return I18n.get("component.bigger_fish.size.cm");
        } else {
            return I18n.get("component.bigger_fish.size.inch");
        }
    }

    public static String getFishSize(FishSize size) {
        if (size == null) return "0";
        if (BiggerFishMod.CONFIG.tooltip.centimeters.value()) {
            return "%s".formatted(Math.round(size.size() * 2.54 * 10d) / 10d);
        } else {
            return "%s".formatted(size.size());
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

    public static String getHookData(ItemStack item) {
        if (item.has(DataComponents.BUNDLE_CONTENTS)) {
            BundleContents bundleContents = item.get(DataComponents.BUNDLE_CONTENTS);
            if (bundleContents != null && !bundleContents.isEmpty())
                return bundleContents.items().get(0).getOrDefault(BiggerFishComponentTypes.HOOK_EFFECTS.get(), "vanilla");
        }
        return "";
    }

    public static Boolean isLavaHook(FishingHook hook) {
        return Platform.INSTANCE.getHookData(hook).equals("netherite");
    }

    public static boolean hasShiftDown() {
        //? if >1.21.8 {
        return Minecraft.getInstance().hasShiftDown();
        //?} else {
        /*return Screen.hasShiftDown();
         *///?}
    }
}
