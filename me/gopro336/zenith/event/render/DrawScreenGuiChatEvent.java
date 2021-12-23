/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.render;

import me.gopro336.zenith.event.EventCancellable;
import net.minecraft.client.gui.GuiTextField;

public class DrawScreenGuiChatEvent
extends EventCancellable {
    private int mouseX;
    private int mouseY;
    private float partialTicks;
    private GuiTextField inputField;

    public DrawScreenGuiChatEvent(int mouseX, int mouseY, float partialTicks, GuiTextField inputField) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.partialTicks = partialTicks;
        this.inputField = inputField;
    }

    public int getMouseX() {
        return this.mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return this.mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }

    public void setPartialTicks(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public GuiTextField getInputField() {
        return this.inputField;
    }

    public void setInputField(GuiTextField inputField) {
        this.inputField = inputField;
    }
}

