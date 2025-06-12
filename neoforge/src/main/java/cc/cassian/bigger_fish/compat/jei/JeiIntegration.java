package cc.cassian.bigger_fish.compat.jei;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.compat.ModCompat;
import cc.cassian.bigger_fish.registry.BiggerFishItems;
import cc.cassian.bigger_fish.registry.BiggerFishTags;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@JeiPlugin
public class JeiIntegration implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return BiggerFishMod.of("jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        for (TagKey<Item> itemTagKey : BiggerFishTags.FISHING_TAGS_FOR_DISPLAY) {
            addTagInfo(registration, itemTagKey);
        }
        addTagInfo(registration, BiggerFishTags.LAVA_FISH);

        if (!ModCompat.FARMERS_DELIGHT) {
            ArrayList<ItemStack> objects = new ArrayList<>();
            for (Supplier<Item> item : BiggerFishItems.HIDDEN_FOOD) {
                objects.add(item.get().getDefaultInstance());
            }

            registration.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, objects);
        }
    }

    private static void addTagInfo(IRecipeRegistration registration, TagKey<Item> itemTag) {
        List<Item> items = new ArrayList<>();
        BuiltInRegistries.ITEM.get(itemTag).ifPresent((holders) -> holders.forEach((holder) -> items.add(holder.value())));
        List<ItemStack> stacks = new ArrayList<>();
        items.forEach((item) -> stacks.add(new ItemStack(item)));
        registration.addItemStackInfo(stacks, Component.translatable("tag."+ itemTag.location().toLanguageKey() + ".eiv"));
    }

}
