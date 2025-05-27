package cc.cassian.bigger_fish.compat;

import dev.yurisuika.compost.api.CompostApi;
import dev.yurisuika.compost.util.config.options.Composition;

import java.util.Set;

public class CompostCompat {
    public static void register() {
        CompostApi.addComposition("bigger_fish_worm", new Composition(new Composition.Compost("bigger_fish:worm", 0.5d, new Composition.Compost.Count(1, 1)), Set.of()));
    }
}
