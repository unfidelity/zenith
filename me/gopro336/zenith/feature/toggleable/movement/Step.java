//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.movement;

import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

@AnnotationHelper(name="Step", description="Instantly jumps on a block", category=Category.MOVEMENT)
public class Step
extends Feature {
    private final NumberProperty<Integer> stepHeight = new NumberProperty<Integer>((Feature)this, "Height", "", 1, Integer.valueOf(2), 2);
    private final NumberProperty<Integer> entityHeight = new NumberProperty<Integer>((Feature)this, "EntityHeight", "", 1, Integer.valueOf(1), 2);
    private final Property<Boolean> entityStep = new Property<Boolean>(this, "EntityStep", "", true);

    @Override
    public void onUpdate() {
        if (Step.mc.player == null) {
            return;
        }
        for (boolean bl : new boolean[]{Step.mc.player.collidedHorizontally, !Step.mc.player.isOnLadder(), !Step.mc.player.isInWater(), !Step.mc.player.isInLava(), Step.mc.player.movementInput.moveStrafe != 0.0f, Step.mc.player.movementInput.moveForward != 0.0f, !Step.mc.player.movementInput.jump}) {
        }
        if (!(Step.mc.player == null || !Step.mc.player.collidedHorizontally || Step.mc.player.isOnLadder() || Step.mc.player.isInWater() || Step.mc.player.isInLava() || Step.mc.player.movementInput.moveStrafe == 0.0f && Step.mc.player.movementInput.moveForward == 0.0f || !Step.mc.player.onGround || Step.mc.player.movementInput.jump)) {
            int stepBlocks = (Integer)this.stepHeight.getValue();
            Vec3d playerHead = Step.mc.player.getPositionVector().add(0.0, 2.0, 0.0);
            if (this.hullCollidesWithBlock((Entity)Step.mc.player, playerHead.add(0.0, 1.0, 0.0))) {
                return;
            }
            if (this.hullCollidesWithBlock((Entity)Step.mc.player, playerHead.add(0.0, 2.0, 0.0))) {
                stepBlocks = 1;
            }
            double startY = Step.mc.player.posY;
            if (stepBlocks == 1) {
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, startY + 0.42, Step.mc.player.posZ, true));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, startY + 0.75, Step.mc.player.posZ, true));
            } else {
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, startY + 0.4, Step.mc.player.posZ, true));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, startY + 0.75, Step.mc.player.posZ, true));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, startY + 0.5, Step.mc.player.posZ, true));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, startY + 0.41, Step.mc.player.posZ, true));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, startY + 0.83, Step.mc.player.posZ, true));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, startY + 1.16, Step.mc.player.posZ, true));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, startY + 1.41, Step.mc.player.posZ, true));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, startY + 1.57, Step.mc.player.posZ, true));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, startY + 1.58, Step.mc.player.posZ, true));
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, startY + 1.42, Step.mc.player.posZ, true));
            }
            Step.mc.player.setPosition(Step.mc.player.posX, startY + (double)stepBlocks, Step.mc.player.posZ);
        }
    }

    private boolean hullCollidesWithBlock(Entity entity, Vec3d nextPosition) {
        AxisAlignedBB boundingBox = entity.getEntityBoundingBox();
        Vec3d[] boundingBoxCorners = new Vec3d[]{new Vec3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ), new Vec3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ), new Vec3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ), new Vec3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ)};
        Vec3d entityPosition = entity.getPositionVector().add(0.0, 2.0, 0.0);
        for (Vec3d entityBoxCorner : boundingBoxCorners) {
            Vec3d nextBoxCorner = entityBoxCorner.subtract(entityPosition).add(nextPosition);
            RayTraceResult rayTraceResult = entity.world.rayTraceBlocks(entityBoxCorner, nextBoxCorner, true, false, true);
            if (rayTraceResult == null || rayTraceResult.typeOfHit != RayTraceResult.Type.BLOCK) continue;
            return true;
        }
        return false;
    }
}

