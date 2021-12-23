//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.userInterface.clickgui.button.buttons;

import java.awt.Color;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.api.util.newUtil.ChatUtils;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.Property;
import me.gopro336.zenith.userInterface.clickgui.button.SettingComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class BoolSettingComponent
extends SettingComponent {
    private final Property<Boolean> value;

    public BoolSettingComponent(Feature feature, Property<Boolean> value, int X, int Y, int W, int H, boolean isSub) {
        super(feature, X, Y, W, H, isSub);
        this.value = value;
    }

    @Override
    public void onRender(int mouseX, int mouseY, float partialTicks) {
        super.onRender(mouseX, mouseY, partialTicks);
        this.preComponentRender(false);
        if (this.getProperty().getValue().booleanValue()) {
            this.drawButton();
        }
        FontUtil.drawString(this.value.getName(), (float)(this.getPosX() + 6) + (float)(this.isSubSetting ? 3 : 0), this.getPosY() + 4);
        if (this.isWithinBuffer(mouseX, mouseY)) {
            int hoverAlpha = 80;
            Zenith.clickGUI.drawGradient(this.getPosX() + 1, (double)this.getPosY() + 0.5, this.getPosX() + this.getWidth(), (double)(this.getPosY() + this.getHeight()) - 0.5, new Color(180, 180, 180, hoverAlpha).getRGB(), new Color(180, 180, 180, hoverAlpha).getRGB());
        }
        this.postComponentRender();
    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.onMouseClicked(mouseX, mouseY, mouseButton);
        ChatUtils.error("broooooo");
        boolean withinBounds = this.isWithinBuffer(mouseX, mouseY);
        if (withinBounds && mouseButton == 0) {
            Minecraft.getMinecraft().getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord((SoundEvent)SoundEvents.UI_BUTTON_CLICK, (float)1.0f));
            this.getProperty().setValue(this.getProperty().getValue() == false);
        } else if (withinBounds && mouseButton == 1) {
            this.getProperty().setValue(this.getProperty().getValue() == false);
            this.getProperty().toggleOpenState();
        }
    }

    @Override
    public void onUpdate() {
        this.setHidden(!this.value.isVisible());
    }

    public Property<Boolean> getProperty() {
        return this.value;
    }
}

