package cc.cassian.bigger_fish.items;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class HookItem extends Item {
    private Item repairItem = null;
    private TagKey<Item> repairTag = null;

    public HookItem(Properties properties, Item repairIngredient) {
        super(properties);
        this.repairItem = repairIngredient;
    }

    public HookItem(Properties properties, TagKey<Item> repairIngredient) {
        super(properties);
        this.repairTag = repairIngredient;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        if (repairTag != null) {
            return stack.is(repairTag);
        }
        if (repairItem != null) {
            return stack.is(repairItem);
        }
        return false;
    }

}
