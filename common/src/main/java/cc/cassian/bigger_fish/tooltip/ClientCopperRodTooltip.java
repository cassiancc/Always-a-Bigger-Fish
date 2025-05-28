package cc.cassian.bigger_fish.tooltip;

import cc.cassian.bigger_fish.BiggerFishMod;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientBundleTooltip;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ClientCopperRodTooltip extends ClientBundleTooltip {

    private static final Component COPPER_ROD_EMPTY_DESCRIPTION = Component.translatable("item.bigger_fish.copper_rod.empty.description");

    public ClientCopperRodTooltip(BundleContents contents) {
        super(contents);

    }
}
