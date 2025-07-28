package cc.cassian.bigger_fish.mixin;

import cc.cassian.bigger_fish.minigame.MinigameLayer;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.world.entity.projectile.FishingHook;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FishingHookRenderer.class)
public class MinigameRendererMixin {
    @WrapMethod(method = "shouldRender(Lnet/minecraft/world/entity/projectile/FishingHook;Lnet/minecraft/client/renderer/culling/Frustum;DDD)Z")
    public boolean shouldRender(FishingHook fishingHook, Frustum frustum, double d, double e, double f, Operation<Boolean> original) {
        if (MinigameLayer.minigameActive) return true;
        return original.call(fishingHook, frustum, d, e, f);
    }
}
