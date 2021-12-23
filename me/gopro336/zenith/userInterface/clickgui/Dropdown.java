/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.userInterface.clickgui;

import java.util.ArrayList;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.IGlobals;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import me.gopro336.zenith.userInterface.clickgui.button.ModuleComponent;
import me.gopro336.zenith.userInterface.clickgui.button.SettingComponent;
import me.gopro336.zenith.userInterface.clickgui.button.buttons.BindSettingComponent;
import me.gopro336.zenith.userInterface.clickgui.button.buttons.BoolSettingComponent;
import me.gopro336.zenith.userInterface.clickgui.button.buttons.EnumSettingComponent;
import me.gopro336.zenith.userInterface.clickgui.button.buttons.SliderSettingComponent;

public class Dropdown
implements IGlobals {
    private final int W;
    private final int H;
    public int X;
    public int Y;
    private final Feature feature;
    private final ModuleComponent moduleComponent;
    private static int modY = 0;
    private final ArrayList<SettingComponent> buttons = new ArrayList();

    public Dropdown(Feature feature, ModuleComponent mButton, int x, int y, int w, int h) {
        this.X = x;
        this.Y = y;
        this.W = w;
        this.H = h;
        this.feature = feature;
        this.moduleComponent = mButton;
        int boost = 0;
        this.initGui(boost);
    }

    public SettingComponent getPropertyButton(Property<?> property, int boost, boolean sub) {
        SettingComponent SettingComponent2 = null;
        if (property.getValue() instanceof Boolean) {
            SettingComponent2 = new BoolSettingComponent(this.feature, property, this.X, this.Y + boost * this.H, this.W, this.H, sub);
        }
        if (property.getValue() instanceof Enum) {
            SettingComponent2 = new EnumSettingComponent(this.feature, property, this.X, this.Y + boost * this.H, this.W, this.H, sub);
        }
        if (property.getValue() instanceof Number) {
            SettingComponent2 = new SliderSettingComponent(this.feature, (NumberProperty)property, this.X, this.Y + boost * this.H, this.W, this.H, sub);
        }
        if (SettingComponent2 != null) {
            this.buttons.add(SettingComponent2);
        }
        return SettingComponent2;
    }

    public void initGui(int boost) {
        if (Zenith.SettingManager.getPropertiesByMod(this.feature) == null) {
            return;
        }
        for (Property<?> property : Zenith.SettingManager.getPropertiesByMod(this.feature)) {
            SettingComponent SettingComponent2;
            if (property.isSubProperty() || (SettingComponent2 = this.getPropertyButton(property, boost, false)) == null) continue;
            for (Property<?> sub : property.getSubProperties()) {
                SettingComponent2.countSub();
                this.getPropertyButton(sub, boost, true);
            }
        }
        this.buttons.add(new BindSettingComponent(this.feature, this.X, this.Y + this.H + boost * this.H, this.W, this.H, (Boolean)false));
    }

    public void onRender(int mX, int mY, float partialTicks) {
        modY = 0;
        int boost = 0;
        for (SettingComponent button : this.buttons) {
            if (button.isHidden()) continue;
            if (button instanceof BindSettingComponent || !button.getProperty().isSubProperty()) {
                button.setPosX(this.X);
                button.setPosY(this.Y + (boost += button.getYOffset()));
                button.onRender(mX, mY, partialTicks);
            } else if (button.getProperty().isSubProperty()) {
                if (!button.getProperty().getParentProperty().isVisible() || !button.getProperty().getParentProperty().isExtended()) continue;
                button.setPosX(this.X);
                button.setPosY(this.Y + (boost += button.getYOffset()));
                button.onRender(mX, mY, partialTicks);
            }
            modY = boost;
        }
    }

    public void onUpdate() {
        this.buttons.forEach(SettingComponent::onUpdate);
    }

    public void setX(int x) {
        this.X = x;
    }

    public double getH() {
        return this.H;
    }

    public void setY(int y) {
        this.Y = y;
    }

    public ArrayList<SettingComponent> getButtons() {
        return this.buttons;
    }

    public int getBoost() {
        return modY;
    }
}

