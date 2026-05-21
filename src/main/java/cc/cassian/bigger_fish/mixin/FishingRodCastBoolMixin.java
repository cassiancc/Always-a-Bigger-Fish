package cc.cassian.bigger_fish.mixin;

import cc.cassian.bigger_fish.access.PlayerWithGrapplingHook;
import cc.cassian.bigger_fish.items.BaitedRodItem;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.conditional.FishingRodCast;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FishingRodCast.class)
public class FishingRodCastBoolMixin {

    // Make the fishing rod item properly render without a line
    @ModifyReturnValue(method = "get", at = @At("RETURN"))
    private static boolean grapplingHookCast(boolean original, final ItemStack stack, final @Nullable ClientLevel level, final @Nullable LivingEntity entity, final int seed, final ItemDisplayContext displayContext) {
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