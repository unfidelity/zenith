/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.kotlin;

public final class floor {
    public static final float PI_FLOAT = (float)Math.PI;

    public static final int floorToInt(double $this$floorToInt) {
        boolean bl = false;
        return (int)Math.floor($this$floorToInt);
    }

    public static final int floorToInt(float $this$floorToInt) {
        boolean bl = false;
        return (int)Math.floor($this$floorToInt);
    }

    public static final int ceilToInt(double $this$ceilToInt) {
        boolean bl = false;
        return (int)Math.ceil($this$ceilToInt);
    }

    public static final int ceilToInt(float $this$ceilToInt) {
        boolean bl = false;
        return (int)Math.ceil($this$ceilToInt);
    }

    public static final float toRadian(float $this$toRadian) {
        return $this$toRadian / 180.0f * (float)Math.PI;
    }

    public static final double toRadian(double $this$toRadian) {
        return $this$toRadian / 180.0 * Math.PI;
    }

    public static final float toDegree(float $this$toDegree) {
        return $this$toDegree * 180.0f / (float)Math.PI;
    }

    public static final double toDegree(double $this$toDegree) {
        return $this$toDegree * 180.0 / Math.PI;
    }
}

