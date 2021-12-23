//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.hudElement.hudElement;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import me.gopro336.zenith.api.util.color.ColorHolder;
import me.gopro336.zenith.api.util.color.ColorUtils;
import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.api.util.math.AnimationUtil;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.FeatureManager;
import me.gopro336.zenith.feature.command.Command;
import me.gopro336.zenith.feature.hudElement.Element;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

@AnnotationHelper(name="ActiveModules", category=Category.HUD)
public class ActiveModulesElement
extends Element {
    private Property<Boolean> lines = new Property<Boolean>(this, "Lines", "", true);
    private Property<Boolean> trans = new Property<Boolean>(this, "Trans", "", false);
    private Property<Color> color = new Property<Color>((Feature)this, "Color", "", new Color(255, 62, 154, 255), v -> this.trans.getValue() == false);
    private Property<Boolean> pulse = new Property<Boolean>(this, "Pulse", "", true);
    private NumberProperty<Float> range = new NumberProperty<Float>((Feature)this, "Range", "", Float.valueOf(0.1f), Float.valueOf(0.6f), Float.valueOf(1.0f));
    private NumberProperty<Float> spread = new NumberProperty<Float>((Feature)this, "Spread", "", Float.valueOf(0.1f), Float.valueOf(1.4f), Float.valueOf(2.0f));
    private NumberProperty<Float> speed = new NumberProperty<Float>((Feature)this, "Speed", "", Float.valueOf(1.0f), Float.valueOf(1.0f), Float.valueOf(10.0f));
    private NumberProperty<Float> animationSpeed = new NumberProperty<Float>((Feature)this, "Speed", "", Float.valueOf(0.0f), Float.valueOf(3.5f), Float.valueOf(5.0f));
    private NumberProperty<Float> saturation = new NumberProperty<Float>((Feature)this, "Saturation", "", Float.valueOf(0.0f), Float.valueOf(143.0f), Float.valueOf(255.0f));
    private NumberProperty<Float> brightness = new NumberProperty<Float>((Feature)this, "Brightness", "", Float.valueOf(0.0f), Float.valueOf(215.0f), Float.valueOf(255.0f));
    private NumberProperty<Integer> delay = new NumberProperty<Integer>((Feature)this, "Delay", "", 50, Integer.valueOf(300), 900);
    public Anchor anchor = Anchor.TOP_RIGHT;

    public ActiveModulesElement() {
        FeatureManager.getModulesStatic().forEach(module -> {
            module.remainingAnimation = module.enabled ? 0.0f : (float)FontUtil.getStringWidth(this.getModuleDisplay((Feature)module));
        });
    }

    @Override
    public void onRender() {
        super.onRender();
        this.setAnchor();
        int[] yDist = new int[]{0};
        int[] counter = new int[]{1};
        boolean isTop = this.anchor == Anchor.TOP_LEFT || this.anchor == Anchor.TOP_RIGHT;
        boolean isRight = this.anchor == Anchor.BOTTOM_RIGHT || this.anchor == Anchor.TOP_RIGHT;
        ArrayList<Feature> modules = FeatureManager.getEnabledVisibleModules();
        this.setWidth(50.0f);
        modules.stream().sorted(Comparator.comparingInt(module -> isTop ? -FontUtil.getStringWidth(this.getModuleDisplay((Feature)module)) : FontUtil.getStringWidth(this.getModuleDisplay((Feature)module)))).forEach(module -> {
            float stringWidth = FontUtil.getStringWidth(this.getModuleDisplay((Feature)module));
            String moduleDisplay = this.getModuleDisplay((Feature)module);
            if (module.enabled && module.remainingAnimation < stringWidth) {
                module.remainingAnimation = AnimationUtil.moveTowards(module.remainingAnimation, stringWidth, 0.01f + ((Float)this.animationSpeed.getValue()).floatValue() / 30.0f, 0.1f);
            }
            int color = this.getColor(counter[0]);
            if (this.lines.getValue().booleanValue()) {
                // empty if block
            }
            if (isRight) {
                FontUtil.drawStringWithShadow(moduleDisplay, (int)((float)((int)this.getX()) + this.getWidth() - module.remainingAnimation), (int)(this.getY() + (float)yDist[0] + 0.5f), module.isVisible() ? color : Color.GRAY.getRGB());
            } else {
                FontUtil.drawStringWithShadow(moduleDisplay, (int)((float)((int)this.getX()) - stringWidth + module.remainingAnimation), (int)(this.getY() + (float)yDist[0] + 0.5f), module.isVisible() ? color : Color.GRAY.getRGB());
            }
            yDist[0] = yDist[0] + (int)((float)FontUtil.getStringHeight() + 1.5f);
            counter[0] = counter[0] + 1;
        });
        this.setHeight(yDist[0]);
    }

    private int getColor(int index) {
        ColorHolder holder = new ColorHolder(this.color.getValue().hashCode());
        float[] clr = Color.RGBtoHSB(holder.getRawColor() >> 16 & 0xFF, holder.getRawColor() >> 8 & 0xFF, holder.getRawColor() & 0xFF, null);
        if (this.trans.getValue().booleanValue()) {
            return this.getCuteColor(index - 1);
        }
        if (this.pulse.getValue().booleanValue()) {
            return ColorUtils.pulse(index, clr, ((Float)this.spread.getValue()).floatValue(), ((Float)this.speed.getValue()).floatValue(), ((Float)this.range.getValue()).floatValue()).getRGB();
        }
        return ColorUtils.rainbow((Integer)this.delay.getValue() * index, ((Float)this.saturation.getValue()).floatValue(), ((Float)this.brightness.getValue()).floatValue()).getRGB();
    }

    private int getCuteColor(int index) {
        int size = FeatureManager.getEnabledModules().size();
        int light_blue = new Color(91, 206, 250).getRGB();
        int white = Color.WHITE.getRGB();
        int pink = new Color(245, 169, 184).getRGB();
        int chunkSize = size / 5;
        if (index < chunkSize) {
            return light_blue;
        }
        if (index < chunkSize * 2) {
            return pink;
        }
        if (index < chunkSize * 3) {
            return white;
        }
        if (index < chunkSize * 4) {
            return pink;
        }
        if (index < chunkSize * 5) {
            return light_blue;
        }
        return light_blue;
    }

    private void setAnchor() {
        ScaledResolution sr;
        float x = this.getX() + this.getWidth() / 2.0f;
        float y = this.getY() + this.getHeight() / 2.0f;
        if (y >= (float)(sr = new ScaledResolution(Minecraft.getMinecraft())).getScaledHeight() / 2.0f && x >= (float)sr.getScaledWidth() / 2.0f) {
            this.anchor = Anchor.BOTTOM_RIGHT;
        } else if (y >= (float)sr.getScaledHeight() / 2.0f && x <= (float)sr.getScaledWidth() / 2.0f) {
            this.anchor = Anchor.BOTTOM_LEFT;
        } else if (y <= (float)sr.getScaledHeight() / 2.0f && x >= (float)sr.getScaledWidth() / 2.0f) {
            this.anchor = Anchor.TOP_RIGHT;
        } else if (y <= (float)sr.getScaledHeight() / 2.0f && x <= (float)sr.getScaledWidth() / 2.0f) {
            this.anchor = Anchor.TOP_LEFT;
        }
    }

    private String getModuleDisplay(Feature module) {
        if (module.getFeatureMetadata() != null) {
            return module.getName() + Command.SECTIONSIGN + "7 " + ChatFormatting.GRAY + "[" + ChatFormatting.WHITE + module.getFeatureMetadata() + ChatFormatting.GRAY + "]";
        }
        return module.getName();
    }

    public static enum Anchor {
        TOP_RIGHT,
        TOP_LEFT,
        BOTTOM_RIGHT,
        BOTTOM_LEFT;

    }
}

