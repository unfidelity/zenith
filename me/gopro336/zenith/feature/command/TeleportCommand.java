//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.command;

import me.gopro336.zenith.api.util.newUtil.ChatUtils;
import me.gopro336.zenith.feature.command.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class TeleportCommand
extends Command {
    public TeleportCommand() {
        super("Teleport", new String[]{"tp"}, "Teleport to where you want to go ", "tp [y], tp [x] [z], tp [x] [y] [z]");
    }

    @Override
    public void call(String[] args2) {
        if (args2.length == 0) {
            ChatUtils.message("Invalid arguments");
            return;
        }
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.player;
        if (args2.length == 1) {
            player.setPosition(player.posX, player.posY + Double.parseDouble(args2[0]), player.posZ);
        }
        if (args2.length == 2) {
            player.setPosition(player.posX + Double.parseDouble(args2[0]), player.posY, player.posZ + Double.parseDouble(args2[1]));
        }
        if (args2.length == 3) {
            player.setPosition(player.posX + Double.parseDouble(args2[0]), player.posY + Double.parseDouble(args2[1]), player.posZ + Double.parseDouble(args2[2]));
        }
    }
}

