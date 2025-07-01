package cc.cassian.bigger_fish.fabric.client;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.minigame.MinigameLayer;
import cc.cassian.bigger_fish.registry.BiggerFishEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public final class BiggerFishFabricClient implements ClientModInitializer {
//    public static final LayeredDraw.Layer LAYER = new MinigameLayer();

    @Override
    public void onInitializeClient() {

        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        HudRenderCallback.EVENT.register(MinigameLayer::render);

        EntityRendererRegistry.register(BiggerFishEntityTypes.LEECH.get(), ThrownItemRenderer::new);
    }
}
