package cc.cassian.bigger_fish.config;


import com.llamalad7.mixinextras.lib.antlr.runtime.misc.FlexibleHashMap;
import folk.sisby.kaleido.api.ReflectiveConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.DisplayName;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.DisplayNameConvention;
import folk.sisby.kaleido.lib.quiltconfig.api.metadata.NamingSchemes;
import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;
import folk.sisby.kaleido.lib.quiltconfig.api.values.ValueList;
import folk.sisby.kaleido.lib.quiltconfig.api.values.ValueMap;

import java.util.List;
import java.util.Map;

@DisplayNameConvention(NamingSchemes.SPACE_SEPARATED_LOWER_CASE_INITIAL_UPPER_CASE)
@DisplayName("There's Always a Bigger Fish")
public class ModConfig extends ReflectiveConfig {

    public final GameplayOptions gameplay = new GameplayOptions();
    public static class GameplayOptions extends Section {
        @Comment("When using a vanilla Fishing Rod, replace vanilla's fishing loot table with biome specific fishing.")
        public final TrackedValue<Boolean> biomeFishing = this.value(true);
        @Comment("Fishing without bait attached to your rod only results in junk. Note this also applies to vanilla fishing rods.")
        public final TrackedValue<Boolean> preventFishingWithoutBait = this.value(false);
        @Comment("Randomize caught fish sizes")
        public final TrackedValue<Boolean> fishSizes = this.value(true);
        public final TrackedValue<Boolean> baitedRodsHaveDurability = this.value(true);
        public final TrackedValue<ValueMap<ValueList<String>>> fishing_line_patterns = map(ValueList.create(""))
                .put("pride", ValueList.create("",
        "#fe0000",
                "#ff8e01",
                "#ffee00",
                "#028215",
                "#014cff",
                "#8b018c"
                )).put("LGBTQ+", ValueList.create("",
                "#ED72B1",
                "#E93423",
                "#F19437",
                "#FFFF55",
                "#3E8A26",
                "#57BDBF",
                "#3A0591",
                "#83188A"
                )).put("transgender", ValueList.create("",
                "#5BCFFA",
                "#F5ABB9",
                "#FFFFFF",
                "#F5ABB9"
                )).put("trans", ValueList.create("",
                "#5BCFFA",
                "#F5ABB9",
                "#FFFFFF",
                "#F5ABB9"
                )).put("lesbian", ValueList.create("",
                "#D62900",
                "#FF9B55",
                "#FFFFFF",
                "#D462A5",
                "#A50062"
                )).put("women", ValueList.create("",
                "#D62900",
                "#FF9B55",
                "#FFFFFF",
                "#D462A5",
                "#A50062"
                )).put("gay", ValueList.create("",
                "#447763",
                "#8DCAAE",
                "#FFFFFF",
                "#5B7ECD",
                "#283064"
                )).put("men", ValueList.create("",
                "#447763",
                "#8DCAAE",
                "#FFFFFF",
                "#5B7ECD",
                "#283064"
                )).put("bisexual", ValueList.create("",
                "#D70071",
                "#9C4E97",
                "#0035A9",
                "#9C4E97"
                )).put("bi", ValueList.create("",
                "#D70071",
                "#9C4E97",
                "#0035A9",
                "#9C4E97"
                )).put("pansexual", ValueList.create("",
                "#FF218C",
                "#FFD800",
                "#0094FF"
                )).put("pan", ValueList.create("",
                "#FF218C",
                "#FFD800",
                "#0094FF"
                )).put("non-binary", ValueList.create("",
                "#FFF430",
                "#FFFFFF",
                "#9C59D1",
                "#292929"
                )).put("enby", ValueList.create("",
                "#FFF430",
                "#FFFFFF",
                "#9C59D1",
                "#292929"
                )).put("asexual", ValueList.create("",
                "#000000",
                "#A3A3A3",
                "#FFFFFF",
                "#800080"
                )).put("ace", ValueList.create("",
                "#000000",
                "#A3A3A3",
                "#FFFFFF",
                "#800080"
                )).put("aromantic", ValueList.create("",
                "#000000",
                "#A3A3A3",
                "#FFFFFF",
                "#3DA542"
                )).put("aro", ValueList.create("",
                "#000000",
                "#A3A3A3",
                "#FFFFFF",
                "#3DA542"
                )).put("aro-ace", ValueList.create("",
        "#E28C00",
                "#ECCD00",
                "#FFFFFF",
                "#62AEDC",
                "#203856"
                )).put("genderfluid", ValueList.create("",
                "#FE75A1",
                "#FFFFFF",
                "#BE16D5",
                "#000000",
                "#333EBC"
                )).put("fluid", ValueList.create("",
                "#FE75A1",
                "#FFFFFF",
                "#BE16D5",
                "#000000",
                "#333EBC"
                )).put("grimbly", ValueList.create("",
                "#899EF7",
                "#6ADEE5",
                "#9DE77D",
                "#E2C783",
                "#F78888",
                "#EFC1EE"
                )).put("delilah", ValueList.create("",
                "#7762AB",
                "#F995E0",
                "#FED9F5",
                "#F0EDFA",
                "#B1D8FC",
                "#808DCA"
                )).put("raz", ValueList.create("",
        "#CE5AE5",
                "#C52A72",
                "#FF5C87",
                "#FFD9C6",
                "#454545"
                )).build();
    }

    public final TooltipOptions tooltip = new TooltipOptions();
    public static class TooltipOptions extends Section {
        @Comment("Show fish size in centimeters instead of inches")
        public final TrackedValue<Boolean> centimeters = this.value(false);
        @Comment("Show fish size in tooltip.")
        @Comment("NOTE: When this option is disabled and randomize fish sizes is still enabled, the reason your fish aren't stacking won't be obvious.")
        public final TrackedValue<Boolean> fishSizeTooltip = this.value(true);
        @Comment("When disabled, shift is required to show fish size.")
        public final TrackedValue<Boolean> showFishSizesAlways = this.value(true);
        @Comment("Show what bait can be used for in a tooltip")
        public final TrackedValue<Boolean> baitUsageTooltip = this.value(true);
        @Comment("Show bait usage always. When disabled, shift is required to show bait usage.")
        public final TrackedValue<Boolean> showBaitUsageAlways = this.value(false);
    }

    public final ClientOptions client = new ClientOptions();
    public static class ClientOptions extends Section {
        public final TrackedValue<Boolean> warnedAboutModefite = this.value(false);
    }
}
