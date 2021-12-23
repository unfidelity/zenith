/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.hudElement.hudElement;

import java.awt.Color;
import me.gopro336.zenith.api.util.color.ColorHolder;
import me.gopro336.zenith.api.util.color.ColorUtils;
import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.hudElement.Element;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;

@AnnotationHelper(name="Watermark", category=Category.HUD)
public class WatermarkElement
extends Element {
    private Property<Boolean> colorSync = new Property<Boolean>(this, "ColorSync", "", false);
    private Property<mode> type = new Property<mode>(this, "Type", "", mode.Solid);
    private Property<Color> color = new Property<Color>((Feature)this, "Color", "", new Color(255, 62, 154, 255), v -> !this.type.getValue().equals((Object)mode.Rainbow) && this.colorSync.getValue().equals(false));
    private NumberProperty<Float> range = new NumberProperty<Float>((Feature)this, "Range", "", Float.valueOf(0.1f), Float.valueOf(1.0f), Float.valueOf(1.0f), v -> this.type.getValue().equals((Object)mode.Pulse) && this.colorSync.getValue().equals(false));
    private NumberProperty<Float> spread = new NumberProperty<Float>((Feature)this, "Spread", "", Float.valueOf(0.1f), Float.valueOf(1.0f), Float.valueOf(2.0f), v -> this.type.getValue().equals((Object)mode.Pulse) && this.colorSync.getValue().equals(false));
    private NumberProperty<Float> speed = new NumberProperty<Float>((Feature)this, "Speed", "", Float.valueOf(1.0f), Float.valueOf(1.0f), Float.valueOf(10.0f), v -> this.type.getValue().equals((Object)mode.Pulse) && this.colorSync.getValue().equals(false));
    private NumberProperty<Float> saturation = new NumberProperty<Float>((Feature)this, "Saturation", "", Float.valueOf(0.0f), Float.valueOf(143.0f), Float.valueOf(255.0f), v -> this.type.getValue().equals((Object)mode.Rainbow) && this.colorSync.getValue().equals(false));
    private NumberProperty<Float> brightness = new NumberProperty<Float>((Feature)this, "Brightness", "", Float.valueOf(0.0f), Float.valueOf(215.0f), Float.valueOf(255.0f), v -> this.type.getValue().equals((Object)mode.Rainbow) && this.colorSync.getValue().equals(false));
    private NumberProperty<Integer> delay = new NumberProperty<Integer>((Feature)this, "Delay", "", 50, Integer.valueOf(300), Integer.valueOf(900), v -> this.type.getValue().equals((Object)mode.Rainbow) && this.colorSync.getValue().equals(false));
    String watermarkString = "Zenith-1.2.4-release";

    public WatermarkElement() {
        this.setWidth(FontUtil.getStringWidth(this.watermarkString));
        this.setHeight(FontUtil.getFontHeight());
    }

    @Override
    public void onRender() {
        if (this.colorSync.getValue().booleanValue()) {
            FontUtil.drawStringWithShadow(this.watermarkString, this.getX(), this.getY(), 0xFFFFFF);
        } else {
            FontUtil.drawStringWithShadow(this.watermarkString, this.getX(), this.getY(), this.getColor(1));
        }
    }

    private int getColor(int index) {
        ColorHolder holder = new ColorHolder(this.color.getValue().hashCode());
        float[] clr = Color.RGBtoHSB(holder.getRawColor() >> 16 & 0xFF, holder.getRawColor() >> 8 & 0xFF, holder.getRawColor() & 0xFF, null);
        if (this.type.getValue().equals((Object)mode.Solid)) {
            return holder.getColor();
        }
        if (this.type.getValue().equals((Object)mode.Pulse)) {
            return ColorUtils.pulse(index, clr, ((Float)this.spread.getValue()).floatValue(), ((Float)this.speed.getValue()).floatValue(), ((Float)this.range.getValue()).floatValue()).getRGB();
        }
        return ColorUtils.rainbow((Integer)this.delay.getValue() * index, ((Float)this.saturation.getValue()).floatValue(), ((Float)this.brightness.getValue()).floatValue()).getRGB();
    }

    private static enum mode {
        Solid,
        Pulse,
        Rainbow;

    }
}

