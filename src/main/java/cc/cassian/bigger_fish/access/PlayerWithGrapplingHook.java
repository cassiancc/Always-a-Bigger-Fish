package cc.cassian.bigger_fish.access;

import cc.cassian.bigger_fish.entity.GrapplingHookEntity;
import org.jspecify.annotations.Nullable;

public interface PlayerWithGrapplingHook {
    @Nullable
    GrapplingHookEntity bigger_fish$getHook();

    void bigger_fish$setHook(@Nullable GrapplingHookEntity hookEntity);
}
