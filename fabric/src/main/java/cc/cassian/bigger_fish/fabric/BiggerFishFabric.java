package cc.cassian.bigger_fish.fabric;

import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import cc.cassian.bigger_fish.registry.BiggerFishLootTables;
import com.mojang.serialization.Codec;
import net.fabricmc.api.ModInitializer;

import cc.cassian.bigger_fish.BiggerFishMod;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;

import static cc.cassian.bigger_fish.BiggerFishMod.MOD_ID;

public final class BiggerFishFabric implements ModInitializer {

    public static final AttachmentType<Boolean> FIREPROOF = AttachmentRegistry.createPersistent(
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "fireproof"), Codec.BOOL
    );

    public static final AttachmentType<Boolean> LAVA_HOOK = AttachmentRegistry.createPersistent(
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "lava_hook"), Codec.BOOL
    );

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        BiggerFishMod.init();

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register((itemGroup) -> {
            itemGroup.addAfter(Items.FISHING_ROD, BiggerFishItems.NETHERITE_ROD.get());
            itemGroup.addAfter(Items.FISHING_ROD, BiggerFishItems.COPPER_ROD.get());
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register((itemGroup) -> {
            itemGroup.addAfter(Items.PUFFERFISH, ModHelpers.toCollection(BiggerFishItems.FISH));
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
