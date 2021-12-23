//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.rina.turok.render.font.management;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import me.rina.turok.render.font.TurokFont;
import me.rina.turok.render.opengl.TurokGL;
import me.rina.turok.util.TurokMath;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.CharUtils;

public class TurokFontManager {
    public static void render(TurokFont fontRenderer, String string, float x, float y, boolean shadow, int factor) {
        float[] currentColor360 = new float[]{(float)(System.currentTimeMillis() % 11520L) / 11520.0f};
        int cycleColor = Color.HSBtoRGB(currentColor360[0], 1.0f, 1.0f);
        Color currentColor = new Color(cycleColor >> 16 & 0xFF, cycleColor >> 8 & 0xFF, cycleColor & 0xFF);
        float hueIncrement = 1.0f / (float)factor;
        float currentHue = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null)[0];
        float saturation = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null)[1];
        float brightness = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null)[2];
        float currentWidth = 0.0f;
        boolean shouldRainbow = true;
        boolean shouldContinue = false;
        ChatFormatting colorCache = ChatFormatting.GRAY;
        for (int i = 0; i < string.length(); ++i) {
            char currentChar = string.charAt(i);
            char nextChar = string.charAt(TurokMath.clamp(i + 1, 0, string.length() - 1));
            String nextFormatting = String.valueOf(currentChar) + nextChar;
            if (nextFormatting.equals("\u00a7r") && !shouldRainbow) {
                shouldRainbow = true;
            } else if (String.valueOf(currentChar).equals("\u00a7")) {
                shouldRainbow = false;
            }
            if (shouldContinue) {
                shouldContinue = false;
                continue;
            }
            if (String.valueOf(currentChar).equals("\u00a7")) {
                shouldContinue = true;
                colorCache = ChatFormatting.getByChar((char)CharUtils.toChar((String)nextFormatting.replaceAll("\u00a7", "")));
                continue;
            }
            TurokFontManager.render(fontRenderer, (!shouldRainbow ? colorCache : "") + String.valueOf(currentChar), x + currentWidth, y, shadow, currentColor);
            currentWidth += (float)TurokFontManager.getStringWidth(fontRenderer, String.valueOf(currentChar));
            if (String.valueOf(currentChar).equals(" ")) continue;
            currentColor = new Color(Color.HSBtoRGB(currentHue, saturation, brightness));
            currentHue += hueIncrement;
        }
    }

    public static void render(String string, float x, float y, boolean shadow, int factor) {
        float[] currentColor360 = new float[]{(float)(System.currentTimeMillis() % 11520L) / 11520.0f};
        int cycleColor = Color.HSBtoRGB(currentColor360[0], 1.0f, 1.0f);
        Color currentColor = new Color(cycleColor >> 16 & 0xFF, cycleColor >> 8 & 0xFF, cycleColor & 0xFF);
        float hueIncrement = 1.0f / (float)factor;
        float currentHue = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null)[0];
        float saturation = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null)[1];
        float brightness = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null)[2];
        float currentWidth = 0.0f;
        boolean shouldRainbow = true;
        boolean shouldContinue = false;
        ChatFormatting colorCache = ChatFormatting.GRAY;
        for (int i = 0; i < string.length(); ++i) {
            char currentChar = string.charAt(i);
            char nextChar = string.charAt(TurokMath.clamp(i + 1, 0, string.length() - 1));
            String nextFormatting = String.valueOf(currentChar) + nextChar;
            if (nextFormatting.equals("\u00a7r") && !shouldRainbow) {
                shouldRainbow = true;
            } else if (String.valueOf(currentChar).equals("\u00a7")) {
                shouldRainbow = false;
            }
            if (shouldContinue) {
                shouldContinue = false;
                continue;
            }
            if (String.valueOf(currentChar).equals("\u00a7")) {
                shouldContinue = true;
                colorCache = ChatFormatting.getByChar((char)CharUtils.toChar((String)nextFormatting.replaceAll("\u00a7", "")));
                continue;
            }
            TurokFontManager.render((!shouldRainbow ? colorCache : "") + String.valueOf(currentChar), x + currentWidth, y, shadow, currentColor);
            currentWidth += (float)TurokFontManager.getStringWidth(String.valueOf(currentChar));
            if (String.valueOf(currentChar).equals(" ")) continue;
            currentColor = new Color(Color.HSBtoRGB(currentHue, saturation, brightness));
            currentHue += hueIncrement;
        }
    }

    public static void render(String string, float x, float y, boolean shadow, Color color) {
        TurokGL.pushMatrix();
        TurokGL.enable(3553);
        if (shadow) {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(string, (float)((int)x), (float)((int)y), color.getRGB());
        } else {
            Minecraft.getMinecraft().fontRenderer.drawString(string, (int)x, (int)y, color.getRGB());
        }
        TurokGL.disable(3553);
        TurokGL.popMatrix();
    }

    public static void render(TurokFont fontRenderer, String string, float x, float y, boolean shadow, Color color) {
        TurokGL.pushMatrix();
        TurokGL.enable(3553);
        if (shadow) {
            if (fontRenderer.isRenderingCustomFont()) {
                fontRenderer.drawStringWithShadow(string, x, y, color.getRGB());
            } else {
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(string, (float)((int)x), (float)((int)y), color.getRGB());
            }
        } else if (fontRenderer.isRenderingCustomFont()) {
            fontRenderer.drawString(string, x, y, color.getRGB());
        } else {
            Minecraft.getMinecraft().fontRenderer.drawString(string, (int)x, (int)y, color.getRGB());
        }
        TurokGL.disable(3553);
        TurokGL.popMatrix();
    }

    public static int getStringWidth(TurokFont fontRenderer, String string) {
        return fontRenderer.isRenderingCustomFont() ? fontRenderer.getStringWidth(string) : Minecraft.getMinecraft().fontRenderer.getStringWidth(string);
    }

    public static int getStringWidth(String string) {
        return Minecraft.getMinecraft().fontRenderer.getStringWidth(string);
    }

    public static int getStringHeight(TurokFont fontRenderer, String string) {
        return fontRenderer.isRenderingCustomFont() ? fontRenderer.getStringHeight(string) : Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * fontRenderer.getFontSize();
    }

    public static int getStringHeight(String string) {
        return Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
    }
}

