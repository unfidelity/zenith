/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.userInterface.guiButton;

import me.gopro336.zenith.api.util.font.FontUtil;

public class Button {
    float X;
    float Y;
    float X2;
    float Y2;
    float width;
    float height;
    boolean toggled;
    boolean hovered;
    String text;

    public Button(String t, float x, float y, float param1, float param2, Enum<?> mod) {
        this.X = x;
        this.Y = y;
        this.X2 = param1;
        this.Y2 = param2;
        if (mod.equals((Object)model.Width_Height)) {
            this.width = param1;
            this.height = param2;
        } else {
            this.width = Math.abs(param1 - x);
            this.height = Math.abs(param2 - y);
        }
        this.toggled = false;
        this.hovered = false;
        this.text = t;
    }

    public void render() {
        FontUtil.drawCenteredString(this.text, this.X + this.width / 2.0f, this.Y + this.height / 2.0f, 0xFFFFFF);
    }

    public boolean isToggled() {
        return this.toggled;
    }

    public boolean isHovered() {
        return this.hovered;
    }

    public void setToggled(boolean in) {
        this.toggled = in;
    }

    @Deprecated
    public void setHovered(boolean in) {
        this.hovered = in;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public float getX() {
        return this.X;
    }

    public float getY() {
        return this.Y;
    }

    public float getX2() {
        return this.X2;
    }

    public float getY2() {
        return this.Y2;
    }

    public static enum model {
        Normal,
        Width_Height;

    }
}

