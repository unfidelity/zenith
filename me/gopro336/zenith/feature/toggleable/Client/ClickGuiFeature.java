//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.Client;

import java.awt.Color;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

@AnnotationHelper(name="ClickGUI", description="Toggle modules by clicking on them", category=Category.CLIENT)
public class ClickGuiFeature
extends Feature {
    public static ClickGuiFeature INSTANCE = new ClickGuiFeature();
    public static Property<Boolean> bColor;
    public static Property<Boolean> oColor;
    public static Property<Boolean> tColor;
    public static Property<Color> color;
    public static Property<Color> accentColor;
    public static NumberProperty<Integer> bred;
    public static NumberProperty<Integer> tred;
    public static NumberProperty<Integer> tgreen;
    public static NumberProperty<Integer> tblue;
    public static NumberProperty<Integer> talpha;
    public static Property<Boolean> fullCtrl;
    public static Property<Boolean> textShadow;
    public static Property<Boolean> thin;
    public static Property<Boolean> round;
    public static Property<Boolean> dot;
    public static NumberProperty<Integer> backalpha;
    public static NumberProperty<Integer> disbuttonalpha;
    public static NumberProperty<Integer> hovereffect;
    public static NumberProperty<Integer> hovereffectslide;
    public static NumberProperty<Integer> dropdownSpeed;
    public static Property<String> textBox;

    public ClickGuiFeature() {
        bColor = new Property<Boolean>((Feature)this, "Button Color", "", true, v -> false);
        oColor = new Property<Boolean>((Feature)this, "Outline Color", "", true, v -> false);
        tColor = new Property<Boolean>((Feature)this, "Text Color", "", true, v -> fullCtrl.getValue());
        color = new Property<Color>(this, "Color", "color of the guis", new Color(229, 11, 137, 181));
        accentColor = new Property<Color>(this, "AccentColor", "accent color of the guis", new Color(16767832));
        tred = new NumberProperty<Integer>((Feature)this, tColor, "Text red", "", Integer.valueOf(0), Integer.valueOf(255), 255);
        tgreen = new NumberProperty<Integer>((Feature)this, tColor, "Text green", "", Integer.valueOf(0), Integer.valueOf(255), 255);
        tblue = new NumberProperty<Integer>((Feature)this, tColor, "Text blue", "", Integer.valueOf(0), Integer.valueOf(255), 255);
        talpha = new NumberProperty<Integer>((Feature)this, tColor, "Text alpha", "", Integer.valueOf(0), Integer.valueOf(255), 255);
        fullCtrl = new Property<Boolean>(this, "FullColorCtrl", "", false);
        textShadow = new Property<Boolean>(this, "TextShadow", "", false);
        thin = new Property<Boolean>((Feature)this, "ThinOutline", "", false, v -> fullCtrl.getValue());
        round = new Property<Boolean>((Feature)this, "Rounded Buttons", "", false, v -> fullCtrl.getValue());
        dot = new Property<Boolean>((Feature)this, "...", "", true, v -> fullCtrl.getValue());
        backalpha = new NumberProperty<Integer>((Feature)this, "BackgroundOpacity", "", 0, Integer.valueOf(174), Integer.valueOf(255), v -> fullCtrl.getValue());
        disbuttonalpha = new NumberProperty<Integer>((Feature)this, "DisabledOpacity", "", 0, Integer.valueOf(146), Integer.valueOf(255), v -> fullCtrl.getValue());
        hovereffect = new NumberProperty<Integer>((Feature)this, "HoverEffect", "", 0, Integer.valueOf(5), Integer.valueOf(10), v -> fullCtrl.getValue());
        hovereffectslide = new NumberProperty<Integer>((Feature)this, "HoverMotionEffect", "", 0, Integer.valueOf(0), Integer.valueOf(10), v -> fullCtrl.getValue());
        dropdownSpeed = new NumberProperty<Integer>((Feature)this, "AnimationSpeed", "", 1, Integer.valueOf(5), Integer.valueOf(30), v -> fullCtrl.getValue());
        textBox = new Property<String>(this, "Text", "", "yo wtf");
        this.setInstance();
        this.setKey(Keyboard.getKeyIndex((String)"P"));
    }

    public static ClickGuiFeature getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClickGuiFeature();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        this.toggle();
        mc.displayGuiScreen((GuiScreen)Zenith.clickGUI);
    }

    @Override
    public void onUpdate() {
    }
}

