package cc.cassian.bigger_fish.mixin.fishing_hook;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.helpers.ModHelpers;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingHook.class)
public class FishSizingMixin {
    @Inject(method = "retrieve", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/ItemEntity;<init>(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;)V"))
    private void randomizedFish(ItemStack fishingRod, CallbackInfoReturnable<Integer> cir, @Local(ordinal = 1) LocalRef<ItemStack> stackLocalRef) {
        if (BiggerFishMod.CONFIG.gameplay.fishSizes.value()) {
            var hook =  (FishingHook) (Object) this;
            ItemStack fishStack = stackLocalRef.get();
            if (fishStack.is(ItemTags.FISHES)) {
                stackLocalRef.set(ModHelpers.setRandomFishSize(fishStack, hook));
            }
        }
    }
}
