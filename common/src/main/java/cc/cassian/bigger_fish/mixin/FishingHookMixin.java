package cc.cassian.bigger_fish.mixin;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.PlatformMethods;
import cc.cassian.bigger_fish.config.ModConfig;
import cc.cassian.bigger_fish.registry.BiggerFishLootTables;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FishingHook.class)
public class FishingHookMixin {
    @Shadow @Nullable
    private Entity hookedIn;

    @Shadow private int nibble;

    @WrapOperation(method = "shouldStopFishing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 0))
    private boolean allowModdedRodsInMainhand(ItemStack instance, Item item, Operation<Boolean> original) {
        return PlatformMethods.isFishingRod(instance) || original.call(instance, item);
    }

    @WrapOperation(method = "shouldStopFishing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 1))
    private boolean allowModdedRodsInOffhand(ItemStack instance, Item item, Operation<Boolean> original) {
        return PlatformMethods.isFishingRod(instance) || original.call(instance, item);
    }

    @WrapMethod(method = "retrieve")
    private int retrieveWithMinigame(ItemStack stack, Operation<Integer> original) {
        if (this.hookedIn == null && this.nibble > 0 && stack.is(BiggerFishTags.REQUIRES_MINIGAME_TO_CATCH)) {
            System.out.println("fishing with copper rod");
        }
        return original.call(stack);
    }

    @WrapOperation(method = "retrieve", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/ReloadableServerRegistries$Holder;getLootTable(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/level/storage/loot/LootTable;"))
    private LootTable biomeSpecificFish(ReloadableServerRegistries.Holder instance, ResourceKey<LootTable> lootTableKey, Operation<LootTable> original) {
        if (ModConfig.get().biomeFishing) {
            return instance.getLootTable(BiggerFishLootTables.FISHING);
        } else {
            return original.call(instance, lootTableKey);
        }
    }
}
