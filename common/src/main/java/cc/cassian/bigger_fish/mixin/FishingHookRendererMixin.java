package cc.cassian.bigger_fish.mixin;

import cc.cassian.bigger_fish.PlatformMethods;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FishingHookRenderer.class)
public class FishingHookRendererMixin {

    @WrapOperation(method = "getPlayerHandPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getMainHandItem()Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack allowModdedRodsInMainhand(Player instance, Operation<ItemStack> original) {
        if (PlatformMethods.isFishingRod(instance.getMainHandItem())) {
            return Items.FISHING_ROD.getDefaultInstance();
        }
        else return original.call(instance);
    }
}
