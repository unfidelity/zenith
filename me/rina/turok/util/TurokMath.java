//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.rina.turok.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import me.rina.turok.util.TurokRect;
import net.minecraft.util.math.Vec3d;

public class TurokMath {
    public static double PI = 3.1416f;

    public static float amount(float value, float maximum) {
        float h = (maximum - value) / maximum * 100.0f;
        return h;
    }

    public static float distancingValues(float value, float maximum, float distance) {
        float h = value * 100.0f / maximum;
        float l = distance / 100.0f;
        return h * l;
    }

    public static int clamp(int value, int minimum, int maximum) {
        return value <= minimum ? minimum : (value >= maximum ? maximum : value);
    }

    public static double clamp(double value, double minimum, double maximum) {
        return value <= minimum ? minimum : (value >= maximum ? maximum : value);
    }

    public static float clamp(float value, float minimum, float maximum) {
        return value <= minimum ? minimum : (value >= maximum ? maximum : value);
    }

    public static double round(double vDouble) {
        BigDecimal decimal = new BigDecimal(vDouble);
        decimal = decimal.setScale(2, RoundingMode.HALF_UP);
        return decimal.doubleValue();
    }

    public static Vec3d lerp(Vec3d a, Vec3d b, float ticks) {
        return new Vec3d(a.x + (b.x - a.x) * (double)ticks, a.y + (b.y - a.y) * (double)ticks, a.z + (b.z - a.z) * (double)ticks);
    }

    public static float lerp(float a, float b, float ticks) {
        if (ticks == 1.0f || ticks == 5.0f) {
            return b;
        }
        return a + (b - a) * ticks;
    }

    public static TurokRect lerp(TurokRect a, TurokRect b, float ticks) {
        if (ticks == 1.0f || ticks == 5.0f) {
            return a.copy(b);
        }
        a.x = TurokMath.serp(a.x, b.x, ticks);
        a.y = TurokMath.serp(a.y, b.y, ticks);
        a.width = TurokMath.serp(a.width, b.width, ticks);
        b.height = TurokMath.serp(a.height, b.height, ticks);
        return a;
    }

    public static double lerp(double a, double b, float ticks) {
        return a + (b - a) * (double)ticks;
    }

    public static float serp(float a, float b, float ticks) {
        return TurokMath.lerp(a, b, ticks);
    }

    public static double serp(double a, double b, float ticks) {
        return TurokMath.lerp(a, b, ticks);
    }

    public static int normalize(int ... value) {
        int normalizedValue = 0;
        int cachedValue = 0;
        int[] nArray = value;
        int n = nArray.length;
        for (int i = 0; i < n; ++i) {
            int values2;
            cachedValue = values2 = nArray[i];
            normalizedValue = values2 / cachedValue * cachedValue;
        }
        return normalizedValue;
    }

    public static double normalize(double ... value) {
        double normalizedValue = 0.0;
        double cachedValue = 0.0;
        double[] dArray = value;
        int n = dArray.length;
        for (int i = 0; i < n; ++i) {
            double values2;
            cachedValue = values2 = dArray[i];
            normalizedValue = values2 / cachedValue * cachedValue;
        }
        return normalizedValue;
    }

    public static float normalize(float ... value) {
        float normalizedValue = 0.0f;
        float cachedValue = 0.0f;
        float[] fArray = value;
        int n = fArray.length;
        for (int i = 0; i < n; ++i) {
            float values2;
            cachedValue = values2 = fArray[i];
            normalizedValue = values2 / cachedValue * cachedValue;
        }
        return normalizedValue;
    }

    public static int ceiling(double value) {
        int valueInt = (int)value;
        return value >= (double)valueInt ? valueInt + 1 : valueInt;
    }

    public static int ceiling(float value) {
        int valueInt = (int)value;
        return value >= (float)valueInt ? valueInt + 1 : valueInt;
    }

    public static double sqrt(double a) {
        return Math.sqrt(a);
    }

    public static float sqrt(float a) {
        return (float)Math.sqrt(a);
    }

    public static int sqrt(int a) {
        return (int)Math.sqrt(a);
    }

    public static int min(int value, int minimum) {
        return Math.max(value, minimum);
    }

    public static float min(float value, float minimum) {
        return value <= minimum ? minimum : value;
    }

    public static double min(double value, double minimum) {
        return value <= minimum ? minimum : value;
    }

    public static int max(int value, int maximum) {
        return Math.min(value, maximum);
    }

    public static double max(double value, double maximum) {
        return value >= maximum ? maximum : value;
    }

    public static float max(float value, float maximum) {
        return value >= maximum ? maximum : value;
    }

    public static int negative(int a) {
        return a - a - a;
    }

    public static double negative(double a) {
        return a - a - a;
    }

    public static float negative(float a) {
        return a - a - a;
    }

    public static int positive(int a) {
        return a > 0 ? a + a + a : a;
    }

    public static double positive(double a) {
        return a > 0.0 ? a + a + a : a;
    }

    public static float positive(float a) {
        return a > 0.0f ? a + a + a : a;
    }
}

