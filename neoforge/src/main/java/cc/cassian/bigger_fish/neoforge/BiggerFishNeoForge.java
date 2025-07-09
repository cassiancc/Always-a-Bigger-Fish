package cc.cassian.bigger_fish.neoforge;

import cc.cassian.bigger_fish.helpers.ModHelpers;
import cc.cassian.bigger_fish.registry.*;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;

import cc.cassian.bigger_fish.BiggerFishMod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.Supplier;

import static cc.cassian.bigger_fish.BiggerFishMod.MOD_ID;

@Mod(MOD_ID)
@EventBusSubscriber(modid = BiggerFishMod.MOD_ID)
public final class BiggerFishNeoForge {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MOD_ID);
    public static final Supplier<AttachmentType<Boolean>> FIREPROOF = ATTACHMENT_TYPES.register(
            "fireproof", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build()
    );

    public static final Supplier<AttachmentType<String>> HOOK = ATTACHMENT_TYPES.register(
            "hook", () -> AttachmentType.builder(() -> "vanilla").serialize(Codec.STRING).build()
    );

    public BiggerFishNeoForge(IEventBus eventBus, ModContainer container) {
        // Run our common setup.
        BiggerFishMod.init();
        ATTACHMENT_TYPES.register(eventBus);
        if (FMLEnvironment.dist.isClient()) {
            BiggerFishNeoForgeClient.init(eventBus, container);
        }
    }

    @SubscribeEvent
    public static void registerCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            for (ItemStack itemStack : ModHelpers.toCollection(BiggerFishItems.TOOLS).reversed()) {
                event.insertAfter(Items.FISHING_ROD.getDefaultInstance(), itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
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

    @SubscribeEvent
    public static void register(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.ITEM)) {
            BiggerFishItems.touch();
        } else if (event.getRegistryKey().equals(Registries.DATA_COMPONENT_TYPE)) {
            BiggerFishComponentTypes.touch();
        } else if (event.getRegistryKey().equals(Registries.ENTITY_TYPE)) {
            BiggerFishEntityTypes.touch();
        } else if (event.getRegistryKey().equals(Registries.SOUND_EVENT)) {
            BiggerFishSoundEvents.touch();
        } else if (event.getRegistryKey().equals(Registries.MOB_EFFECT)) {
            BiggerFishMobEffects.touch();
        }
    }
}
