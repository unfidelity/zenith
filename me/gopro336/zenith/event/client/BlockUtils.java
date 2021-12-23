//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import me.gopro336.zenith.api.util.client.PlayerUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockCake;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockRedstoneDiode;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class BlockUtils {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static void rightClickBlock(BlockPos pos, Vec3d vec, EnumHand hand, EnumFacing direction, boolean packet) {
        if (packet) {
            float f = (float)(vec.x - (double)pos.getX());
            float f1 = (float)(vec.y - (double)pos.getY());
            float f2 = (float)(vec.z - (double)pos.getZ());
            BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(pos, direction, hand, f, f1, f2));
            BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketAnimation(hand));
        } else {
            BlockUtils.mc.playerController.processRightClickBlock(BlockUtils.mc.player, BlockUtils.mc.world, pos, direction, vec, hand);
            BlockUtils.mc.player.swingArm(hand);
        }
    }

    public static Vec3d blockPosToVec(BlockPos pos) {
        return new Vec3d((Vec3i)pos);
    }

    public static BlockPos vecToBlockPos(Vec3d vec) {
        return new BlockPos(vec);
    }

    public static List<BlockPos> getBlocksInSphere(BlockPos center, int radius) {
        ArrayList<BlockPos> blocksInSphere = new ArrayList<BlockPos>();
        int centerX = center.getX();
        int centerY = center.getY();
        int centerZ = center.getZ();
        for (int x = centerX - radius; x <= centerX + radius; ++x) {
            for (int z = centerZ - radius; z <= centerZ + radius; ++z) {
                for (int y = centerY - radius; y < centerY + radius; ++y) {
                    double dist = (centerX - x) * (centerX - x) + (centerZ - z) * (centerZ - z) + (centerY - y) * (centerY - y);
                    if (!(dist < (double)(radius * radius))) continue;
                    blocksInSphere.add(new BlockPos(x, y, z));
                }
            }
        }
        return blocksInSphere;
    }

    public static boolean hasNeighbours(BlockPos blockPos) {
        for (EnumFacing facing : EnumFacing.values()) {
            BlockPos neighbour = blockPos.offset(facing);
            if (BlockUtils.mc.world.getBlockState(neighbour).getMaterial().isReplaceable()) continue;
            return true;
        }
        return false;
    }

    public static Optional<ClickLocation> generateClickLocation(BlockPos pos) {
        return BlockUtils.generateClickLocation(pos, false, false);
    }

    public static Optional<ClickLocation> generateClickLocation(BlockPos pos, boolean ignoreEntities) {
        return BlockUtils.generateClickLocation(pos, ignoreEntities, false);
    }

    public static Optional<ClickLocation> generateClickLocation(BlockPos pos, boolean ignoreEntities, boolean noPistons) {
        return BlockUtils.generateClickLocation(pos, ignoreEntities, false, false);
    }

    public static Optional<ClickLocation> generateClickLocation(BlockPos pos, boolean ignoreEntities, boolean noPistons, boolean onlyCrystals) {
        Block block = BlockUtils.mc.world.getBlockState(pos).getBlock();
        if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid)) {
            return Optional.empty();
        }
        if (!ignoreEntities) {
            for (Entity entity : BlockUtils.mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(pos))) {
                if (onlyCrystals && entity instanceof EntityEnderCrystal || entity instanceof EntityItem || entity instanceof EntityXPOrb || entity instanceof EntityArrow) continue;
                return Optional.empty();
            }
        }
        EnumFacing side = null;
        for (EnumFacing blockSide : EnumFacing.values()) {
            IBlockState blockState;
            BlockPos sidePos = pos.offset(blockSide);
            if (noPistons && BlockUtils.mc.world.getBlockState(sidePos).getBlock() == Blocks.PISTON || !BlockUtils.mc.world.getBlockState(sidePos).getBlock().canCollideCheck(BlockUtils.mc.world.getBlockState(sidePos), false) || (blockState = BlockUtils.mc.world.getBlockState(sidePos)).getMaterial().isReplaceable()) continue;
            side = blockSide;
            break;
        }
        if (side == null) {
            return Optional.empty();
        }
        BlockPos neighbour = pos.offset(side);
        EnumFacing opposite = side.getOpposite();
        if (!BlockUtils.mc.world.getBlockState(neighbour).getBlock().canCollideCheck(BlockUtils.mc.world.getBlockState(neighbour), false)) {
            return Optional.empty();
        }
        return Optional.of(new ClickLocation(neighbour, opposite));
    }

    public static double[] calculateLookAt(BlockPos pos, EnumFacing facing, EntityPlayer me) {
        return PlayerUtils.calculateLookAt((double)pos.getX() + 0.5 + (double)facing.getDirectionVec().getX() * 0.5, (double)pos.getY() + 0.5 + (double)facing.getDirectionVec().getY() * 0.5, (double)pos.getZ() + 0.5 + (double)facing.getDirectionVec().getZ() * 0.5, me);
    }

    public static double[] calculateLookAt(double x, double y, double z, EnumFacing facing, EntityPlayer me) {
        return PlayerUtils.calculateLookAt(x + 0.5 + (double)facing.getDirectionVec().getX() * 0.5, y + 0.5 + (double)facing.getDirectionVec().getY() * 0.5, z + 0.5 + (double)facing.getDirectionVec().getZ() * 0.5, me);
    }

    public static boolean shouldSneakWhileRightClicking(BlockPos blockPos) {
        Block block = BlockUtils.mc.world.getBlockState(blockPos).getBlock();
        TileEntity tileEntity = null;
        for (TileEntity tE : BlockUtils.mc.world.loadedTileEntityList) {
            if (!tE.getPos().equals((Object)blockPos)) continue;
            tileEntity = tE;
            break;
        }
        return tileEntity != null || block instanceof BlockBed || block instanceof BlockContainer || block instanceof BlockDoor || block instanceof BlockTrapDoor || block instanceof BlockFenceGate || block instanceof BlockButton || block instanceof BlockAnvil || block instanceof BlockWorkbench || block instanceof BlockCake || block instanceof BlockRedstoneDiode;
    }

    public static boolean validObi(BlockPos pos) {
        return !(BlockUtils.validBedrock(pos) || BlockUtils.mc.world.getBlockState(pos.add(0, -1, 0)).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.add(0, -1, 0)).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos.add(1, 0, 0)).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos.add(-1, 0, 0)).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos.add(0, 0, 1)).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos.add(0, 0, -1)).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos).getMaterial() != Material.AIR || BlockUtils.mc.world.getBlockState(pos.add(0, 1, 0)).getMaterial() != Material.AIR || BlockUtils.mc.world.getBlockState(pos.add(0, 2, 0)).getMaterial() != Material.AIR);
    }

    public static boolean validBedrock(BlockPos pos) {
        return BlockUtils.mc.world.getBlockState(pos.add(0, -1, 0)).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos.add(1, 0, 0)).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos.add(-1, 0, 0)).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos.add(0, 0, 1)).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos.add(0, 0, -1)).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos).getMaterial() == Material.AIR && BlockUtils.mc.world.getBlockState(pos.add(0, 1, 0)).getMaterial() == Material.AIR && BlockUtils.mc.world.getBlockState(pos.add(0, 2, 0)).getMaterial() == Material.AIR;
    }

    public static BlockPos validTwoBlockObiXZ(BlockPos pos) {
        if (!(BlockUtils.mc.world.getBlockState(pos.down()).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.down()).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos.west()).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.west()).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos.south()).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.south()).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos.north()).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.north()).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos).getMaterial() != Material.AIR || BlockUtils.mc.world.getBlockState(pos.up()).getMaterial() != Material.AIR || BlockUtils.mc.world.getBlockState(pos.up(2)).getMaterial() != Material.AIR || BlockUtils.mc.world.getBlockState(pos.east().down()).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.east().down()).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos.east(2)).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.east(2)).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos.east().south()).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.east().south()).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos.east().north()).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.east().north()).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos.east()).getMaterial() != Material.AIR || BlockUtils.mc.world.getBlockState(pos.east().up()).getMaterial() != Material.AIR || BlockUtils.mc.world.getBlockState(pos.east().up(2)).getMaterial() != Material.AIR)) {
            return BlockUtils.validTwoBlockBedrockXZ(pos) == null ? new BlockPos(1, 0, 0) : null;
        }
        if (!(BlockUtils.mc.world.getBlockState(pos.down()).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.down()).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos.west()).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.west()).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos.east()).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.east()).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos.north()).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.north()).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos).getMaterial() != Material.AIR || BlockUtils.mc.world.getBlockState(pos.up()).getMaterial() != Material.AIR || BlockUtils.mc.world.getBlockState(pos.up(2)).getMaterial() != Material.AIR || BlockUtils.mc.world.getBlockState(pos.south().down()).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.south().down()).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos.south(2)).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.south(2)).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos.south().east()).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.south().east()).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos.south().west()).getBlock() != Blocks.OBSIDIAN && BlockUtils.mc.world.getBlockState(pos.south().west()).getBlock() != Blocks.BEDROCK || BlockUtils.mc.world.getBlockState(pos.south()).getMaterial() != Material.AIR || BlockUtils.mc.world.getBlockState(pos.south().up()).getMaterial() != Material.AIR || BlockUtils.mc.world.getBlockState(pos.south().up(2)).getMaterial() != Material.AIR)) {
            return BlockUtils.validTwoBlockBedrockXZ(pos) == null ? new BlockPos(0, 0, 1) : null;
        }
        return null;
    }

    public static BlockPos validTwoBlockBedrockXZ(BlockPos pos) {
        if (BlockUtils.mc.world.getBlockState(pos.down()).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos.west()).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos.south()).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos.north()).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos).getMaterial() == Material.AIR && BlockUtils.mc.world.getBlockState(pos.up()).getMaterial() == Material.AIR && BlockUtils.mc.world.getBlockState(pos.up(2)).getMaterial() == Material.AIR && BlockUtils.mc.world.getBlockState(pos.east().down()).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos.east(2)).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos.east().south()).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos.east().north()).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos.east()).getMaterial() == Material.AIR && BlockUtils.mc.world.getBlockState(pos.east().up()).getMaterial() == Material.AIR && BlockUtils.mc.world.getBlockState(pos.east().up(2)).getMaterial() == Material.AIR) {
            return new BlockPos(1, 0, 0);
        }
        if (BlockUtils.mc.world.getBlockState(pos.down()).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos.west()).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos.east()).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos.north()).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos).getMaterial() == Material.AIR && BlockUtils.mc.world.getBlockState(pos.up()).getMaterial() == Material.AIR && BlockUtils.mc.world.getBlockState(pos.up(2)).getMaterial() == Material.AIR && BlockUtils.mc.world.getBlockState(pos.south().down()).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos.south(2)).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos.south().east()).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos.south().west()).getBlock() == Blocks.BEDROCK && BlockUtils.mc.world.getBlockState(pos.south()).getMaterial() == Material.AIR && BlockUtils.mc.world.getBlockState(pos.south().up()).getMaterial() == Material.AIR && BlockUtils.mc.world.getBlockState(pos.south().up(2)).getMaterial() == Material.AIR) {
            return new BlockPos(0, 0, 1);
        }
        return null;
    }

    public static boolean isHole(BlockPos pos) {
        return BlockUtils.validObi(pos) || BlockUtils.validBedrock(pos);
    }

    public static class ClickLocation {
        public final BlockPos neighbour;
        public final EnumFacing opposite;

        public ClickLocation(BlockPos neighbour, EnumFacing opposite) {
            this.neighbour = neighbour;
            this.opposite = opposite;
        }
    }
}

