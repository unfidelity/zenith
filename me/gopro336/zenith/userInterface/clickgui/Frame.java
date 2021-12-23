//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.userInterface.clickgui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import me.gopro336.zenith.api.util.color.RenderUtil;
import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.FeatureManager;
import me.gopro336.zenith.feature.toggleable.Client.ClickGuiFeature;
import me.gopro336.zenith.userInterface.clickgui.Dropdown;
import me.gopro336.zenith.userInterface.clickgui.IFrame;
import me.gopro336.zenith.userInterface.clickgui.button.ModuleComponent;
import me.gopro336.zenith.userInterface.clickgui.button.SettingComponent;
import net.minecraft.client.gui.Gui;

public class Frame
implements IFrame {
    public String title;
    public final Category category;
    public static int[] counter1 = new int[]{1};
    private boolean isDragging;
    private int bottom;
    private int place;
    private int scroll;
    private final ArrayList<ModuleComponent> mouduleButtonComponents = new ArrayList();
    public int width;
    public int height;
    public int posX;
    public int posY;
    public int dragX;
    public int dragY;
    public int topBarHeight;
    public boolean extended = true;
    public static List<Frame> windows = new ArrayList<Frame>();

    public Frame(Category category, int posX, int posY, int width, int height) {
        this.title = category.getName();
        this.isDragging = false;
        this.category = category;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.topBarHeight = height - 1;
        int yOffset = this.posY + this.height;
        for (Feature feature : FeatureManager.getModules(category)) {
            ModuleComponent button = new ModuleComponent(feature, this.posX, yOffset, this.width, this.height);
            this.mouduleButtonComponents.add(button);
            yOffset += this.height;
        }
    }

    public void onRender(int mouseX, int mouseY, float partialTicks) {
        this.updatePosition(mouseX, mouseY);
        counter1 = new int[]{1};
        Gui.drawRect((int)this.posX, (int)this.posY, (int)(this.posX + this.width), (int)(this.posY + this.height + 1), (int)ClickGuiFeature.color.getValue().getRGB());
        FontUtil.drawString(this.category.getName(), this.posX + 4, this.posY + 4);
        if (this.extended) {
            int modY = this.posY + this.height;
            for (ModuleComponent moduleComponent : this.mouduleButtonComponents) {
                Frame.counter1[0] = counter1[0] + 1;
                moduleComponent.setPosX(this.posX);
                moduleComponent.setPosY(modY);
                moduleComponent.onRender(mouseX, mouseY, partialTicks);
                if (moduleComponent.isModuleExtended()) {
                    Dropdown dropdown = moduleComponent.dropdown;
                    dropdown.setX(this.posX);
                    dropdown.setY(modY);
                    dropdown.onRender(mouseX, mouseY, partialTicks);
                    modY += dropdown.getBoost();
                }
                modY += this.height;
            }
            RenderUtil.drawRectOutline(this.posX, this.posY, this.posX + this.width, modY, ClickGuiFeature.thin.getValue() != false ? 0.5 : 1.0, new Color(Math.min(ClickGuiFeature.color.getValue().getRed() + 10, 255), Math.min(ClickGuiFeature.color.getValue().getGreen() + 10, 255), Math.min(ClickGuiFeature.color.getValue().getBlue() + 10, 255), Math.min(ClickGuiFeature.color.getValue().getAlpha() + 10, 255)).getRGB());
        }
    }

    public boolean onMouseClicked(int mouseX, int mouseY, int mouseButton) {
        boolean withinBounds = this.mouseWithinBounds(mouseX, mouseY, this.getPosX(), this.getPosY(), this.getPosX() + this.getWidth(), this.getPosY() + this.getTopBarHeight());
        switch (mouseButton) {
            case 0: {
                break;
            }
            case 1: {
                if (!withinBounds) break;
                return true;
            }
        }
        this.getButtonComponents().stream().peek(moduleComponent -> moduleComponent.onMouseClicked(mouseX, mouseY, mouseButton)).filter(moduleComponent -> moduleComponent.extended).forEach(moduleComponent -> moduleComponent.dropdown.getButtons().stream().filter(settingComponent -> !settingComponent.isSubSetting).filter(SettingComponent::isNotHidden).forEach(component -> component.onMouseClicked(mouseX, mouseY, mouseButton)));
        this.getButtonComponents().stream().filter(moduleComponent -> moduleComponent.extended).forEach(moduleComponent -> moduleComponent.dropdown.getButtons().stream().filter(settingComponent -> settingComponent.isSubSetting).filter(SettingComponent::isSubNotHidden).forEach(component -> component.onMouseClicked(mouseX, mouseY, mouseButton)));
        return false;
    }

    public void onMouseReleased(int mouseX, int mouseY, int mouseButton) {
        this.getButtonComponents().stream().peek(moduleComponent -> moduleComponent.onMouseReleased(mouseX, mouseY, mouseButton)).filter(moduleComponent -> moduleComponent.extended).forEach(moduleComponent -> moduleComponent.dropdown.getButtons().stream().filter(settingComponent -> !settingComponent.isSubSetting).filter(SettingComponent::isNotHidden).forEach(component -> component.onMouseReleased(mouseX, mouseY, mouseButton)));
        this.getButtonComponents().stream().filter(moduleComponent -> moduleComponent.extended).forEach(moduleComponent -> moduleComponent.dropdown.getButtons().stream().filter(settingComponent -> settingComponent.isSubSetting).filter(SettingComponent::isSubNotHidden).forEach(component -> component.onMouseReleased(mouseX, mouseY, mouseButton)));
    }

    public void onMouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        this.getButtonComponents().stream().peek(moduleComponent -> moduleComponent.onMouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick)).filter(moduleComponent -> moduleComponent.extended).forEach(moduleComponent -> moduleComponent.dropdown.getButtons().stream().filter(settingComponent -> !settingComponent.isSubSetting).filter(SettingComponent::isNotHidden).forEach(component -> component.onMouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick)));
        this.getButtonComponents().stream().filter(moduleComponent -> moduleComponent.extended).forEach(moduleComponent -> moduleComponent.dropdown.getButtons().stream().filter(settingComponent -> settingComponent.isSubSetting).filter(SettingComponent::isSubNotHidden).forEach(component -> component.onMouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick)));
    }

    public void onKeyTyped(char character, int keyCode) {
        this.getButtonComponents().stream().peek(moduleComponent -> moduleComponent.onKeyTyped(character, keyCode)).filter(moduleComponent -> moduleComponent.extended).forEach(moduleComponent -> moduleComponent.dropdown.getButtons().stream().filter(settingComponent -> !settingComponent.isSubSetting).filter(SettingComponent::isNotHidden).forEach(component -> component.onKeyTyped(character, keyCode)));
        this.getButtonComponents().stream().filter(moduleComponent -> moduleComponent.extended).forEach(moduleComponent -> moduleComponent.dropdown.getButtons().stream().filter(settingComponent -> settingComponent.isSubSetting).filter(SettingComponent::isSubNotHidden).forEach(component -> component.onKeyTyped(character, keyCode)));
    }

    private int getTotalHeight() {
        int currentHeight = this.getTopBarHeight();
        if (!this.extended) {
            return currentHeight;
        }
        for (ModuleComponent component : this.getButtonComponents()) {
            currentHeight += component.getHeight();
            if (!component.isModuleExtended()) continue;
            currentHeight += component.dropdown.getBoost();
        }
        return currentHeight;
    }

    public void updatePosition(int mouseX, int mouseY) {
        if (this.isDragging) {
            this.setPosX(mouseX - this.dragX);
            this.setPosY(mouseY - this.dragY);
        }
    }

    public void onUpdate() {
        if (!this.extended) {
            return;
        }
        for (ModuleComponent button : this.mouduleButtonComponents) {
            button.dropdown.onUpdate();
        }
    }

    public ArrayList<ModuleComponent> getButtonComponents() {
        return this.mouduleButtonComponents;
    }

    public boolean isWithinHeader(int x, int y) {
        return x >= this.posX && x <= this.posX + this.width && y >= this.posY && y <= this.posY + this.height;
    }

    public void setDragging(boolean drag) {
        this.isDragging = drag;
    }

    public boolean isDragging() {
        return this.isDragging;
    }

    private boolean mouseWithinBounds(int X, int Y, int W, int H, int mX, int mY) {
        return mX >= X && mX <= X + W && mY >= Y && mY <= Y + H;
    }

    public int getDragX() {
        return this.dragX;
    }

    public void setDragX(int dragX) {
        this.dragX = dragX;
    }

    public int getDragY() {
        return this.dragY;
    }

    public void setDragY(int dragY) {
        this.dragY = dragY;
    }

    public int getPosY() {
        return this.posY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setExtended(boolean open) {
        this.extended = open;
    }

    public boolean isExtended() {
        return this.extended;
    }

    public int getTopBarHeight() {
        return this.topBarHeight;
    }
}

