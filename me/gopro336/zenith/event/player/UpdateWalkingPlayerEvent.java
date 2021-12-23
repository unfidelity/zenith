/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.player;

import me.gopro336.zenith.event.EventCancellable;

public class UpdateWalkingPlayerEvent
extends EventCancellable {
    protected double x;
    protected double y;
    protected double z;
    protected float yaw;
    protected float pitch;
    protected boolean onGround;

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return this.z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public float getYaw() {
        return this.yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public boolean isOnGround() {
        return this.onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public static class Post
    extends UpdateWalkingPlayerEvent {
        private static Post INSTANCE = new Post();

        public static Post get(double x, double y, double z, float yaw, float pitch, boolean onGround) {
            INSTANCE.setCanceled(false);
            Post.INSTANCE.x = x;
            Post.INSTANCE.y = y;
            Post.INSTANCE.z = z;
            Post.INSTANCE.yaw = yaw;
            Post.INSTANCE.pitch = pitch;
            Post.INSTANCE.onGround = onGround;
            return INSTANCE;
        }
    }

    public static class Pre
    extends UpdateWalkingPlayerEvent {
        private static Pre INSTANCE = new Pre();

        public static Pre get(double x, double y, double z, float yaw, float pitch, boolean onGround) {
            INSTANCE.setCanceled(false);
            Pre.INSTANCE.x = x;
            Pre.INSTANCE.y = y;
            Pre.INSTANCE.z = z;
            Pre.INSTANCE.yaw = yaw;
            Pre.INSTANCE.pitch = pitch;
            Pre.INSTANCE.onGround = onGround;
            return INSTANCE;
        }
    }
}

