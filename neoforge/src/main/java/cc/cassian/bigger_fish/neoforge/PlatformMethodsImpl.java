package cc.cassian.bigger_fish.neoforge;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.Tags;

import java.nio.file.Path;

public class PlatformMethodsImpl {

    public static Path getConfigFolder() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static boolean isModLoaded(String modID) {
        return ModList.get().isLoaded(modID);
    }

    public static boolean isFishingRod(ItemStack stack) {
        return stack.is(Tags.Items.TOOLS_FISHING_ROD);
    }

    public static void makeFireproof(ItemEntity itemEntity) {
        itemEntity.setData(BiggerFishNeoForge.FIREPROOF, true);
    }

    public static Boolean isFireproof(ItemEntity itemEntity) {
        return itemEntity.getExistingData(BiggerFishNeoForge.FIREPROOF).orElse(false);
    }

    public static void makeLavaHook(FishingHook hook) {
        hook.setData(BiggerFishNeoForge.LAVA_HOOK, true);
    }

    public static Boolean isLavaHook(FishingHook hook) {
        return hook.getExistingData(BiggerFishNeoForge.LAVA_HOOK).orElse(false);
    }
}
