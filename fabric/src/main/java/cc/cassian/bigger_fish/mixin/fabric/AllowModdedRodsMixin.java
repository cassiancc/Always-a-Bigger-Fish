package cc.cassian.bigger_fish.mixin.fabric;

import cc.cassian.bigger_fish.PlatformMethods;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FishingHook.class)
public class AllowModdedRodsMixin {

    @WrapOperation(method = "shouldStopFishing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 0))
    private boolean allowModdedRodsInMainhand(ItemStack instance, Item item, Operation<Boolean> original) {
        return PlatformMethods.isFishingRod(instance) || original.call(instance, item);
    }

    @WrapOperation(method = "shouldStopFishing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 1))
    private boolean allowModdedRodsInOffhand(ItemStack instance, Item item, Operation<Boolean> original) {
        return PlatformMethods.isFishingRod(instance) || original.call(instance, item);
    }
}
