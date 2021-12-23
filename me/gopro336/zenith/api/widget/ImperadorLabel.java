/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.widget;

import java.awt.Color;
import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.api.widget.api.Widget;
import me.rina.turok.render.font.TurokFont;
import me.rina.turok.render.opengl.TurokGL;
import me.rina.turok.render.opengl.TurokRenderGL;
import me.rina.turok.render.opengl.TurokShaderGL;
import me.rina.turok.util.TurokRect;
import org.lwjgl.opengl.GL11;

public class ImperadorLabel
extends Widget {
    private String text;
    public int[] background = new int[]{0, 0, 0, 0};
    public int[] string = new int[]{255, 255, 255, 255};
    private boolean isShadow;
    private boolean isRendering;
    private float offsetX;
    private float offsetY;
    private float offsetW;
    private float offsetH;
    private TurokFont font;
    private final TurokRect scissor = new TurokRect(0.0f, 0.0f);

    public ImperadorLabel(String text) {
        super("Imperador:Label");
        this.text = text;
        this.setShadow(true);
        this.setOffsetY(3.0f);
    }

    public TurokRect getScissor() {
        return this.scissor;
    }

    public void setFont(TurokFont font) {
        this.font = font;
    }

    public TurokFont getFont() {
        return this.font;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setShadow(boolean shadow) {
        this.isShadow = shadow;
    }

    public void setRendering(boolean rendering) {
        this.isRendering = rendering;
    }

    public boolean isRendering() {
        return this.isRendering;
    }

    public boolean isShadow() {
        return this.isShadow;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public float getOffsetX() {
        return this.offsetX;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public float getOffsetY() {
        return this.offsetY;
    }

    public void setOffsetW(float offsetW) {
        this.offsetW = offsetW;
    }

    public float getOffsetW() {
        return this.offsetW;
    }

    public void setOffsetH(float offsetH) {
        this.offsetH = offsetH;
    }

    public float getOffsetH() {
        return this.offsetH;
    }

    public void scissor() {
        this.scissor.copy(this.rect);
    }

    public void center() {
        this.offsetX = this.rect.getWidth() / 2.0f - (float)FontUtil.getStringWidth(this.text) / 2.0f;
    }

    public void left(float difference) {
        this.offsetX = difference;
    }

    public void right(float difference) {
        this.offsetX = this.rect.getWidth() - (float)FontUtil.getStringWidth(this.text) - difference;
    }

    @Override
    public void onScreenClosed() {
    }

    @Override
    public void onCustomScreenClosed() {
    }

    @Override
    public void onScreenOpened() {
    }

    @Override
    public void onCustomScreenOpened() {
    }

    @Override
    public void onKeyboardPressed(char character, int key) {
    }

    @Override
    public void onCustomKeyboardPressed(char character, int key) {
    }

    @Override
    public void onMouseReleased(int button) {
    }

    @Override
    public void onCustomMouseReleased(int button) {
    }

    @Override
    public void onMouseClicked(int button) {
    }

    @Override
    public void onCustomMouseClicked(int button) {
    }

    @Override
    public void onRender() {
        if (this.isRendering()) {
            return;
        }
        boolean flag = !GL11.glIsEnabled((int)3089);
        Color backColor = TurokGL.arrayColorToColorClass(this.background);
        TurokGL.color(backColor.getRed(), backColor.getGreen(), backColor.getBlue(), backColor.getAlpha());
        TurokRenderGL.drawSolidRect(this.rect);
        if (flag) {
            GL11.glEnable((int)3089);
        }
        Color color = TurokGL.arrayColorToColorClass(this.string);
        TurokShaderGL.drawScissor(this.scissor);
        FontUtil.drawString(this.text, this.rect.getX() + this.getOffsetX(), this.rect.getY() + this.getOffsetY(), color.getRGB());
        if (flag) {
            GL11.glDisable((int)3089);
        }
    }

    @Override
    public void onCustomRender() {
    }
}

