package cc.cassian.bigger_fish.mixin.fishing_hook;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FishingHook.class)
public class FishingCausesBubblesMixin {
    @WrapOperation(method = "catchingFish", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    private boolean alwaysBubbleInFluids(BlockState instance, Block block, Operation<Boolean> original) {
        return instance.getFluidState().is(FluidTags.LAVA) || instance.getFluidState().is(FluidTags.WATER) || original.call(instance, block);
    }
}
