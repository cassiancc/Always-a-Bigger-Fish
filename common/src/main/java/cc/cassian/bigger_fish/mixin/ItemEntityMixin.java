package cc.cassian.bigger_fish.mixin;

import cc.cassian.bigger_fish.PlatformMethods;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    @WrapMethod(method = "fireImmune")
    private boolean fireproofItems(Operation<Boolean> original) {
        var itemEntity = (ItemEntity) (Object) this;
        return PlatformMethods.isFireproof(itemEntity) || original.call();
    }
}
