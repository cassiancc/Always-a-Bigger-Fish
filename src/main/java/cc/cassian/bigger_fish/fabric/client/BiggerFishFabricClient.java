package cc.cassian.bigger_fish.fabric.client;

//? if fabric {
import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.client.BiggerFishModClient;
import cc.cassian.bigger_fish.fabric.BiggerFishFabric;
import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.minigame.MinigameLayer;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import cc.cassian.bigger_fish.registry.BiggerFishEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

public final class BiggerFishFabricClient implements ClientModInitializer {
//    public static final LayeredDraw.Layer LAYER = new MinigameLayer();

    @Override
    public void onInitializeClient() {

        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
//        HudLayerRegistrationCallback.EVENT.register(layeredDrawerWrapper -> {
//            layeredDrawerWrapper.addLayer(IdentifiedLayer.of(BiggerFishMod.of("minigame"), LAYER));
//        });

        EntityRendererRegistry.register(BiggerFishEntityTypes.LEECH.get(), ThrownItemRenderer::new);
        ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipFlag, list) -> {
            BiggerFishModClient.addTooltip(stack, list);
        });
    }
}
//?}