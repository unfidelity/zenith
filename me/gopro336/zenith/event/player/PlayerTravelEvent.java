/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.player;

import me.gopro336.zenith.event.EventCancellable;

public class PlayerTravelEvent
extends EventCancellable {
    public float strafe;
    public float vertical;
    public float forward;

    public float getVertical() {
        return this.vertical;
    }

    public PlayerTravelEvent(float strafe, float vertical, float forward) {
        this.strafe = strafe;
        this.vertical = vertical;
        this.forward = forward;
    }

    public float getForward() {
        return this.forward;
    }

    public float getStrafe() {
        return this.strafe;
    }
}

