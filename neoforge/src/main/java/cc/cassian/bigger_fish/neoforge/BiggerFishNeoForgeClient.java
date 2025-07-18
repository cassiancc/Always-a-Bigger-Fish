package cc.cassian.bigger_fish.neoforge;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.config.neoforge.ModConfigFactory;
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

@EventBusSubscriber(modid = BiggerFishMod.MOD_ID, value = Dist.CLIENT)
public final class BiggerFishNeoForgeClient {


    public static void init(IEventBus eventBus, ModContainer container) {
        registerModsPage();
    }

    public static void registerModsPage() {
        if (ModList.get().isLoaded("cloth_config")) ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, ModConfigFactory::new);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(BiggerFishEntityTypes.LEECH.get(), ThrownItemRenderer::new);
    }

}
