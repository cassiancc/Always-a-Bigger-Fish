package cc.cassian.bigger_fish.mixin;

import cc.cassian.bigger_fish.registry.BiggerFishItems;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.renderer.item.BundleSelectedItemSpecialRenderer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.component.BundleContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(BundleSelectedItemSpecialRenderer.class)
public class BundleSelectedItemSpecialRendererMixin {
	@WrapOperation(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BundleItem;getSelectedItem(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStackTemplate;"))
	private static ItemStackTemplate baitedRodFullnessIsGood(ItemStack stack, Operation<ItemStackTemplate> original) {
        if (stack.is(BiggerFishItems.FISH_BARREL)) {
			List<ItemStackTemplate> items = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).items();
			if (!items.isEmpty())
				return items.getFirst();
		}
		return original.call(stack);
	}
}
