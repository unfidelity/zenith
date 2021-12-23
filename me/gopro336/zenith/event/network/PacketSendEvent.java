/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.network;

import io.netty.channel.SimpleChannelInboundHandler;
import me.gopro336.zenith.event.EventCancellable;
import me.gopro336.zenith.event.EventStageable;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;

public class PacketSendEvent
extends EventCancellable {
    private Packet packet;
    private NetworkManager networkManager;

    public PacketSendEvent(EventStageable.EventStage stage, Packet packet, SimpleChannelInboundHandler networkManager) {
        super(stage);
        this.packet = packet;
        this.networkManager = (NetworkManager)networkManager;
    }

    public PacketSendEvent(EventStageable.EventStage stage, Packet packet, NetworkManager networkManager) {
        super(stage);
        this.packet = packet;
        this.networkManager = networkManager;
    }

    public Packet getPacket() {
        return this.packet;
    }

    public NetworkManager getNetworkManager() {
        return this.networkManager;
    }
}

