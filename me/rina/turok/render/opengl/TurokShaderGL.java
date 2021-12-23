//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.rina.turok.render.opengl;

import java.awt.Color;
import me.rina.turok.hardware.mouse.TurokMouse;
import me.rina.turok.render.opengl.TurokGL;
import me.rina.turok.util.TurokDisplay;
import me.rina.turok.util.TurokMath;
import me.rina.turok.util.TurokRect;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class TurokShaderGL {
    private static TurokShaderGL INSTANCE;
    private TurokDisplay display;
    private TurokMouse mouse;
    public static final Tessellator TESSELLATOR;

    public static void init(TurokDisplay display, TurokMouse mouse) {
        INSTANCE = new TurokShaderGL();
        TurokShaderGL.INSTANCE.display = display;
        TurokShaderGL.INSTANCE.mouse = mouse;
    }

    public static BufferBuilder start() {
        return TESSELLATOR.getBuffer();
    }

    public static void end() {
        TESSELLATOR.draw();
    }

    public static void drawOutlineRectFadingMouse(TurokRect rect, int radius, Color color) {
        TurokShaderGL.drawOutlineRectFadingMouse(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), radius, color);
    }

    public static void drawOutlineRectFadingMouse(float x, float y, float w, float h, int radius, Color color) {
        float offset = 0.5f;
        float vx = x - (float)TurokShaderGL.INSTANCE.mouse.getX();
        float vy = y - (float)TurokShaderGL.INSTANCE.mouse.getY();
        float vw = x + w - (float)TurokShaderGL.INSTANCE.mouse.getX();
        float vh = y + h - (float)TurokShaderGL.INSTANCE.mouse.getY();
        int valueAlpha = color.getAlpha();
        TurokGL.enable(3042);
        TurokGL.blendFunc(770, 771);
        TurokGL.shaderMode(7425);
        TurokGL.color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        TurokGL.lineSize(1.0f);
        TurokGL.prepare(2);
        TurokGL.color(color.getRed(), color.getGreen(), color.getBlue(), (float)valueAlpha - TurokMath.clamp(TurokMath.sqrt(vx * vx + vy * vy) / ((float)radius / 100.0f), 0.0f, (float)valueAlpha));
        TurokGL.addVertex(x + offset, y);
        TurokGL.color(color.getRed(), color.getGreen(), color.getBlue(), (float)valueAlpha - TurokMath.clamp(TurokMath.sqrt(vx * vx + vh * vh) / ((float)radius / 100.0f), 0.0f, (float)valueAlpha));
        TurokGL.addVertex(x + offset, y + h + offset);
        TurokGL.color(color.getRed(), color.getGreen(), color.getBlue(), (float)valueAlpha - TurokMath.clamp(TurokMath.sqrt(vw * vw + vh * vh) / ((float)radius / 100.0f), 0.0f, (float)valueAlpha));
        TurokGL.addVertex(x + w, y + h);
        TurokGL.color(color.getRed(), color.getGreen(), color.getBlue(), (float)valueAlpha - TurokMath.clamp(TurokMath.sqrt(vw * vw + vy * vy) / ((float)radius / 100.0f), 0.0f, (float)valueAlpha));
        TurokGL.addVertex(x + w, y);
        TurokGL.release();
    }

    public static void drawSolidRectFadingMouse(TurokRect rect, int radius, Color color) {
        TurokShaderGL.drawSolidRectFadingMouse(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), radius, color);
    }

    public static void drawSolidRectFadingMouse(float x, float y, float w, float h, int radius, Color color) {
        float vx = x - (float)TurokShaderGL.INSTANCE.mouse.getX();
        float vy = y - (float)TurokShaderGL.INSTANCE.mouse.getY();
        float vw = x + w - (float)TurokShaderGL.INSTANCE.mouse.getX();
        float vh = y + h - (float)TurokShaderGL.INSTANCE.mouse.getY();
        int valueAlpha = color.getAlpha();
        TurokGL.pushMatrix();
        TurokGL.enable(3042);
        TurokGL.blendFunc(770, 771);
        TurokGL.shaderMode(7425);
        TurokGL.prepare(7);
        TurokGL.color(color.getRed(), color.getGreen(), color.getBlue(), (float)valueAlpha - TurokMath.clamp(TurokMath.sqrt(vx * vx + vy * vy) / ((float)radius / 100.0f), 0.0f, (float)valueAlpha));
        TurokGL.addVertex(x, y);
        TurokGL.color(color.getRed(), color.getGreen(), color.getBlue(), (float)valueAlpha - TurokMath.clamp(TurokMath.sqrt(vx * vx + vh * vh) / ((float)radius / 100.0f), 0.0f, (float)valueAlpha));
        TurokGL.addVertex(x, y + h);
        TurokGL.color(color.getRed(), color.getGreen(), color.getBlue(), (float)valueAlpha - TurokMath.clamp(TurokMath.sqrt(vw * vw + vh * vh) / ((float)radius / 100.0f), 0.0f, (float)valueAlpha));
        TurokGL.addVertex(x + w, y + h);
        TurokGL.color(color.getRed(), color.getGreen(), color.getBlue(), (float)valueAlpha - TurokMath.clamp(TurokMath.sqrt(vw * vw + vy * vy) / ((float)radius / 100.0f), 0.0f, (float)valueAlpha));
        TurokGL.addVertex(x + w, y);
        TurokGL.release();
        TurokGL.popMatrix();
    }

    public static void drawLine(float x, float y, float x1, float y1, float w, int[] c) {
        Color color = new Color(TurokMath.clamp(c[0], 0, 255), TurokMath.clamp(c[1], 0, 255), TurokMath.clamp(c[2], 0, 255), TurokMath.clamp(c[3], 0, 255));
        float r = (float)(color.getRGB() >> 16 & 0xFF) / 255.0f;
        float g = (float)(color.getRGB() >> 8 & 0xFF) / 255.0f;
        float b = (float)(color.getRGB() & 0xFF) / 255.0f;
        float a = (float)(color.getRGB() >> 24 & 0xFF) / 255.0f;
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.glLineWidth((float)w);
        BufferBuilder bufferBuilder = TurokShaderGL.start();
        bufferBuilder.begin(6913, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos((double)x, (double)y, 0.0).color(r, g, b, a).endVertex();
        bufferBuilder.pos((double)x1, (double)y1, 0.0).color(r, g, b, a).endVertex();
        TurokShaderGL.end();
        GlStateManager.disableBlend();
    }

    public static void drawSolidRect(TurokRect rect, int[] color) {
        TurokShaderGL.drawSolidRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), color);
    }

    public static void drawSolidRect(float x, float y, float w, float h, int[] c) {
        Color color = new Color(TurokMath.clamp(c[0], 0, 255), TurokMath.clamp(c[1], 0, 255), TurokMath.clamp(c[2], 0, 255), TurokMath.clamp(c[3], 0, 255));
        float r = (float)(color.getRGB() >> 16 & 0xFF) / 255.0f;
        float g = (float)(color.getRGB() >> 8 & 0xFF) / 255.0f;
        float b = (float)(color.getRGB() & 0xFF) / 255.0f;
        float a = (float)(color.getRGB() >> 24 & 0xFF) / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        BufferBuilder bufferBuilder = TurokShaderGL.start();
        bufferBuilder.begin(9, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos((double)x, (double)y, 0.0).color(r, g, b, a).endVertex();
        bufferBuilder.pos((double)x, (double)(y + h), 0.0).color(r, g, b, a).endVertex();
        bufferBuilder.pos((double)(x + w), (double)(y + h), 0.0).color(r, g, b, a).endVertex();
        bufferBuilder.pos((double)(x + w), (double)y, 0.0).color(r, g, b, a).endVertex();
        bufferBuilder.pos((double)x, (double)y, 0.0).color(r, g, b, a).endVertex();
        bufferBuilder.pos((double)x, (double)y, 0.0).color(r, g, b, a).endVertex();
        TurokShaderGL.end();
        GlStateManager.popMatrix();
    }

    public static void drawOutlineRect(TurokRect rect, int[] color) {
        TurokShaderGL.drawOutlineRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), color);
    }

    public static void drawOutlineRect(float x, float y, float w, float h, int[] c) {
        Color color = new Color(TurokMath.clamp(c[0], 0, 255), TurokMath.clamp(c[1], 0, 255), TurokMath.clamp(c[2], 0, 255), TurokMath.clamp(c[3], 0, 255));
        float r = (float)(color.getRGB() >> 16 & 0xFF) / 255.0f;
        float g = (float)(color.getRGB() >> 8 & 0xFF) / 255.0f;
        float b = (float)(color.getRGB() & 0xFF) / 255.0f;
        float a = (float)(color.getRGB() >> 24 & 0xFF) / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.glLineWidth((float)0.5f);
        GlStateManager.shadeModel((int)7425);
        BufferBuilder bufferBuilder = TurokShaderGL.start();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos((double)x, (double)y, 0.0).color(r, g, b, a).endVertex();
        bufferBuilder.pos((double)x, (double)(y + h), 0.0).color(r, g, b, a).endVertex();
        bufferBuilder.pos((double)(x + w), (double)(y + h), 0.0).color(r, g, b, a).endVertex();
        bufferBuilder.pos((double)(x + w), (double)y, 0.0).color(r, g, b, a).endVertex();
        bufferBuilder.pos((double)x, (double)y, 0.0).color(r, g, b, a).endVertex();
        bufferBuilder.pos((double)x, (double)y, 0.0).color(r, g, b, a).endVertex();
        TurokShaderGL.end();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void pushScissor() {
        TurokGL.enable(3089);
    }

    public static void pushScissorMatrix() {
        TurokGL.pushMatrix();
        TurokGL.enable(3089);
    }

    public static void pushScissorAttrib() {
        TurokGL.pushAttrib(524288);
        TurokGL.enable(3089);
    }

    public static void drawScissor(TurokRect rect) {
        TurokShaderGL.drawScissor(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    public static void drawScissor(float x, float y, float w, float h) {
        int calculatedW = (int)(x + w);
        int calculatedH = (int)(y + h);
        TurokGL.scissor((int)(x * (float)TurokShaderGL.INSTANCE.display.getScaleFactor()), TurokShaderGL.INSTANCE.display.getHeight() - calculatedH * TurokShaderGL.INSTANCE.display.getScaleFactor(), (int)(((float)calculatedW - x) * (float)TurokShaderGL.INSTANCE.display.getScaleFactor()), (int)(((float)calculatedH - y) * (float)TurokShaderGL.INSTANCE.display.getScaleFactor()));
    }

    public static void popScissor() {
        TurokGL.disable(3089);
    }

    public static void popScissorMatrix() {
        TurokGL.disable(3089);
        TurokGL.popMatrix();
    }

    public static void popScissorAttrib() {
        TurokGL.disable(3089);
        TurokGL.popAttrib();
    }

    static {
        TESSELLATOR = Tessellator.getInstance();
    }
}

