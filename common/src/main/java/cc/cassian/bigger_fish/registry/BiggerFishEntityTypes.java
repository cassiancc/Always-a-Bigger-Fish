package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.Platform;
import cc.cassian.bigger_fish.entity.LeechEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

public class BiggerFishEntityTypes {
    public static final Supplier<EntityType<LeechEntity>> LEECH = Platform.REGISTRY.registerEntity("leech", () -> (
            EntityType.Builder.<LeechEntity>of(LeechEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, BiggerFishMod.of("leech")))));

    public static void touch() {

    }
}
