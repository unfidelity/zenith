/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.world;

import net.minecraftforge.fml.common.gameevent.TickEvent;

public class UpdateEvent {
    private static UpdateEvent INSTANCE = new UpdateEvent();
    private TickEvent.Phase phase;

    public static UpdateEvent get(TickEvent.Phase phase) {
        UpdateEvent.INSTANCE.phase = phase;
        return INSTANCE;
    }

    public TickEvent.Phase getPhase() {
        return this.phase;
    }
}

