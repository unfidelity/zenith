/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.userInterface.clickgui.button;

import java.awt.Color;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.api.util.newRender.BlurUtil;
import me.gopro336.zenith.api.util.newUtil.ChatUtils;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.toggleable.Client.ClickGuiFeature;
import me.gopro336.zenith.userInterface.clickgui.Dropdown;
import me.gopro336.zenith.userInterface.clickgui.button.IComponent;

public class ModuleComponent
implements IComponent {
    private final Feature feature;
    private int posX;
    private int posY;
    private int width;
    private int height;
    public boolean extended;
    public Dropdown dropdown;

    public ModuleComponent(Feature feature, int posX, int posY, int width, int height) {
        this.feature = feature;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.extended = false;
        this.dropdown = new Dropdown(this.getFeature(), this, this.posX, this.posY, this.width, this.height);
    }

    @Override
    public void onRender(int mouseX, int mouseY, float partialTicks) {
        Zenith.clickGUI.drawGradient(this.getPosX(), this.getPosY(), this.getPosX() + this.getWidth(), this.getPosY() + this.getHeight(), new Color(20, 20, 20, (Integer)ClickGuiFeature.backalpha.getValue()).getRGB(), new Color(20, 20, 20, (Integer)ClickGuiFeature.backalpha.getValue()).getRGB());
        BlurUtil.blurArea(this.getPosX(), this.getPosY() - 7, this.getWidth(), this.getHeight() + 7, 8.0f, 3.0f, 1.0f);
        Zenith.clickGUI.drawGradient(this.getPosX(), this.getPosY(), this.getPosX() + this.getWidth(), this.getPosY() + this.getHeight(), new Color(190, 190, 175, 85).getRGB(), new Color(190, 190, 175, 85).getRGB());
        if (this.feature.isEnabled()) {
            Zenith.clickGUI.drawGradient(this.getPosX() + 1, (double)this.getPosY() + 0.5, this.getPosX() + this.getWidth() - 1, (double)(this.getPosY() + this.getHeight()) - 0.5, this.getComponentColor(), this.getComponentColor());
        }
        FontUtil.drawString(this.feature.getName(), this.getPosX() + 5, this.getPosY() + 4);
        if (this.isHover(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight(), mouseX, mouseY)) {
            int hoverAlpha = 80;
            Zenith.clickGUI.drawGradient(this.getPosX() + 1, (double)this.getPosY() + 0.5, this.getPosX() + this.getWidth(), (double)(this.getPosY() + this.getHeight()) - 0.5, new Color(180, 180, 180, hoverAlpha).getRGB(), new Color(180, 180, 180, hoverAlpha).getRGB());
        }
    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY, int mouseButton) {
        IComponent.super.onMouseClicked(mouseX, mouseY, mouseButton);
        ChatUtils.error("NOOOOOO HOOOOOO MOOOOOOO");
        boolean hovered = this.isHover(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight(), mouseX, mouseY);
        if (hovered) {
            switch (mouseButton) {
                case 0: {
                    this.playButtonSoundEffect();
                    this.getFeature().toggle();
                }
                case 1: {
                    this.playButtonSoundEffect();
                    this.setModuleExtended(!this.isModuleExtended());
                }
            }
        }
    }

    @Override
    public void setExtended(boolean extended) {
    }

    @Override
    public boolean isExtended() {
        return false;
    }

    private boolean isHover(int X, int Y, int W, int H, int mX, int mY) {
        return mX >= X && mX <= X + W && mY >= Y && mY <= Y + H;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setModuleExtended(boolean extended) {
        this.extended = extended;
    }

    public boolean isModuleExtended() {
        return this.extended;
    }

    public Feature getFeature() {
        return this.feature;
    }
}

