package cc.cassian.bigger_fish.client.renderer;

import cc.cassian.bigger_fish.BiggerFishMod;
import cc.cassian.bigger_fish.entity.GrapplingHookEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import folk.sisby.kaleido.lib.quiltconfig.api.values.ValueList;
import folk.sisby.kaleido.lib.quiltconfig.api.values.ValueMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix3f;
import org.joml.Matrix4f;


import java.util.List;
import java.util.Map;

import static net.minecraft.client.renderer.entity.FishingHookRenderer.getHoldingArm;

public class GrapplingHookRenderer extends EntityRenderer<GrapplingHookEntity, GrapplingHookRenderState> {
    private static final Identifier HOOK = BiggerFishMod.of("textures/entity/projectiles/grappling_hook.png");
    private static final Identifier HOOK_STICKY = BiggerFishMod.of("textures/entity/projectiles/grappling_hook_sticky.png");
    private static final Identifier BOBBER_OVERLAY = BiggerFishMod.of("textures/entity/projectiles/grappling_hook_bobber_overlay.png");
    private static final RenderType HOOK_RENDER = RenderTypes.entityCutout(HOOK);
    private static final RenderType HOOK_STICKY_RENDER = RenderTypes.entityCutout(HOOK_STICKY);
    private static final RenderType BOBBER_OVERLAY_RENDER = RenderTypes.entityCutout(BOBBER_OVERLAY);

    public GrapplingHookRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public GrapplingHookRenderState createRenderState() {
        return new GrapplingHookRenderState();
    }

    private Vec3 getPlayerHandPos(final Player owner, final float swing, final float partialTicks) {
        int invert = getHoldingArm(owner) == HumanoidArm.RIGHT ? 1 : -1;
        if (this.entityRenderDispatcher.options.getCameraType().isFirstPerson() && owner == Minecraft.getInstance().player) {
            float fov = (float)(Integer)this.entityRenderDispatcher.options.fov().get();
            double viewBobbingScale = (double)960.0F / (double)fov;
            Vec3 viewVec = this.entityRenderDispatcher.camera.getNearPlane(fov).getPointOnPlane((float)invert * 0.525F, -0.1F).scale(viewBobbingScale).yRot(swing * 0.5F).xRot(-swing * 0.7F);
            return owner.getEyePosition(partialTicks).add(viewVec);
        } else {
            float ownerYRot = Mth.lerp(partialTicks, owner.yBodyRotO, owner.yBodyRot) * ((float)Math.PI / 180F);
            double sin = (double)Mth.sin((double)ownerYRot);
            double cos = (double)Mth.cos((double)ownerYRot);
            float playerScale = owner.getScale();
            double rightOffset = (double)invert * 0.35 * (double)playerScale;
            double forwardOffset = 0.8 * (double)playerScale;
            float yOffset = owner.isCrouching() ? -0.1875F : 0.0F;
            return owner.getEyePosition(partialTicks).add(-cos * rightOffset - sin * forwardOffset, (double)yOffset - 0.45 * (double)playerScale, -sin * rightOffset + cos * forwardOffset);
        }
    }

