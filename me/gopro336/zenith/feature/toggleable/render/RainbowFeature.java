/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.render;

import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;

@AnnotationHelper(name="Rainbow", category=Category.CLIENT)
public class RainbowFeature
extends Feature {
    public static NumberProperty<Integer> Saturation;
    public static NumberProperty<Integer> Brightness;
    public static NumberProperty<Integer> rainbowhue;
    public static NumberProperty<Integer> rainbowSpeed;
    public static NumberProperty<Integer> Difference;
    public static NumberProperty<Integer> Speed;
    public static NumberProperty<Integer> a;
    public static Property<Boolean> ClickguiRainbow;

    public RainbowFeature() {
        Saturation = new NumberProperty<Integer>((Feature)this, "Saturation", "", 0, Integer.valueOf(143), 255);
        Brightness = new NumberProperty<Integer>((Feature)this, "Brightness", "", 0, Integer.valueOf(215), 255);
        rainbowhue = new NumberProperty<Integer>((Feature)this, "Delay", "", 0, Integer.valueOf(240), 600);
        rainbowSpeed = new NumberProperty<Integer>((Feature)this, "Speed2", "", 1, Integer.valueOf(2), 50);
        Difference = new NumberProperty<Integer>((Feature)this, "Difference", "", 0, Integer.valueOf(1), Integer.valueOf(100), v -> false);
        Speed = new NumberProperty<Integer>((Feature)this, "New Speed", "", 0, Integer.valueOf(1), Integer.valueOf(100), v -> false);
        a = new NumberProperty<Integer>((Feature)this, "Alpha", "", 0, Integer.valueOf(1), Integer.valueOf(255), v -> false);
        ClickguiRainbow = new Property<Boolean>(this, "ClickGui Rainbow", "", true);
    }
}

