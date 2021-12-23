//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.rina.turok.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;

public class TurokDisplay {
    private Minecraft mc;
    private int scaleFactor;
    private float partialTicks;

    public TurokDisplay(Minecraft mc) {
        this.mc = mc;
        this.onUpdate();
    }

    public int getWidth() {
        return this.mc.displayWidth;
    }

    public int getHeight() {
        return this.mc.displayHeight;
    }

    public int getScaledWidth() {
        return MathHelper.ceil((double)((double)this.mc.displayWidth / (double)this.scaleFactor));
    }

    public int getScaledHeight() {
        return MathHelper.ceil((double)((double)this.mc.displayHeight / (double)this.scaleFactor));
    }

    public int getScaleFactor() {
        return this.scaleFactor;
    }

    public void setPartialTicks(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }

    protected void onUpdate() {
        this.scaleFactor = 1;
        boolean isUnicode = this.mc.isUnicode();
        int minecraftScale = this.mc.gameSettings.guiScale;
        if (minecraftScale == 0) {
            minecraftScale = 1000;
        }
        while (this.scaleFactor < minecraftScale && this.getWidth() / (this.scaleFactor + 1) >= 320 && this.getHeight() / (this.scaleFactor + 1) >= 240) {
            ++this.scaleFactor;
        }
        if (isUnicode && this.scaleFactor % 2 != 0 && this.scaleFactor != 1) {
            --this.scaleFactor;
        }
    }
}

