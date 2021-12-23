//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.command;

import me.gopro336.zenith.feature.command.Command;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.tileentity.TileEntityShulkerBox;

public class PeekCommand
extends Command {
    public static TileEntityShulkerBox sb;

    public PeekCommand() {
        super("Peek", "See inside a shulkerbox", "peek");
    }

    @Override
    public void call(String[] args2) {
        if (PeekCommand.mc.player.getHeldItemMainhand().getItem() instanceof ItemShulkerBox) {
            // empty if block
        }
    }
}

