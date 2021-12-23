/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.userInterface.clickgui.button.buttons;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.api.util.newRender.RenderUtils2D;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.toggleable.Client.ClickGuiFeature;
import me.gopro336.zenith.property.EnumUtil;
import me.gopro336.zenith.property.Property;
import me.gopro336.zenith.userInterface.clickgui.button.SettingComponent;

public class EnumSettingComponent
extends SettingComponent {
    private final Property<Enum<?>> value;

    public EnumSettingComponent(Feature feature, Property<Enum<?>> value, int X, int Y, int W, int H, Boolean isSub) {
        super(feature, X, Y, W, H, isSub);
        this.value = value;
    }

    @Override
    public void onRender(int mouseX, int mouseY, float partialTicks) {
        super.onRender(mouseX, mouseY, partialTicks);
        this.preComponentRender(true);
        FontUtil.drawString(this.value.getName(), (float)(this.getPosX() + 6) + (float)(this.isSubSetting ? 3 : 0), this.getPosY() + 4);
        FontUtil.drawString(ChatFormatting.GRAY + this.value.getValue().toString(), this.getPosX() + this.getWidth() - 6 - FontUtil.getStringWidth(this.value.getValue().toString()), this.getPosY() + 4);
        if (this.isSubSetting) {
            RenderUtils2D.drawRect(this.getPosX() + 2, (double)this.getPosY(), this.getPosX() + 3, (double)(this.getPosY() + this.getHeight()), ClickGuiFeature.accentColor.getValue().getRGB());
        }
        if (ClickGuiFeature.dot.getValue().booleanValue() && this.getSubCount() > 0) {
            FontUtil.drawString("...", this.getPosX() + this.getWidth() - 3 - FontUtil.getStringWidth("..."), this.getPosY() + 4, new Color(255, 255, 255, 255).getRGB());
        }
        this.postComponentRender();
    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY, int mouseButton) {
        boolean forward;
        super.onMouseClicked(mouseX, mouseY, mouseButton);
        boolean bl = forward = mouseX > this.getPosX() + this.getWidth() / 2;
        if (this.isWithinBuffer(mouseX, mouseY)) {
            if (mouseButton == 1) {
                this.value.toggleOpenState();
            }
            if (forward) {
                EnumUtil.setEnumValue(this.value, EnumUtil.getNextEnumValue(this.value, false));
            } else {
                EnumUtil.setEnumValue(this.value, EnumUtil.getNextEnumValue(this.value, true));
            }
        }
    }

    @Override
    public void onUpdate() {
        this.setHidden(!this.value.isVisible());
    }

    @Override
    public Property<?> getProperty() {
        return this.value;
    }
}

