package cc.cassian.bigger_fish;

import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTabOutput;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

public class CommonEvents {
	//? fabric {
	public static void modifyOutput(CreativeModeTab creativeModeTab, FabricCreativeModeTabOutput tab) {
		ResourceKey<CreativeModeTab> tabKey = BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(creativeModeTab).orElse(null);
		if (tabKey == null) {
			return;
		}
	//?} else {
	/*public static void modifyOutput(ResourceKey<CreativeModeTab> creativeModeTab, FabricCreativeModeTabOutput tab) {
	*///?}
		if (tabKey.equals(CreativeModeTabs.TOOLS_AND_UTILITIES))
			tab.insertAfter(Items.FISHING_ROD, ModHelpers.toCollection(BiggerFishItems.TOOLS));
		else if (tabKey.equals(CreativeModeTabs.FOOD_AND_DRINKS)) {
			var group = ModHelpers.toCollection(BiggerFishItems.FISH);
			group.addAll(ModHelpers.toCollection(BiggerFishItems.FOOD));
			tab.insertAfter(Items.PUFFERFISH, group);
		}
		else if (tabKey.equals(CreativeModeTabs.INGREDIENTS)) {
			tab.insertAfter(Items.BONE_MEAL, ModHelpers.toCollection(BiggerFishItems.INGREDIENTS));
		}
	}
}
