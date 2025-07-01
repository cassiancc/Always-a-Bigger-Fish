package cc.cassian.bigger_fish.compat.emi;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiInfoRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiRenderable;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.List;

@EmiEntrypoint
public class EmiCompat implements EmiPlugin {
    public static final EmiRecipeCategory FISHING = new EmiRecipeCategory(BiggerFishMod.of("fishing"), EmiStack.of(BiggerFishItems.COPPER_ROD.get()));

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(FISHING);
        registry.addWorkstation(FISHING, EmiIngredient.of(ItemTags.FISHING_ENCHANTABLE));
        for (TagKey<Item> itemTagKey : BiggerFishTags.FISHING_TAGS_FOR_DISPLAY) {
            addTagInfo(registry, itemTagKey);
        }
        addTagInfo(registry, BiggerFishTags.LAVA_FISH);
        addTagInfo(registry, BiggerFishTags.TIER_ONE_BAIT);
        addTagInfo(registry, BiggerFishTags.TIER_TWO_BAIT);
        addTagInfo(registry, BiggerFishTags.TIER_THREE_BAIT);
    }

    private static void addTagInfo(EmiRegistry registry, TagKey<Item> itemTag) {
        registry.addRecipe(new EmiInfoRecipe(
                List.of(EmiIngredient.of(itemTag)),
                List.of(Component.translatable("tag."+ itemTag.location().toLanguageKey() + ".description")), BiggerFishMod.of("/"+itemTag.location().getPath()+"_description")
        ));
    }
}
