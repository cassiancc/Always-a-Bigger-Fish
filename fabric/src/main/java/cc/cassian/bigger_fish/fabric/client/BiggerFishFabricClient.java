package cc.cassian.bigger_fish.fabric.client;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.minigame.MinigameLayer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.minecraft.client.gui.LayeredDraw;

public final class BiggerFishFabricClient implements ClientModInitializer {
    public static final LayeredDraw.Layer LAYER = new MinigameLayer();

    @Override
    public void onInitializeClient() {

        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        HudLayerRegistrationCallback.EVENT.register(layeredDrawerWrapper -> {
            layeredDrawerWrapper.addLayer(IdentifiedLayer.of(BiggerFishMod.of("minigame"), LAYER));
        });
    }
}
