package cc.cassian.bigger_fish.registry;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.entity.GrapplingHookEntity;
import cc.cassian.bigger_fish.entity.LeechEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

import static cc.cassian.bigger_fish.registry.CommonRegistry.registerEntity;

public class BiggerFishEntityTypes {
    public static final EntityType<LeechEntity> LEECH = registerEntity("leech", () -> (
            EntityType.Builder.<LeechEntity>of(LeechEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build(
                            //? if >1.21.4
                            ResourceKey.create(Registries.ENTITY_TYPE, BiggerFishMod.of(
                                    "leech"
                            //? if >1.21.4
                            ))
            ))
    );

    public static final EntityType<GrapplingHookEntity> GRAPPLING_HOOK = registerEntity("grappling_hook", () -> (
            EntityType.Builder.<GrapplingHookEntity>of(GrapplingHookEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(5)
                    .noSave()
                    .noSummon()
                    .build(
                            //? if >1.21.4
                            ResourceKey.create(Registries.ENTITY_TYPE, BiggerFishMod.of(
                            "grappling_hook"
                            //? if >1.21.4
                            ))
                    ))
    );

    public static void touch() {

    }
}
