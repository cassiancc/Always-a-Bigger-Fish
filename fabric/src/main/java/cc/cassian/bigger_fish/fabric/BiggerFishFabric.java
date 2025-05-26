package cc.cassian.bigger_fish.fabric;

import cc.cassian.bigger_fish.registry.BiggerFishItems;
import net.fabricmc.api.ModInitializer;

import cc.cassian.bigger_fish.BiggerFishMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

public final class BiggerFishFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        BiggerFishMod.init();

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register((itemGroup) -> {
            itemGroup.addAfter(Items.FISHING_ROD, BiggerFishItems.COPPER_ROD.get());
        });
    }
}
