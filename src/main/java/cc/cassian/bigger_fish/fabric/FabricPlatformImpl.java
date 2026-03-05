package cc.cassian.bigger_fish.fabric;

//? fabric {
import cc.cassian.bigger_fish.Platform;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FishingHook;

import java.nio.file.Path;

public class FabricPlatformImpl implements Platform {

    @Override
    public boolean isModLoaded(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }

    @Override
    public String loader() {
        return "fabric";
    }

    public Path getConfigFolder() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public void makeFireproof(ItemEntity itemEntity) {
        itemEntity.setAttached(BiggerFishFabric.FIREPROOF, true);
    }

    public Boolean isFireproof(ItemEntity itemEntity) {
        return itemEntity.getAttachedOrElse(BiggerFishFabric.FIREPROOF, false);
    }

    public void setHookData(FishingHook hook, String data) {
        hook.setAttached(BiggerFishFabric.HOOK, data);
    }

    public String getHookData(FishingHook hook) {
        return hook.getAttachedOrElse(BiggerFishFabric.HOOK, "vanilla");
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

}
//?}