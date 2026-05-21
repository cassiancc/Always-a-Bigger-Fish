package cc.cassian.bigger_fish.client.renderer;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class GrapplingHookRenderState extends EntityRenderState {
    public boolean sticky;
    public Vec3 lineOriginOffset;
    public List<Integer> colors;
    public boolean isAttached;
    public Vec3 playerPos;
    public Vec3 hookPos;
    public Vec3 playerMovement;
    public Vec3 hookMovement;
    public int shakeTime;
    public boolean hasBobber;
    public ItemStack bobber =  ItemStack.EMPTY;
    public float yRotO;
    public float yRot;
    public float xRotO;
    public float xRot;
    public float partialTicks;
}
