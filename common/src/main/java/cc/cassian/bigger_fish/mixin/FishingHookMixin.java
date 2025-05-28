package cc.cassian.bigger_fish.mixin;

import cc.cassian.bigger_fish.PlatformMethods;
import cc.cassian.bigger_fish.config.ModConfig;
import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import cc.cassian.bigger_fish.registry.BiggerFishLootTables;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(FishingHook.class)
public class FishingHookMixin {
    @Shadow @Nullable
    private Entity hookedIn;

    @Shadow private int nibble;

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

    @Inject(method = "retrieve", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/ItemEntity;<init>(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;)V"))
    private void randomizedFish(ItemStack fishingRod, CallbackInfoReturnable<Integer> cir, @Local(ordinal = 1) LocalRef<ItemStack> stackLocalRef) {
        if (ModConfig.get().fishSizes) {
            var hook =  (FishingHook) (Object) this;
            ItemStack fishStack = stackLocalRef.get();
            if (fishStack.is(ItemTags.FISHES)) {
                stackLocalRef.set(ModHelpers.setRandomFishSize(fishStack, hook));
            }
        }
    }
    @Inject(method = "retrieve", at = @At(value = "RETURN", target = "Lnet/minecraft/world/entity/item/ItemEntity;<init>(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;)V"))
    private void removeBait(ItemStack fishingRod, CallbackInfoReturnable<Integer> cir) {
        if (fishingRod.has(DataComponents.BUNDLE_CONTENTS)) {
            BundleContents bundleContents = fishingRod.get(DataComponents.BUNDLE_CONTENTS);
            if (bundleContents != null && !bundleContents.isEmpty()) {
                BundleContents.Mutable mutable = new BundleContents.Mutable(bundleContents);
                ItemStack itemStack = mutable.removeOne();
                if (itemStack != null && itemStack.getCount() > 1) {
                    itemStack.setCount(itemStack.getCount()-1);
                    mutable.tryInsert(itemStack);
                }
                fishingRod.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
            }
        }
    }
}
