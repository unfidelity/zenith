//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.Combat;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import me.gopro336.zenith.api.util.kotlin.SurroundUtils;
import me.gopro336.zenith.api.util.newRender.RenderUtils3D;
import me.gopro336.zenith.api.util.newUtil.ChatUtils;
import me.gopro336.zenith.event.client.BlockUtils;
import me.gopro336.zenith.event.network.PacketReceiveEvent;
import me.gopro336.zenith.event.player.MoveEvent;
import me.gopro336.zenith.event.render.Render3DEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="HoleSnap", description="gravitates you twords holes", category=Category.MOVEMENT)
public class HoleSnap
extends Feature {
    public NumberProperty<Integer> blockRange = new NumberProperty<Integer>((Feature)this, "BlockRange", "", 1, Integer.valueOf(3), 6);
    private Vec3d Center = Vec3d.ZERO;
    private BlockPos target;

    @Override
    public void onEnable() {
        HoleSnap.mc.player.motionX = 0.0;
        HoleSnap.mc.player.motionZ = 0.0;
    }

    @Listener
    public void onPacket(PacketReceiveEvent event) {
        if (event.getPacket() instanceof SPacketPlayerPosLook) {
            this.toggle();
        }
    }

    @Listener
    public void onWorldRender(Render3DEvent event) {
        if (this.target == null) {
            return;
        }
        RenderUtils3D.draw(this.target, true, true, 1.0, 3.0, new Color(255, 255, 255, 255));
    }

    @Listener
    public void moveEvent(MoveEvent event) {
        if (!HoleSnap.mc.player.isEntityAlive()) {
            return;
        }
        if (SurroundUtils.INSTANCE.checkHole((Entity)HoleSnap.mc.player).equals((Object)SurroundUtils.HoleType.NONE)) {
            this.toggle();
        }
        this.target = this.nearestHole();
        if (this.target != null) {
            this.Center = this.GetCenter(this.target.getX(), this.target.getY(), this.target.getZ());
            double l_MotionX = this.Center.x - HoleSnap.mc.player.posX;
            double l_MotionZ = this.Center.z - HoleSnap.mc.player.posZ;
            HoleSnap.mc.player.motionX = l_MotionX / 2.0;
            HoleSnap.mc.player.motionZ = l_MotionZ / 2.0;
        } else {
            ChatUtils.error("No valid HoleSnap locations detected");
            this.toggle();
        }
    }

    public BlockPos nearestHole() {
        return this.findHoles().stream().min(Comparator.comparingDouble(pos -> HoleSnap.mc.player.getDistanceSqToCenter(pos))).orElse(null);
    }

    public boolean isUsableHole(BlockPos blockPos) {
        if (HoleSnap.mc.world.getBlockState(blockPos).getBlock() != Blocks.AIR) {
            return false;
        }
        if (!BlockUtils.isHole(blockPos)) {
            return false;
        }
        if ((double)blockPos.y >= HoleSnap.mc.player.posY) {
            return false;
        }
        return HoleSnap.mc.world.getBlockState(blockPos.up()) == Blocks.AIR && HoleSnap.mc.world.getBlockState(blockPos.up().up()) == Blocks.AIR;
    }

    private List<BlockPos> findHoles() {
        NonNullList positions = NonNullList.create();
        positions.addAll((Collection)HoleSnap.getSphere(new BlockPos((Entity)HoleSnap.mc.player), ((Integer)this.blockRange.getValue()).intValue(), 4, false, true, 0).stream().filter(this::isUsableHole).collect(Collectors.toList()));
        return positions;
    }

    public static List<BlockPos> getSphere(BlockPos loc, float r, int h, boolean hollow, boolean sphere, int plus_y) {
        ArrayList<BlockPos> circleblocks = new ArrayList<BlockPos>();
        int cx = loc.getX();
        int cy = loc.getY();
        int cz = loc.getZ();
        int x = cx - (int)r;
        while ((float)x <= (float)cx + r) {
            int z = cz - (int)r;
            while ((float)z <= (float)cz + r) {
                int y = sphere ? cy - (int)r : cy;
                while (true) {
                    float f = y;
                    float f2 = sphere ? (float)cy + r : (float)(cy + h);
                    if (!(f < f2)) break;
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);
                    if (!(!(dist < (double)(r * r)) || hollow && dist < (double)((r - 1.0f) * (r - 1.0f)))) {
                        BlockPos l = new BlockPos(x, y + plus_y, z);
                        circleblocks.add(l);
                    }
                    ++y;
                }
                ++z;
            }
            ++x;
        }
        return circleblocks;
    }

    public Vec3d GetCenter(double posX, double posY, double posZ) {
        double x = Math.floor(posX) + 0.5;
        double y = Math.floor(posY);
        double z = Math.floor(posZ) + 0.5;
        return new Vec3d(x, y, z);
    }
}

