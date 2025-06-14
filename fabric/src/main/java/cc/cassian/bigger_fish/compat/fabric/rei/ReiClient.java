package cc.cassian.bigger_fish.compat.fabric.rei;

import cc.cassian.bigger_fish.registry.BiggerFishTags;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.plugin.common.displays.DefaultInformationDisplay;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ReiClient implements REIClientPlugin {

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        for (TagKey<Item> itemTagKey : BiggerFishTags.FISHING_TAGS_FOR_DISPLAY) {
            addTagInfo(registry, itemTagKey);
        }
        addTagInfo(registry, BiggerFishTags.LAVA_FISH);
        addTagInfo(registry, BiggerFishTags.TIER_ONE_BAIT);
        addTagInfo(registry, BiggerFishTags.TIER_TWO_BAIT);
        addTagInfo(registry, BiggerFishTags.TIER_THREE_BAIT);
    }

    private static void addTagInfo(DisplayRegistry registry, TagKey<Item> itemTag) {
        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItemTag(itemTag), Component.translatable("tag."+ itemTag.location().toLanguageKey())).lines(Component.translatable("tag."+ itemTag.location().toLanguageKey() + ".eiv")));
    }
}
