package cc.cassian.bigger_fish.neoforge;

import cc.cassian.bigger_fish.config.neoforge.ModConfigFactory;
import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;

import cc.cassian.bigger_fish.BiggerFishMod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(BiggerFishMod.MOD_ID)
public final class BiggerFishNeoForge {
    public BiggerFishNeoForge(IEventBus eventBus, ModContainer container) {
        // Run our common setup.
        BiggerFishMod.init();
        eventBus.addListener(BiggerFishNeoForge::registerCreativeTabs);
        registerModsPage();
    }

    @SubscribeEvent
    public static void registerCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            event.insertAfter(Items.FISHING_ROD.getDefaultInstance(), BiggerFishItems.COPPER_ROD.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey().equals(CreativeModeTabs.FOOD_AND_DRINKS)) {
            for (ItemStack itemStack : ModHelpers.toCollection(BiggerFishItems.FISH).reversed()) {
                event.insertAfter(Items.PUFFERFISH.getDefaultInstance(), itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
        }
    }

    public void registerModsPage() {
        if (ModList.get().isLoaded("cloth_config")) ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, ModConfigFactory::new);
    }
}
