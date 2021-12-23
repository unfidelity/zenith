//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.movement;

import java.util.concurrent.atomic.AtomicBoolean;
import me.gopro336.zenith.api.util.player.PlayerUtil;
import me.gopro336.zenith.asm.mixin.imixin.ISPacketPlayerPosLook;
import me.gopro336.zenith.event.network.PacketReceiveEvent;
import me.gopro336.zenith.event.network.PacketSendEvent;
import me.gopro336.zenith.event.player.PlayerDismountEvent;
import me.gopro336.zenith.event.player.PlayerTravelEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.client.CPacketInput;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraft.network.play.server.SPacketMoveVehicle;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="BoatFly", category=Category.MOVEMENT, description="")
public class BoatFly
extends Feature {
    public Property<Boolean> fixYaw = new Property<Boolean>(this, "FixYaw", "", true);
    public Property<Boolean> antiKick = new Property<Boolean>(this, "AntiKick", "", true);
    public Property<Boolean> confirm = new Property<Boolean>(this, "Confirm", "", false);
    public Property<Boolean> bypass = new Property<Boolean>(this, "Bypass", "", true);
    public Property<Boolean> semi = new Property<Boolean>(this, "Semi", "", true);
    public Property<Boolean> constrict = new Property<Boolean>(this, "Constrict", "", false);
    public NumberProperty<Float> speed = new NumberProperty<Float>((Feature)this, "Speed", "", Float.valueOf(0.1f), Float.valueOf(1.0f), Float.valueOf(50.0f));
    public NumberProperty<Float> vSpeed = new NumberProperty<Float>((Feature)this, "VSpeed", "", Float.valueOf(0.0f), Float.valueOf(0.5f), Float.valueOf(10.0f));
    public NumberProperty<Integer> safetyFactor = new NumberProperty<Integer>((Feature)this, "SafetyFactor", "", 0, Integer.valueOf(2), 10);
    public NumberProperty<Integer> maxSetbacks = new NumberProperty<Integer>((Feature)this, "maxSetbacks", "", 0, Integer.valueOf(10), 20);
    public int Field1241;
    public Vec3d vec = null;
    public int Field1243;
    public AtomicBoolean Field1244 = new AtomicBoolean(false);
    public int Field1245 = 0;

    /*
     * Unable to fully structure code
     */
    @Listener
    public void onPacket(PacketReceiveEvent packetEvent) {
        block10: {
            block11: {
                block14: {
                    block13: {
                        block12: {
                            if (BoatFly.mc.player == null || BoatFly.mc.world == null) {
                                this.toggle();
                                return;
                            }
                            if (!(packetEvent.getPacket() instanceof SPacketPlayerPosLook) || !BoatFly.mc.player.isRiding()) break block10;
                            sPacketPlayerPosLook = (SPacketPlayerPosLook)packetEvent.getPacket();
                            ((ISPacketPlayerPosLook)sPacketPlayerPosLook).setYaw(BoatFly.mc.player.rotationYaw);
                            ((ISPacketPlayerPosLook)sPacketPlayerPosLook).setPitch(BoatFly.mc.player.rotationPitch);
                            sPacketPlayerPosLook.getFlags().remove(SPacketPlayerPosLook.EnumFlags.X_ROT);
                            sPacketPlayerPosLook.getFlags().remove(SPacketPlayerPosLook.EnumFlags.Y_ROT);
                            this.Field1241 = sPacketPlayerPosLook.getTeleportId();
                            if ((Integer)this.maxSetbacks.getValue() <= 0) break block11;
                            if (this.vec != null) break block12;
                            this.vec = new Vec3d(sPacketPlayerPosLook.getX(), sPacketPlayerPosLook.getY(), sPacketPlayerPosLook.getZ());
                            this.Field1243 = 1;
                            break block11;
                        }
                        if (!PlayerUtil.isPressingMoveBinds()) break block13;
                        v0 = new Vec3d(sPacketPlayerPosLook.getX(), sPacketPlayerPosLook.getY(), sPacketPlayerPosLook.getZ());
                        if (!(this.Method1221(this.vec, v0) < (double)((Float)this.speed.getValue()).floatValue() * 0.8)) break block13;
                        this.vec = new Vec3d(sPacketPlayerPosLook.getX(), sPacketPlayerPosLook.getY(), sPacketPlayerPosLook.getZ());
                        ++this.Field1243;
                        break block11;
                    }
                    if (!BoatFly.mc.gameSettings.keyBindJump.isKeyDown() && !BoatFly.mc.gameSettings.keyBindSneak.isKeyDown()) break block14;
                    v1 = new Vec3d(sPacketPlayerPosLook.getX(), sPacketPlayerPosLook.getY(), sPacketPlayerPosLook.getZ());
                    if (!(this.Method1223(this.vec, v1) < (double)((Float)this.vSpeed.getValue()).floatValue() * 0.5)) break block14;
                    this.vec = new Vec3d(sPacketPlayerPosLook.getX(), sPacketPlayerPosLook.getY(), sPacketPlayerPosLook.getZ());
                    ++this.Field1243;
                    break block11;
                }
                if (BoatFly.mc.gameSettings.keyBindJump.isKeyDown() || BoatFly.mc.gameSettings.keyBindSneak.isKeyDown()) ** GOTO lbl-1000
                if (this.Method1223(this.vec, new Vec3d(sPacketPlayerPosLook.getX(), sPacketPlayerPosLook.getY(), sPacketPlayerPosLook.getZ())) < 0.02) ** GOTO lbl-1000
                v2 = new Vec3d(sPacketPlayerPosLook.getX(), sPacketPlayerPosLook.getY(), sPacketPlayerPosLook.getZ());
                if (this.Method1223(this.vec, v2) > 1.0) lbl-1000:
                // 2 sources

                {
                    this.vec = new Vec3d(sPacketPlayerPosLook.getX(), sPacketPlayerPosLook.getY(), sPacketPlayerPosLook.getZ());
                    ++this.Field1243;
                } else lbl-1000:
                // 2 sources

                {
                    this.vec = new Vec3d(sPacketPlayerPosLook.getX(), sPacketPlayerPosLook.getY(), sPacketPlayerPosLook.getZ());
                    this.Field1243 = 1;
                }
            }
            if ((Integer)this.maxSetbacks.getValue() > 0 && this.Field1243 > (Integer)this.maxSetbacks.getValue()) {
                return;
            }
            if (BoatFly.mc.player.isEntityAlive() && BoatFly.mc.world.isBlockLoaded(new BlockPos(BoatFly.mc.player.posX, BoatFly.mc.player.posY, BoatFly.mc.player.posZ)) && !(BoatFly.mc.currentScreen instanceof GuiDownloadTerrain)) {
                if (this.Field1241 <= 0) {
                    this.Field1241 = sPacketPlayerPosLook.getTeleportId();
                    return;
                }
                if (!this.confirm.getValue().booleanValue()) {
                    BoatFly.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(sPacketPlayerPosLook.getTeleportId()));
                }
                packetEvent.setCanceled(true);
            }
        }
        if (packetEvent.getPacket() instanceof SPacketMoveVehicle && BoatFly.mc.player.isRiding()) {
            if (this.semi.getValue().booleanValue()) {
                this.Field1244.set(true);
            } else {
                packetEvent.setCanceled(true);
            }
        }
    }

    public double Method1221(Vec3d vec3d, Vec3d vec3d2) {
        double d = vec3d.x - vec3d2.x;
        double d2 = vec3d.z - vec3d2.z;
        return MathHelper.sqrt((double)(d * d + d2 * d2));
    }

    @Listener
    public void onSendPacket(PacketSendEvent sendPacketEvent) {
        if (BoatFly.mc.player == null || BoatFly.mc.world == null) {
            this.toggle();
            return;
        }
        if (!this.bypass.getValue().booleanValue()) {
            return;
        }
        if (sendPacketEvent.getPacket() instanceof CPacketVehicleMove) {
            if (BoatFly.mc.player.isRiding() && BoatFly.mc.player.ticksExisted % 2 == 0) {
                BoatFly.mc.playerController.interactWithEntity((EntityPlayer)BoatFly.mc.player, BoatFly.mc.player.getRidingEntity(), this.constrict.getValue() != false ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
            }
        } else if (sendPacketEvent.getPacket() instanceof CPacketPlayer.Rotation && BoatFly.mc.player.isRiding()) {
            sendPacketEvent.setCanceled(true);
        } else if (sendPacketEvent.getPacket() instanceof CPacketInput && (!this.semi.getValue().booleanValue() || BoatFly.mc.player.ticksExisted % 2 == 0)) {
            sendPacketEvent.setCanceled(true);
        }
    }

    @Listener
    public void Method1222(PlayerDismountEvent dismountEvent) {
        if (!BoatFly.mc.gameSettings.keyBindSneak.isKeyDown()) {
            return;
        }
        dismountEvent.setCanceled(true);
    }

    @Override
    public void onEnable() {
        this.Field1243 = 0;
        this.vec = null;
        this.Field1241 = 0;
        if (BoatFly.mc.player == null || BoatFly.mc.world == null) {
            this.toggle();
        }
    }

    public double Method1223(Vec3d vec3d, Vec3d vec3d2) {
        double d = vec3d.y - vec3d2.y;
        return MathHelper.sqrt((double)(d * d));
    }

    @Listener
    public void onPlayerTravel(PlayerTravelEvent playerTravelEvent) {
        if (BoatFly.mc.player == null || BoatFly.mc.world == null) {
            this.toggle();
            return;
        }
        if (BoatFly.mc.player.getRidingEntity() instanceof EntityBoat) {
            EntityBoat entityBoat = (EntityBoat)BoatFly.mc.player.getRidingEntity();
            double d = 0.0;
            double d2 = 0.0;
            double d3 = 0.0;
            if (PlayerUtil.isPressingMoveBinds()) {
                double[] dArray = BoatFly.maths(((Float)this.speed.getValue()).floatValue());
                d = dArray[0];
                d3 = dArray[1];
            } else {
                d = 0.0;
                d3 = 0.0;
            }
            if (BoatFly.mc.gameSettings.keyBindJump.isKeyDown()) {
                d2 = ((Float)this.vSpeed.getValue()).floatValue();
                if (this.antiKick.getValue().booleanValue() && BoatFly.mc.player.ticksExisted % 20 == 0) {
                    d2 = -0.04;
                }
            } else if (BoatFly.mc.gameSettings.keyBindSneak.isKeyDown()) {
                d2 = -((Float)this.vSpeed.getValue()).floatValue();
            } else if (this.antiKick.getValue().booleanValue() && BoatFly.mc.player.ticksExisted % 4 == 0) {
                d2 = -0.04;
            }
            if (this.fixYaw.getValue().booleanValue()) {
                entityBoat.rotationYaw = BoatFly.mc.player.rotationYaw;
            }
            if ((Integer)this.safetyFactor.getValue() > 0 && !BoatFly.mc.world.isBlockLoaded(new BlockPos(entityBoat.posX + d * (double)((Integer)this.safetyFactor.getValue()).intValue(), entityBoat.posY + d2 * (double)((Integer)this.safetyFactor.getValue()).intValue(), entityBoat.posZ + d3 * (double)((Integer)this.safetyFactor.getValue()).intValue()), false)) {
                d = 0.0;
                d3 = 0.0;
            }
            if (!this.semi.getValue().booleanValue() || BoatFly.mc.player.ticksExisted % 2 != 0) {
                if (this.Field1244.get() && this.semi.getValue().booleanValue()) {
                    entityBoat.setVelocity(0.0, 0.0, 0.0);
                    this.Field1244.set(false);
                } else {
                    entityBoat.setVelocity(d, d2, d3);
                }
            }
            if (this.confirm.getValue().booleanValue()) {
                ++this.Field1241;
                BoatFly.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(this.Field1241));
            }
            playerTravelEvent.setCanceled(true);
        }
    }

    public static double[] maths(double d) {
        float f = PlayerUtil.mc.player.movementInput.moveForward;
        float f2 = PlayerUtil.mc.player.movementInput.moveStrafe;
        float f3 = PlayerUtil.mc.player.prevRotationYaw + (PlayerUtil.mc.player.rotationYaw - PlayerUtil.mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
        if (f != 0.0f) {
            if (f2 > 0.0f) {
                f3 += (float)(f > 0.0f ? -45 : 45);
            } else if (f2 < 0.0f) {
                f3 += (float)(f > 0.0f ? 45 : -45);
            }
            f2 = 0.0f;
            if (f > 0.0f) {
                f = 1.0f;
            } else if (f < 0.0f) {
                f = -1.0f;
            }
        }
        double d2 = Math.sin(Math.toRadians(f3 + 90.0f));
        double d3 = Math.cos(Math.toRadians(f3 + 90.0f));
        double d4 = (double)f * d * d3 + (double)f2 * d * d2;
        double d5 = (double)f * d * d2 - (double)f2 * d * d3;
        return new double[]{d4, d5};
    }
}

