package cc.cassian.bigger_fish.mixin.fishing_hook;

import cc.cassian.bigger_fish.registry.BiggerFishTags;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FishingHook.class)
public class MinigameMixin {
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
}
