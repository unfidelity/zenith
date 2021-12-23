//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.render;

import me.gopro336.zenith.event.network.PacketReceiveEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import net.minecraft.network.play.client.CPacketChatMessage;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="KillDisplay", description="displayes kill messages onscreen like dotgod", category=Category.RENDER)
public class KillDisplay
extends Feature {
    @Listener
    public void onChat(PacketReceiveEvent event) {
        if (!(event.getPacket() instanceof CPacketChatMessage) || ((CPacketChatMessage)event.getPacket()).getMessage().toLowerCase().contains(KillDisplay.mc.player.getName())) {
            // empty if block
        }
    }
}

