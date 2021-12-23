/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.render;

import net.minecraft.client.renderer.chunk.ChunkCompileTaskGenerator;
import net.minecraft.client.renderer.chunk.RenderChunk;

public class FreeRenderBuilderEvent {
    private ChunkCompileTaskGenerator chunkCompileTaskGenerator;
    private RenderChunk renderChunk;

    public FreeRenderBuilderEvent(ChunkCompileTaskGenerator chunkCompileTaskGenerator, RenderChunk renderChunk) {
        this.chunkCompileTaskGenerator = chunkCompileTaskGenerator;
        this.renderChunk = renderChunk;
    }

    public RenderChunk getRenderChunk() {
        return this.renderChunk;
    }

    public void setRenderChunk(RenderChunk renderChunk) {
        this.renderChunk = renderChunk;
    }

    public ChunkCompileTaskGenerator getChunkCompileTaskGenerator() {
        return this.chunkCompileTaskGenerator;
    }

    public void setChunkCompileTaskGenerator(ChunkCompileTaskGenerator chunkCompileTaskGenerator) {
        this.chunkCompileTaskGenerator = chunkCompileTaskGenerator;
    }
}

