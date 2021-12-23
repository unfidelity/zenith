//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.userInterface.clickgui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import me.gopro336.zenith.api.util.newRender.BlurUtil;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.toggleable.Client.ClickGuiFeature;
import me.gopro336.zenith.userInterface.clickgui.Frame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;

public class ClickGUI
extends GuiScreen {
    public static ArrayList<Frame> frames = new ArrayList();
    private static boolean noEsc = false;

    public ClickGUI() {
        int xOffset = 3;
        for (Category category : Category.values()) {
            if (category == Category.HUD) continue;
            frames.add(new Frame(category, xOffset, 60, 110, 15));
            xOffset += 120;
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        frames.forEach(window -> window.onRender(mouseX, mouseY, partialTicks));
        this.renderTopBar();
    }

    public void renderTopBar() {
        BlurUtil.blurArea(0, -7, new ScaledResolution(this.mc).getScaledWidth(), 47, 8.0f, 1.0f, 3.0f);
        this.drawGradient(0.0, 0.0, new ScaledResolution(this.mc).getScaledWidth_double(), 40.0, new Color(190, 190, 175, 85).getRGB(), new Color(190, 190, 175, 85).getRGB());
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        for (Frame f : frames) {
            f.onMouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    protected void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        super.mouseReleased(mouseX, mouseY, mouseButton);
        frames.forEach(frame -> frame.onMouseReleased(mouseX, mouseY, mouseButton));
        for (Frame frame2 : ClickGUI.getFrames()) {
        }
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (!noEsc) {
            super.keyTyped(typedChar, keyCode);
        }
        frames.forEach(window -> window.onKeyTyped(typedChar, keyCode));
    }

    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        ClickGUI.getFrames().forEach(frame -> frame.onMouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick));
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void onGuiClosed() {
        frames.forEach(frame -> frame.setDragging(false));
        ClickGuiFeature.getInstance().disable();
    }

    @Deprecated
    public void drawGradient(double left, double top, double right, double bottom, int startColor, int endColor) {
        this.drawGradientRect((int)left, (int)top, (int)right, (int)bottom, startColor, endColor);
    }

    public void onUpdate() {
        frames.forEach(Frame::onUpdate);
    }

    private void doScroll() {
        block3: {
            int w;
            block2: {
                w = Mouse.getDWheel();
                if (w >= 0) break block2;
                for (Frame window : frames) {
                    window.setPosY(window.getPosY() - 8);
                }
                break block3;
            }
            if (w <= 0) break block3;
            for (Frame window : frames) {
                window.setPosY(window.getPosY() + 8);
            }
        }
    }

    public static ArrayList<Frame> getFrames() {
        return frames;
    }

    public static Frame getWindowByName(String in) {
        for (Frame w : ClickGUI.getFrames()) {
            if (!w.title.equalsIgnoreCase(in)) continue;
            return w;
        }
        return null;
    }

    public static boolean isNoEsc() {
        return noEsc;
    }

    public static void setNoEsc(boolean noEsc) {
        ClickGUI.noEsc = noEsc;
    }
}

