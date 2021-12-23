//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.newRotations;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class RotationManager {
    public Minecraft mc = Minecraft.getMinecraft();
    public float yaw;
    public float pitch;
    public boolean rotationsSet = false;

    public void setRotations(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.rotationsSet = true;
    }

    public void reset() {
        this.yaw = this.mc.player.rotationYaw;
        this.pitch = this.mc.player.rotationPitch;
        this.rotationsSet = false;
    }

    public boolean safeSetRotations(float yaw, float pitch) {
        if (this.rotationsSet) {
            return false;
        }
        this.setRotations(yaw, pitch);
        return true;
    }

    public boolean isRotationsSet() {
        return this.rotationsSet;
    }

    public void lookAtXYZ(double x, double y, double z) {
        Vec3d vec3d = new Vec3d(x, y, z);
        this.lookAtVec3d(vec3d);
    }

    public void lookAtVec3d(Vec3d vec3d) {
        float[] angle = RotationManager.calculateAngle(this.mc.player.getPositionEyes(this.mc.getRenderPartialTicks()), new Vec3d(vec3d.x, vec3d.y, vec3d.z));
        this.setRotations(angle[0], angle[1]);
    }

    public void lookAtPos(BlockPos blockPos) {
        float[] angle = RotationManager.calculateAngle(this.mc.player.getPositionEyes(this.mc.getRenderPartialTicks()), new Vec3d((double)((float)blockPos.getX() + 0.5f), (double)((float)blockPos.getY() + 0.5f), (double)((float)blockPos.getZ() + 0.5f)));
        this.setRotations(angle[0], angle[1]);
    }

    public float getPitch() {
        return this.pitch;
    }

    public float getYaw() {
        return this.yaw;
    }

    public static float[] calculateAngle(Vec3d from, Vec3d to) {
        double difX = to.x - from.x;
        double difY = (to.y - from.y) * -1.0;
        double difZ = to.z - from.z;
        double dist = MathHelper.sqrt((double)(difX * difX + difZ * difZ));
        float yD = (float)MathHelper.wrapDegrees((double)(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0));
        float pD = (float)MathHelper.wrapDegrees((double)Math.toDegrees(Math.atan2(difY, dist)));
        if (pD > 90.0f) {
            pD = 90.0f;
        } else if (pD < -90.0f) {
            pD = -90.0f;
        }
        return new float[]{yD, pD};
    }
}

