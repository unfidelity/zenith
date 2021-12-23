/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.player;

import me.gopro336.zenith.event.EventCancellable;

public class PushOutOfBlocksEvent
extends EventCancellable {
    public double x;
    public double y;
    public double z;

    public PushOutOfBlocksEvent(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

