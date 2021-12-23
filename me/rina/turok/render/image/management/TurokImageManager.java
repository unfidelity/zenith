//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.rina.turok.render.image.management;

import java.awt.Color;
import me.rina.turok.render.image.TurokImage;
import me.rina.turok.render.opengl.TurokGL;
import me.rina.turok.render.opengl.TurokRenderGL;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class TurokImageManager {
    public static void render(TurokImage image, int x, int y, float xx, float yy, int w, int h, float ww, float hh, Color color) {
        TurokGL.enable(3042);
        TurokGL.blendFunc(770, 771);
        TurokGL.enable(3553);
        TurokGL.enable(2884);
        TurokGL.disable(2929);
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        Minecraft.getMinecraft().renderEngine.bindTexture(image.getResourceLocation());
        GL11.glTexParameteri((int)3553, (int)10242, (int)10497);
        GL11.glTexParameteri((int)3553, (int)10243, (int)10497);
        TurokRenderGL.drawTextureInterpolated(x, y, xx, yy, w, h, ww, hh);
        TurokGL.disable(3042);
        TurokGL.disable(3553);
        TurokGL.disable(2884);
        TurokGL.enable(2929);
    }

    public static void render(TurokImage image, int x, int y, int w, int h, Color color) {
        TurokImageManager.render(image, x, y, w, h, 0, 0, 1.0f, 1.0f, color);
    }
}

