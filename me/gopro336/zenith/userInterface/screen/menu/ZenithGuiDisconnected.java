//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.userInterface.screen.menu;

import java.util.List;
import me.gopro336.zenith.api.util.time.Timer;
import me.gopro336.zenith.asm.mixin.mixins.accessor.IGuiDisconnected;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;

public class ZenithGuiDisconnected
extends GuiScreen {
    private final String reason;
    private final ITextComponent message;
    private List<String> multilineMessage;
    private final GuiScreen parentScreen;
    private int textHeight;
    private final ServerData lastServer;
    private final Timer timer;
    private final int delay;

    public ZenithGuiDisconnected(GuiDisconnected disconnected, ServerData lastServer, int delay) {
        this.parentScreen = ((IGuiDisconnected)disconnected).getParentScreen();
        this.reason = ((IGuiDisconnected)disconnected).getReason();
        this.message = ((IGuiDisconnected)disconnected).getMessage();
        this.lastServer = lastServer;
        this.timer = new Timer();
        this.delay = delay;
    }

    protected void keyTyped(char typedChar, int keyCode) {
    }

    public void initGui() {
        this.buttonList.clear();
        this.multilineMessage = this.fontRenderer.listFormattedStringToWidth(this.message.getFormattedText(), this.width - 50);
        this.textHeight = this.multilineMessage.size() * this.fontRenderer.FONT_HEIGHT;
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, Math.min(this.height / 2 + this.textHeight / 2 + this.fontRenderer.FONT_HEIGHT, this.height - 30), I18n.format((String)"gui.toMenu", (Object[])new Object[0])));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, (int)Math.min((double)this.height / 1.85 + (double)this.textHeight / 1.85 + (double)this.fontRenderer.FONT_HEIGHT, (double)(this.height + 80)), "Reconnect"));
    }

    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0: {
                this.mc.displayGuiScreen(this.parentScreen);
                break;
            }
            case 1: {
                this.connectToLastServer();
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, this.reason, this.width / 2, this.height / 2 - this.textHeight / 2 - this.fontRenderer.FONT_HEIGHT * 2, 0xAAAAAA);
        int i = this.height / 2 - this.textHeight / 2;
        if (this.multilineMessage != null) {
            for (String s : this.multilineMessage) {
                this.drawCenteredString(this.fontRenderer, s, this.width / 2, i, 0xFFFFFF);
                i += this.fontRenderer.FONT_HEIGHT;
            }
        }
        if (this.timer.hasPassed(this.delay * 1000)) {
            this.connectToLastServer();
        }
        float secondsLeft = (float)this.delay - (float)(System.currentTimeMillis() - this.timer.getTime()) / 1000.0f;
        ((GuiButton)this.buttonList.get((int)1)).displayString = "Reconnecting: " + Math.round(secondsLeft);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void connectToLastServer() {
        this.mc.displayGuiScreen((GuiScreen)new GuiConnecting(this.parentScreen, this.mc, this.lastServer == null ? this.mc.getCurrentServerData() : this.lastServer));
    }
}

