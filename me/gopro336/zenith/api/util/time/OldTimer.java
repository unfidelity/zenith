//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.time;

import me.gopro336.zenith.api.util.IGlobals;

@Deprecated
public class OldTimer
implements IGlobals {
    public long time = -1L;
    public long timeSaved = System.currentTimeMillis();

    public long GetCurrentTime() {
        return this.timeSaved;
    }

    public boolean GetDifferenceTiming(double d) {
        return (double)(System.currentTimeMillis() - this.timeSaved) >= d;
    }

    public void SetCurrentTime(long l) {
        this.timeSaved = l;
    }

    public void UpdateCurrentTime() {
        this.timeSaved = System.currentTimeMillis();
    }

    public long getMS(long time) {
        return time / 1000000L;
    }

    public boolean passed(long time, Format format) {
        switch (format) {
            case System: {
                return this.getMS(System.nanoTime() - this.time) >= time;
            }
            case Ticks: {
                return OldTimer.mc.player.ticksExisted % (int)time == 0;
            }
        }
        return true;
    }

    public boolean reach(long time, Format format) {
        switch (format) {
            case System: {
                return this.getMS(System.nanoTime() - this.time) <= time;
            }
            case Ticks: {
                return OldTimer.mc.player.ticksExisted % (int)time != 0;
            }
        }
        return true;
    }

    public boolean sleep(long time) {
        if (System.nanoTime() / 1000000L - time >= time) {
            this.reset();
            return true;
        }
        return false;
    }

    public void reset() {
        this.time = System.nanoTime();
    }

    public static enum Format {
        System,
        Ticks;

    }
}

