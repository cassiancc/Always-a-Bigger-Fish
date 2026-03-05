package cc.cassian.bigger_fish.mixin.fishing_hook;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import cc.cassian.bigger_fish.registry.BiggerFishLootTables;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FishingHook.class)
public class BiomeSpecificFishMixin {
    @WrapOperation(method = "retrieve", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/ReloadableServerRegistries$Holder;getLootTable(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/level/storage/loot/LootTable;"))
    private LootTable biomeSpecificFish(ReloadableServerRegistries.Holder instance, ResourceKey<LootTable> lootTableKey, Operation<LootTable> original, ItemStack fishingRod) {
        LootTable fish = ModHelpers.fish(instance, ModHelpers.getBaitFromRod(fishingRod), ModHelpers.isLavaHook((FishingHook) (Object) this), fishingRod.is(BiggerFishTags.CATCHES_BIGGER_FISH));
        if (fish != null) {
            return fish;
        }
        return original.call(instance, lootTableKey);
    }
}
