package cc.cassian.bigger_fish.mixin;

import cc.cassian.bigger_fish.IPlatformMethods;
import cc.cassian.bigger_fish.Platform;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    @WrapMethod(method = "fireImmune")
    private boolean fireproofItems(Operation<Boolean> original) {
        var itemEntity = (ItemEntity) (Object) this;
        return Platform.METHODS.isFireproof(itemEntity) || original.call();
    }
}
