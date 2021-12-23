//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util;

import net.minecraft.client.Minecraft;

public interface IGlobals {
    public static final Minecraft mc = Minecraft.getMinecraft();

    default public boolean nullCheck() {
        return IGlobals.mc.player != null || IGlobals.mc.world != null;
    }
}

