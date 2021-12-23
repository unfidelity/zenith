/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.player;

import me.gopro336.zenith.event.EventCancellable;

public class PlayerDismountEvent
extends EventCancellable {
    public static PlayerDismountEvent INSTANCE = new PlayerDismountEvent();

    public static PlayerDismountEvent getEvent() {
        INSTANCE.setCanceled(false);
        return INSTANCE;
    }
}

