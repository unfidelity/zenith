/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.render;

import me.gopro336.zenith.event.EventCancellable;
import net.minecraft.block.Block;

public class ShouldSideRenderEvent
extends EventCancellable {
    private Block block;

    public ShouldSideRenderEvent(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return this.block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}

