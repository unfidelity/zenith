/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.userInterface.clickgui.button;

import java.awt.Color;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.api.util.newRender.RenderUtils2D;
import me.gopro336.zenith.api.util.newUtil.ChatUtils;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.toggleable.Client.ClickGuiFeature;
import me.gopro336.zenith.property.Property;
import me.gopro336.zenith.userInterface.clickgui.button.IComponent;

public class SettingComponent
implements IComponent {
    public Property<?> value;
    private Feature feature;
    private int posX;
    private int posY;
    private int width;
    public int height;
    public boolean hidden;
    public boolean extended = false;
    public boolean isSubSetting;
    private int subsCount = 0;

    public SettingComponent(Feature feature, int posX, int posY, int width, int height, boolean isSub) {
        this.feature = feature;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.isSubSetting = isSub;
    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY, int mouseButton) {
        ChatUtils.error("no homo");
    }

    @Override
    public void onRender(int mouseX, int mouseY, float partialTicks) {
    }

    @Override
    public void onKeyTyped(char character, int keyCode) {
    }

    @Override
    public void onMouseReleased(int mouseX, int mouseY, int mouseButton) {
    }

    @Override
    public void onMouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    }

    @Override
    public void onUpdate() {
    }

    @Override
    public void setExtended(boolean extended) {
    }

    @Override
    public boolean isExtended() {
        return false;
    }

    public Property<?> getProperty() {
        return this.value;
    }

    public Feature getFeature() {
        return this.feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public boolean isSubProperty() {
        return this.isSubSetting;
    }

    public SettingComponent getSelf() {
        return this;
    }

    public SettingComponent getType() {
        return this;
    }

    public void countSub() {
        ++this.subsCount;
    }

    public int getSubCount() {
        return this.subsCount;
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

    public int getYOffset() {
        return this.height;
    }

    public void setSettingExtended(boolean extended) {
        this.extended = extended;
    }

    public boolean isSettingExtended() {
        return this.extended;
    }

    public boolean isSubNotHidden() {
        if (!this.getProperty().getParentProperty().isVisible()) {
            return false;
        }
        return this.getProperty().getParentProperty().isExtended();
    }

    public boolean isNotHidden() {
        return !this.hidden;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isHover(int X, int Y, int W, int H, int mX, int mY) {
        return mX >= X && mX <= X + W && mY >= Y && mY <= Y + H;
    }

    protected boolean isWithinBuffer(int mouseX, int mouseY) {
        return mouseX >= this.getPosX() && mouseX <= this.getPosX() + this.getWidth() && mouseY >= this.getPosY() && mouseY <= this.getPosY() + this.height;
    }

    public void preComponentRender(boolean drawButton) {
        Zenith.clickGUI.drawGradient(this.posX, this.posY, this.posX + this.width, this.posY + this.height, new Color(20, 20, 20, (Integer)ClickGuiFeature.backalpha.getValue()).getRGB(), new Color(20, 20, 20, (Integer)ClickGuiFeature.backalpha.getValue()).getRGB());
        if (drawButton) {
            this.drawButton();
        }
    }

    public void drawButton() {
        Zenith.clickGUI.drawGradient(this.getPosX() + 1, (double)this.getPosY() + 0.5, this.getPosX() + this.getWidth() - 1, (double)(this.getPosY() + this.getHeight()) - 0.5, this.getComponentColor(), this.getComponentColor());
    }

    public void postComponentRender() {
        if (this.isSubSetting) {
            this.drawSubDropdownLine();
        }
        if (ClickGuiFeature.dot.getValue().booleanValue() && this.getSubCount() > 0) {
            this.drawDropdownIndicator();
        }
    }

    public void drawDropdownIndicator() {
        FontUtil.drawString("...", this.getPosX() + this.getWidth() - 3 - FontUtil.getStringWidth("..."), this.getPosY() + 4, new Color(255, 255, 255, 255).getRGB());
    }

    public void drawSubDropdownLine() {
        RenderUtils2D.drawRect(this.getPosX() + 2, (double)this.getPosY(), this.getPosX() + 3, (double)(this.getPosY() + this.getHeight()), ClickGuiFeature.accentColor.getValue().getRGB());
    }
}

