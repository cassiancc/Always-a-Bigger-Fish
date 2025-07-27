package cc.cassian.bigger_fish;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FishingHook;

import java.nio.file.Path;

public class PlatformMethods {

    @ExpectPlatform
    public static Path getConfigFolder() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isModLoaded(String modID) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void makeFireproof(ItemEntity itemEntity) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Boolean isFireproof(ItemEntity itemEntity) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void setHookData(FishingHook hook, String data) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static String getHookData(FishingHook hook) {
        throw new AssertionError();
    }
}
