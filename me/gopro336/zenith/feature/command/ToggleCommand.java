/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.command;

import me.gopro336.zenith.api.util.newUtil.ChatUtils;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.FeatureManager;
import me.gopro336.zenith.feature.command.Command;

public class ToggleCommand
extends Command {
    public ToggleCommand() {
        super("Toggle", new String[]{"t", "tl"}, "Toggle a module", "toggle [module], toggle [module] on/off");
    }

    @Override
    public void call(String[] args2) {
        if (args2.length == 0) {
            ChatUtils.message("Please specify a module!");
            return;
        }
        Feature feature = FeatureManager.getModule(args2[0]);
        if (feature == null) {
            ChatUtils.message("Can't find module " + args2[0]);
            return;
        }
        if (args2.length >= 2) {
            if (args2[1].equalsIgnoreCase("on") || args2[1].equalsIgnoreCase("off")) {
                boolean value = args2[1].equalsIgnoreCase("on");
                feature.setEnabled(value);
            } else {
                ChatUtils.message("Invalid argument " + args2[1]);
            }
        } else {
            feature.toggle();
        }
        String state = feature.isEnabled() ? "\u00a7aenabled" : "\u00a7cdisabled";
        ChatUtils.message(feature.getName() + " has been " + state);
    }
}

