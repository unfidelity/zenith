/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.command;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.feature.command.Command;

public class SaveCommand
extends Command {
    public SaveCommand() {
        super("Save", new String[]{"safe"}, "Save the config", "save");
    }

    @Override
    public void call(String[] args2) {
        Zenith.save();
    }
}

