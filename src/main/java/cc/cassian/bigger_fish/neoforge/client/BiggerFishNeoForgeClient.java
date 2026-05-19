package cc.cassian.bigger_fish.neoforge.client;

//? if neoforge {
/*import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.client.BiggerFishModClient;
import cc.cassian.bigger_fish.client.renderer.GrapplingHookRenderer;
import cc.cassian.bigger_fish.registry.BiggerFishEntityTypes;
import cc.cassian.bigger_fish.tooltip.FishContainerTooltip;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.event.RegisterRangeSelectItemModelPropertyEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = BiggerFishMod.MOD_ID, value = Dist.CLIENT)
public final class BiggerFishNeoForgeClient {

    @SubscribeEvent
    public static void registerTooltip(ItemTooltipEvent event) {
        BiggerFishModClient.addTooltips(event.getItemStack(), event.getContext(), event.getFlags(), event.getToolTip());
    }

    @SubscribeEvent
    public static void registerSizeProperty(RegisterRangeSelectItemModelPropertyEvent event) {
        event.register(BiggerFishMod.of("size"), SizeProperty.MAP_CODEC);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(BiggerFishEntityTypes.LEECH, ThrownItemRenderer::new);
        event.registerEntityRenderer(BiggerFishEntityTypes.GRAPPLING_HOOK, GrapplingHookRenderer::new);
    }

    @SubscribeEvent
    public static void registerClientTooltip(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(FishContainerTooltip.class, BiggerFishModClient::getClientBaitedRodTooltip);
    }

}
*///?}