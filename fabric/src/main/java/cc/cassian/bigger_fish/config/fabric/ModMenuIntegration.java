package cc.cassian.bigger_fish.config.fabric;

import cc.cassian.bigger_fish.BiggerFishMod;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        //Display Cloth Config screen if mod present, else error.
        if (FabricLoader.getInstance().isModLoaded("cloth-config")) return new ModConfigFactory();
        else {
            BiggerFishMod.LOGGER.warn("User attempted to edit config, but Cloth Config is not present!");
            return parent -> null;
        }
    }
}
