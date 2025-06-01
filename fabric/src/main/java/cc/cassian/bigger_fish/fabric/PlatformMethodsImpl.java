package cc.cassian.bigger_fish.fabric;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;

public class PlatformMethodsImpl {
    public static boolean isFishingRod(ItemStack stack) {
        return stack.is(ConventionalItemTags.FISHING_ROD_TOOLS);
    }

    public static void makeFireproof(ItemEntity itemEntity) {
        itemEntity.setAttached(BiggerFishFabric.FIREPROOF, true);
    }

    public static Boolean isFireproof(ItemEntity itemEntity) {
        return itemEntity.getAttachedOrElse(BiggerFishFabric.FIREPROOF, false);
    }

    public static void makeLavaHook(FishingHook hook) {
        hook.setAttached(BiggerFishFabric.LAVA_HOOK, true);
    }

    public static Boolean isLavaHook(FishingHook hook) {
        return hook.getAttachedOrElse(BiggerFishFabric.LAVA_HOOK, false);
    }
}
