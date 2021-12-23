//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public final class Logger {
    private static Logger logger = null;

    public void print(String message) {
        System.out.println(String.format("[%s] %s", "Zenith", message));
    }

    public void printToChat(String message) {
        Minecraft.getMinecraft().player.sendMessage(new TextComponentString(String.format("\u00a7c[%s] \u00a77%s", "Zenith", message.replace("&", "\u00a7"))).setStyle(new Style().setColor(TextFormatting.GRAY)));
    }

    public static Logger getLogger() {
        return logger == null ? (logger = new Logger()) : logger;
    }
}

