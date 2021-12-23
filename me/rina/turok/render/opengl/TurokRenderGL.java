//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.rina.turok.render.opengl;

import me.rina.turok.render.opengl.TurokGL;
import me.rina.turok.util.TurokRect;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class TurokRenderGL {
    public static void color(int r, int g, int b, int a) {
        TurokGL.color((float)r + 0.0f, (float)g + 0.0f, (float)b + 0.0f, (float)a + 0.0f);
    }

    public static void drawTexture(float x, float y, float width, float height) {
        TurokGL.prepare(7);
        TurokGL.sewTexture(0.0f, 0.0f);
        TurokGL.addVertex(x, y);
        TurokGL.sewTexture(0.0f, 1.0f);
        TurokGL.addVertex(x, y + height);
        TurokGL.sewTexture(1.0f, 1.0f);
        TurokGL.addVertex(x + width, y + height);
        TurokGL.sewTexture(1.0f, 0.0f);
        TurokGL.addVertex(x + width, y);
        TurokGL.release();
    }

    public static void drawTextureInterpolated(float x, float y, float xx, float yy, float width, float height, float ww, float hh) {
        TurokGL.prepare(7);
        TurokGL.sewTexture(0.0f + xx, 0.0f + hh);
        TurokGL.addVertex(x, y);
        TurokGL.sewTexture(0.0f + xx, 1.0f + hh);
        TurokGL.addVertex(x, y + height);
        TurokGL.sewTexture(1.0f + ww, 1.0f + hh);
        TurokGL.addVertex(x + width, y + height);
        TurokGL.sewTexture(1.0f + ww, 0.0f + hh);
        TurokGL.addVertex(x + width, y);
        TurokGL.release();
    }

    public static void drawUpTriangle(float x, float y, float width, float height, int offsetX) {
        TurokGL.enable(3042);
        TurokGL.blendFunc(770, 771);
        TurokGL.prepare(6);
        TurokGL.addVertex(x + width, y + height);
        TurokGL.addVertex(x + width, y);
        TurokGL.addVertex(x - (float)offsetX, y);
        TurokGL.release();
    }

    public static void drawDownTriangle(float x, float y, float width, float height, int offsetX) {
        TurokGL.enable(3042);
        TurokGL.blendFunc(770, 771);
        TurokGL.prepare(6);
        TurokGL.addVertex(x, y);
        TurokGL.addVertex(x, y + height);
        TurokGL.addVertex(x + width + (float)offsetX, y + height);
        TurokGL.release();
    }

    public static void drawArc(float cx, float cy, float r, float start_angle, float end_angle, float num_segments) {
        TurokGL.enable(3042);
        TurokGL.blendFunc(770, 771);
        TurokGL.prepare(4);
        int i = (int)(num_segments / (360.0f / start_angle)) + 1;
        while ((float)i <= num_segments / (360.0f / end_angle)) {
            float previousAngle = (float)(Math.PI * 2 * (double)(i - 1) / (double)num_segments);
            float angle = (float)(Math.PI * 2 * (double)i / (double)num_segments);
            TurokGL.addVertex(cx, cy);
            TurokGL.addVertex((float)((double)cx + Math.cos(angle) * (double)r), (float)((double)cy + Math.sin(angle) * (double)r));
            TurokGL.addVertex((float)((double)cx + Math.cos(previousAngle) * (double)r), (float)((double)cy + Math.sin(previousAngle) * (double)r));
            ++i;
        }
        TurokGL.release();
    }

    public static void drawArc(float x, float y, float radius) {
        TurokRenderGL.drawArc(x, y, radius, 0.0f, 360.0f, 40.0f);
    }

    public static void drawArcOutline(float cx, float cy, float r, float start_angle, float end_angle, float num_segments) {
        TurokGL.enable(3042);
        TurokGL.blendFunc(770, 771);
        TurokGL.prepare(2);
        int i = (int)(num_segments / (360.0f / start_angle)) + 1;
        while ((float)i <= num_segments / (360.0f / end_angle)) {
            float angle = (float)(Math.PI * 2 * (double)i / (double)num_segments);
            TurokGL.addVertex((float)((double)cx + Math.cos(angle) * (double)r), (float)((double)cy + Math.sin(angle) * (double)r));
            ++i;
        }
        TurokGL.release();
    }

    public static void drawArcOutline(float x, float y, float radius) {
        TurokRenderGL.drawArcOutline(x, y, radius, 0.0f, 360.0f, 40.0f);
    }

    public static void drawOutlineRect(float x, float y, float width, float height) {
        TurokGL.pushMatrix();
        TurokGL.enable(3042);
        TurokGL.blendFunc(770, 771);
        TurokGL.lineSize(1.0f);
        TurokGL.prepare(3);
        TurokGL.addVertex(x, y);
        TurokGL.addVertex(x, y + height);
        TurokGL.addVertex(x + width, y + height);
        TurokGL.addVertex(x + width, y);
        TurokGL.addVertex(x, y);
        TurokGL.addVertex(x, y);
        TurokGL.release();
        TurokGL.popMatrix();
    }

    public static void drawOutlineRect(TurokRect rect) {
        TurokRenderGL.drawOutlineRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    public static void drawOutlineRoundedRect(float x, float y, float width, float height, float radius, float dR, float dG, float dB, float dA, float line_width) {
        TurokRenderGL.drawRoundedRect(x, y, width, height, radius);
        TurokGL.color(dR, dG, dB, dA);
        TurokRenderGL.drawRoundedRect(x + line_width, y + line_width, width - line_width * 2.0f, height - line_width * 2.0f, radius);
    }

    public static void drawRoundedRect(float x, float y, float width, float height, float radius) {
        TurokGL.pushMatrix();
        TurokGL.enable(3042);
        TurokGL.blendFunc(770, 771);
        TurokRenderGL.drawArc(x + width - radius, y + height - radius, radius, 0.0f, 90.0f, 16.0f);
        TurokRenderGL.drawArc(x + radius, y + height - radius, radius, 90.0f, 180.0f, 16.0f);
        TurokRenderGL.drawArc(x + radius, y + radius, radius, 180.0f, 270.0f, 16.0f);
        TurokRenderGL.drawArc(x + width - radius, y + radius, radius, 270.0f, 360.0f, 16.0f);
        TurokGL.prepare(4);
        TurokGL.addVertex(x + width - radius, y);
        TurokGL.addVertex(x + radius, y);
        TurokGL.addVertex(x + width - radius, y + radius);
        TurokGL.addVertex(x + width - radius, y + radius);
        TurokGL.addVertex(x + radius, y);
        TurokGL.addVertex(x + radius, y + radius);
        TurokGL.addVertex(x + width, y + radius);
        TurokGL.addVertex(x, y + radius);
        TurokGL.addVertex(x, y + height - radius);
        TurokGL.addVertex(x + width, y + radius);
        TurokGL.addVertex(x, y + height - radius);
        TurokGL.addVertex(x + width, y + height - radius);
        TurokGL.addVertex(x + width - radius, y + height - radius);
        TurokGL.addVertex(x + radius, y + height - radius);
        TurokGL.addVertex(x + width - radius, y + height);
        TurokGL.addVertex(x + width - radius, y + height);
        TurokGL.addVertex(x + radius, y + height - radius);
        TurokGL.addVertex(x + radius, y + height);
        TurokGL.release();
        TurokGL.popMatrix();
    }

    public static void drawRoundedRect(TurokRect rect, float size) {
        TurokRenderGL.drawRoundedRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), size);
    }

    public static void drawSolidRect(float x, float y, float width, float height) {
        TurokGL.pushMatrix();
        TurokGL.enable(3042);
        TurokGL.blendFunc(770, 771);
        TurokGL.prepare(7);
        TurokGL.addVertex(x, y);
        TurokGL.addVertex(x, y + height);
        TurokGL.addVertex(x + width, y + height);
        TurokGL.addVertex(x + width, y);
        TurokGL.release();
        TurokGL.popMatrix();
    }

    public static void drawSolidRect(int x, int y, int width, int height) {
        TurokRenderGL.drawSolidRect((float)x, (float)y, (float)width, (float)height);
    }

    public static void drawSolidRect(TurokRect rect) {
        TurokRenderGL.drawSolidRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    public static void drawLine(int x, int y, int x1, int xy, float line) {
        TurokGL.enable(3042);
        TurokGL.blendFunc(770, 771);
        TurokGL.lineSize(line);
        TurokGL.prepare(2848);
        TurokGL.addVertex(x, y);
        TurokGL.addVertex(x1, xy);
        TurokGL.release();
    }

    public static void drawLine3D(double x, double y, double z, double x1, double y1, double z1, int r, int g, int b, int a, float line) {
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.shadeModel((int)7425);
        TurokGL.lineSize(line);
        TurokGL.enable(2848);
        TurokGL.hint(3154, 4354);
        GlStateManager.disableDepth();
        TurokGL.enable(34383);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(x, y, z).color(r, g, b, a).endVertex();
        bufferBuilder.pos(x1, y1, z1).color(r, g, b, a).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel((int)7424);
        TurokGL.disable(2848);
        GlStateManager.enableDepth();
        TurokGL.disable(34383);
        TurokGL.disable(3042);
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    public static void prepareOverlay() {
        TurokGL.pushMatrix();
        TurokGL.enable(3553);
        TurokGL.enable(3042);
        TurokGL.enable(3042);
        TurokGL.blendFunc(770, 771);
        TurokGL.popMatrix();
    }

    public static void releaseOverlay() {
        GlStateManager.enableCull();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
    }

    public static void prepare3D(float size) {
        TurokGL.blendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.glLineWidth((float)size);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static void release3D() {
        GlStateManager.enableCull();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
    }
}

