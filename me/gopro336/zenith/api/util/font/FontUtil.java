//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.font;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import me.gopro336.zenith.api.util.font.CustomFontRenderer;
import me.gopro336.zenith.api.util.newUtil.ChatUtils;
import me.gopro336.zenith.feature.FeatureManager;
import me.gopro336.zenith.feature.toggleable.Client.ClickGuiFeature;
import me.gopro336.zenith.feature.toggleable.render.CustomFont;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class FontUtil {
    public static CustomFontRenderer customFontRenderer = new CustomFontRenderer(new Font("Arial", 0, 21), true, false);
    public static CustomFontRenderer customChatFontRenderer = new CustomFontRenderer(new Font("Verdana", 0, 19), true, false);
    private static final FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;

    public static int getStringHeight() {
        return FontUtil.fontRenderer.FONT_HEIGHT;
    }

    public static int getStringWidth(String text) {
        return FontUtil.customFont() ? customFontRenderer.getStringWidth(text) + 3 : fontRenderer.getStringWidth(text);
    }

    public static void drawString(String text, double X, double Y) {
        if (ClickGuiFeature.textShadow.getValue().booleanValue()) {
            FontUtil.drawStringWithShadow(text, (int)X, (int)Y, new Color((Integer)ClickGuiFeature.tred.getValue(), (Integer)ClickGuiFeature.tgreen.getValue(), (Integer)ClickGuiFeature.tblue.getValue(), (Integer)ClickGuiFeature.talpha.getValue()).getRGB());
        } else {
            FontUtil.drawString(text, (int)X, (int)Y, new Color((Integer)ClickGuiFeature.tred.getValue(), (Integer)ClickGuiFeature.tgreen.getValue(), (Integer)ClickGuiFeature.tblue.getValue(), (Integer)ClickGuiFeature.talpha.getValue()).getRGB());
        }
    }

    public static void drawString(String text, double x, double y, int color) {
        if (FontUtil.customFont()) {
            customFontRenderer.drawString(text, x, y - 1.0 + (double)((Integer)CustomFont.yoffset.getValue()).intValue(), color, false);
        } else {
            fontRenderer.drawString(text, (int)x, (int)y, color);
        }
    }

    public static void drawStringWithShadow(String text, double x, double y, int color) {
        if (FontUtil.customFont()) {
            customFontRenderer.drawStringWithShadow(text, x, y - 1.0 + (double)((Integer)CustomFont.yoffset.getValue()).intValue(), color);
        } else {
            fontRenderer.drawStringWithShadow(text, (float)x, (float)y, color);
        }
    }

    public static int drawStringInt(String text, double x, double y, int color) {
        customChatFontRenderer.drawStringWithShadow(text, x, y - 1.0 + (double)((Integer)CustomFont.yoffset.getValue()).intValue(), color);
        return 0;
    }

    public static int drawStringWithShadowInt(String text, double x, double y, int color) {
        customChatFontRenderer.drawString(text, (float)x, (float)(y - 1.0) + (float)((Integer)CustomFont.yoffset.getValue()).intValue(), color);
        return 0;
    }

    public static void drawCenteredStringWithShadow(String text, float x, float y, int color) {
        if (FontUtil.customFont()) {
            customFontRenderer.drawCenteredStringWithShadow(text, x, y - 1.0f + (float)((Integer)CustomFont.yoffset.getValue()).intValue(), color);
        } else {
            fontRenderer.drawStringWithShadow(text, x - (float)fontRenderer.getStringWidth(text) / 2.0f, y, color);
        }
    }

    public static void drawCenteredString(String text, float x, float y, int color) {
        if (FontUtil.customFont()) {
            customFontRenderer.drawCenteredString(text, x, y - 1.0f + (float)((Integer)CustomFont.yoffset.getValue()).intValue(), color);
        } else {
            fontRenderer.drawString(text, (int)(x - (float)(FontUtil.getStringWidth(text) / 2)), (int)y, color);
        }
    }

    public static int getFontHeight() {
        return FontUtil.customFont() ? FontUtil.customFontRenderer.fontHeight / 2 - 1 : FontUtil.fontRenderer.FONT_HEIGHT;
    }

    public static boolean validateFont(String font) {
        for (String s : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()) {
            if (!s.equals(font)) continue;
            return true;
        }
        return false;
    }

    public static void setFontRenderer(String stringIn, int size) {
        try {
            if (FontUtil.getCorrectFont(stringIn) == null && !stringIn.equalsIgnoreCase("Zenith")) {
                ChatUtils.error("Invalid font!");
                return;
            }
            customFontRenderer = new CustomFontRenderer(new Font(stringIn, 0, size), true, false);
        }
        catch (Exception e) {
            ChatUtils.error(e.toString());
            e.printStackTrace();
        }
    }

    public static String getCorrectFont(String stringIn) {
        for (String s : FontUtil.getFonts()) {
            if (!s.equalsIgnoreCase(stringIn)) continue;
            return s;
        }
        return null;
    }

    public static String[] getFonts() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    }

    private static boolean customFont() {
        return FeatureManager.getModule("CustomFont").isEnabled();
    }
}

