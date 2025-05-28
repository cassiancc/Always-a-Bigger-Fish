package cc.cassian.bigger_fish.compat.rei;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.plugin.common.displays.beacon.FishingDisplay;
import net.minecraft.tags.ItemTags;

import java.util.Collections;

public class REIClient implements REIClientPlugin {
    public static final CategoryIdentifier<FishingDisplay> FISHING_DISPLAY = CategoryIdentifier.of(BiggerFishMod.MOD_ID, "fishing");

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new FishingCategory());
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.add(new FishingDisplay(Collections.singletonList(EntryIngredients.ofItemTag(BiggerFishTags.BAIT))));
    }
}
