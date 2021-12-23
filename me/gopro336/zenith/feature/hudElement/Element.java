//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.hudElement;

import me.gopro336.zenith.feature.Feature;
import net.minecraft.client.Minecraft;

public class Element
extends Feature {
    protected final Minecraft mc = Minecraft.getMinecraft();
    public float x;
    public float y;
    public float w;
    public float h;
    public float lastWidth;
    public float lastHeight;
    public float lastX;
    public float lastY;
    private boolean showing;

    public void onRender() {
    }

    @Override
    public void onUpdate() {
    }

    public void init() {
    }

    public void close() {
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return this.w;
    }

    public void setWidth(float w) {
        this.w = w;
    }

    public float getHeight() {
        return this.h;
    }

    public void setHeight(int h) {
        this.h = h;
    }

    public boolean isShowing() {
        return this.showing;
    }

    public void setShowing(boolean showing) {
        this.showing = showing;
    }
}

