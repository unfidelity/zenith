/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.render;

import me.gopro336.zenith.event.EventCancellable;
import net.minecraft.client.gui.GuiScreen;

public class DisplayGuiScreenEvent
extends EventCancellable {
    private GuiScreen guiScreen;

    public DisplayGuiScreenEvent(GuiScreen guiScreen) {
        this.guiScreen = guiScreen;
    }

    public GuiScreen getGuiScreen() {
        return this.guiScreen;
    }

    public void setGuiScreen(GuiScreen guiScreen) {
        this.guiScreen = guiScreen;
    }
}

