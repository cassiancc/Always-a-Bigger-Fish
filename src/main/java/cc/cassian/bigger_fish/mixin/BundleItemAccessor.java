package cc.cassian.bigger_fish.mixin;

import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BundleItem.class)
public interface BundleItemAccessor {

	@Invoker("getWeightSafe")
	static Fraction invokeGetWeightSafe(BundleContents bundleContents) {
		throw new AssertionError();
	}
}
