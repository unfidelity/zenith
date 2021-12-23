/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.render;

import java.awt.Font;
import me.gopro336.zenith.api.util.font.CustomFontRenderer;
import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;

@AnnotationHelper(name="CustomFont", description="Use a custom font render instead of Minecraft's default", category=Category.RENDER)
public class CustomFont
extends Feature {
    public static NumberProperty<Integer> fontsise;
    public static NumberProperty<Integer> yoffset;

    public CustomFont() {
        fontsise = new NumberProperty<Integer>((Feature)this, "FontSize", "", 0, Integer.valueOf(20), 50).onChanged(integerOnChangedValue -> CustomFont.updateFont("Verdana", 0, (Integer)fontsise.getValue(), true, true));
        yoffset = new NumberProperty<Integer>((Feature)this, "Y-Offset", "", -8, Integer.valueOf(0), 8);
    }

    public static void updateFont(String newName, int style, int size, boolean antialias, boolean metrics) {
        try {
            FontUtil.customFontRenderer = new CustomFontRenderer(new Font("Arial", 0, size), true, false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdate() {
    }
}

