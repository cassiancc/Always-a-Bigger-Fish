package cc.cassian.bigger_fish.config;


import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;

import static cc.cassian.bigger_fish.helpers.ModHelpers.*;


public class ClothConfigFactory {

    private static final ModConfig DEFAULT_VALUES = new ModConfig();

    private static ConfigCategory createCategory(String section, ConfigBuilder builder) {
        if (section == null) {
            section = "";
        } else {
            section += "_";
        }
        return builder.getOrCreateCategory(Component.translatable("config.bigger_fish.%stitle".formatted(section)));
    }

    private static boolean is(Field field, String name) {
        return field.getName().toLowerCase(Locale.ROOT).contains(name);
    }

    public static Screen create(Screen parent) {
        final var builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("modmenu.nameTranslation.bigger_fish"));

        final var entryBuilder = builder.entryBuilder();
        final var configInstance = ModConfig.get();
        final var gameplayCategory = createCategory("gameplay", builder);
        final var tooltipCategory = createCategory("tooltip", builder);


        for (var field : ModConfig.class.getFields()) {
            ConfigCategory category = gameplayCategory;
            if (is(field,"tooltip_")) category = tooltipCategory;

            if (field.getType() == boolean.class) {
                category.addEntry(entryBuilder.startBooleanToggle(fieldName(field), fieldGet(configInstance, field))
                        .setSaveConsumer(fieldSetter(configInstance, field))
                        .setTooltip(fieldTooltip(field))
                        .setDefaultValue((boolean) fieldGet(DEFAULT_VALUES, field)).build());

            }
            else if (field.getType() == String.class) {
                category.addEntry(entryBuilder.startStrField(fieldName(field), fieldGet(configInstance, field))
                        .setSaveConsumer(fieldSetter(configInstance, field))
                        .setTooltip(fieldTooltip(field))
                        .setDefaultValue((String) fieldGet(DEFAULT_VALUES, field)).build());
            }
            else if (field.getType() == int.class) {
                category.addEntry(entryBuilder.startIntField(fieldName(field), fieldGet(configInstance, field))
                        .setSaveConsumer(fieldSetter(configInstance, field))
                        .setTooltip(fieldTooltip(field))
                        .setDefaultValue((int) fieldGet(DEFAULT_VALUES, field)).build());
            }
            else if (field.getType() == List.class) {
                category.addEntry(entryBuilder.startStrList(fieldName(field), fieldGet(configInstance, field))
                        .setSaveConsumer(fieldSetter(configInstance, field))
                        .setTooltip(fieldTooltip(field))
                        .setDefaultValue((List<String>) fieldGet(DEFAULT_VALUES, field)).build());
            }
        }
        builder.setSavingRunnable(ModConfig::save);
        return builder.build();
    }
}