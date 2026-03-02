package cc.cassian.bigger_fish;

import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
//? fabric {
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTabOutput;
//?}
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BundleContents;
//? neoforge
//import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

import static net.minecraft.world.level.block.Block.popResourceFromFace;

public class CommonEvents {
	//? fabric {
	public static void modifyOutput(CreativeModeTab creativeModeTab, FabricCreativeModeTabOutput tab) {
		ResourceKey<CreativeModeTab> tabKey = BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(creativeModeTab).orElse(null);
		if (tabKey == null) {
			return;
		}
	//?} else {
	/*public static void modifyOutput(ResourceKey<CreativeModeTab> tabKey, BuildCreativeModeTabContentsEvent tab) {
	*///?}
		if (tabKey.equals(CreativeModeTabs.TOOLS_AND_UTILITIES))
			insertAfter(tab, Items.FISHING_ROD, ModHelpers.toCollection(BiggerFishItems.TOOLS));
		else if (tabKey.equals(CreativeModeTabs.FOOD_AND_DRINKS)) {
			var group = ModHelpers.toCollection(BiggerFishItems.FISH);
			group.addAll(ModHelpers.toCollection(BiggerFishItems.FOOD));
			insertAfter(tab, Items.PUFFERFISH, group);
		}
		else if (tabKey.equals(CreativeModeTabs.INGREDIENTS)) {
			insertAfter(tab, Items.BONE_MEAL, ModHelpers.toCollection(BiggerFishItems.INGREDIENTS));
		}
	}

	//? fabric {
	private static void insertAfter(FabricCreativeModeTabOutput tab, Item anchor, List<ItemStack> collection) {
		tab.insertAfter(anchor, collection);
	}
	//?}

	//? neoforge {
	/*private static void insertAfter(BuildCreativeModeTabContentsEvent tab, Item anchor, List<ItemStack> collection) {
		collection.reversed().forEach(stack -> {
			tab.insertAfter(anchor.getDefaultInstance(), stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
		});
	}
	*///?}

	public static void tryInsertingIntoFishBarrel(Inventory inventory, ItemEntity itemEntity) {
		Predicate<ItemStack> fishContainers = (stack) -> stack.is(BiggerFishTags.FISH_CONTAINERS);
		ItemStack fish = itemEntity.getItem();
		if (fish.is(BiggerFishTags.FISH) && inventory.hasAnyMatching(fishContainers)) {
			inventory.getNonEquipmentItems().stream().filter(fishContainers).findFirst().ifPresent(fishContainer -> {
				int index = inventory.findSlotMatchingItem(fishContainer);
				if (fishContainer.has(DataComponents.BUNDLE_CONTENTS)) {
					BundleContents bundleContents = fishContainer.get(DataComponents.BUNDLE_CONTENTS);
					assert bundleContents != null;
					BundleContents.Mutable mutable = new BundleContents.Mutable(bundleContents);
					int i = mutable.tryInsert(fish);
					fish.setCount(fish.getCount() - i);
					fishContainer.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
					itemEntity.setItem(fish);
					inventory.setItem(index, fishContainer);
				}
			});
		}
	}

	public static void giveToPlayer(Player player, InteractionHand interactionHand, Level level, BlockPos pos, Direction direction, ItemStack stack) {
		if (player != null) {
			if (interactionHand != null && player.getItemInHand(interactionHand).isEmpty()) {
				player.setItemInHand(interactionHand, stack);
			} else {
				player.addItem(stack);
			}
		} else {
			popResourceFromFace(level, pos, direction, stack);
		}
	}
}
