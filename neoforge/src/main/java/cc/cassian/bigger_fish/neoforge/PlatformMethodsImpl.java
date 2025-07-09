package cc.cassian.bigger_fish.neoforge;

import cc.cassian.bigger_fish.PlatformMethods;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.Tags;

import java.nio.file.Path;

/**
 * See {@link PlatformMethods} for usages.
 */
@SuppressWarnings("unused")
public class PlatformMethodsImpl {

    public static Path getConfigFolder() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static boolean isModLoaded(String modID) {
        return ModList.get().isLoaded(modID);
    }

    public static void makeFireproof(ItemEntity itemEntity) {
        itemEntity.setData(BiggerFishNeoForge.FIREPROOF, true);
    }

    public static Boolean isFireproof(ItemEntity itemEntity) {
        return itemEntity.getExistingData(BiggerFishNeoForge.FIREPROOF).orElse(false);
    }

    public static void setHookData(FishingHook hook, String data) {
        hook.setData(BiggerFishNeoForge.HOOK, data);
    }

    public static String getHookData(FishingHook hook) {
        return hook.getExistingData(BiggerFishNeoForge.HOOK).orElse("vanilla");
    }
}
