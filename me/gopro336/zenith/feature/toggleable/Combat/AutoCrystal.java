/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.Combat;

import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;

@AnnotationHelper(name="AutoCrystal", description="Blows ppl wit crystals", category=Category.COMBAT)
public class AutoCrystal
extends Feature {
    public static Property<Boolean> terrainIgnore;
    public static NumberProperty<Integer> predictTicks;
    public static Property<Boolean> collision;

    public AutoCrystal() {
        terrainIgnore = new Property<Boolean>(this, "PredictDestruction", "", false);
        predictTicks = new NumberProperty<Integer>((Feature)this, "PredictTicks", "", 0, Integer.valueOf(1), 10);
        collision = new Property<Boolean>(this, "Collision", "", false);
    }
}

