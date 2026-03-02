package cc.cassian.bigger_fish.mixin.fish_barrel;

import cc.cassian.bigger_fish.CommonEvents;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

	@WrapOperation(method = "playerTouch", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;add(Lnet/minecraft/world/item/ItemStack;)Z"))
	private boolean pickupWithBarrel(Inventory inventory, ItemStack itemStack, Operation<Boolean> original) {
		AtomicBoolean cancel = CommonEvents.tryInsertingIntoFishBarrel(inventory, (ItemEntity) (Object) this);
		if (!cancel.get()) return original.call(inventory, itemStack);
		return true;
	}

}
