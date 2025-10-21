package cc.cassian.bigger_fish;

//? fabric {
import cc.cassian.bigger_fish.fabric.BiggerFishFabric;
import cc.cassian.bigger_fish.fabric.FabricPlatformImpl;
import net.fabricmc.loader.api.FabricLoader;
//?}
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FishingHook;

import java.nio.file.Path;
//? neoforge {
/*import cc.cassian.bigger_fish.neoforge.NeoforgePlatformImpl;
*///?}

public interface Platform {

    //? fabric {
    Platform INSTANCE = new FabricPlatformImpl();
    //?}
    //? neoforge {
    /*Platform INSTANCE = new NeoforgePlatformImpl();
    *///?}


    boolean isModLoaded(String modid);

    String loader();

    Path getConfigFolder();

    void makeFireproof(ItemEntity itemEntity);

    Boolean isFireproof(ItemEntity itemEntity);

    void setHookData(FishingHook hook, String data);

    String getHookData(FishingHook hook);

}
