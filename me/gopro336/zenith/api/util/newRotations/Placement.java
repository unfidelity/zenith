/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.newRotations;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Placement {
    public BlockPos neighbour;
    public EnumFacing opposite;
    public Vec3d hitVec;
    public float yaw;
    public float pitch;

    public float getPitch() {
        return this.pitch;
    }

    public Placement(BlockPos blockPos, EnumFacing enumFacing, Vec3d vec3d, float yaw, float pitch) {
        this.neighbour = blockPos;
        this.opposite = enumFacing;
        this.hitVec = vec3d;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public float getYaw() {
        return this.yaw;
    }

    public EnumFacing getOpposite() {
        return this.opposite;
    }

    public Vec3d getHitVec() {
        return this.hitVec;
    }

    public BlockPos getNeighbour() {
        return this.neighbour;
    }
}

