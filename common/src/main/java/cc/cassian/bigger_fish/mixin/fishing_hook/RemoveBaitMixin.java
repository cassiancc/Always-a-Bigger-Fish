package cc.cassian.bigger_fish.mixin.fishing_hook;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingHook.class)
public class RemoveBaitMixin {
    @Inject(method = "retrieve", at = @At(value = "RETURN", target = "Lnet/minecraft/world/entity/item/ItemEntity;<init>(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;)V"))
    private void removeBait(ItemStack fishingRod, CallbackInfoReturnable<Integer> cir) {
        if (fishingRod.has(DataComponents.BUNDLE_CONTENTS)) {
            BundleContents bundleContents = fishingRod.get(DataComponents.BUNDLE_CONTENTS);
            if (bundleContents != null && !bundleContents.isEmpty()) {
                BundleContents.Mutable mutable = new BundleContents.Mutable(bundleContents);
                ItemStack itemStack = mutable.removeOne();
                if (itemStack != null && itemStack.getCount() > 1) {
                    itemStack.setCount(itemStack.getCount()-1);
                    mutable.tryInsert(itemStack);
                }
                fishingRod.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
            }
        }
    }
}
