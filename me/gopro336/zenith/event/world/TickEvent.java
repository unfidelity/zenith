/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.world;

import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TickEvent {
    public static TickEvent INSTANCE = new TickEvent();
    public TickEvent.Phase Field199;

    public TickEvent.Phase Method324() {
        return this.Field199;
    }

    public static TickEvent Method325(TickEvent.Phase phase) {
        TickEvent.INSTANCE.Field199 = phase;
        return INSTANCE;
    }
}

