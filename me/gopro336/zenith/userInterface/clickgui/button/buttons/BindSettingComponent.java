//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.userInterface.clickgui.button.buttons;

import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.userInterface.clickgui.ClickGUI;
import me.gopro336.zenith.userInterface.clickgui.button.SettingComponent;
import net.minecraft.client.settings.GameSettings;

public class BindSettingComponent
extends SettingComponent {
    private final Feature feature;
    private boolean isBinding;

    public BindSettingComponent(Feature feature, int x, int y, int w, int h, Boolean isSub) {
        super(feature, x, y, w, h, isSub);
        this.feature = feature;
    }

    @Override
    public void onRender(int mouseX, int mouseY, float partialTicks) {
        super.onRender(mouseX, mouseY, partialTicks);
        this.preComponentRender(false);
        FontUtil.drawString(this.isBinding() ? "Press new bind..." : "Bind: " + GameSettings.getKeyDisplayString((int)this.getFeature().getKey()), (int)((float)this.getPosX() + 5.0f), (int)((float)this.getPosY() + (float)this.getHeight() / 2.0f - (float)(FontUtil.getStringHeight() / 2) - 0.5f), 0xFFFFFF);
    }

    @Override
    public void onKeyTyped(char character, int keyCode) {
        super.onKeyTyped(character, keyCode);
        if (this.isBinding()) {
            if (keyCode == 1) {
                this.getFeature().setKey(0);
                this.setBinding(false);
                return;
            }
            this.getFeature().setKey(keyCode);
            this.setBinding(false);
        }
    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.onMouseClicked(mouseX, mouseY, mouseButton);
        if (this.isBinding()) {
            this.getFeature().setKey(mouseButton - 100);
            this.setBinding(false);
        } else {
            boolean withinBounds = this.isWithinBuffer(mouseX, mouseY);
            if (withinBounds && mouseButton == 0) {
                this.playButtonSoundEffect();
                this.setBinding(!this.isBinding());
            }
        }
    }

    public boolean isBinding() {
        return this.isBinding;
    }

    public void setBinding(boolean binding) {
        this.isBinding = binding;
        ClickGUI.setNoEsc(binding);
    }
}

