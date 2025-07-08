package cc.cassian.bigger_fish.config;


import cc.cassian.bigger_fish.BiggerFishMod;
import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static cc.cassian.bigger_fish.BiggerFishMod.MOD_ID;
import static cc.cassian.bigger_fish.helpers.ModHelpers.*;

public class ClothConfigFactory {

    private static ConfigCategory createCategory(String section, ConfigBuilder builder) {
        if (section == null) {
            section = "";
        } else {
            section += "_";
        }
        return builder.getOrCreateCategory(Component.translatable("config.%s.%stitle".formatted(MOD_ID, section)));
    }

    public static Screen create(Screen parent) {
        final var builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("modmenu.nameTranslation."+MOD_ID));

        addEntries(BiggerFishMod.CONFIG.values(), builder);

        builder.setSavingRunnable(BiggerFishMod.CONFIG::save);
        return builder.build();
    }

    private static void addEntries(Iterable<TrackedValue<?>> fields, ConfigBuilder builder) {
        var entryBuilder = builder.entryBuilder();
        for (var field : fields) {
            String categoryName = field.key().toString();
            if (categoryName.contains(".")) {
                categoryName = toSnakeCase(categoryName.split("\\.")[0]);
            } else {
                categoryName = null;
            }
            var category = createCategory(categoryName, builder);
            if (field.value().getClass() == Boolean.class) {
                category.addEntry(entryBuilder.startBooleanToggle(fieldName(field), (boolean) field.value())
                        .setSaveConsumer((o)-> fieldSetter(o, (TrackedValue<Boolean>) field))
                        .setTooltip(fieldTooltip(field))
                        .setDefaultValue((boolean) field.getDefaultValue()).build());

            }
            else if (field.value().getClass() == String.class) {
                category.addEntry(entryBuilder.startStrField(fieldName(field), (String) field.value())
                        .setSaveConsumer((o)-> fieldSetter(o, (TrackedValue<String>) field))
                        .setTooltip(fieldTooltip(field))
                        .setDefaultValue((String) field.getDefaultValue()).build());
            }
            else if (field.value().getClass() == Integer.class) {
                category.addEntry(entryBuilder.startIntField(fieldName(field), (int) field.value())
                        .setSaveConsumer((o)-> fieldSetter(o, (TrackedValue<Integer>) field))
                        .setTooltip(fieldTooltip(field))
                        .setDefaultValue((int) field.getDefaultValue()).build());
            }
//            else if (field.getType() == List.class) {
//                category.addEntry(entryBuilder.startStrList(fieldName(field, categoryName), fieldGet(config, field))
//                        .setSaveConsumer(fieldSetter(config, field))
//                        .setTooltip(fieldTooltip(field, categoryName))
//                        .setDefaultValue((List<String>) fieldGet(defaultValues, field)).build());
//            }
        }
    }
}