    @Override
    public void extractRenderState(GrapplingHookEntity entity, GrapplingHookRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.sticky = entity.isSticky();
        Player player = entity.getPlayerOwner();
        if (player == null) return;

        float swing = player.getAttackAnim(partialTicks);
        float swing2 = Mth.sin((double)(Mth.sqrt(swing) * (float)Math.PI));
        Vec3 playerPos = this.getPlayerHandPos(player, swing2, partialTicks);
        Vec3 hookPos = entity.getPosition(partialTicks).add((double)0.0F, (double)0.25F, (double)0.0F);
        state.playerPos = playerPos;
        state.hookPos = hookPos;
        state.lineOriginOffset = playerPos.subtract(hookPos);

        state.isAttached = entity.isAttached() || entity.getHookedIn() != null;

        // Line Colors
        ItemStack line = entity.getFishingLine();
        List<Integer> colors = List.of(
                0x704b2a,
                0x634225
        );

        if (!line.isEmpty()) {
            if (line.has(DataComponents.DYED_COLOR)) {
                int colorInt = line.get(DataComponents.DYED_COLOR).rgb();

                // Darken secondary color
                int r = (colorInt >> 16) & 0xFF;
                int g = (colorInt >> 8) & 0xFF;
                int b = colorInt & 0xFF;

                r = (int)(r * 0.95f);
                g = (int)(g * 0.95f);
                b = (int)(b * 0.95f);

                int darkened = (r << 16) | (g << 8) | b;

                colors = List.of(colorInt, darkened);
            }

            if (line.has(DataComponents.CUSTOM_NAME)) {
                String name = line.getHoverName().getString();
                ValueMap<ValueList<String>> patterns = BiggerFishMod.CONFIG.gameplay.fishing_line_patterns.value();
                for (Map.Entry<String, ValueList<String>> entry : patterns.entrySet()) {
                    if (name.equalsIgnoreCase(entry.getKey())) {
                        colors = patterns.get(entry.getKey()).stream().map(colorString -> {
                            if (colorString.startsWith("#")) {
                                colorString = colorString.substring(1);
                            }
                            return Integer.parseInt(colorString, 16);
                        }).toList();
                        break;
                    }
                }
            }
        }
        state.colors = colors;
        state.shakeTime = entity.shakeTime;
        state.playerMovement = player.getDeltaMovement();
        state.hookMovement = entity.getDeltaMovement();
        state.yRotO = entity.yRotO;
        state.xRotO = entity.xRotO;
        state.yRot = entity.getYRot();
        state.xRot = entity.getXRot();
        state.hasBobber = entity.hasBobber();
        state.bobber = entity.getBobber();
        state.partialTicks = partialTicks;
    }

