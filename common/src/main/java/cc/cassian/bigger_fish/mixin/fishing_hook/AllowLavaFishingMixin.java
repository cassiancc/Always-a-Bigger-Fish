package cc.cassian.bigger_fish.mixin.fishing_hook;

import cc.cassian.bigger_fish.IPlatformMethods;
import cc.cassian.bigger_fish.Platform;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FishingHook.class)
public class AllowLavaFishingMixin {
    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;is(Lnet/minecraft/tags/TagKey;)Z"))
    private boolean allowLavaFishing(FluidState instance, TagKey<Fluid> tag, Operation<Boolean> original) {
        var hook = (FishingHook) (Object) this;
        if ((hook.getPlayerOwner().getMainHandItem().is(BiggerFishTags.CAN_FISH_IN_LAVA) || hook.getPlayerOwner().getOffhandItem().is(BiggerFishTags.CAN_FISH_IN_LAVA)) && instance.is(FluidTags.LAVA)) {
            Platform.METHODS.makeLavaHook(hook);
            return true;
        } else {
            return original.call(instance, tag);
        }
    }
}
