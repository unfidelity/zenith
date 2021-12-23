//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.rina.turok.render.opengl.deprecated;

import java.awt.Color;
import java.util.HashMap;
import me.rina.turok.hardware.mouse.TurokMouse;
import me.rina.turok.util.TurokDisplay;
import me.rina.turok.util.TurokMath;
import me.rina.turok.util.TurokRect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class TurokRenderGL {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static TurokRenderGL INSTANCE;
    protected TurokDisplay display;
    protected TurokMouse mouse;
    protected int program;
    protected HashMap<String, Integer> uniforms;
    protected boolean isShaderInitializedWithoutErrors;
    public static final int TUROKGL_NULL = 0;
    public static final int TUROKGL_INIT = 1;
    public static final int TUROKGL_SHADER = 2;
    public static final int TUROKGL_UNIFORM_NULL = -1;

    public static void init() {
        INSTANCE = new TurokRenderGL();
    }

    public static void init(Object object) {
        if (object instanceof TurokDisplay) {
            TurokRenderGL.INSTANCE.display = (TurokDisplay)object;
        }
        if (object instanceof TurokMouse) {
            TurokRenderGL.INSTANCE.mouse = (TurokMouse)object;
        }
        if (object instanceof Integer) {
            switch ((Integer)object) {
                case 0: {
                    break;
                }
                case 1: {
                    TurokRenderGL.init();
                    break;
                }
                case 2: {
                    INSTANCE.initializeShader();
                }
            }
        }
    }

    public void initializeShader() {
        this.program = GL20.glCreateProgram();
        this.uniforms = new HashMap();
        switch (TurokRenderGL.INSTANCE.program) {
            case 0: {
                System.err.println("Turok: Shader creation failed, returned " + TurokRenderGL.INSTANCE.program);
                this.isShaderInitializedWithoutErrors = false;
                break;
            }
            default: {
                this.isShaderInitializedWithoutErrors = true;
            }
        }
    }

    public static boolean isShaderProgramInitialized() {
        return TurokRenderGL.INSTANCE.isShaderInitializedWithoutErrors;
    }

    public static void drawOutlineRectFadingMouse(TurokRect rect, int radius, Color color) {
        TurokRenderGL.drawOutlineRectFadingMouse(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), radius, color);
    }

    public static void drawOutlineRectFadingMouse(float x, float y, float w, float h, int radius, Color color) {
        float offset = 0.5f;
        float vx = x - (float)TurokRenderGL.INSTANCE.mouse.getX();
        float vy = y - (float)TurokRenderGL.INSTANCE.mouse.getY();
        float vw = x + w - (float)TurokRenderGL.INSTANCE.mouse.getX();
        float vh = y + h - (float)TurokRenderGL.INSTANCE.mouse.getY();
        int valueAlpha = color.getAlpha();
        TurokRenderGL.enable(3042);
        TurokRenderGL.blendFunc(770, 771);
        TurokRenderGL.shaderMode(7425);
        TurokRenderGL.color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        TurokRenderGL.lineSize(1.0f);
        TurokRenderGL.prepare(2);
        TurokRenderGL.color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), (float)valueAlpha - TurokMath.clamp(TurokMath.sqrt(vx * vx + vy * vy) / ((float)radius / 100.0f), 0.0f, (float)valueAlpha));
        TurokRenderGL.addVertex(x + offset, y);
        TurokRenderGL.color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), (float)valueAlpha - TurokMath.clamp(TurokMath.sqrt(vx * vx + vh * vh) / ((float)radius / 100.0f), 0.0f, (float)valueAlpha));
        TurokRenderGL.addVertex(x + offset, y + h + offset);
        TurokRenderGL.color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), (float)valueAlpha - TurokMath.clamp(TurokMath.sqrt(vw * vw + vh * vh) / ((float)radius / 100.0f), 0.0f, (float)valueAlpha));
        TurokRenderGL.addVertex(x + w, y + h);
        TurokRenderGL.color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), (float)valueAlpha - TurokMath.clamp(TurokMath.sqrt(vw * vw + vy * vy) / ((float)radius / 100.0f), 0.0f, (float)valueAlpha));
        TurokRenderGL.addVertex(x + w, y);
        TurokRenderGL.release();
    }

    public static void drawSolidRectFadingMouse(TurokRect rect, int radius, Color color) {
        TurokRenderGL.drawSolidRectFadingMouse(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), radius, color);
    }

    public static void drawSolidRectFadingMouse(float x, float y, float w, float h, int radius, Color color) {
        float vx = x - (float)TurokRenderGL.INSTANCE.mouse.getX();
        float vy = y - (float)TurokRenderGL.INSTANCE.mouse.getY();
        float vw = x + w - (float)TurokRenderGL.INSTANCE.mouse.getX();
        float vh = y + h - (float)TurokRenderGL.INSTANCE.mouse.getY();
        int valueAlpha = color.getAlpha();
        TurokRenderGL.enable(3042);
        TurokRenderGL.blendFunc(770, 771);
        TurokRenderGL.prepare(7);
        TurokRenderGL.color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), (float)valueAlpha - TurokMath.clamp(TurokMath.sqrt(vx * vx + vy * vy) / ((float)radius / 100.0f), 0.0f, (float)valueAlpha));
        TurokRenderGL.addVertex(x, y);
        TurokRenderGL.color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), (float)valueAlpha - TurokMath.clamp(TurokMath.sqrt(vx * vx + vh * vh) / ((float)radius / 100.0f), 0.0f, (float)valueAlpha));
        TurokRenderGL.addVertex(x, y + h);
        TurokRenderGL.color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), (float)valueAlpha - TurokMath.clamp(TurokMath.sqrt(vw * vw + vh * vh) / ((float)radius / 100.0f), 0.0f, (float)valueAlpha));
        TurokRenderGL.addVertex(x + w, y + h);
        TurokRenderGL.color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), (float)valueAlpha - TurokMath.clamp(TurokMath.sqrt(vw * vw + vy * vy) / ((float)radius / 100.0f), 0.0f, (float)valueAlpha));
        TurokRenderGL.addVertex(x + w, y);
        TurokRenderGL.release();
    }

    public static void disableState(int target) {
        TurokRenderGL.disable(target);
    }

    public static void enableState(int target) {
        TurokRenderGL.enable(target);
    }

    public static void drawScissor(float x, float y, float w, float h, TurokDisplay display) {
        int calculatedX = (int)x;
        int calculatedY = (int)y;
        int calculatedW = (int)((float)calculatedX + w);
        int calculatedH = (int)((float)calculatedY + h);
        GL11.glScissor((int)(calculatedX * display.getScaleFactor()), (int)(display.getHeight() - calculatedH * display.getScaleFactor()), (int)((calculatedW - calculatedX) * display.getScaleFactor()), (int)((calculatedH - calculatedY) * display.getScaleFactor()));
    }

    public static void drawScissor(int x, int y, int w, int h) {
        int calculatedX = x;
        int calculatedY = y;
        int calculatedW = calculatedX + w;
        int calculatedH = calculatedY + h;
        GL11.glScissor((int)(calculatedX * TurokRenderGL.INSTANCE.display.getScaleFactor()), (int)(TurokRenderGL.INSTANCE.display.getHeight() - calculatedH * TurokRenderGL.INSTANCE.display.getScaleFactor()), (int)((calculatedW - calculatedX) * TurokRenderGL.INSTANCE.display.getScaleFactor()), (int)((calculatedH - calculatedY) * TurokRenderGL.INSTANCE.display.getScaleFactor()));
    }

    public static void drawTexture(float x, float y, float width, float height) {
        TurokRenderGL.prepare(7);
        TurokRenderGL.sewTexture(0, 0);
        TurokRenderGL.addVertex(x, y);
        TurokRenderGL.sewTexture(0, 1);
        TurokRenderGL.addVertex(x, y + height);
        TurokRenderGL.sewTexture(1, 1);
        TurokRenderGL.addVertex(x + width, y + height);
        TurokRenderGL.sewTexture(1, 0);
        TurokRenderGL.addVertex(x + width, y);
        TurokRenderGL.release();
    }

    public static void drawTextureInterpolated(float x, float y, float xx, float yy, float width, float height, float ww, float hh) {
        TurokRenderGL.prepare(7);
        TurokRenderGL.sewTexture(0.0f + xx, 0.0f + hh);
        TurokRenderGL.addVertex(x, y);
        TurokRenderGL.sewTexture(0.0f + xx, 1.0f + hh);
        TurokRenderGL.addVertex(x, y + height);
        TurokRenderGL.sewTexture(1.0f + ww, 1.0f + hh);
        TurokRenderGL.addVertex(x + width, y + height);
        TurokRenderGL.sewTexture(1.0f + ww, 0.0f + hh);
        TurokRenderGL.addVertex(x + width, y);
        TurokRenderGL.release();
    }

    public static void drawUpTriangle(float x, float y, float width, float height, int offsetX) {
        TurokRenderGL.enable(3042);
        TurokRenderGL.blendFunc(770, 771);
        TurokRenderGL.prepare(6);
        TurokRenderGL.addVertex(x + width, y + height);
        TurokRenderGL.addVertex(x + width, y);
        TurokRenderGL.addVertex(x - (float)offsetX, y);
        TurokRenderGL.release();
    }

    public static void drawDownTriangle(float x, float y, float width, float height, int offsetX) {
        TurokRenderGL.enable(3042);
        TurokRenderGL.blendFunc(770, 771);
        TurokRenderGL.prepare(6);
        TurokRenderGL.addVertex(x, y);
        TurokRenderGL.addVertex(x, y + height);
        TurokRenderGL.addVertex(x + width + (float)offsetX, y + height);
        TurokRenderGL.release();
    }

    public static void drawArc(float cx, float cy, float r, float start_angle, float end_angle, float num_segments) {
        TurokRenderGL.prepare(4);
        int i = (int)(num_segments / (360.0f / start_angle)) + 1;
        while ((float)i <= num_segments / (360.0f / end_angle)) {
            double previousAngle = Math.PI * 2 * (double)(i - 1) / (double)num_segments;
            double angle = Math.PI * 2 * (double)i / (double)num_segments;
            TurokRenderGL.addVertex(cx, cy);
            TurokRenderGL.addVertex((double)cx + Math.cos(angle) * (double)r, (double)cy + Math.sin(angle) * (double)r);
            TurokRenderGL.addVertex((double)cx + Math.cos(previousAngle) * (double)r, (double)cy + Math.sin(previousAngle) * (double)r);
            ++i;
        }
        TurokRenderGL.release();
    }

    public static void drawArc(float x, float y, float radius) {
        TurokRenderGL.drawArc(x, y, radius, 0.0f, 360.0f, 40.0f);
    }

    public static void drawArcOutline(float cx, float cy, float r, float start_angle, float end_angle, float num_segments) {
        TurokRenderGL.prepare(2);
        int i = (int)(num_segments / (360.0f / start_angle)) + 1;
        while ((float)i <= num_segments / (360.0f / end_angle)) {
            double angle = Math.PI * 2 * (double)i / (double)num_segments;
            TurokRenderGL.addVertex((double)cx + Math.cos(angle) * (double)r, (double)cy + Math.sin(angle) * (double)r);
            ++i;
        }
        TurokRenderGL.release();
    }

    public static void drawArcOutline(float x, float y, float radius) {
        TurokRenderGL.drawArcOutline(x, y, radius, 0.0f, 360.0f, 40.0f);
    }

    public static void drawOutlineRect(float x, float y, float width, float height) {
        float offset = 0.5f;
        TurokRenderGL.enable(3042);
        TurokRenderGL.blendFunc(770, 771);
        TurokRenderGL.prepare(2);
        TurokRenderGL.addVertex(x + offset, y);
        TurokRenderGL.addVertex(x + offset, y + height + offset);
        TurokRenderGL.addVertex(x + width, y + height);
        TurokRenderGL.addVertex(x + width, y);
        TurokRenderGL.release();
    }

    public static void drawOutlineRect(int x, int y, int width, int height) {
        TurokRenderGL.drawOutlineRect((float)x, (float)y, (float)width, (float)height);
    }

    public static void drawOutlineRect(TurokRect rect) {
        TurokRenderGL.drawOutlineRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    public static void drawOutlineRoundedRect(float x, float y, float width, float height, float radius, float dR, float dG, float dB, float dA, float line_width) {
        TurokRenderGL.drawRoundedRect(x, y, width, height, radius);
        TurokRenderGL.color(dR, dG, dB, dA);
        TurokRenderGL.drawRoundedRect(x + line_width, y + line_width, width - line_width * 2.0f, height - line_width * 2.0f, radius);
    }

    public static void drawRoundedRect(float x, float y, float width, float height, float radius) {
        TurokRenderGL.enable(3042);
        TurokRenderGL.blendFunc(770, 771);
        TurokRenderGL.drawArc(x + width - radius, y + height - radius, radius, 0.0f, 90.0f, 16.0f);
        TurokRenderGL.drawArc(x + radius, y + height - radius, radius, 90.0f, 180.0f, 16.0f);
        TurokRenderGL.drawArc(x + radius, y + radius, radius, 180.0f, 270.0f, 16.0f);
        TurokRenderGL.drawArc(x + width - radius, y + radius, radius, 270.0f, 360.0f, 16.0f);
        TurokRenderGL.prepare(4);
        TurokRenderGL.addVertex(x + width - radius, y);
        TurokRenderGL.addVertex(x + radius, y);
        TurokRenderGL.addVertex(x + width - radius, y + radius);
        TurokRenderGL.addVertex(x + width - radius, y + radius);
        TurokRenderGL.addVertex(x + radius, y);
        TurokRenderGL.addVertex(x + radius, y + radius);
        TurokRenderGL.addVertex(x + width, y + radius);
        TurokRenderGL.addVertex(x, y + radius);
        TurokRenderGL.addVertex(x, y + height - radius);
        TurokRenderGL.addVertex(x + width, y + radius);
        TurokRenderGL.addVertex(x, y + height - radius);
        TurokRenderGL.addVertex(x + width, y + height - radius);
        TurokRenderGL.addVertex(x + width - radius, y + height - radius);
        TurokRenderGL.addVertex(x + radius, y + height - radius);
        TurokRenderGL.addVertex(x + width - radius, y + height);
        TurokRenderGL.addVertex(x + width - radius, y + height);
        TurokRenderGL.addVertex(x + radius, y + height - radius);
        TurokRenderGL.addVertex(x + radius, y + height);
        TurokRenderGL.release();
    }

    public static void drawRoundedRect(TurokRect rect, float size) {
        TurokRenderGL.drawRoundedRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), size);
    }

    public static void drawSolidRect(float x, float y, float width, float height) {
        TurokRenderGL.enable(3042);
        TurokRenderGL.blendFunc(770, 771);
        TurokRenderGL.prepare(7);
        TurokRenderGL.addVertex(x, y);
        TurokRenderGL.addVertex(x, y + height);
        TurokRenderGL.addVertex(x + width, y + height);
        TurokRenderGL.addVertex(x + width, y);
        TurokRenderGL.release();
    }

    public static void drawSolidRect(int x, int y, int width, int height) {
        TurokRenderGL.drawSolidRect((float)x, (float)y, (float)width, (float)height);
    }

    public static void drawSolidRect(TurokRect rect) {
        TurokRenderGL.drawSolidRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    public static void drawLine(int x, int y, int x1, int xy, float line) {
        TurokRenderGL.enableAlphaBlend();
        TurokRenderGL.lineSize(line);
        TurokRenderGL.prepare(2848);
        TurokRenderGL.addVertex(x, y);
        TurokRenderGL.addVertex(x1, xy);
        TurokRenderGL.release();
    }

    public static void drawLine3D(double x, double y, double z, double x1, double y1, double z1, int r, int g, int b, int a, float line) {
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.shadeModel((int)7425);
        TurokRenderGL.lineSize(line);
        TurokRenderGL.enable(2848);
        TurokRenderGL.hint(3154, 4354);
        GlStateManager.disableDepth();
        TurokRenderGL.enable(34383);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(x, y, z).color(r, g, b, a).endVertex();
        bufferBuilder.pos(x1, y1, z1).color(r, g, b, a).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel((int)7424);
        TurokRenderGL.disable(2848);
        GlStateManager.enableDepth();
        TurokRenderGL.disable(34383);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    public static void autoScale() {
        TurokRenderGL.pushMatrix();
        TurokRenderGL.translate(TurokRenderGL.INSTANCE.display.getScaledWidth(), TurokRenderGL.INSTANCE.display.getScaledHeight());
        TurokRenderGL.scale(0.5f, 0.5f, 0.5f);
        TurokRenderGL.popMatrix();
    }

    public static void addVertexShader(String text) {
        try {
            TurokRenderGL.addProgram(text, 35633);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void addGeometryShader(String text) {
        try {
            TurokRenderGL.addProgram(text, 36313);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void addFragmentShader(String text) {
        try {
            TurokRenderGL.addProgram(text, 35632);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void addTessellationControlShader(String text) {
        try {
            TurokRenderGL.addProgram(text, 36488);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void addTessellationEvaluationShader(String text) {
        try {
            TurokRenderGL.addProgram(text, 36487);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void addComputeShader(String text) {
        try {
            TurokRenderGL.addProgram(text, 37305);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void compileShader() throws Exception {
        GL20.glLinkProgram((int)TurokRenderGL.INSTANCE.program);
        if (GL20.glGetProgrami((int)TurokRenderGL.INSTANCE.program, (int)35714) != 0) {
            GL20.glValidateProgram((int)TurokRenderGL.INSTANCE.program);
            if (GL20.glGetProgrami((int)TurokRenderGL.INSTANCE.program, (int)35715) == 0) {
                throw new Exception("Turok: Failed to compile shader, " + INSTANCE.getClass().getName() + " " + GL20.glGetProgramInfoLog((int)TurokRenderGL.INSTANCE.program, (int)1024));
            }
        } else {
            throw new Exception("Turok: Failed to compile shader, " + INSTANCE.getClass().getName() + " " + GL20.glGetProgramInfoLog((int)TurokRenderGL.INSTANCE.program, (int)1024));
        }
    }

    public static void addUniform(String uniform) throws Exception {
        int uniformLocation = GL20.glGetUniformLocation((int)TurokRenderGL.INSTANCE.program, (CharSequence)uniform);
        if (uniformLocation == -1) {
            throw new Exception("Turok: Failed to load uniform.");
        }
        TurokRenderGL.INSTANCE.uniforms.put(uniform, uniformLocation);
    }

    public static void addProgram(String program, int type) throws Exception {
        int shader = GL20.glCreateShader((int)type);
        if (shader != 0) {
            GL20.glShaderSource((int)shader, (CharSequence)program);
            GL20.glCompileShader((int)shader);
            if (GL20.glGetShaderi((int)shader, (int)35713) != 0) {
                throw new Exception("Turok: " + INSTANCE.getClass().getName() + " " + GL20.glGetShaderInfoLog((int)shader, (int)1024));
            }
        } else {
            throw new Exception("Turok: Failed to load shader.");
        }
        GL20.glAttachShader((int)TurokRenderGL.INSTANCE.program, (int)shader);
    }

    public static void bind() {
        GL20.glUseProgram((int)TurokRenderGL.INSTANCE.program);
    }

    public static void color(float r, float g, float b, float a) {
        GL11.glColor4f((float)(r / 255.0f), (float)(g / 255.0f), (float)(b / 255.0f), (float)(a / 255.0f));
    }

    public static void color(double r, double g, double b, double a) {
        GL11.glColor4f((float)((float)r / 255.0f), (float)((float)g / 255.0f), (float)((float)b / 255.0f), (float)((float)a / 255.0f));
    }

    public static void color(int r, int g, int b, int a) {
        GL11.glColor4f((float)((float)r / 255.0f), (float)((float)g / 255.0f), (float)((float)b / 255.0f), (float)((float)a / 255.0f));
    }

    public static void color(float r, float g, float b) {
        GL11.glColor3f((float)(r / 255.0f), (float)(g / 255.0f), (float)(b / 255.0f));
    }

    public static void color(double r, double g, double b) {
        GL11.glColor3f((float)((float)r / 255.0f), (float)((float)g / 255.0f), (float)((float)b / 255.0f));
    }

    public static void color(int r, int g, int b) {
        GL11.glColor3f((float)((float)r / 255.0f), (float)((float)g / 255.0f), (float)((float)b / 255.0f));
    }

    public static void prepare(int mode2) {
        GL11.glBegin((int)mode2);
    }

    public static void release() {
        GL11.glEnd();
    }

    public static void sewTexture(float s, float t, float r) {
        GL11.glTexCoord3f((float)s, (float)t, (float)r);
    }

    public static void sewTexture(float s, float t) {
        GL11.glTexCoord2f((float)s, (float)t);
    }

    public static void sewTexture(float s) {
        GL11.glTexCoord1f((float)s);
    }

    public static void sewTexture(double s, double t, double r) {
        TurokRenderGL.sewTexture((float)s, (float)t, (float)r);
    }

    public static void sewTexture(double s, double t) {
        TurokRenderGL.sewTexture((float)s, (float)t);
    }

    public static void sewTexture(double s) {
        TurokRenderGL.sewTexture((float)s);
    }

    public static void sewTexture(int s, int t, int r) {
        TurokRenderGL.sewTexture((float)s, (float)t, (float)r);
    }

    public static void sewTexture(int s, int t) {
        TurokRenderGL.sewTexture((float)s, (float)t);
    }

    public static void sewTexture(int s) {
        TurokRenderGL.sewTexture((float)s);
    }

    public static void addVertex(float x, float y, float z) {
        TurokRenderGL.sewTexture(x, y, z);
    }

    public static void addVertex(float x, float y) {
        GL11.glVertex2f((float)x, (float)y);
    }

    public static void addVertex(double x, double y, double z) {
        TurokRenderGL.addVertex((float)x, (float)y, (float)z);
    }

    public static void addVertex(double x, double y) {
        TurokRenderGL.addVertex((float)x, (float)y);
    }

    public static void addVertex(int x, int y, int z) {
        TurokRenderGL.addVertex((float)x, (float)y, (float)z);
    }

    public static void addVertex(int x, int y) {
        TurokRenderGL.addVertex((float)x, (float)y);
    }

    public static void hint(int target, int target1) {
        GL11.glHint((int)target, (int)target1);
    }

    public static void translate(float x, float y, float z) {
        GL11.glTranslated((double)x, (double)y, (double)z);
    }

    public static void translate(double x, double y, double z) {
        GL11.glTranslated((double)x, (double)y, (double)z);
    }

    public static void translate(int x, int y, int z) {
        GL11.glTranslated((double)x, (double)y, (double)z);
    }

    public static void translate(float x, float y) {
        GL11.glTranslated((double)x, (double)y, (double)0.0);
    }

    public static void translate(double x, double y) {
        GL11.glTranslated((double)x, (double)y, (double)0.0);
    }

    public static void translate(int x, int y) {
        GL11.glTranslated((double)x, (double)y, (double)0.0);
    }

    public static void scale(float scaledPosX, float scaledPosY, float scaledPosZ) {
        GL11.glScaled((double)scaledPosX, (double)scaledPosY, (double)scaledPosZ);
    }

    public static void scale(double scaledPosX, double scaledPosY, double scaledPosZ) {
        GL11.glScaled((double)scaledPosX, (double)scaledPosY, (double)scaledPosZ);
    }

    public static void scale(int scaledPosX, int scaledPosY, int scaledPosZ) {
        GL11.glScaled((double)scaledPosX, (double)scaledPosY, (double)scaledPosZ);
    }

    public static void lineSize(float width) {
        GL11.glLineWidth((float)width);
    }

    public static void pushMatrix() {
        GL11.glPushMatrix();
    }

    public static void popMatrix() {
        GL11.glPopMatrix();
    }

    public static void enable(int glState) {
        GL11.glEnable((int)glState);
    }

    public static void disable(int glState) {
        GL11.glDisable((int)glState);
    }

    public static void blendFunc(int glState, int glState1) {
        GL11.glBlendFunc((int)glState, (int)glState1);
    }

    public static void polygonOffset(float factor, float units) {
        GL11.glPolygonOffset((float)factor, (float)units);
    }

    public static void polygonOffset(double factor, double units) {
        GL11.glPolygonOffset((float)((float)factor), (float)((float)units));
    }

    public static void polygonOffset(int factor, int units) {
        GL11.glPolygonOffset((float)factor, (float)units);
    }

    public static void polygonMode(int face, int mode2) {
        GL11.glPolygonMode((int)face, (int)mode2);
    }

    public static void shaderMode(int mode2) {
        GL11.glShadeModel((int)mode2);
    }

    public static void enableAlphaBlend() {
        TurokRenderGL.enable(3042);
        TurokRenderGL.blendFunc(770, 771);
    }

    public static void disableAlphaBlend() {
        TurokRenderGL.disable(3042);
    }

    public static void prepareOverlay() {
        TurokRenderGL.pushMatrix();
        TurokRenderGL.enable(3553);
        TurokRenderGL.enable(3042);
        GlStateManager.enableBlend();
        TurokRenderGL.popMatrix();
    }

    public static void releaseOverlay() {
        GlStateManager.enableCull();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
    }

    public static void prepare3D(float size) {
        TurokRenderGL.blendFunc(770, 771);
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

