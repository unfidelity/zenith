//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.command;

import me.gopro336.zenith.feature.command.Command;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;

public class SoftLeaveCommand
extends Command {
    public SoftLeaveCommand() {
        super("softleave", "Leave without closing the connection", "softleave {unload}");
    }

    @Override
    public void call(String[] args2) {
        if (args2.length >= 1 && args2[0].equals("unload")) {
            mc.loadWorld(null);
        }
        SoftLeaveCommand.mc.currentScreen = new GuiMultiplayer((GuiScreen)new GuiMainMenu());
    }
}

