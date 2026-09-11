package cc.cassian.bigger_fish.helpers;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.Platform;
import cc.cassian.bigger_fish.client.BiggerFishModClient;
import cc.cassian.bigger_fish.components.FishingLoot;
import cc.cassian.bigger_fish.components.HookEffects;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import cc.cassian.bigger_fish.registry.BiggerFishLootTables;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import cc.cassian.bigger_fish.components.FishSize;
//? fabric
import net.fabricmc.fabric.api.item.v1.FabricTooltipFlag;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.locale.Language;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ModHelpers {

    public static FishSize getRandomFishSize(RandomSource random) {
        var size = random.nextIntBetweenInclusive(1, 15)*Math.log(random.nextIntBetweenInclusive(1, 160));
        if (random.nextIntBetweenInclusive(0, 100) > 60) {
            size = size*.5;
        }
        return new FishSize((float) (Math.round(size * 10d) / 10d));
    }

    public static ItemStack setRandomFishSize(ItemStack itemStack, RandomSource hook) {
        if (itemStack.is(BiggerFishTags.FISH) && BiggerFishMod.CONFIG.gameplay.fishSizes.value()) {
			itemStack.set(BiggerFishComponentTypes.SIZE.get(), ModHelpers.getRandomFishSize(hook));
        }
        return itemStack;
    }

    public static String getUnit() {
        if (BiggerFishMod.CONFIG.tooltip.centimeters.value()) {
            return Language.getInstance().getOrDefault("component.bigger_fish.size.cm");
        } else {
            return Language.getInstance().getOrDefault("component.bigger_fish.size.inch");
        }
    }

    public static String getFishSize(FishSize size) {
		if (BiggerFishMod.CONFIG.tooltip.centimeters.value()) {
            return "%s".formatted(Math.round(size.size() * 2.54 * 10d) / 10d);
        } else {
            return "%s".formatted(size.size());
        }
    }

    public static List<ItemStack> toCollection(List<Item> fish) {
        List<ItemStack> list = new ArrayList<>();
        for (Item itemDeferredSupplier : fish) {
            list.add(itemDeferredSupplier.getDefaultInstance());
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
                return bundleContents.items().getFirst().getOrDefault(BiggerFishComponentTypes.HOOK_EFFECTS.get(), HookEffects.VANILLA).effect();
        }
        return "";
    }

    public static Boolean isLavaHook(FishingHook hook) {
        return Platform.INSTANCE.getHookData(hook).equals("netherite");
    }

    public static boolean hasShiftDown(TooltipFlag tooltipFlag) {
        //? fabric {
		if (tooltipFlag instanceof FabricTooltipFlag fabricTooltipFlag && fabricTooltipFlag.shouldDisplayAllInformation())
			return true;
        //?}
        return BiggerFishModClient.hasShiftDown();
    }

    public static @Nullable LootTable fish(ReloadableServerRegistries.Holder reloadableRegistries, @Nullable ItemStack bait, boolean isLavaHook, boolean catchesBiggerFish) {
        ResourceKey<LootTable> lootTable = lootTableByBait(bait, isLavaHook, catchesBiggerFish);
        if (lootTable != null) {
            return reloadableRegistries.getLootTable(lootTable);
        }
        return null;
    }

    private static @Nullable ResourceKey<LootTable> lootTableByBait(@Nullable ItemStack bait, boolean isLavaHook, boolean catchesBiggerFish) {
        if (isLavaHook) {
            return BiggerFishLootTables.LAVA_FISHING;
        }
        if (BiggerFishMod.CONFIG.gameplay.biomeFishing.value() || catchesBiggerFish) {
            if (bait != null) {
                // check for the fishing loot table component
                if (bait.has(BiggerFishComponentTypes.FISHING_LOOT.get())) {
                    FishingLoot identifier = bait.get(BiggerFishComponentTypes.FISHING_LOOT.get());
                    assert identifier != null;
                    return ResourceKey.create(Registries.LOOT_TABLE, identifier.lootTable());
                }
                // most fishing is done via components, these are here as fallbacks for modded content
                else if (bait.is(BiggerFishTags.TIER_ONE_BAIT)) {
                    return BiggerFishLootTables.TIER_ONE_FISHING;
                } else if (bait.is(BiggerFishTags.TIER_TWO_BAIT)) {
                    return BiggerFishLootTables.TIER_TWO_FISHING;
                } else if (bait.is(BiggerFishTags.TIER_THREE_BAIT)) {
                    return BiggerFishLootTables.TIER_THREE_FISHING;
                } else {
                    return BiggerFishLootTables.FISHING;
                }
            } else if (BiggerFishMod.CONFIG.gameplay.preventFishingWithoutBait.value()) {
                return BuiltInLootTables.FISHING_JUNK;
            }
            return BiggerFishLootTables.FISHING;
        } else {
            return null;
        }
    }

    public static void hurtOrRemoveHook(BundleContents.Mutable mutable, @Nullable Player player, Level level) {
        ItemStack itemStack = mutable.removeOne();
        if (itemStack != null) {
            if (itemStack.getCount() > 1) {
                itemStack.setCount(itemStack.getCount()-1);
                mutable.tryInsert(itemStack);
            }
            if (itemStack.isDamageableItem()) {
                int damageValue = itemStack.getDamageValue();
                BiggerFishMod.LOGGER.debug(damageValue);
                if (level instanceof ServerLevel serverLevel) {
                    ServerPlayer owner = null;
                    if (player instanceof ServerPlayer serverPlayer)
                        owner = serverPlayer;
                    itemStack.hurtAndBreak(1, serverLevel, owner, (item)->{});
                    mutable.tryInsert(itemStack);
                }
            }
        }
    }

	public static @Nullable ItemStack getBaitFromRod(ItemStack fishingRod) {
        if (fishingRod.has(DataComponents.BUNDLE_CONTENTS)) {
            BundleContents bundleContents = fishingRod.get(DataComponents.BUNDLE_CONTENTS);
            if (bundleContents != null && !bundleContents.isEmpty())
                return bundleContents.items().getFirst().create();
        }
        return null;
	}
}
