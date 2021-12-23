//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.misc;

import me.gopro336.zenith.event.EventStageable;
import me.gopro336.zenith.event.network.PacketReceiveEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.util.EnumHand;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="AutoFish", description="Automatically fishes using your fishing rod", category=Category.MISC)
public class AutoFish
extends Feature {
    @Listener
    private void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacket() instanceof SPacketEntityVelocity && event.getStage() == EventStageable.EventStage.PRE) {
            Entity entity;
            SPacketEntityVelocity packet = (SPacketEntityVelocity)event.getPacket();
            if (AutoFish.mc.player.getHeldItemMainhand().getItem() instanceof ItemFishingRod && (entity = AutoFish.mc.world.getEntityByID(packet.getEntityID())) instanceof EntityFishHook && ((EntityFishHook)entity).getAngler() == AutoFish.mc.player && entity.motionX == 0.0 && entity.motionY <= -0.02 && entity.motionZ == 0.0) {
                AutoFish.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                AutoFish.mc.player.swingArm(EnumHand.MAIN_HAND);
                AutoFish.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                AutoFish.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
        }
    }
}

