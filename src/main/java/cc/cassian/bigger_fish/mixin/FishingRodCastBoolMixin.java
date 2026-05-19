package cc.cassian.bigger_fish.mixin;

import cc.cassian.bigger_fish.access.PlayerWithGrapplingHook;
import cc.cassian.bigger_fish.items.BaitedRodItem;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import timmychips.modefiteitemdefinitions.property.resolver.condition.FishingRodCastBool;
import timmychips.modefiteitemdefinitions.property.type.codec.ConditionDefinition;

@Mixin(FishingRodCastBool.class)
public class FishingRodCastBoolMixin {

    // Make the fishing rod item properly render without a line
    @ModifyReturnValue(method = "getValue", at = @At("RETURN"))
    private static boolean grapplingHookCast(boolean original, ItemStack stack, LivingEntity entity, ConditionDefinition definition) {
        if (entity != null) {
            boolean isMainhand = entity.getMainHandItem() == stack;
            boolean isOffHand = entity.getOffhandItem() == stack;
            if (entity.getMainHandItem().getItem() instanceof BaitedRodItem) {
                isOffHand = false;
            }
            if ((isMainhand || isOffHand) && entity instanceof Player && ((PlayerWithGrapplingHook)entity).bigger_fish$getHook() != null) {
                return true;
            }
        }
        return original;
    }
}