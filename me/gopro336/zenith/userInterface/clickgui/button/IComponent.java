//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.userInterface.clickgui.button;

import java.awt.Color;
import me.gopro336.zenith.api.util.color.ColorUtils;
import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.feature.toggleable.Client.ClickGuiFeature;
import me.gopro336.zenith.feature.toggleable.render.RainbowFeature;
import me.gopro336.zenith.userInterface.clickgui.Frame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public interface IComponent {
    public static final Minecraft mc = Minecraft.getMinecraft();

    default public void onRender(int mouseX, int mouseY, float partialTicks) {
    }

    default public void onMouseClicked(int mouseX, int mouseY, int mouseButton) {
    }

    default public void onKeyTyped(char character, int keyCode) {
    }

    default public void onMouseReleased(int mouseX, int mouseY, int mouseButton) {
    }

    default public void onMouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    }

    default public void onUpdate() {
    }

    default public void onMove() {
    }

    public void setExtended(boolean var1);

    public boolean isExtended();

    default public void drawDescription() {
        FontUtil.drawStringWithShadow(this.getDescription(), 2.0, new ScaledResolution(mc).getScaledHeight() - FontUtil.getFontHeight() - 2, new Color(-221985596, true).getRGB());
    }

    default public String getDescription() {
        return "";
    }

    default public int getComponentColor() {
        if (RainbowFeature.ClickguiRainbow.getValue().booleanValue()) {
            return ColorUtils.rainbow(Frame.counter1[0] * (Integer)RainbowFeature.rainbowhue.getValue(), ((Integer)RainbowFeature.Saturation.getValue()).intValue(), ((Integer)RainbowFeature.Brightness.getValue()).intValue()).getRGB();
        }
        return ClickGuiFeature.color.getValue().getRGB();
    }

    default public void playButtonSoundEffect() {
        Minecraft.getMinecraft().getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord((SoundEvent)SoundEvents.UI_BUTTON_CLICK, (float)1.0f));
    }
}

