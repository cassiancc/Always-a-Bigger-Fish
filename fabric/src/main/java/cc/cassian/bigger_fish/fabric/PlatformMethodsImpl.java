package cc.cassian.bigger_fish.fabric;

import cc.cassian.bigger_fish.PlatformMethods;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;

import java.nio.file.Path;

/**
 * See {@link PlatformMethods} for usages.
 */
@SuppressWarnings("unused")
public class PlatformMethodsImpl {

    public static Path getConfigFolder() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public static boolean isModLoaded(String modID) {
        return FabricLoader.getInstance().isModLoaded(modID);
    }

    public static void makeFireproof(ItemEntity itemEntity) {
        itemEntity.setAttached(BiggerFishFabric.FIREPROOF, true);
    }

    public static Boolean isFireproof(ItemEntity itemEntity) {
        return itemEntity.getAttachedOrElse(BiggerFishFabric.FIREPROOF, false);
    }

    public static void setHookData(FishingHook hook, String data) {
        hook.setAttached(BiggerFishFabric.HOOK, data);
    }

    public static String getHookData(FishingHook hook) {
        return hook.getAttachedOrElse(BiggerFishFabric.HOOK, "vanilla");
    }
}
