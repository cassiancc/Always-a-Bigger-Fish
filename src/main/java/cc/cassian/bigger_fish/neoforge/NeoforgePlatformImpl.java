package cc.cassian.bigger_fish.neoforge;

//? neoforge {
/*import cc.cassian.bigger_fish.Platform;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public class NeoforgePlatformImpl implements Platform {

    @Override
    public boolean isModLoaded(String modid) {
        return ModList.get().isLoaded(modid);
    }

    @Override
    public String loader() {
        return "neoforge";
    }

    public Path getConfigFolder() {
        return FMLPaths.CONFIGDIR.get();
    }

    public void makeFireproof(ItemEntity itemEntity) {
        itemEntity.setData(BiggerFishNeoForge.FIREPROOF, true);
    }

    public Boolean isFireproof(ItemEntity itemEntity) {
        return itemEntity.getExistingData(BiggerFishNeoForge.FIREPROOF).orElse(false);
    }

    public void setHookData(FishingHook hook, String data) {
        hook.setData(BiggerFishNeoForge.HOOK, data);
    }

    public String getHookData(FishingHook hook) {
        return hook.getExistingData(BiggerFishNeoForge.HOOK).orElse("vanilla");
    }

}
*///?}