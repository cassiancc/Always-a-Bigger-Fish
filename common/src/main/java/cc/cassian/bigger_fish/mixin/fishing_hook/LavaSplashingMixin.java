package cc.cassian.bigger_fish.mixin.fishing_hook;

import cc.cassian.bigger_fish.PlatformMethods;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.projectile.FishingHook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FishingHook.class)
public class LavaSplashingMixin {
    @WrapOperation(method = "catchingFish", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;sendParticles(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I", ordinal = 0))
    private int lavaBubble0(ServerLevel instance, ParticleOptions type, double posX, double posY, double posZ, int particleCount, double xOffset, double yOffset, double zOffset, double speed, Operation<Integer> original) {
        var hook = (FishingHook) (Object) this;
        if (PlatformMethods.isLavaHook(hook)) {
            return original.call(instance, ParticleTypes.LAVA, posX, posY, posZ, particleCount, xOffset, yOffset, zOffset, speed);
        } else {
            return original.call(instance, type, posX, posY, posZ, particleCount, xOffset, yOffset, zOffset, speed);
        }
    }

    @WrapOperation(method = "catchingFish", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;sendParticles(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I", ordinal = 1))
    private int lavaFishing1(ServerLevel instance, ParticleOptions type, double posX, double posY, double posZ, int particleCount, double xOffset, double yOffset, double zOffset, double speed, Operation<Integer> original) {
        var hook = (FishingHook) (Object) this;
        if (PlatformMethods.isLavaHook(hook)) {
            return original.call(instance, ParticleTypes.LAVA, posX, posY, posZ, particleCount, xOffset, yOffset, zOffset, speed);
        } else {
            return original.call(instance, type, posX, posY, posZ, particleCount, xOffset, yOffset, zOffset, speed);
        }
    }

    @WrapOperation(method = "catchingFish", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;sendParticles(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I", ordinal = 2))
    private int lavaFishing2(ServerLevel instance, ParticleOptions type, double posX, double posY, double posZ, int particleCount, double xOffset, double yOffset, double zOffset, double speed, Operation<Integer> original) {
        var hook = (FishingHook) (Object) this;
        if (PlatformMethods.isLavaHook(hook)) {
            return original.call(instance, ParticleTypes.LAVA, posX, posY, posZ, particleCount, xOffset, yOffset, zOffset, speed);
        } else {
            return original.call(instance, type, posX, posY, posZ, particleCount, xOffset, yOffset, zOffset, speed);
        }
    }

    @WrapOperation(method = "catchingFish", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;sendParticles(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I", ordinal = 3))
    private int lavaBubble3(ServerLevel instance, ParticleOptions type, double posX, double posY, double posZ, int particleCount, double xOffset, double yOffset, double zOffset, double speed, Operation<Integer> original) {
        var hook = (FishingHook) (Object) this;
        if (PlatformMethods.isLavaHook(hook)) {
            return original.call(instance, ParticleTypes.LAVA, posX, posY, posZ, particleCount, xOffset, yOffset, zOffset, speed);
        } else {
            return original.call(instance, type, posX, posY, posZ, particleCount, xOffset, yOffset, zOffset, speed);
        }
    }

    @WrapOperation(method = "catchingFish", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;sendParticles(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I", ordinal = 4))
    private int lavaFishing4(ServerLevel instance, ParticleOptions type, double posX, double posY, double posZ, int particleCount, double xOffset, double yOffset, double zOffset, double speed, Operation<Integer> original) {
        var hook = (FishingHook) (Object) this;
        if (PlatformMethods.isLavaHook(hook)) {
            return original.call(instance, ParticleTypes.LAVA, posX, posY, posZ, particleCount, xOffset, yOffset, zOffset, speed);
        } else {
            return original.call(instance, type, posX, posY, posZ, particleCount, xOffset, yOffset, zOffset, speed);
        }
    }

    @WrapOperation(method = "catchingFish", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;sendParticles(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I", ordinal = 5))
    private int lavaSplash5(ServerLevel instance, ParticleOptions type, double posX, double posY, double posZ, int particleCount, double xOffset, double yOffset, double zOffset, double speed, Operation<Integer> original) {
        var hook = (FishingHook) (Object) this;
        if (PlatformMethods.isLavaHook(hook)) {
            return original.call(instance, ParticleTypes.LAVA, posX, posY, posZ, particleCount, xOffset, yOffset, zOffset, speed);
        } else {
            return original.call(instance, type, posX, posY, posZ, particleCount, xOffset, yOffset, zOffset, speed);
        }
    }
}
