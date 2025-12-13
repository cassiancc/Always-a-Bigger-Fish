package cc.cassian.bigger_fish.neoforge;

//? if neoforge {
/*import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.client.BiggerFishModClient;
import cc.cassian.bigger_fish.registry.BiggerFishEntityTypes;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = BiggerFishMod.MOD_ID, value = Dist.CLIENT)
public final class BiggerFishNeoForgeClient {


    public static void init(IEventBus eventBus, ModContainer container) {

    }

    @SubscribeEvent
    public static void registerTooltip(ItemTooltipEvent event) {
        BiggerFishModClient.addTooltip(event.getItemStack(), event.getToolTip());
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(BiggerFishEntityTypes.LEECH.get(), ThrownItemRenderer::new);
    }

}
*///?}