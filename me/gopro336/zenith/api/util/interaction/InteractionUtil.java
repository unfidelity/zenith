//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.interaction;

import java.util.ArrayList;
import java.util.List;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.interaction.RotationManager;
import me.gopro336.zenith.asm.mixin.imixin.IMinecraft;
import me.gopro336.zenith.asm.mixin.mixins.accessor.IEntityPlayerSP;
import me.gopro336.zenith.event.client.BlockUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class InteractionUtil {
    private static Minecraft mc = Minecraft.getMinecraft();

    public static boolean canPlaceNormally() {
        return !Zenith.INSTANCE.rotationManager.isRotationsSet();
    }

    public static boolean canPlaceNormally(boolean rotate) {
        if (!rotate) {
            return true;
        }
        return !Zenith.INSTANCE.rotationManager.isRotationsSet();
    }

    public static Placement preparePlacement(BlockPos pos, boolean rotate) {
        return InteractionUtil.preparePlacement(pos, rotate, false);
    }

    public static Placement preparePlacement(BlockPos pos, boolean rotate, boolean instant) {
        return InteractionUtil.preparePlacement(pos, rotate, instant, false);
    }

    public static Placement preparePlacement(BlockPos pos, boolean rotate, boolean instant, boolean strictDirection) {
        return InteractionUtil.preparePlacement(pos, rotate, instant, strictDirection, false);
    }

    public static Placement preparePlacement(BlockPos pos, boolean rotate, boolean instant, boolean strictDirection, boolean rayTrace) {
        EnumFacing side = null;
        Vec3d hitVec = null;
        double dist = 69420.0;
        for (EnumFacing facing : InteractionUtil.getPlacableFacings(pos, strictDirection, rayTrace)) {
            BlockPos tempNeighbour = pos.offset(facing);
            Vec3d tempVec = new Vec3d((Vec3i)tempNeighbour).add(0.5, 0.5, 0.5).add(new Vec3d(facing.getDirectionVec()).scale(0.5));
            if (!(InteractionUtil.mc.player.getPositionVector().add(0.0, (double)InteractionUtil.mc.player.getEyeHeight(), 0.0).distanceTo(tempVec) < dist)) continue;
            side = facing;
            hitVec = tempVec;
        }
        if (side == null) {
            return null;
        }
        BlockPos neighbour = pos.offset(side);
        EnumFacing opposite = side.getOpposite();
        float[] angle = RotationManager.calculateAngle(InteractionUtil.mc.player.getPositionEyes(mc.getRenderPartialTicks()), hitVec);
        if (rotate) {
            if (instant) {
                InteractionUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(angle[0], angle[1], InteractionUtil.mc.player.onGround));
                ((IEntityPlayerSP)InteractionUtil.mc.player).setLastReportedYaw(angle[0]);
                ((IEntityPlayerSP)InteractionUtil.mc.player).setLastReportedPitch(angle[1]);
            } else {
                Zenith.INSTANCE.rotationManager.setRotations(angle[0], angle[1]);
            }
        }
        return new Placement(neighbour, opposite, hitVec, angle[0], angle[1]);
    }

    public static void placeBlockSafely(Placement placement, EnumHand hand, boolean packet) {
        boolean isSprinting = InteractionUtil.mc.player.isSprinting();
        boolean shouldSneak = BlockUtils.shouldSneakWhileRightClicking(placement.getNeighbour());
        if (isSprinting) {
            InteractionUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)InteractionUtil.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
        }
        if (shouldSneak) {
            InteractionUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)InteractionUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
        }
        InteractionUtil.placeBlock(placement, hand, packet);
        if (shouldSneak) {
            InteractionUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)InteractionUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        if (isSprinting) {
            InteractionUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)InteractionUtil.mc.player, CPacketEntityAction.Action.START_SPRINTING));
        }
    }

    public static void placeBlock(Placement placement, EnumHand hand, boolean packet) {
        InteractionUtil.rightClickBlock(placement.getNeighbour(), placement.getHitVec(), hand, placement.getOpposite(), packet, true);
    }

    public static void rightClickBlock(BlockPos pos, Vec3d vec, EnumHand hand, EnumFacing direction, boolean packet, boolean swing) {
        if (packet) {
            float dX = (float)(vec.x - (double)pos.getX());
            float dY = (float)(vec.y - (double)pos.getY());
            float dZ = (float)(vec.z - (double)pos.getZ());
            InteractionUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(pos, direction, hand, dX, dY, dZ));
        } else {
            InteractionUtil.mc.playerController.processRightClickBlock(InteractionUtil.mc.player, InteractionUtil.mc.world, pos, direction, vec, hand);
        }
        if (swing) {
            InteractionUtil.mc.player.connection.sendPacket((Packet)new CPacketAnimation(hand));
        }
        ((IMinecraft)mc).setRightClickDelayTimer(4);
    }

    public static boolean canPlaceBlock(BlockPos pos, boolean strictDirection) {
        return InteractionUtil.canPlaceBlock(pos, strictDirection, true);
    }

    public static boolean canRayTrace(BlockPos pos) {
        return InteractionUtil.mc.world.rayTraceBlocks(new Vec3d(InteractionUtil.mc.player.posX, InteractionUtil.mc.player.posY + (double)InteractionUtil.mc.player.getEyeHeight(), InteractionUtil.mc.player.posZ), new Vec3d((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()), false, true, false) == null;
    }

    public static boolean canPlaceBlock(BlockPos pos, boolean strictDirection, boolean checkEntities) {
        return InteractionUtil.canPlaceBlock(pos, strictDirection, false, checkEntities);
    }

    public static boolean canPlaceBlock(BlockPos pos, boolean strictDirection, boolean rayTrace, boolean checkEntities) {
        Block block = InteractionUtil.mc.world.getBlockState(pos).getBlock();
        if (!(block instanceof BlockAir || block instanceof BlockLiquid || block instanceof BlockTallGrass || block instanceof BlockFire || block instanceof BlockDeadBush || block instanceof BlockSnow)) {
            return false;
        }
        if (checkEntities) {
            for (Entity entity : InteractionUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(pos))) {
                if (entity instanceof EntityItem || entity instanceof EntityXPOrb) continue;
                return false;
            }
        }
        for (EnumFacing side : InteractionUtil.getPlacableFacings(pos, strictDirection, rayTrace)) {
            if (!InteractionUtil.canClick(pos.offset(side))) continue;
            return true;
        }
        return false;
    }

    public static boolean canClick(BlockPos pos) {
        return InteractionUtil.mc.world.getBlockState(pos).getBlock().canCollideCheck(InteractionUtil.mc.world.getBlockState(pos), false);
    }

    public static List<EnumFacing> getPlacableFacings(BlockPos pos, boolean strictDirection, boolean rayTrace) {
        ArrayList<EnumFacing> validFacings = new ArrayList<EnumFacing>();
        for (EnumFacing side : EnumFacing.values()) {
            if (rayTrace) {
                Vec3d testVec = new Vec3d((Vec3i)pos).add(0.5, 0.5, 0.5).add(new Vec3d(side.getDirectionVec()).scale(0.5));
                RayTraceResult result = InteractionUtil.mc.world.rayTraceBlocks(InteractionUtil.mc.player.getPositionEyes(1.0f), testVec);
                if (result != null && result.typeOfHit != RayTraceResult.Type.MISS) {
                    System.out.println("weary");
                    continue;
                }
            }
            BlockPos neighbour = pos.offset(side);
            if (strictDirection) {
                Vec3d eyePos = InteractionUtil.mc.player.getPositionEyes(1.0f);
                Vec3d blockCenter = new Vec3d((double)neighbour.getX() + 0.5, (double)neighbour.getY() + 0.5, (double)neighbour.getZ() + 0.5);
                IBlockState blockState = InteractionUtil.mc.world.getBlockState(neighbour);
                boolean isFullBox = blockState.getBlock() == Blocks.AIR || blockState.isFullBlock();
                ArrayList<EnumFacing> validAxis = new ArrayList<EnumFacing>();
                validAxis.addAll(InteractionUtil.checkAxis(eyePos.x - blockCenter.x, EnumFacing.WEST, EnumFacing.EAST, !isFullBox));
                validAxis.addAll(InteractionUtil.checkAxis(eyePos.y - blockCenter.y, EnumFacing.DOWN, EnumFacing.UP, true));
                validAxis.addAll(InteractionUtil.checkAxis(eyePos.z - blockCenter.z, EnumFacing.NORTH, EnumFacing.SOUTH, !isFullBox));
                if (!validAxis.contains(side.getOpposite())) continue;
            }
            IBlockState blockState = InteractionUtil.mc.world.getBlockState(neighbour);
            validFacings.add(side);
        }
        return validFacings;
    }

    public static ArrayList<EnumFacing> checkAxis(double diff, EnumFacing negativeSide, EnumFacing positiveSide, boolean bothIfInRange) {
        ArrayList<EnumFacing> valid = new ArrayList<EnumFacing>();
        if (diff < -0.5) {
            valid.add(negativeSide);
        }
        if (diff > 0.5) {
            valid.add(positiveSide);
        }
        if (bothIfInRange) {
            if (!valid.contains(negativeSide)) {
                valid.add(negativeSide);
            }
            if (!valid.contains(positiveSide)) {
                valid.add(positiveSide);
            }
        }
        return valid;
    }

    public static class Placement {
        private final BlockPos neighbour;
        private final EnumFacing opposite;
        private final Vec3d hitVec;
        private final float yaw;
        private final float pitch;

        public Placement(BlockPos neighbour, EnumFacing opposite, Vec3d hitVec, float yaw, float pitch) {
            this.neighbour = neighbour;
            this.opposite = opposite;
            this.hitVec = hitVec;
            this.yaw = yaw;
            this.pitch = pitch;
        }

        public BlockPos getNeighbour() {
            return this.neighbour;
        }

        public EnumFacing getOpposite() {
            return this.opposite;
        }

        public Vec3d getHitVec() {
            return this.hitVec;
        }

        public float getYaw() {
            return this.yaw;
        }

        public float getPitch() {
            return this.pitch;
        }
    }
}

