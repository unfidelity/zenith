/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.color;

import java.awt.Color;

public class ColorUtils {
    public static float[] intToRGB(int color) {
        float alpha = (float)(color >> 24 & 0xFF) / 255.0f;
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        return new float[]{red, green, blue, alpha};
    }

    public static Color rainbow(int delay, float sat, float bright) {
        double rainbowState = Math.ceil((double)(System.currentTimeMillis() + (long)delay) / 20.0);
        return Color.getHSBColor((float)((rainbowState %= 360.0) / 360.0), sat / 255.0f, bright / 255.0f);
    }

    public static Color pulse(int delay, float[] hsb, float spread, float speed, float range) {
        double sin = Math.sin((double)spread * ((double)System.currentTimeMillis() / Math.pow(10.0, 2.0) * (double)(speed / 10.0f) + (double)delay));
        return Color.getHSBColor(hsb[0], hsb[1], (float)(((sin *= (double)range) + 1.0) / 2.0) + (1.0f - range) * 0.5f);
    }

    private Color parseHex(String string) {
        Color color;
        if (!string.startsWith("#")) {
            return null;
        }
        String hex = string.toLowerCase().replaceAll("[^0-9a-f]", "");
        if (hex.length() != 6 && hex.length() != 8) {
            return null;
        }
        try {
            color = hex.length() == 8 ? new Color(Integer.parseInt(hex.substring(0, 2), 16), Integer.parseInt(hex.substring(2, 4), 16), Integer.parseInt(hex.substring(4, 6), 16), Integer.parseInt(hex.substring(6, 8), 16)) : new Color(Integer.parseInt(hex.substring(0, 2), 16), Integer.parseInt(hex.substring(2, 4), 16), Integer.parseInt(hex.substring(4, 6), 16));
        }
        catch (NumberFormatException e) {
            return null;
        }
        return color;
    }
}

