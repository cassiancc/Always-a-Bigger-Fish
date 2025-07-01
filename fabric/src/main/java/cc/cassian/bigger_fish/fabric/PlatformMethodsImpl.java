package cc.cassian.bigger_fish.fabric;

import cc.cassian.bigger_fish.IPlatformMethods;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;

import java.nio.file.Path;

public class PlatformMethodsImpl implements IPlatformMethods {

    public Path getConfigFolder() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public boolean isModLoaded(String modID) {
        return FabricLoader.getInstance().isModLoaded(modID);
    }

    public boolean isFishingRod(ItemStack stack) {
        return stack.is(ConventionalItemTags.FISHING_ROD_TOOLS);
    }

    public void makeFireproof(ItemEntity itemEntity) {
        itemEntity.setAttached(BiggerFishFabric.FIREPROOF, true);
    }

    public Boolean isFireproof(ItemEntity itemEntity) {
        return itemEntity.getAttachedOrElse(BiggerFishFabric.FIREPROOF, false);
    }

    public void makeLavaHook(FishingHook hook) {
        hook.setAttached(BiggerFishFabric.LAVA_HOOK, true);
    }

    public Boolean isLavaHook(FishingHook hook) {
        return hook.getAttachedOrElse(BiggerFishFabric.LAVA_HOOK, false);
    }
}
