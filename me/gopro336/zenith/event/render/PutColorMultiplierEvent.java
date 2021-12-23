/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.render;

import java.nio.IntBuffer;
import me.gopro336.zenith.event.EventCancellable;

public class PutColorMultiplierEvent
extends EventCancellable {
    float r;
    float g;
    float b;
    IntBuffer buffer;
    int startBufferSizeIn;

    public PutColorMultiplierEvent(float r, float g, float b, IntBuffer buffer, int startBufferSizeIn) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.buffer = buffer;
        this.startBufferSizeIn = startBufferSizeIn;
    }

    public float getR() {
        return this.r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getG() {
        return this.g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getB() {
        return this.b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public IntBuffer getBuffer() {
        return this.buffer;
    }

    public void setBuffer(IntBuffer buffer) {
        this.buffer = buffer;
    }

    public int getStartBufferSizeIn() {
        return this.startBufferSizeIn;
    }

    public void setStartBufferSizeIn(int startBufferSizeIn) {
        this.startBufferSizeIn = startBufferSizeIn;
    }
}

