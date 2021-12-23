//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event;

import java.util.HashMap;
import java.util.Map;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.IGlobals;
import me.gopro336.zenith.event.entity.TotemPopEvent;
import me.gopro336.zenith.event.network.PacketReceiveEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.world.World;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

public class TotemPopListener
implements IGlobals {
    public final Map<String, Integer> popMap = new HashMap<String, Integer>();
    private final Zenith notorious = Zenith.INSTANCE;

    public TotemPopListener() {
        Zenith.INSTANCE.getEventManager().addEventListener(this);
    }

    @Listener
    public void onPacket(PacketReceiveEvent event) {
        Entity entity;
        SPacketEntityStatus packet;
        if (event.getPacket() instanceof SPacketEntityStatus && (packet = (SPacketEntityStatus)event.getPacket()).getOpCode() == 35 && (entity = packet.getEntity((World)TotemPopListener.mc.world)) instanceof EntityPlayer) {
            if (entity.equals((Object)TotemPopListener.mc.player)) {
                return;
            }
            EntityPlayer player = (EntityPlayer)entity;
            this.handlePop(player);
        }
    }

    public void handlePop(EntityPlayer player) {
        if (!this.popMap.containsKey(player.getName())) {
            Zenith.INSTANCE.getEventManager().dispatchEvent(new TotemPopEvent(player, 1));
            this.popMap.put(player.getName(), 1);
        } else {
            this.popMap.put(player.getName(), this.popMap.get(player.getName()) + 1);
            Zenith.INSTANCE.getEventManager().dispatchEvent(new TotemPopEvent(player, this.popMap.get(player.getName())));
        }
    }
}

