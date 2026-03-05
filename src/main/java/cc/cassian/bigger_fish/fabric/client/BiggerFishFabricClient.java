package cc.cassian.bigger_fish.fabric.client;

//? if fabric {
import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.client.BiggerFishModClient;
import cc.cassian.bigger_fish.client.renderer.FishBarrelRenderer;
import cc.cassian.bigger_fish.client.tooltip.ClientBaitedRodTooltip;
import cc.cassian.bigger_fish.fabric.BiggerFishFabric;
import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.minigame.MinigameLayer;
import cc.cassian.bigger_fish.registry.BiggerFishBlockEntityTypes;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import cc.cassian.bigger_fish.registry.BiggerFishEntityTypes;
import cc.cassian.bigger_fish.registry.FishSize;
import cc.cassian.bigger_fish.tooltip.BaitedRodTooltip;
import cc.cassian.bigger_fish.tooltip.FishBarrelTooltip;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.ClientTooltipComponentCallback;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.item.v1.ItemComponentTooltipProviderRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public final class BiggerFishFabricClient implements ClientModInitializer {
//    public static final LayeredDraw.Layer LAYER = new MinigameLayer();

    @Override
    public void onInitializeClient() {

        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
//        HudLayerRegistrationCallback.EVENT.register(layeredDrawerWrapper -> {
//            layeredDrawerWrapper.addLayer(IdentifiedLayer.of(BiggerFishMod.of("minigame"), LAYER));
//        });

        EntityRenderers.register(BiggerFishEntityTypes.LEECH.get(), ThrownItemRenderer::new);
        BlockEntityRenderers.register(BiggerFishBlockEntityTypes.FISH_BARREL_BLOCK_ENTITY, FishBarrelRenderer::new);
        ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipFlag, list) -> {
            BiggerFishModClient.addBaitUsageTooltip(stack, list);
        });
        ClientTooltipComponentCallback.EVENT.register(BiggerFishModClient::getClientBaitedRodTooltip);
        ItemComponentTooltipProviderRegistry.addFirst(BiggerFishComponentTypes.SIZE.get());
    }
}
//?}