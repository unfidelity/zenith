//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.hudElement.hudElement;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.hudElement.Element;
import net.minecraft.util.math.MathHelper;

@AnnotationHelper(name="Tps", category=Category.HUD)
public class TpsElement
extends Element {
    private final float[] ticks = new float[20];

    @Override
    public void onRender() {
        String tpsString = "TPS " + ChatFormatting.WHITE + String.format("%.2f", Float.valueOf(this.getTickRate()));
        FontUtil.drawStringWithShadow(tpsString, this.getX(), this.getY(), -1);
        this.setWidth(FontUtil.getStringWidth(tpsString));
        this.setHeight(FontUtil.getFontHeight());
    }

    private float getTickRate() {
        int tickCount = 0;
        float tickRate = 0.0f;
        for (float tick : this.ticks) {
            if (!(tick > 0.0f)) continue;
            tickRate += tick;
            ++tickCount;
        }
        return MathHelper.clamp((float)(tickRate / (float)tickCount), (float)0.0f, (float)20.0f);
    }
}

