package cc.cassian.bigger_fish.mixin.fabric;


import cc.cassian.bigger_fish.registry.BiggerFishItems;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FishingHookRenderer.class)
public class FishingHookRendererMixin {

	//? fabric {
	@WrapOperation(method = "getPlayerHandPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 0))
	private boolean allowModdedRodsInMainhand(ItemStack instance, Item item, Operation<Boolean> original) {
		return instance.is(BiggerFishItems.COPPER_ROD.get()) || original.call(instance, item);
	}
	//?}
}