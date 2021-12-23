//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.render;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;

@AnnotationHelper(name="Fullbright", description="Makes it fully bright", category=Category.RENDER)
public class Fullbright
extends Feature {
    private final List<Float> previousLevels = new ArrayList<Float>();

    @Override
    public void onEnable() {
        if (Fullbright.mc.player != null && Fullbright.mc.world != null) {
            float[] table = Fullbright.mc.world.provider.getLightBrightnessTable();
            if (Fullbright.mc.world.provider != null) {
                for (float f : table) {
                    this.previousLevels.add(Float.valueOf(f));
                }
                Arrays.fill(table, 1.0f);
            }
        } else {
            this.toggle();
        }
    }

    @Override
    public void onDisable() {
        if (Fullbright.mc.player != null && Fullbright.mc.world != null) {
            float[] table = Fullbright.mc.world.provider.getLightBrightnessTable();
            for (int i = 0; i < table.length; ++i) {
                table[i] = this.previousLevels.get(i).floatValue();
            }
            this.previousLevels.clear();
        }
    }
}

