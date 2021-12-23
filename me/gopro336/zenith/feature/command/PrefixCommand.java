/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.command;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.newUtil.ChatUtils;
import me.gopro336.zenith.feature.command.Command;

public class PrefixCommand
extends Command {
    public PrefixCommand() {
        super("Prefix", "Set the prefix", "prefix [prefix]");
    }

    @Override
    public void call(String[] args2) {
        if (args2.length == 0) {
            ChatUtils.message("Please specify what you would like as prefix!");
            return;
        }
        Zenith.commandManager.setPrefix(args2[0]);
        ChatUtils.message("Prefix has been changed to: " + args2[0]);
    }
}

