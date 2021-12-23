//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.userInterface.clickgui.button.buttons;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.api.util.math.RoundingUtil;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.toggleable.Client.ClickGuiFeature;
import me.gopro336.zenith.feature.toggleable.render.RainbowFeature;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.userInterface.clickgui.button.SettingComponent;
import net.minecraft.util.math.MathHelper;

public class SliderSettingComponent
extends SettingComponent {
    private final NumberProperty<Number> value;
    private boolean isSliding;

    public SliderSettingComponent(Feature feature, NumberProperty<Number> value, int X, int Y, int W, int H, Boolean isSub) {
        super(feature, X, Y, W, H, isSub);
        this.value = value;
    }

    @Override
    public void onRender(int mouseX, int mouseY, float partialTicks) {
        super.onRender(mouseX, mouseY, partialTicks);
        this.preComponentRender(false);
        float length = MathHelper.floor((float)((((Number)this.getProperty().getValue()).floatValue() - ((Number)((NumberProperty)this.getProperty()).getMin()).floatValue()) / (((Number)((NumberProperty)this.getProperty()).getMax()).floatValue() - ((Number)((NumberProperty)this.getProperty()).getMin()).floatValue()) * (float)(this.getWidth() - 2)));
        if (length < 0.0f) {
            this.getProperty().setValue(((NumberProperty)this.getProperty()).getMin());
            this.setSliding(false);
        } else if (length > (float)(this.getWidth() - 2)) {
            this.getProperty().setValue(((NumberProperty)this.getProperty()).getMax());
            this.setSliding(false);
        }
        if (RainbowFeature.ClickguiRainbow.getValue().booleanValue()) {
            Zenith.clickGUI.drawGradient(this.getPosX() + 1 + 1 + (this.isSubSetting ? 3 : 0), (double)this.getPosY() + 0.5, length + 1.0f, this.getPosY() + this.getHeight() - 1, this.getComponentColor(), this.getComponentColor());
        } else {
            Zenith.clickGUI.drawGradient(this.getPosX() + 1 + 1 + (this.isSubSetting ? 3 : 0), (double)this.getPosY() + 0.5, length + 1.0f, this.getPosY() + this.getHeight() - 1, ClickGuiFeature.color.getValue().getRGB(), ClickGuiFeature.color.getValue().getRGB());
        }
        FontUtil.drawString(this.value.getName(), (float)(this.getPosX() + 6) + (float)(this.isSubSetting ? 3 : 0), this.getPosY() + 4);
        FontUtil.drawString(ChatFormatting.GRAY + String.valueOf(this.value.getValue()), this.getPosX() + this.getWidth() - 6 + (this.isSubSetting ? 1 : 0) - FontUtil.getStringWidth(String.valueOf(this.value.getValue())), this.getPosY() + 4);
        if (this.isSliding) {
            if (this.getProperty().getValue() instanceof Float) {
                float newValue = (float)(mouseX - this.getPosX()) * (((Number)((NumberProperty)this.getProperty()).getMax()).floatValue() - ((Number)((NumberProperty)this.getProperty()).getMin()).floatValue()) / (float)this.getWidth() + ((Number)((NumberProperty)this.getProperty()).getMin()).floatValue();
                this.getProperty().setValue(Float.valueOf(MathHelper.clamp((float)RoundingUtil.roundFloat(RoundingUtil.roundToStep(newValue, 0.1f), 2), (float)((Float)((NumberProperty)this.getProperty()).getMin()).floatValue(), (float)((Float)((NumberProperty)this.getProperty()).getMax()).floatValue())));
            } else if (this.getProperty().getValue() instanceof Integer) {
                int newValue = (mouseX - this.getPosX()) * (((Number)((NumberProperty)this.getProperty()).getMax()).intValue() - ((Number)((NumberProperty)this.getProperty()).getMin()).intValue()) / this.getWidth() + ((Number)((NumberProperty)this.getProperty()).getMin()).intValue();
                this.getProperty().setValue(newValue);
            } else if (this.getProperty().getValue() instanceof Double) {
                double newValue = (double)(mouseX - this.getPosX()) * (((Number)((NumberProperty)this.getProperty()).getMax()).doubleValue() - ((Number)((NumberProperty)this.getProperty()).getMin()).doubleValue()) / (double)this.getWidth() + ((Number)((NumberProperty)this.getProperty()).getMin()).doubleValue();
                this.getProperty().setValue(MathHelper.clamp((double)RoundingUtil.roundDouble(RoundingUtil.roundToStep(newValue, 0.1), 2), (double)((Double)((NumberProperty)this.getProperty()).getMin()), (double)((Double)((NumberProperty)this.getProperty()).getMax())));
            } else if (this.getProperty().getValue() instanceof Long) {
                long newValue = (long)((double)(mouseX - this.getPosX()) * (((Number)((NumberProperty)this.getProperty()).getMax()).doubleValue() - ((Number)((NumberProperty)this.getProperty()).getMin()).doubleValue()) / (double)this.getWidth() + ((Number)((NumberProperty)this.getProperty()).getMin()).doubleValue());
                this.getProperty().setValue(newValue);
            }
        }
        this.postComponentRender();
    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY, int button) {
        super.onMouseClicked(mouseX, mouseY, button);
        if (this.isWithinBuffer(mouseX, mouseY) && button == 0) {
            this.setSliding(true);
        }
    }

    @Override
    public void onMouseReleased(int mouseX, int mouseY, int button) {
        super.onMouseReleased(mouseX, mouseY, button);
        if (this.isSliding()) {
            this.setSliding(false);
        }
    }

    @Override
    public void onMouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (!this.isWithinBuffer(mouseX, mouseY)) {
            this.setSliding(false);
        }
    }

    public void setSliding(boolean sliding) {
        this.isSliding = sliding;
    }

    public boolean isSliding() {
        return this.isSliding;
    }

    @Override
    public void onUpdate() {
        this.setHidden(!this.value.isVisible());
    }

    public NumberProperty<Number> getProperty() {
        return this.value;
    }
}

