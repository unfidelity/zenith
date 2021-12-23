/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundingUtil {
    public static double roundDouble(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double roundToStep(double value, double step) {
        return step * (double)Math.round(value * (1.0 / step));
    }

    public static float roundFloat(float value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
    }

    public static float roundToStep(float value, float step) {
        return step * (float)Math.round(value * (1.0f / step));
    }
}

