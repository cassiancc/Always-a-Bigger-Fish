package cc.cassian.bigger_fish.config.fabric;


import cc.cassian.bigger_fish.config.ClothConfigFactory;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import net.minecraft.client.gui.screens.Screen;

public class ModConfigFactory implements ConfigScreenFactory<Screen> {

    @Override
    public Screen create(Screen parent) {
        return ClothConfigFactory.create(parent);
    }
}