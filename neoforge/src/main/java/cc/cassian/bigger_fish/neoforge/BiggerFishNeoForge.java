package cc.cassian.bigger_fish.neoforge;

import cc.cassian.bigger_fish.config.neoforge.ModConfigFactory;
import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import cc.cassian.bigger_fish.registry.neoforge.CommonRegistryImpl;
import com.mojang.serialization.Codec;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;

import cc.cassian.bigger_fish.BiggerFishMod;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

import static cc.cassian.bigger_fish.BiggerFishMod.MOD_ID;

@Mod(MOD_ID)
public final class BiggerFishNeoForge {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MOD_ID);
    public static final Supplier<AttachmentType<Boolean>> FIREPROOF = ATTACHMENT_TYPES.register(
            "fireproof", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build()
    );
    public static final Supplier<AttachmentType<Boolean>> LAVA_HOOK = ATTACHMENT_TYPES.register(
            "lava_hook", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build()
    );

    public BiggerFishNeoForge(IEventBus eventBus, ModContainer container) {
        // Run our common setup.
        BiggerFishMod.init();
        CommonRegistryImpl.register(eventBus);
        ATTACHMENT_TYPES.register(eventBus);
        eventBus.addListener(BiggerFishNeoForge::registerCreativeTabs);
        NeoForge.EVENT_BUS.addListener(BiggerFishNeoForge::registerVillageTrades);
        registerModsPage();
    }

    @SubscribeEvent
    public static void registerCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            event.insertAfter(Items.FISHING_ROD.getDefaultInstance(), BiggerFishItems.NETHERITE_ROD.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.FISHING_ROD.getDefaultInstance(), BiggerFishItems.COPPER_ROD.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        else if (event.getTabKey().equals(CreativeModeTabs.FOOD_AND_DRINKS)) {
            for (ItemStack itemStack : ModHelpers.toCollection(BiggerFishItems.FISH).reversed()) {
                event.insertAfter(Items.PUFFERFISH.getDefaultInstance(), itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
        }
        else if (event.getTabKey().equals(CreativeModeTabs.INGREDIENTS)) {
            for (ItemStack itemStack : ModHelpers.toCollection(BiggerFishItems.INGREDIENTS).reversed()) {
                event.insertAfter(Items.BONE_MEAL.getDefaultInstance(), itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
        }
    }

    @SubscribeEvent
    public static void registerVillageTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FISHERMAN)
            event.getTrades().get(1).add(new VillagerTrades.ItemsForEmeralds(BiggerFishItems.LEECH.get(), 1, 2, 4));
    }

    public void registerModsPage() {
        if (ModList.get().isLoaded("cloth_config")) ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, ModConfigFactory::new);
    }
}
