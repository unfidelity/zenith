/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.time;

public class NanoTimer {
    private long time = System.nanoTime();

    public boolean hasPassed(double ns) {
        return (double)(System.nanoTime() - this.time) >= ns;
    }

    public void reset() {
        this.time = System.nanoTime();
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

