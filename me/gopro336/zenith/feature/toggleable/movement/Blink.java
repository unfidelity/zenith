//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.movement;

import com.mojang.authlib.GameProfile;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import me.gopro336.zenith.api.util.newUtil.ChatUtils;
import me.gopro336.zenith.event.network.PacketSendEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.Property;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketClientStatus;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.client.CPacketKeepAlive;
import net.minecraft.network.play.client.CPacketTabComplete;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="Blink", category=Category.MOVEMENT, description="Cancels all player packets")
public class Blink
extends Feature {
    public Property<Boolean> playerModel = new Property<Boolean>(this, "PlayerModel", "", true);
    Queue<Packet<?>> packets = new ConcurrentLinkedQueue();

    @Override
    public void onEnable() {
        if (this.nullCheck()) {
            return;
        }
        if (this.playerModel.getValue().booleanValue()) {
            Blink.createFakePlayer(null, true, true, true, true, 6640);
        }
        ChatUtils.message("Cancelling all player packets!");
    }

    public static void createFakePlayer(@Nullable String name, boolean copyInventory, boolean copyAngles, boolean health, boolean player, int entityID) {
        EntityOtherPlayerMP entity = player ? new EntityOtherPlayerMP((World)Blink.mc.world, mc.getSession().getProfile()) : new EntityOtherPlayerMP((World)Blink.mc.world, new GameProfile(UUID.fromString("70ee432d-0a96-4137-a2c0-37cc9df67f03"), name));
        entity.copyLocationAndAnglesFrom((Entity)Blink.mc.player);
        if (copyInventory) {
            entity.inventory.copyInventory(Blink.mc.player.inventory);
        }
        if (copyAngles) {
            entity.rotationYaw = Blink.mc.player.rotationYaw;
            entity.rotationYawHead = Blink.mc.player.rotationYawHead;
        }
        if (health) {
            entity.setHealth(Blink.mc.player.getHealth() + Blink.mc.player.getAbsorptionAmount());
        }
        Blink.mc.world.addEntityToWorld(entityID, (Entity)entity);
    }

    @Override
    public void onDisable() {
        if (this.nullCheck()) {
            return;
        }
        Blink.mc.world.removeEntityFromWorld(69420);
        for (Packet packet : this.packets) {
            Blink.mc.player.connection.sendPacket(packet);
        }
        this.packets.clear();
    }

    @Listener
    public void onPacketSend(PacketSendEvent event) {
        if (event.getPacket() instanceof CPacketChatMessage || event.getPacket() instanceof CPacketConfirmTeleport || event.getPacket() instanceof CPacketKeepAlive || event.getPacket() instanceof CPacketTabComplete || event.getPacket() instanceof CPacketClientStatus) {
            return;
        }
        this.packets.add(event.getPacket());
        event.setCanceled(true);
    }
}

