package cc.cassian.bigger_fish.client.renderer;


import cc.cassian.bigger_fish.blocks.entity.FishContainerBlockEntity;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import cc.cassian.bigger_fish.components.FishSize;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class FishContainerRenderer implements BlockEntityRenderer<FishContainerBlockEntity, FishContainerBlockEntityRenderState> {
	private static final float SIZE = 0.375F;
	private final ItemModelResolver itemRenderer;

	public FishContainerRenderer(BlockEntityRendererProvider.Context context) {
		this.itemRenderer = context.itemModelResolver();
	}

	@Override
	public FishContainerBlockEntityRenderState createRenderState() {
		return new FishContainerBlockEntityRenderState();
	}

	@Override
	public void extractRenderState(
			FishContainerBlockEntity blockEntity,
			FishContainerBlockEntityRenderState state,
			float partialTicks,
			Vec3 cameraPosition,
			ModelFeatureRenderer.CrumblingOverlay crumblingOverlay
	) {
		BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, crumblingOverlay);


		int k = (int)blockEntity.getBlockPos().asLong();

		List<ItemStack> items = blockEntity.getItems();
		state.items.clear();
		if (!items.isEmpty()) {
			items.forEach(itemStack -> {
				itemStack.set(BiggerFishComponentTypes.SIZE.get(), new FishSize(15)); // hide scale when rendering as it looks terrible otherwise
				ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
				this.itemRenderer.updateForTopItem(itemStackRenderState, itemStack, ItemDisplayContext.FIXED, blockEntity.getLevel(), null, k);
				state.items.add(itemStackRenderState);
			});
		}

	}


	@Override
	public void submit(FishContainerBlockEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
		AtomicReference<Float> yPos = new AtomicReference<>(0.44921875F);
		List<ItemStackRenderState> items = state.items;
		for (int i = 0; i < items.size(); i++) {
			ItemStackRenderState itemStack = items.get(i);
			poseStack.pushPose();
			if (i==0) {
				poseStack.translate(0.5F, yPos.get(), 0.5F);
				yPos.updateAndGet(v -> (float) (v + .2));
				poseStack.rotateDegrees(Axis.XP,90.0F);
				poseStack.translate(0.0, 0, 0.0F);
			}
			else if (i<5) {
				poseStack.translate(0.5F, 0.34921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.rotateDegrees(Axis.YP,angle);
				poseStack.rotateDegrees(Axis.XP,90.0F);
				poseStack.translate(-0.2125F, -0.2125F, 0.0F);
			} else if (i<10) {
				poseStack.translate(0.5F, 0.39921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.rotateDegrees(Axis.YP,angle);
				poseStack.rotateDegrees(Axis.XP,90.0F);
				poseStack.translate(-0.1125F, -0.1125F, 0.0F);
			}
			else if (i<15) {
				poseStack.translate(0.5F, 0.44921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.rotateDegrees(Axis.YP,angle);
				poseStack.rotateDegrees(Axis.XP,90.0F);
				poseStack.translate(-0.2125F, -0.2125F, 0.0F);
			} else if (i<20) {
				poseStack.translate(0.5F, 0.50921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.rotateDegrees(Axis.YP,angle);
				poseStack.rotateDegrees(Axis.XP,90.0F);
				poseStack.translate(-0.1125F, -0.1125F, 0.0F);
			}
			else if (i<30) {
				poseStack.translate(0.5F, 0.55921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.rotateDegrees(Axis.YP,angle);
				poseStack.rotateDegrees(Axis.XP,90.0F);
				poseStack.translate(-0.2125F, -0.2125F, 0.0F);
			} else if (i<35) {
				poseStack.translate(0.5F, 0.60921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.rotateDegrees(Axis.YP,angle);
				poseStack.rotateDegrees(Axis.XP,90.0F);
				poseStack.translate(-0.1125F, -0.1125F, 0.0F);
			} else if (i<40) {
				poseStack.translate(0.5F, 0.65921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.rotateDegrees(Axis.YP,angle);
				poseStack.rotateDegrees(Axis.XP,90.0F);
				poseStack.translate(-0.2125F, -0.2125F, 0.0F);
			} else if (i<50) {
				poseStack.translate(0.5F, 0.70921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.rotateDegrees(Axis.YP,angle);
				poseStack.rotateDegrees(Axis.XP,90.0F);
				poseStack.translate(-0.1125F, -0.1125F, 0.0F);
			} else if (i<55) {
				poseStack.translate(0.5F, 0.75921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.rotateDegrees(Axis.YP,angle);
				poseStack.rotateDegrees(Axis.XP,90.0F);
				poseStack.translate(-0.2125F, -0.2125F, 0.0F);
			} else if (i<60) {
				poseStack.translate(0.5F, 0.85921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.rotateDegrees(Axis.YP,angle);
				poseStack.rotateDegrees(Axis.XP,90.0F);
				poseStack.translate(-0.1125F, -0.1125F, 0.0F);
			} else  {
				poseStack.translate(0.5F, 0.90921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.rotateDegrees(Axis.YP,angle);
				poseStack.rotateDegrees(Axis.XP,90.0F);
				poseStack.translate(-0.2125F, -0.2125F, 0.0F);
			}
			poseStack.scale(SIZE, SIZE, SIZE);
			itemStack.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
			poseStack.popPose();
		}
	}


}