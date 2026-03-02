package cc.cassian.bigger_fish.fabric;

//? if fabric {
import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.registry.*;
import com.mojang.serialization.Codec;
import net.fabricmc.api.ModInitializer;

import cc.cassian.bigger_fish.BiggerFishMod;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;

public final class BiggerFishFabric implements ModInitializer {

    public static final AttachmentType<Boolean> FIREPROOF = AttachmentRegistry.createPersistent(
            BiggerFishMod.of("fireproof"), Codec.BOOL
    );

    public static final AttachmentType<String> HOOK = AttachmentRegistry.createPersistent(
           BiggerFishMod.of("hook"), Codec.STRING
    );

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        BiggerFishMod.init();
        BiggerFishComponentTypes.touch();
        BiggerFishItems.touch();
        BiggerFishEntityTypes.touch();
        BiggerFishSoundEvents.touch();
        BiggerFishMobEffects.touch();

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register((itemGroup) -> {
            itemGroup.insertAfter(Items.FISHING_ROD, ModHelpers.toCollection(BiggerFishItems.TOOLS));
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register((itemGroup) -> {
            var group = ModHelpers.toCollection(BiggerFishItems.FISH);
            group.addAll(ModHelpers.toCollection(BiggerFishItems.FOOD));
            itemGroup.insertAfter(Items.PUFFERFISH, group);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register((itemGroup) -> {
            itemGroup.insertAfter(Items.BONE_MEAL, ModHelpers.toCollection(BiggerFishItems.INGREDIENTS));
        });
        LootTableEvents.MODIFY.register(((key, tableBuilder, source, registries) -> {
            if (key == BuiltInLootTables.FISHING_JUNK) {
                tableBuilder.modifyPools((builder -> {
                    builder.add(LootItem.lootTableItem(BiggerFishItems.FISH_BONES.get()).setWeight(15)).add(LootItem.lootTableItem(BiggerFishItems.CAN.get()).setWeight(15)).build();
                }));
            }
        }));
    }
}
//?}