    @Override
    public void submit(GrapplingHookRenderState state, PoseStack matrixStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        matrixStack.pushPose();

        float rodOffsetX = (float) state.lineOriginOffset.x;
        float rodOffsetY = (float) state.lineOriginOffset.y;
        float rodOffsetZ = (float) state.lineOriginOffset.z;
        var packedLight = state.lightCoords;
        var partialTicks = state.partialTicks;

        Vec3 rodTipPosition = state.hookPos;
        Vec3 entityPosition = state.playerPos;

        // Offset so the leash appear at the right thickness
        float scaleFactor = (1.0F / Mth.sqrt(rodOffsetX * rodOffsetX + rodOffsetZ * rodOffsetZ)) * 0.05F / 2.0F;
        float offsetZ = rodOffsetZ * scaleFactor;
        float offsetX = rodOffsetX * scaleFactor;

        float LEASH_THICKNESS = 0.035F;
        int segments = 24;
        boolean isAttached = state.isAttached;
        var colors = state.colors;
        var entityShakeTime = state.shakeTime;

        double hookRelativeVelocity = getRelativeVelocity(rodTipPosition, state.playerMovement, entityPosition, state.hookMovement);
        double negativeRelativeVelocity = Math.min(hookRelativeVelocity, 0);

        // Rope Rendering
        submitNodeCollector.submitCustomGeometry(matrixStack, RenderTypes.leash(), (pose, vertexConsumer)->{
            for (int segment = 0; segment <= segments; segment++) {
                addVertexPair(vertexConsumer, pose, rodOffsetX, rodOffsetY, rodOffsetZ, LEASH_THICKNESS, LEASH_THICKNESS,
                        offsetZ, offsetX, segment, segments,false, packedLight, colors, isAttached, entityShakeTime - partialTicks, negativeRelativeVelocity);
            }
            for (int segment = segments; segment >= 0; segment--) {
                addVertexPair(vertexConsumer, pose, rodOffsetX, rodOffsetY, rodOffsetZ, LEASH_THICKNESS, 0.0F,
                        offsetZ, offsetX, segment, segments,true, packedLight, colors, isAttached, entityShakeTime - partialTicks, negativeRelativeVelocity);
            }
        });
        matrixStack.popPose();


        // Hook rendering
        matrixStack.pushPose();
        matrixStack.scale(0.05625F, 0.05625F, 0.05625F);
        matrixStack.translate(0.0, 1, 0.0);
        matrixStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, state.yRotO, state.yRot) - 90.0F));
        matrixStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, state.xRotO, state.xRot)));
        matrixStack.mulPose(Axis.XP.rotationDegrees(45.0F));

        RenderType renderType = state.sticky ? HOOK_STICKY_RENDER : HOOK_RENDER;

        int hookOffset = 8;
        int width = 8;
        float size = 32;
        float offset = 8;
        float height = 7;
        // Front plane
        submitNodeCollector.submitCustomGeometry(matrixStack, renderType, (pose, consumer)->{
            Matrix4f matrix4f = pose.pose();
            Matrix3f matrix3f = pose.normal();

            // Texture


            this.vertex(matrix4f, matrix3f, consumer, hookOffset-2, -3, -3, offset/size, 0, -1, 0, 0, packedLight);
            this.vertex(matrix4f, matrix3f, consumer, hookOffset-2, -3, 3, (offset+height)/size, 0, -1, 0, 0, packedLight);
            this.vertex(matrix4f, matrix3f, consumer, hookOffset-2, 3, 3, (offset+height)/size, height/size, -1, 0, 0, packedLight);
            this.vertex(matrix4f, matrix3f, consumer, hookOffset-2, 3, -3, offset/size, height/size, -1, 0, 0, packedLight);

            this.vertex(matrix4f, matrix3f, consumer, hookOffset-2, 3, -3, offset/size, 0, 1, 0, 0, packedLight);
            this.vertex(matrix4f, matrix3f, consumer, hookOffset-2, 3, 3, (offset+height)/size, 0, 1, 0, 0, packedLight);
            this.vertex(matrix4f, matrix3f, consumer, hookOffset-2, -3, 3, (offset+height)/size, height/size, 1, 0, 0, packedLight);
            this.vertex(matrix4f, matrix3f, consumer, hookOffset-2, -3, -3, offset/size, height/size, 1, 0, 0, packedLight);

            // Side cross
            for(int r = 0; r < 4; ++r) {
                matrixStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                this.vertex(matrix4f, matrix3f, consumer, hookOffset-width, -3, 0, 0.0F, 0.0F, 0, 1, 0, packedLight);
                this.vertex(matrix4f, matrix3f, consumer, hookOffset, -3, 0, width/size, 0.0F, 0, 1, 0, packedLight);
                this.vertex(matrix4f, matrix3f, consumer, hookOffset, 3, 0, width/size, height/size, 0, 1, 0, packedLight);
                this.vertex(matrix4f, matrix3f, consumer, hookOffset-width, 3, 0, 0.0F, height/size, 0, 1, 0, packedLight);
            }
        });


        // Bobber overlay
        submitNodeCollector.submitCustomGeometry(matrixStack, BOBBER_OVERLAY_RENDER, (pose, consumer)->{
            Matrix4f matrix4f = pose.pose();
            Matrix3f matrix3f = pose.normal();
            float bobberR = 1.0F;
            float bobberG = 1.0F;
            float bobberB = 1.0F;
            if (state.hasBobber) {
                ItemStack bobberStack = state.bobber;
                if (!bobberStack.isEmpty() && bobberStack.has(DataComponents.DYED_COLOR)) {
                    int colorInt = bobberStack.get(DataComponents.DYED_COLOR).rgb();
                    bobberR = (float)(colorInt >> 16 & 255) / 255.0F;
                    bobberG = (float)(colorInt >> 8 & 255) / 255.0F;
                    bobberB = (float)(colorInt & 255) / 255.0F;
                }
            }
            for(int r = 0; r < 4; ++r) {
                matrixStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                this.vertex(matrix4f, matrix3f, consumer, hookOffset-width, -3, 0, 0.0F, 0.0F, 0, 1, 0, packedLight, bobberR, bobberG, bobberB);
                this.vertex(matrix4f, matrix3f, consumer, hookOffset, -3, 0, width/size, 0.0F, 0, 1, 0, packedLight, bobberR, bobberG, bobberB);
                this.vertex(matrix4f, matrix3f, consumer, hookOffset, 3, 0, width/size, height/size, 0, 1, 0, packedLight, bobberR, bobberG, bobberB);
                this.vertex(matrix4f, matrix3f, consumer, hookOffset-width, 3, 0, 0.0F, height/size, 0, 1, 0, packedLight, bobberR, bobberG, bobberB);
            }
        });


        matrixStack.popPose();
    }

    public Identifier getTextureLocation(GrapplingHookEntity entity) {
        return HOOK;
    }

    public void vertex(Matrix4f matrix, Matrix3f normals, VertexConsumer vertexBuilder, float offsetX, float offsetY, float offsetZ, float textureX, float textureY, int normalX, int m, int n, int packedLight, float r, float g, float b) {
        vertexBuilder.addVertex(matrix, offsetX, offsetY, offsetZ).setColor(r, g, b, 1.0F).setUv(textureX, textureY).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal((float)normalX, (float)n, (float)m);
    }
    public void vertex(Matrix4f matrix, Matrix3f normals, VertexConsumer vertexBuilder, float offsetX, float offsetY, float offsetZ, float textureX, float textureY, int normalX, int m, int n, int packedLight) {
        this.vertex(matrix, normals, vertexBuilder, offsetX, offsetY, offsetZ, textureX, textureY, normalX, m, n, packedLight, 1,1,1);
    }

    private static void addVertexPair(
            VertexConsumer vertices, PoseStack.Pose matrices,
            float deltaX, float deltaY, float deltaZ,
            float thickness1, float thickness2,
            float offsetZ, float offsetX,
            int segment, int totalSegments,
            boolean isInnerFace, int packedLight,
            List<Integer> colors, boolean attached, float shakeTime,
            double negativeRelativeHookVelocity
    ) {
        float progress = (float) segment / totalSegments;
        float offsetY = 0.05F;

        // Color
        int color = colors.get((isInnerFace ? segment : segment + colors.size() - 1) % colors.size());
        float red   = (float)(color >> 16 & 255) / 255.0F;
        float green = (float)(color >> 8 & 255) / 255.0F;
        float blue  = (float)(color & 255) / 255.0F;

        float posX = deltaX * progress;
        float posZ = deltaZ * progress;
        float posY = deltaY * progress;

        double lengthSqr = new Vec3(deltaX, deltaY, deltaZ).lengthSqr();
        int waveFrequency = 3;
        float waveAmplitude = (float) (Math.abs(negativeRelativeHookVelocity) * 0.3 + lengthSqr * 0.0001);
        float wavyness = (float) (waveAmplitude * Math.sin(2 * Math.PI * waveFrequency * progress));
        float midpoint = progress * (1.0f - progress);
        float curve = waveAmplitude * 10 * midpoint;

        if (attached) {
            float shakeProgress = shakeTime / 7;
            if (shakeTime > 0.0F) {
                wavyness = wavyness * Mth.sin(shakeTime * 3) * shakeProgress;
                curve = 0;
            } else {
                wavyness = 0;
                curve = 0;
            }
        }

        posY = posY + offsetY + wavyness + curve;;

        vertices.addVertex(matrices.pose(), posX - offsetZ, posY + thickness2, posZ + offsetX)
                .setColor(red, green, blue, 1.0f).setLight(packedLight);
        vertices.addVertex(matrices.pose(), posX + offsetZ, posY + thickness1 - thickness2, posZ - offsetX)
                .setColor(red, green, blue, 1.0f).setLight(packedLight);
    }

    private static double getRelativeVelocity(Vec3 pos1, Vec3 vel1, Vec3 pos2, Vec3 vel2) {
        Vec3 displacement = pos2.subtract(pos1);
        Vec3 relativeVelocity = vel2.subtract(vel1);
        return relativeVelocity.dot(displacement.normalize());
    }

}
