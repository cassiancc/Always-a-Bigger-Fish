package cc.cassian.bigger_fish.client.renderer;


import cc.cassian.bigger_fish.blocks.entity.FishContainerBlockEntity;
import cc.cassian.bigger_fish.registry.BiggerFishComponentTypes;
import cc.cassian.bigger_fish.components.FishSize;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class FishContainerRenderer implements BlockEntityRenderer<FishContainerBlockEntity> {
	private static final float SIZE = 0.375F;
	private final ItemRenderer itemRenderer;

	public FishContainerRenderer(BlockEntityRendererProvider.Context context) {
		this.itemRenderer = context.getItemRenderer();
	}


	@Override
	public void render(FishContainerBlockEntity state, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		AtomicReference<Float> yPos = new AtomicReference<>(0.44921875F);
		List<ItemStack> items = state.getItems();
		for (int i = 0; i < items.size(); i++) {
			ItemStack itemStack = items.get(i).copy();
			itemStack.set(BiggerFishComponentTypes.SIZE.get(), new FishSize(15)); // hide scale when rendering as it looks terrible otherwise
			poseStack.pushPose();
			if (i==0) {
				poseStack.translate(0.5F, yPos.get(), 0.5F);
				yPos.updateAndGet(v -> (float) (v + .2));
				poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
				poseStack.translate(0.0, 0, 0.0F);
			}
			else if (i<5) {
				poseStack.translate(0.5F, 0.34921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.mulPose(Axis.YP.rotationDegrees(angle));
				poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
				poseStack.translate(-0.2125F, -0.2125F, 0.0F);
			} else if (i<10) {
				poseStack.translate(0.5F, 0.39921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.mulPose(Axis.YP.rotationDegrees(angle));
				poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
				poseStack.translate(-0.1125F, -0.1125F, 0.0F);
			}
			else if (i<15) {
				poseStack.translate(0.5F, 0.44921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.mulPose(Axis.YP.rotationDegrees(angle));
				poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
				poseStack.translate(-0.2125F, -0.2125F, 0.0F);
			} else if (i<20) {
				poseStack.translate(0.5F, 0.50921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.mulPose(Axis.YP.rotationDegrees(angle));
				poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
				poseStack.translate(-0.1125F, -0.1125F, 0.0F);
			}
			else if (i<30) {
				poseStack.translate(0.5F, 0.55921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.mulPose(Axis.YP.rotationDegrees(angle));
				poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
				poseStack.translate(-0.2125F, -0.2125F, 0.0F);
			} else if (i<35) {
				poseStack.translate(0.5F, 0.60921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.mulPose(Axis.YP.rotationDegrees(angle));
				poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
				poseStack.translate(-0.1125F, -0.1125F, 0.0F);
			} else if (i<40) {
				poseStack.translate(0.5F, 0.65921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.mulPose(Axis.YP.rotationDegrees(angle));
				poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
				poseStack.translate(-0.2125F, -0.2125F, 0.0F);
			} else if (i<50) {
				poseStack.translate(0.5F, 0.70921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.mulPose(Axis.YP.rotationDegrees(angle));
				poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
				poseStack.translate(-0.1125F, -0.1125F, 0.0F);
			} else if (i<55) {
				poseStack.translate(0.5F, 0.75921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.mulPose(Axis.YP.rotationDegrees(angle));
				poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
				poseStack.translate(-0.2125F, -0.2125F, 0.0F);
			} else if (i<60) {
				poseStack.translate(0.5F, 0.85921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.mulPose(Axis.YP.rotationDegrees(angle));
				poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
				poseStack.translate(-0.1125F, -0.1125F, 0.0F);
			} else  {
				poseStack.translate(0.5F, 0.90921875F, 0.5F);
				Direction direction = Direction.from2DDataValue((i + Direction.UP.get2DDataValue()) % 4);
				float angle = -direction.toYRot();
				poseStack.mulPose(Axis.YP.rotationDegrees(angle));
				poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
				poseStack.translate(-0.2125F, -0.2125F, 0.0F);
			}
			poseStack.scale(SIZE, SIZE, SIZE);
			itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, packedLight, packedOverlay, poseStack, bufferSource, state.getLevel(), 0);
			poseStack.popPose();
		}
	}
}