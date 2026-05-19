package cc.cassian.bigger_fish.fabric.client;

//? if fabric {
import cc.cassian.bigger_fish.client.BiggerFishModClient;
import cc.cassian.bigger_fish.client.renderer.FishContainerRenderer;
import cc.cassian.bigger_fish.client.renderer.GrapplingHookRenderer;
import cc.cassian.bigger_fish.registry.BiggerFishBlockEntityTypes;
import cc.cassian.bigger_fish.registry.BiggerFishBlocks;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import cc.cassian.bigger_fish.registry.BiggerFishEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.RenderType;

public final class BiggerFishFabricClient implements ClientModInitializer {
//    public static final LayeredDraw.Layer LAYER = new MinigameLayer();

    @Override
    public void onInitializeClient() {

        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
//        HudLayerRegistrationCallback.EVENT.register(layeredDrawerWrapper -> {
//            layeredDrawerWrapper.addLayer(IdentifiedLayer.of(BiggerFishMod.of("minigame"), LAYER));
//        });

        EntityRendererRegistry.register(BiggerFishEntityTypes.LEECH, ThrownItemRenderer::new);
        EntityRendererRegistry.register(BiggerFishEntityTypes.GRAPPLING_HOOK, GrapplingHookRenderer::new);
        BlockEntityRenderers.register(BiggerFishBlockEntityTypes.FISH_BARREL_BLOCK_ENTITY, FishContainerRenderer::new);
        BlockEntityRenderers.register(BiggerFishBlockEntityTypes.FISH_TRAP_BLOCK_ENTITY, FishContainerRenderer::new);
        ItemTooltipCallback.EVENT.register(BiggerFishModClient::addTooltips);
        TooltipComponentCallback.EVENT.register(BiggerFishModClient::getClientBaitedRodTooltip);
        BlockRenderLayerMap.INSTANCE.putBlock(BiggerFishBlocks.FISH_TRAP, RenderType.cutout());
    }
}
//?}