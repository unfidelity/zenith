/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.world;

import me.gopro336.zenith.event.EventCancellable;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.world.chunk.Chunk;

public class ChunkEvent
extends EventCancellable {
    private Chunk chunk;
    private SPacketChunkData data;

    public ChunkEvent(Chunk chunk, SPacketChunkData data) {
        this.chunk = chunk;
        this.data = data;
    }

    public Chunk getChunk() {
        return this.chunk;
    }

    public SPacketChunkData getData() {
        return this.data;
    }
}

