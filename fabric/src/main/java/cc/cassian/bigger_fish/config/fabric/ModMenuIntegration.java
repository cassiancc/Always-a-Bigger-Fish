package cc.cassian.bigger_fish.config.fabric;

import cc.cassian.bigger_fish.BiggerFishMod;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.sisby.mcqoy.McQoy;
import net.fabricmc.loader.api.FabricLoader;

import java.util.Collections;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        //Display Cloth Config screen if mod present, else error.
        if (FabricLoader.getInstance().isModLoaded("cloth-config")) return new ModConfigFactory();
        else if (FabricLoader.getInstance().isModLoaded("mcqoy")) {
            return parent-> McQoy.createScreen(parent, BiggerFishMod.MOD_ID, Collections.singletonList(BiggerFishMod.CONFIG));
        }
        else {
            return parent -> null;
        }
    }
}
