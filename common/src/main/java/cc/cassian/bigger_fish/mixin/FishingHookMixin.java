package cc.cassian.bigger_fish.mixin;

import cc.cassian.bigger_fish.PlatformMethods;
import cc.cassian.bigger_fish.config.ModConfig;
import cc.cassian.bigger_fish.helpers.ModHelpers;
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
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
        var hook = (FishingHook) (Object) this;
        var pos = hook.blockPosition();
        FluidState fluidstate = hook.level().getFluidState(pos);
        FluidState fluidstateBelow = hook.level().getFluidState(pos.below());
        if (fluidstate.is(FluidTags.LAVA) || fluidstateBelow.is(FluidTags.LAVA)) {
            return instance.getLootTable(BiggerFishLootTables.LAVA_FISHING);
        }
        if (ModConfig.get().biomeFishing) {
            if (pos.getY() > 32) {
                return instance.getLootTable(BiggerFishLootTables.FISHING);
            } else {
                return instance.getLootTable(BiggerFishLootTables.CAVE_FISHING);
            }
        } else {
            return original.call(instance, lootTableKey);
        }
    }

    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;is(Lnet/minecraft/tags/TagKey;)Z"))
    private boolean biomeSpecificFish(FluidState instance, TagKey<Fluid> tag, Operation<Boolean> original) {
        var hook = (FishingHook) (Object) this;
        if ((hook.getPlayerOwner().getMainHandItem().is(BiggerFishTags.CAN_FISH_IN_LAVA) || hook.getPlayerOwner().getOffhandItem().is(BiggerFishTags.CAN_FISH_IN_LAVA)) && instance.is(FluidTags.LAVA)) {
            return true;
        } else {
            return original.call(instance, tag);
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

    @Inject(method = "retrieve", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/ItemEntity;setDeltaMovement(DDD)V"))
    private void fireproofItems(ItemStack fishingRod, CallbackInfoReturnable<Integer> cir, @Local ItemEntity itemEntity) {
        var hook = (FishingHook) (Object) this;
        if ((hook.getPlayerOwner().getMainHandItem().is(BiggerFishTags.CAN_FISH_IN_LAVA) || hook.getPlayerOwner().getOffhandItem().is(BiggerFishTags.CAN_FISH_IN_LAVA)) && hook.level().getFluidState(hook.blockPosition()).is(FluidTags.LAVA)) {
            PlatformMethods.makeFireproof(itemEntity);
        }
    }
}
