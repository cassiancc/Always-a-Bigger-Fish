package cc.cassian.bigger_fish.config;


import folk.sisby.kaleido.api.ReflectiveConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.DisplayName;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.DisplayNameConvention;
import folk.sisby.kaleido.lib.quiltconfig.api.metadata.NamingSchemes;
import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;

@DisplayNameConvention(NamingSchemes.SPACE_SEPARATED_LOWER_CASE_INITIAL_UPPER_CASE)
@DisplayName("There's Always a Bigger Fish")
public class ModConfig extends ReflectiveConfig {

    public final GameplayOptions gameplay = new GameplayOptions();
    public static class GameplayOptions extends Section {
        @Comment("When using a vanilla Fishing Rod, replace vanilla's fishing loot table with biome specific fishing.")
        public final TrackedValue<Boolean> biomeFishing = this.value(true);
        @Comment("Randomize caught fish sizes")
        public final TrackedValue<Boolean> fishSizes = this.value(true);
    }

    public final TooltipOptions tooltip = new TooltipOptions();
    public static class TooltipOptions extends Section {
        @Comment("Show fish size in centimeters instead of inches")
        public final TrackedValue<Boolean> centimeters = this.value(false);
        @Comment("Show fish size in tooltip. NOTE: When this option is disabled and randomize fish sizes is still enabled, the reason your fish aren't stacking won't be obvious.")
        public final TrackedValue<Boolean> fishSizeTooltip = this.value(true);
        @Comment("When disabled, shift is required to show fish size.")
        public final TrackedValue<Boolean> showFishSizesAlways = this.value(true);
        @Comment("Show what bait can be used for in a tooltip")
        public final TrackedValue<Boolean> baitUsageTooltip = this.value(true);
        @Comment("Show bait usage always. When disabled, shift is required to show bait usage.")
        public final TrackedValue<Boolean> showBaitUsageAlways = this.value(false);
    }
}
