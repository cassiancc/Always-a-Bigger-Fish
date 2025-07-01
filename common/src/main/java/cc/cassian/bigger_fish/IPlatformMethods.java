package cc.cassian.bigger_fish;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;

import java.nio.file.Path;

public interface IPlatformMethods {

    
     Path getConfigFolder();

    
     boolean isModLoaded(String modID);

    
     boolean isFishingRod(ItemStack stack);

    
     void makeFireproof(ItemEntity itemEntity);

    
     Boolean isFireproof(ItemEntity itemEntity);

    
     void makeLavaHook(FishingHook hook);

    
     Boolean isLavaHook(FishingHook hook);
}
