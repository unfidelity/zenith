/*
 * Decompiled with CFR 0.152.
 */
package me.rina.turok.util;

import me.rina.turok.hardware.mouse.TurokMouse;
import me.rina.turok.util.TurokDisplay;
import me.rina.turok.util.TurokMath;

public class TurokRect {
    public float x;
    public float y;
    public float width;
    public float height;
    public String tag;
    protected Dock docking = Dock.TOP_LEFT;
    private DockDimension dimension = DockDimension.D2;

    public TurokRect(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.tag = "";
    }

    public TurokRect(String tag, float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.tag = "";
    }

    public TurokRect(float x, float y) {
        this.x = x;
        this.y = y;
        this.width = 0.0f;
        this.height = 0.0f;
        this.tag = "";
    }

    public TurokRect(String tag, float x, float y) {
        this.x = x;
        this.y = y;
        this.width = 0.0f;
        this.height = 0.0f;
        this.tag = tag;
    }

    public void setDimension(DockDimension dimension) {
        this.dimension = dimension;
    }

    public DockDimension getDimension() {
        return this.dimension;
    }

    public TurokRect copy(TurokRect rect) {
        this.x = rect.getX();
        this.y = rect.getY();
        this.width = rect.getWidth();
        this.height = rect.getHeight();
        return this;
    }

    public void set(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getTag() {
        return this.tag;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public float getDistance(TurokRect rect) {
        float calculatedX = this.x - rect.getX();
        float calculatedY = this.y - rect.getY();
        float calculatedW = this.x + this.width - (rect.getX() + rect.getWidth());
        float calculatedH = this.y + this.height - (rect.getY() + rect.getHeight());
        return TurokMath.sqrt(calculatedX * calculatedW + calculatedY * calculatedH);
    }

    public float getDistance(float x, float y) {
        float calculatedX = this.x - x;
        float calculatedY = this.y - y;
        float calculatedW = this.x + this.width - x;
        float calculatedH = this.y + this.height - y;
        return TurokMath.sqrt(calculatedX * calculatedW + calculatedY * calculatedH);
    }

    public boolean collideWithMouse(TurokMouse mouse) {
        return (float)mouse.getX() >= this.x && (float)mouse.getX() <= this.x + this.width && (float)mouse.getY() >= this.y && (float)mouse.getY() <= this.y + this.height;
    }

    public boolean collideWithRect(TurokRect rect) {
        return this.x <= rect.getX() + rect.getWidth() && this.x + this.width >= rect.getX() && this.y <= rect.getY() + rect.getHeight() && this.y + this.height >= rect.getY();
    }

    public Dock getDockHit(TurokDisplay display, int diff) {
        if (this.x <= (float)diff) {
            if (this.docking == Dock.TOP_CENTER || this.docking == Dock.TOP_RIGHT) {
                this.docking = Dock.TOP_LEFT;
            } else if (this.docking == Dock.CENTER || this.docking == Dock.CENTER_RIGHT) {
                this.docking = Dock.CENTER_LEFT;
            } else if (this.docking == Dock.BOTTOM_CENTER || this.docking == Dock.BOTTOM_RIGHT) {
                this.docking = Dock.BOTTOM_LEFT;
            }
        } else if (this.y <= (float)diff) {
            if (this.docking == Dock.CENTER_LEFT || this.docking == Dock.BOTTOM_LEFT) {
                this.docking = Dock.TOP_LEFT;
            } else if (this.docking == Dock.CENTER || this.docking == Dock.BOTTOM_CENTER) {
                this.docking = Dock.TOP_CENTER;
            } else if (this.docking == Dock.CENTER_RIGHT || this.docking == Dock.BOTTOM_RIGHT) {
                this.docking = Dock.TOP_RIGHT;
            }
        }
        if (this.dimension == DockDimension.D3) {
            if (this.x >= (float)display.getScaledWidth() / 2.0f - (this.width + (float)diff) / 2.0f && this.x <= (float)display.getScaledWidth() / 2.0f + (this.width + (float)diff) / 2.0f) {
                if (this.docking == Dock.TOP_LEFT || this.docking == Dock.TOP_RIGHT) {
                    this.docking = Dock.TOP_CENTER;
                } else if (this.docking == Dock.CENTER_LEFT || this.docking == Dock.CENTER_RIGHT) {
                    this.docking = Dock.CENTER;
                } else if (this.docking == Dock.BOTTOM_LEFT || this.docking == Dock.BOTTOM_RIGHT) {
                    this.docking = Dock.BOTTOM_CENTER;
                }
            } else if (this.y >= (float)display.getScaledHeight() / 2.0f - (this.height + (float)diff) / 2.0f && this.y <= (float)display.getScaledHeight() / 2.0f + (this.height + (float)diff) / 2.0f) {
                if (this.docking == Dock.TOP_LEFT || this.docking == Dock.BOTTOM_LEFT) {
                    this.docking = Dock.CENTER_LEFT;
                } else if (this.docking == Dock.TOP_CENTER || this.docking == Dock.BOTTOM_CENTER) {
                    this.docking = Dock.CENTER;
                } else if (this.docking == Dock.TOP_RIGHT || this.docking == Dock.BOTTOM_RIGHT) {
                    this.docking = Dock.CENTER_RIGHT;
                }
            }
        }
        if (this.x + this.width >= (float)(display.getScaledWidth() - diff)) {
            if (this.docking == Dock.TOP_LEFT || this.docking == Dock.TOP_CENTER) {
                this.docking = Dock.TOP_RIGHT;
            } else if (this.docking == Dock.CENTER_LEFT || this.docking == Dock.CENTER) {
                this.docking = Dock.CENTER_RIGHT;
            } else if (this.docking == Dock.BOTTOM_LEFT || this.docking == Dock.BOTTOM_CENTER) {
                this.docking = Dock.BOTTOM_RIGHT;
            }
        }
        if (this.y + this.height >= (float)(display.getScaledHeight() - diff)) {
            if (this.docking == Dock.TOP_LEFT || this.docking == Dock.CENTER_LEFT) {
                this.docking = Dock.BOTTOM_LEFT;
            } else if (this.docking == Dock.TOP_CENTER || this.docking == Dock.CENTER) {
                this.docking = Dock.BOTTOM_CENTER;
            } else if (this.docking == Dock.TOP_RIGHT || this.docking == Dock.CENTER_RIGHT) {
                this.docking = Dock.BOTTOM_RIGHT;
            }
        }
        return this.docking;
    }

    public Dock getDocking() {
        return this.docking;
    }

    public static boolean collideRectWith(TurokRect rect, TurokMouse mouse) {
        return rect.collideWithMouse(mouse);
    }

    public static boolean collideRectWith(TurokRect rect, TurokRect rect1) {
        return rect.collideWithRect(rect1);
    }

    public static enum DockDimension {
        D3,
        D2;

    }

    public static enum Dock {
        TOP_LEFT,
        TOP_CENTER,
        TOP_RIGHT,
        CENTER_LEFT,
        CENTER,
        CENTER_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_CENTER,
        BOTTOM_RIGHT;

    }
}

