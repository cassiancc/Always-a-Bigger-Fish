package cc.cassian.bigger_fish.client.renderer;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;

import java.util.ArrayList;
import java.util.List;

public class FishContainerBlockEntityRenderState extends BlockEntityRenderState {
	public List<ItemStackRenderState> items = new ArrayList<>();
}
