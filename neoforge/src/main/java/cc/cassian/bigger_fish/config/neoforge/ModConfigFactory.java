package cc.cassian.bigger_fish.config.neoforge;


import cc.cassian.bigger_fish.config.ClothConfigFactory;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public class ModConfigFactory implements IConfigScreenFactory {

    @Override
    public Screen createScreen(ModContainer modContainer, Screen parent) {
        return ClothConfigFactory.create(parent);
    }
}