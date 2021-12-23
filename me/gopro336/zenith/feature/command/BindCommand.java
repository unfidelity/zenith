/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.command;

import me.gopro336.zenith.api.util.newUtil.ChatUtils;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.FeatureManager;
import me.gopro336.zenith.feature.command.Command;
import org.lwjgl.input.Keyboard;

public class BindCommand
extends Command {
    public BindCommand() {
        super("Bind", new String[]{"b", "bd"}, "Bind a module", "bind [module] {key}");
    }

    @Override
    public void call(String[] args2) {
        if (args2.length < 2) {
            ChatUtils.message("Please specify a module/key!");
            return;
        }
        Feature feature = FeatureManager.getModule(args2[0]);
        if (feature == null) {
            ChatUtils.message("Can't find module " + args2[0]);
            return;
        }
        feature.setKey(Keyboard.getKeyIndex((String)args2[1].toUpperCase()));
        ChatUtils.message("Set keybind of " + feature.getName() + " to " + feature.getKey());
    }
}

