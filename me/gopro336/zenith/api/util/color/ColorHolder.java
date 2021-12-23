/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.color;

import java.awt.Color;

public final class ColorHolder {
    private int color;
    private boolean cycle = false;
    private int globalOffset = 0;

    public ColorHolder(int color) {
        this.color = color;
    }

    public ColorHolder(int color, boolean cycle) {
        this.color = color;
        this.cycle = cycle;
    }

    public ColorHolder(int color, boolean cycle, int globalOffset) {
        this.color = color;
        this.cycle = cycle;
        this.globalOffset = globalOffset;
    }

    public ColorHolder withAlpha(int alpha) {
        int red = this.getColor() >> 16 & 0xFF;
        int green = this.getColor() >> 8 & 0xFF;
        int blue = this.getColor() & 0xFF;
        return new ColorHolder((alpha & 0xFF) << 24 | (red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF);
    }

    public static int parseColor(String nm) throws NumberFormatException {
        Integer intval = Integer.decode(nm);
        return intval;
    }

    public int getColor() {
        if (this.cycle) {
            float[] hsb = Color.RGBtoHSB(this.color >> 16 & 0xFF, this.color >> 8 & 0xFF, this.color & 0xFF, null);
            double rainbowState = Math.ceil((double)(System.currentTimeMillis() + 300L + (long)this.globalOffset) / 20.0);
            int rgb = Color.getHSBColor((float)((rainbowState %= 360.0) / 360.0), hsb[1], hsb[2]).getRGB();
            int alpha = this.color >> 24 & 0xFF;
            int red = rgb >> 16 & 0xFF;
            int green = rgb >> 8 & 0xFF;
            int blue = rgb & 0xFF;
            return (alpha & 0xFF) << 24 | (red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF;
        }
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getGlobalOffset() {
        return this.globalOffset;
    }

    public void setGlobalOffset(int globalOffset) {
        this.globalOffset = globalOffset;
    }

    public int getOffsetColor(int offset) {
        if (this.cycle) {
            float[] hsb = Color.RGBtoHSB(this.color >> 16 & 0xFF, this.color >> 8 & 0xFF, this.color & 0xFF, null);
            double rainbowState = Math.ceil((double)(System.currentTimeMillis() + 300L + (long)offset + (long)this.globalOffset) / 20.0);
            int rgb = Color.getHSBColor((float)((rainbowState %= 360.0) / 360.0), hsb[1], hsb[2]).getRGB();
            int alpha = this.color >> 24 & 0xFF;
            int red = rgb >> 16 & 0xFF;
            int green = rgb >> 8 & 0xFF;
            int blue = rgb & 0xFF;
            return (alpha & 0xFF) << 24 | (red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF;
        }
        return this.color;
    }

    public int getRed() {
        if (this.cycle) {
            float[] hsb = Color.RGBtoHSB(this.color >> 16 & 0xFF, this.color >> 8 & 0xFF, this.color & 0xFF, null);
            double rainbowState = Math.ceil((double)(System.currentTimeMillis() + 300L + (long)this.globalOffset) / 20.0);
            int rgb = Color.getHSBColor((float)((rainbowState %= 360.0) / 360.0), hsb[1], hsb[2]).getRGB();
            return rgb >> 16 & 0xFF;
        }
        return this.color >> 16 & 0xFF;
    }

    public int getGreen() {
        if (this.cycle) {
            float[] hsb = Color.RGBtoHSB(this.color >> 16 & 0xFF, this.color >> 8 & 0xFF, this.color & 0xFF, null);
            double rainbowState = Math.ceil((double)(System.currentTimeMillis() + 300L + (long)this.globalOffset) / 20.0);
            int rgb = Color.getHSBColor((float)((rainbowState %= 360.0) / 360.0), hsb[1], hsb[2]).getRGB();
            return rgb >> 8 & 0xFF;
        }
        return this.color >> 8 & 0xFF;
    }

    public int getBlue() {
        if (this.cycle) {
            float[] hsb = Color.RGBtoHSB(this.color >> 16 & 0xFF, this.color >> 8 & 0xFF, this.color & 0xFF, null);
            double rainbowState = Math.ceil((double)(System.currentTimeMillis() + 300L + (long)this.globalOffset) / 20.0);
            int rgb = Color.getHSBColor((float)((rainbowState %= 360.0) / 360.0), hsb[1], hsb[2]).getRGB();
            return rgb & 0xFF;
        }
        return this.color & 0xFF;
    }

    public int getAlpha() {
        return this.color >> 24 & 0xFF;
    }

    public Color getColorObject() {
        int color = this.getColor();
        int alpha = color >> 24 & 0xFF;
        int red = color >> 16 & 0xFF;
        int green = color >> 8 & 0xFF;
        int blue = color & 0xFF;
        return new Color(red, green, blue, alpha);
    }

    public int getRawColor() {
        return this.color;
    }

    public boolean isCycle() {
        return this.cycle;
    }

    public void setCycle(boolean cycle) {
        this.cycle = cycle;
    }

    public void toggleCycle() {
        this.cycle = !this.cycle;
    }
}

