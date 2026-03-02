package cc.cassian.bigger_fish;

import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTabOutput;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.Container;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.component.ItemContainerContents;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

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

	public static AtomicBoolean tryInsertingIntoFishBarrel(Inventory inventory, ItemEntity itemEntity) {
		AtomicBoolean preventDefault = new AtomicBoolean(false);
		Predicate<ItemStack> fishContainers = (stack) -> stack.is(BiggerFishTags.FISH_CONTAINERS);
		ItemStack fish = itemEntity.getItem();
		if (fish.is(BiggerFishTags.FISH) && inventory.hasAnyMatching(fishContainers)) {
			inventory.getNonEquipmentItems().stream().filter(fishContainers).findFirst().ifPresent(fishContainer -> {
				if (!preventDefault.get()) {
					int index = inventory.findSlotMatchingItem(fishContainer);
					if (fishContainer.has(DataComponents.BUNDLE_CONTENTS)) {
						BundleContents bundleContents = fishContainer.get(DataComponents.BUNDLE_CONTENTS);
						assert bundleContents != null;
						BundleContents.Mutable mutable = new BundleContents.Mutable(bundleContents);
						fish.setCount(fishContainer.getCount() - mutable.tryInsert(fish));
						fishContainer.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
						itemEntity.setItem(fish);
						inventory.setItem(index, fishContainer);
						preventDefault.set(true);
					}
				}
			});
		}
		return preventDefault;
	}
}
