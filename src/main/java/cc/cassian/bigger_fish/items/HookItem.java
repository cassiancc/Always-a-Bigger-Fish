package cc.cassian.bigger_fish.items;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.UnknownNullability;

public class HookItem extends Item {
	private final Item repairMaterial;

	public HookItem(Properties properties, Item repairMaterial) {
		super(properties);
		this.repairMaterial = repairMaterial;
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
		return repairCandidate.is(repairMaterial);
	}
}
