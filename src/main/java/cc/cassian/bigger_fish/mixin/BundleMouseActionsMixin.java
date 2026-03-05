package cc.cassian.bigger_fish.mixin;

import cc.cassian.bigger_fish.items.FishContainer;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.BundleMouseActions;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.component.BundleContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(BundleMouseActions.class)
public class BundleMouseActionsMixin {
	@WrapOperation(method = "matches", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/tags/TagKey;)Z"))
	private static boolean scrollContainers(ItemStack instance, TagKey<Item> tagKey, Operation<Boolean> original) {
		if (instance.is(BiggerFishTags.FISH_CONTAINERS)) return true;
		return original.call(instance, tagKey);
	}
}
