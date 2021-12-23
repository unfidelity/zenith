/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.player;

import me.gopro336.zenith.event.EventCancellable;

public class MotionUpdateEvent
extends EventCancellable {
    float yaw;
    float pitch;

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }
}

