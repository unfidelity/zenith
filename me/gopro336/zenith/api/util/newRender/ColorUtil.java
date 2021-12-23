/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.newRender;

import java.awt.Color;
import org.lwjgl.opengl.GL11;

public class ColorUtil {
    public static Color alphaCycle(Color color, int index, int count) {
        float[] hsb = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsb);
        float brightness = Math.abs(((float)(System.currentTimeMillis() % 2000L) / 1000.0f + (float)index / (float)count * 2.0f) % 2.0f - 1.0f);
        brightness = 0.5f + 0.5f * brightness;
        hsb[2] = brightness % 2.0f;
        return new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
    }

    public static int makeBrighter(int color) {
        Color c = new Color(color, true);
        return ColorUtil.toInt(c.brighter());
    }

    public static int makeDarker(int color) {
        Color c = new Color(color, true);
        return ColorUtil.toInt(c.darker());
    }

    public static int toInt(Color color) {
        return (color.getRed() << 16) + (color.getGreen() << 8) + (color.getBlue() << 0) + (color.getAlpha() << 24);
    }

    public static void setColor(Color color) {
        GL11.glColor4d((double)((float)color.getRed() / 255.0f), (double)((float)color.getGreen() / 255.0f), (double)((float)color.getBlue() / 255.0f), (double)((float)color.getAlpha() / 255.0f));
    }

    public static Color staticRainbow() {
        float hue = (float)(System.currentTimeMillis() % 11520L) / 12000.0f;
        int rgb = Color.HSBtoRGB(hue, 1.0f, 1.0f);
        int red = rgb >> 16 & 0xFF;
        int green = rgb >> 8 & 0xFF;
        int blue = rgb & 0xFF;
        return new Color(red, green, blue);
    }

    public static Color dynamicRainbow(long offset, float brightness, int speed) {
        float hue = (float)(System.nanoTime() + offset * (long)speed) / 1.0E10f % 1.0f;
        int color = (int)Long.parseLong(Integer.toHexString(Color.HSBtoRGB(hue, brightness, 1.0f)), 16);
        return new Color((float)new Color(color).getRed() / 255.0f, (float)new Color(color).getGreen() / 255.0f, (float)new Color(color).getBlue() / 255.0f, (float)new Color(color).getAlpha() / 255.0f);
    }

    public static Color alphaIntegrate(Color color, float alpha) {
        float red = (float)color.getRed() / 255.0f;
        float green = (float)color.getGreen() / 255.0f;
        float blue = (float)color.getBlue() / 255.0f;
        return new Color(red, green, blue, alpha);
    }

    public static Color alphaStep(Color color, int index, int count) {
        float[] hsb = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsb);
        float brightness = Math.abs(((float)(System.currentTimeMillis() % 2000L) / 1000.0f + (float)index / (float)count * 2.0f) % 2.0f - 1.0f);
        brightness = 0.5f + 0.5f * brightness;
        hsb[2] = brightness % 2.0f;
        return new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
    }

    public static int toRGBA(int r, int g, int b, int a) {
        return (r << 16) + (g << 8) + b + (a << 24);
    }
}

