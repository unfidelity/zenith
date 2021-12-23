//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.newRotations;

import me.gopro336.zenith.asm.mixin.mixins.accessor.IEntityPlayerSP;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;

public class RotationUtil {
    public static Minecraft mc = Minecraft.getMinecraft();

    public static void update(float yaw, float pitch) {
        boolean flag1;
        boolean flag = RotationUtil.mc.player.isSprinting();
        if (flag != ((IEntityPlayerSP)RotationUtil.mc.player).getServerSprintState()) {
            if (flag) {
                RotationUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)RotationUtil.mc.player, CPacketEntityAction.Action.START_SPRINTING));
            } else {
                RotationUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)RotationUtil.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
            }
            ((IEntityPlayerSP)RotationUtil.mc.player).setServerSprintState(flag);
        }
        if ((flag1 = RotationUtil.mc.player.isSneaking()) != ((IEntityPlayerSP)RotationUtil.mc.player).getServerSneakState()) {
            if (flag1) {
                RotationUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)RotationUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            } else {
                RotationUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)RotationUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            }
            ((IEntityPlayerSP)RotationUtil.mc.player).setServerSneakState(flag1);
        }
        if (RotationUtil.mc.player == mc.getRenderViewEntity()) {
            boolean rotationChanged;
            AxisAlignedBB axisalignedbb = RotationUtil.mc.player.getEntityBoundingBox();
            double dX = RotationUtil.mc.player.posX - ((IEntityPlayerSP)RotationUtil.mc.player).getLastReportedPosX();
            double dY = axisalignedbb.minY - ((IEntityPlayerSP)RotationUtil.mc.player).getLastReportedPosY();
            double dZ = RotationUtil.mc.player.posZ - ((IEntityPlayerSP)RotationUtil.mc.player).getLastReportedPosZ();
            double dYaw = yaw - ((IEntityPlayerSP)RotationUtil.mc.player).getLastReportedYaw();
            double dPitch = pitch - ((IEntityPlayerSP)RotationUtil.mc.player).getLastReportedPitch();
            ((IEntityPlayerSP)RotationUtil.mc.player).setPositionUpdateTicks(((IEntityPlayerSP)RotationUtil.mc.player).getPositionUpdateTicks() + 1);
            boolean positionChanged = dX * dX + dY * dY + dZ * dZ > 9.0E-4 || ((IEntityPlayerSP)RotationUtil.mc.player).getPositionUpdateTicks() >= 20;
            boolean bl = rotationChanged = dYaw != 0.0 || dPitch != 0.0;
            if (RotationUtil.mc.player.isRiding()) {
                RotationUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(RotationUtil.mc.player.motionX, -999.0, RotationUtil.mc.player.motionZ, yaw, pitch, RotationUtil.mc.player.onGround));
                positionChanged = false;
            } else if (positionChanged && rotationChanged) {
                RotationUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(RotationUtil.mc.player.posX, axisalignedbb.minY, RotationUtil.mc.player.posZ, yaw, pitch, RotationUtil.mc.player.onGround));
            } else if (positionChanged) {
                RotationUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(RotationUtil.mc.player.posX, axisalignedbb.minY, RotationUtil.mc.player.posZ, RotationUtil.mc.player.onGround));
            } else if (rotationChanged) {
                RotationUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(yaw, pitch, RotationUtil.mc.player.onGround));
            } else if (((IEntityPlayerSP)RotationUtil.mc.player).getPrevOnGround() != RotationUtil.mc.player.onGround) {
                RotationUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer(RotationUtil.mc.player.onGround));
            }
            if (positionChanged) {
                ((IEntityPlayerSP)RotationUtil.mc.player).setLastReportedPosX(RotationUtil.mc.player.posX);
                ((IEntityPlayerSP)RotationUtil.mc.player).setLastReportedPosY(axisalignedbb.minY);
                ((IEntityPlayerSP)RotationUtil.mc.player).setLastReportedPosZ(RotationUtil.mc.player.posZ);
                ((IEntityPlayerSP)RotationUtil.mc.player).setPositionUpdateTicks(0);
            }
            if (rotationChanged) {
                ((IEntityPlayerSP)RotationUtil.mc.player).setLastReportedYaw(yaw);
                ((IEntityPlayerSP)RotationUtil.mc.player).setLastReportedPitch(pitch);
            }
            ((IEntityPlayerSP)RotationUtil.mc.player).setPrevOnGround(RotationUtil.mc.player.onGround);
            ((IEntityPlayerSP)RotationUtil.mc.player).setAutoJumpEnabled(RotationUtil.mc.gameSettings.autoJump);
        }
    }
}

