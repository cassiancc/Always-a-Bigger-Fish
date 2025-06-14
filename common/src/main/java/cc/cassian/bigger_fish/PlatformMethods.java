package cc.cassian.bigger_fish;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;

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
    public static boolean isFishingRod(ItemStack stack) {
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
    public static void makeLavaHook(FishingHook hook) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Boolean isLavaHook(FishingHook hook) {
        throw new AssertionError();
    }
}
