/*
 * Decompiled with CFR 0.152.
 */
package me.rina.turok.render.opengl;

import java.awt.Color;
import org.lwjgl.opengl.GL11;

public class TurokGL {
    public static void scissor(int x, int y, int w, int h) {
        GL11.glScissor((int)x, (int)y, (int)w, (int)h);
    }

    public static void pushMatrix() {
        GL11.glPushMatrix();
    }

    public static void popMatrix() {
        GL11.glPopMatrix();
    }

    public static void translate(float x, float y, float z) {
        GL11.glTranslatef((float)x, (float)y, (float)z);
    }

    public static void translate(double x, double y, double z) {
        GL11.glTranslated((double)x, (double)y, (double)z);
    }

    public static void translate(float x, float y) {
        GL11.glTranslatef((float)x, (float)y, (float)0.0f);
    }

    public static void translate(double x, double y) {
        GL11.glTranslated((double)x, (double)y, (double)0.0);
    }

    public static void rotate(float angle, float x, float y, float z) {
        GL11.glRotatef((float)angle, (float)x, (float)y, (float)z);
    }

    public static void scale(float x, float y, float z) {
        GL11.glScalef((float)x, (float)y, (float)z);
    }

    public static void hint(int target, int target1) {
        GL11.glHint((int)target, (int)target1);
    }

    public static void enable(int state) {
        GL11.glEnable((int)state);
    }

    public static void disable(int state) {
        GL11.glDisable((int)state);
    }

    public static void blendFunc(int a, int b) {
        GL11.glBlendFunc((int)a, (int)b);
    }

    public static void prepare(int mode2) {
        GL11.glBegin((int)mode2);
    }

    public static void release() {
        GL11.glEnd();
    }

    public static void color(float r, float g, float b, float a) {
        GL11.glColor4f((float)(r / 255.0f), (float)(g / 255.0f), (float)(b / 255.0f), (float)(a / 255.0f));
    }

    public static void color(float r, float g, float b) {
        GL11.glColor3f((float)(r / 255.0f), (float)(g / 255.0f), (float)(b / 255.0f));
    }

    public static void lineSize(float size) {
        GL11.glLineWidth((float)size);
    }

    public static void pointSize(float size) {
        GL11.glPointSize((float)size);
    }

    public static void addVertex(float x, float y, float z) {
        GL11.glVertex3f((float)x, (float)y, (float)z);
    }

    public static void addVertex(float x, float y) {
        GL11.glVertex2f((float)x, (float)y);
    }

    public static void sewTexture(float s, float t, float r) {
        GL11.glTexCoord3f((float)s, (float)t, (float)r);
    }

    public static void sewTexture(float s, float t) {
        GL11.glTexCoord2f((float)s, (float)t);
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

    public static void pushAttrib(int mask) {
        GL11.glPushAttrib((int)mask);
    }

    public static void popAttrib() {
        GL11.glPopAttrib();
    }

    public static void depthMask(boolean flag) {
        GL11.glDepthMask((boolean)flag);
    }

    public static Color arrayColorToColorClass(int[] string) {
        return new Color(string[0], string[1], string[2], string[3]);
    }
}

