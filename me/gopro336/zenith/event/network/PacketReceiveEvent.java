/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.network;

import me.gopro336.zenith.event.EventCancellable;
import me.gopro336.zenith.event.EventStageable;
import net.minecraft.network.Packet;

public class PacketReceiveEvent
extends EventCancellable {
    private Packet packet;

    public PacketReceiveEvent(EventStageable.EventStage stage, Packet packet) {
        super(stage);
        this.packet = packet;
    }

    public Packet getPacket() {
        return this.packet;
    }
}

