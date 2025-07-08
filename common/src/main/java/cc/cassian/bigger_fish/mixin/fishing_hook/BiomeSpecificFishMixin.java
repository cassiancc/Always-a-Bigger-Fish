package cc.cassian.bigger_fish.mixin.fishing_hook;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.PlatformMethods;
import cc.cassian.bigger_fish.config.ModConfig;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import cc.cassian.bigger_fish.registry.BiggerFishLootTables;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FishingHook.class)
public class BiomeSpecificFishMixin {
    @WrapOperation(method = "retrieve", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/ReloadableServerRegistries$Holder;getLootTable(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/level/storage/loot/LootTable;"))
    private LootTable biomeSpecificFish(ReloadableServerRegistries.Holder instance, ResourceKey<LootTable> lootTableKey, Operation<LootTable> original, ItemStack stack) {
        var hook = (FishingHook) (Object) this;
        if (PlatformMethods.isLavaHook(hook)) {
            return instance.getLootTable(BiggerFishLootTables.LAVA_FISHING);
        }
        if (BiggerFishMod.CONFIG.gameplay.biomeFishing.value() || stack.is(BiggerFishTags.CATCHES_BIGGER_FISH)) {
            if (stack.has(DataComponents.BUNDLE_CONTENTS)) {
                BundleContents bundleContents = stack.get(DataComponents.BUNDLE_CONTENTS);
                if (bundleContents != null && !bundleContents.isEmpty()) {
                    ItemStack itemUnsafe = bundleContents.getItemUnsafe(0);
                    // check for the fishing loot table component
                    if (itemUnsafe.has(BiggerFishComponentTypes.FISHING_LOOT.get())) {
                        return instance.getLootTable(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse(itemUnsafe.get(BiggerFishComponentTypes.FISHING_LOOT.get()))));
                    }
                    // most fishing is done via components, these are here as fallbacks for modded content
                    else if (itemUnsafe.is(BiggerFishTags.TIER_ONE_BAIT)) {
                        return instance.getLootTable(BiggerFishLootTables.TIER_ONE_FISHING);
                    } else if (itemUnsafe.is(BiggerFishTags.TIER_TWO_BAIT)) {
                        return instance.getLootTable(BiggerFishLootTables.TIER_TWO_FISHING);
                    } else if (itemUnsafe.is(BiggerFishTags.TIER_THREE_BAIT)) {
                        return instance.getLootTable(BiggerFishLootTables.TIER_THREE_FISHING);
                    } else {
                        return instance.getLootTable(BiggerFishLootTables.FISHING);
                    }
                }
            }
            return instance.getLootTable(BiggerFishLootTables.FISHING);
        } else {
            return original.call(instance, lootTableKey);
        }
    }
}
