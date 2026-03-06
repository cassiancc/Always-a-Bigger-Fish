package cc.cassian.bigger_fish.client.tooltip;

import net.minecraft.client.gui.screens.inventory.tooltip.ClientBundleTooltip;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.component.BundleContents;

public class ClientFishContainerTooltip extends ClientBundleTooltip {

    public ClientFishContainerTooltip(BundleContents contents, MutableComponent translatable) {
        super(contents);
    }
}