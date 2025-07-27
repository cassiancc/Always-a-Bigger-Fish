package cc.cassian.bigger_fish.mixin.fishing_hook;

import cc.cassian.bigger_fish.PlatformMethods;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingHook.class)
public class FireproofItemsMixin {

    @Inject(method = "retrieve", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/ItemEntity;setDeltaMovement(DDD)V"))
    private void fireproofItems(ItemStack fishingRod, CallbackInfoReturnable<Integer> cir, @Local ItemEntity itemEntity) {
        var hook = (FishingHook) (Object) this;
        if (fishingRod.is(BiggerFishTags.CAN_FISH_IN_LAVA) && hook.level().getFluidState(hook.blockPosition()).is(FluidTags.LAVA)) {
            PlatformMethods.makeFireproof(itemEntity);
        }
    }
}
