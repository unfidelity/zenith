//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.Combat;

import me.gopro336.zenith.api.util.time.OldTimer;
import me.gopro336.zenith.asm.mixin.imixin.ISPacketPlayerPosLook;
import me.gopro336.zenith.asm.mixin.mixins.accessor.IEntityPlayerSP;
import me.gopro336.zenith.event.network.PacketReceiveEvent;
import me.gopro336.zenith.event.player.PushOutOfBlocksEvent;
import me.gopro336.zenith.event.world.TickEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.Property;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="Burrow", description="Fills your own hole", category=Category.EXPLOIT)
public class Burrow
extends Feature {
    public Property<Boolean> rotate = new Property<Boolean>(this, "Rotate", "", true);
    public Property<Boolean> swing = new Property<Boolean>(this, "Swing", "", true);
    public Property<Boolean> strict = new Property<Boolean>(this, "Strict", "", false);
    public Property<Boolean> skulls = new Property<Boolean>(this, "Skulls", "", true);
    public CurrentStep Field294 = CurrentStep.WAITING;
    public OldTimer Field295 = new OldTimer();

    @Listener
    public void Method461(PushOutOfBlocksEvent pushOutOfBlocksEvent) {
        pushOutOfBlocksEvent.setCanceled(true);
    }

    @Override
    public void onEnable() {
        if (Burrow.mc.player == null || Burrow.mc.world == null) {
            this.toggle();
            return;
        }
        if (!Burrow.mc.player.onGround) {
            this.toggle();
            return;
        }
        this.Field294 = CurrentStep.WAITING;
    }

    @Listener
    public void Method131(PacketReceiveEvent packetEvent) {
        if (Burrow.mc.currentScreen instanceof GuiDownloadTerrain) {
            this.toggle();
            return;
        }
        if (packetEvent.getPacket() instanceof SPacketPlayerPosLook && !this.strict.getValue().booleanValue()) {
            ((ISPacketPlayerPosLook)packetEvent.getPacket()).setYaw(Burrow.mc.player.rotationYaw);
            ((ISPacketPlayerPosLook)packetEvent.getPacket()).setPitch(Burrow.mc.player.rotationPitch);
        }
    }

    @Listener
    public void Method462(TickEvent tickEvent) {
        if (Burrow.mc.player == null || Burrow.mc.world == null) {
            return;
        }
        if (this.Field294 == CurrentStep.DISABLING) {
            if (this.Field295.GetDifferenceTiming(500.0)) {
                this.toggle();
            }
            return;
        }
        if (!Burrow.mc.player.onGround) {
            this.toggle();
            return;
        }
        if (Burrow.mc.world.getBlockState(new BlockPos((Entity)Burrow.mc.player)).getBlock() == Blocks.AIR) {
            if (this.skulls.getValue().booleanValue() && Burrow.mc.world.getBlockState(new BlockPos((Entity)Burrow.mc.player).up(2)).getBlock() != Blocks.AIR) {
                if (this.getBlockInHotbar() == -1) {
                    this.toggle();
                    return;
                }
                BlockPos blockPos = new BlockPos(Burrow.mc.player.posX, Burrow.mc.player.posY, Burrow.mc.player.posZ);
                BlockPos blockPos2 = blockPos.down();
                EnumFacing enumFacing = EnumFacing.UP;
                Vec3d vec3d = new Vec3d((Vec3i)blockPos2).add(0.5, 0.5, 0.5).add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
                if (this.rotate.getValue().booleanValue()) {
                    if (((IEntityPlayerSP)Burrow.mc.player).getLastReportedPitch() < 0.0f) {
                        Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(Burrow.mc.player.rotationYaw, 0.0f, true));
                    }
                    Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(Burrow.mc.player.posX, Burrow.mc.player.posY, Burrow.mc.player.posZ, Burrow.mc.player.rotationYaw, 90.0f, true));
                    ((IEntityPlayerSP)Burrow.mc.player).setLastReportedPosY(Burrow.mc.player.posY + 1.16);
                    ((IEntityPlayerSP)Burrow.mc.player).setLastReportedPitch(90.0f);
                }
                float f = (float)(vec3d.x - (double)blockPos.getX());
                float f2 = (float)(vec3d.y - (double)blockPos.getY());
                float f3 = (float)(vec3d.z - (double)blockPos.getZ());
                boolean bl = Burrow.mc.player.inventory.currentItem != this.getBlockInHotbar();
                int n = Burrow.mc.player.inventory.currentItem;
                if (bl) {
                    Burrow.mc.player.inventory.currentItem = this.getBlockInHotbar();
                    Burrow.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.getBlockInHotbar()));
                }
                Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos2, enumFacing, EnumHand.MAIN_HAND, f, f2, f3));
                if (this.swing.getValue().booleanValue()) {
                    Burrow.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
                }
                if (bl) {
                    Burrow.mc.player.inventory.currentItem = n;
                    Burrow.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
                }
                this.Field295.UpdateCurrentTime();
                this.Field294 = CurrentStep.DISABLING;
                return;
            }
            if (this.Method464() == -1) {
                this.toggle();
                return;
            }
            BlockPos blockPos = new BlockPos(Burrow.mc.player.posX, Burrow.mc.player.posY, Burrow.mc.player.posZ);
            BlockPos blockPos3 = blockPos.down();
            EnumFacing enumFacing = EnumFacing.UP;
            Vec3d vec3d = new Vec3d((Vec3i)blockPos3).add(0.5, 0.5, 0.5).add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
            if (this.rotate.getValue().booleanValue()) {
                if (((IEntityPlayerSP)Burrow.mc.player).getLastReportedPitch() < 0.0f) {
                    Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(Burrow.mc.player.rotationYaw, 0.0f, true));
                }
                Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(Burrow.mc.player.posX, Burrow.mc.player.posY, Burrow.mc.player.posZ, Burrow.mc.player.rotationYaw, 90.0f, true));
                ((IEntityPlayerSP)Burrow.mc.player).setLastReportedPosY(Burrow.mc.player.posY + 1.16);
                ((IEntityPlayerSP)Burrow.mc.player).setLastReportedPitch(90.0f);
            }
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 0.42, Burrow.mc.player.posZ, false));
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 0.75, Burrow.mc.player.posZ, false));
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 1.01, Burrow.mc.player.posZ, false));
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 1.16, Burrow.mc.player.posZ, false));
            if (mc.getCurrentServerData() != null && Burrow.mc.getCurrentServerData().serverIP.toLowerCase().contains("crystalpvp")) {
                Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 1.16, Burrow.mc.player.posZ, false));
                Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 1.24, Burrow.mc.player.posZ, false));
            }
            float f = (float)(vec3d.x - (double)blockPos.getX());
            float f4 = (float)(vec3d.y - (double)blockPos.getY());
            float f5 = (float)(vec3d.z - (double)blockPos.getZ());
            boolean bl = Burrow.mc.player.inventory.currentItem != this.Method464();
            int n = Burrow.mc.player.inventory.currentItem;
            if (bl) {
                Burrow.mc.player.inventory.currentItem = this.Method464();
                Burrow.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.Method464()));
            }
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos3, enumFacing, EnumHand.MAIN_HAND, f, f4, f5));
            if (this.swing.getValue().booleanValue()) {
                Burrow.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
            }
            if (bl) {
                Burrow.mc.player.inventory.currentItem = n;
                Burrow.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
            }
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, this.Method463(), Burrow.mc.player.posZ, false));
            this.Field295.UpdateCurrentTime();
            this.Field294 = CurrentStep.DISABLING;
        } else {
            this.toggle();
        }
    }

    public double Method463() {
        BlockPos blockPos;
        if (mc.getCurrentServerData() != null) {
            if (Burrow.mc.getCurrentServerData().serverIP.toLowerCase().contains("crystalpvp") || Burrow.mc.getCurrentServerData().serverIP.toLowerCase().contains("2b2t")) {
                blockPos = new BlockPos(Burrow.mc.player.posX, Burrow.mc.player.posY + 2.34, Burrow.mc.player.posZ);
                if (Burrow.mc.world.getBlockState(blockPos).getBlock() instanceof BlockAir && Burrow.mc.world.getBlockState(blockPos.up()).getBlock() instanceof BlockAir) {
                    return Burrow.mc.player.posY + 2.34;
                }
            } else {
                if (Burrow.mc.getCurrentServerData().serverIP.toLowerCase().contains("endcrystal")) {
                    if (Burrow.mc.world.getBlockState(new BlockPos(Burrow.mc.player.posX, Burrow.mc.player.posY + 4.0, Burrow.mc.player.posZ)).getBlock() instanceof BlockAir) {
                        return Burrow.mc.player.posY + 4.0;
                    }
                    return Burrow.mc.player.posY + 3.0;
                }
                if (Burrow.mc.getCurrentServerData().serverIP.toLowerCase().contains("netheranarchy")) {
                    if (Burrow.mc.world.getBlockState(new BlockPos(Burrow.mc.player.posX, Burrow.mc.player.posY + 8.5, Burrow.mc.player.posZ)).getBlock() instanceof BlockAir) {
                        return Burrow.mc.player.posY + 8.5;
                    }
                    return Burrow.mc.player.posY + 9.5;
                }
                if (Burrow.mc.getCurrentServerData().serverIP.toLowerCase().contains("9b9t")) {
                    BlockPos blockPos2 = new BlockPos(Burrow.mc.player.posX, Burrow.mc.player.posY + 9.0, Burrow.mc.player.posZ);
                    if (Burrow.mc.world.getBlockState(blockPos2).getBlock() instanceof BlockAir && Burrow.mc.world.getBlockState(blockPos2.up()).getBlock() instanceof BlockAir) {
                        return Burrow.mc.player.posY + 9.0;
                    }
                    for (int i = 10; i < 20; ++i) {
                        BlockPos blockPos3 = new BlockPos(Burrow.mc.player.posX, Burrow.mc.player.posY + (double)i, Burrow.mc.player.posZ);
                        if (!(Burrow.mc.world.getBlockState(blockPos3).getBlock() instanceof BlockAir) || !(Burrow.mc.world.getBlockState(blockPos3.up()).getBlock() instanceof BlockAir)) continue;
                        return Burrow.mc.player.posY + (double)i;
                    }
                    return Burrow.mc.player.posY + 20.0;
                }
            }
        }
        if (Burrow.mc.world.getBlockState(blockPos = new BlockPos(Burrow.mc.player.posX, Burrow.mc.player.posY - 9.0, Burrow.mc.player.posZ)).getBlock() instanceof BlockAir && Burrow.mc.world.getBlockState(blockPos.up()).getBlock() instanceof BlockAir) {
            return Burrow.mc.player.posY - 9.0;
        }
        for (int i = -10; i > -20; --i) {
            BlockPos blockPos4 = new BlockPos(Burrow.mc.player.posX, Burrow.mc.player.posY - (double)i, Burrow.mc.player.posZ);
            if (!(Burrow.mc.world.getBlockState(blockPos4).getBlock() instanceof BlockAir) || !(Burrow.mc.world.getBlockState(blockPos4.up()).getBlock() instanceof BlockAir)) continue;
            return Burrow.mc.player.posY - (double)i;
        }
        return Burrow.mc.player.posY - 24.0;
    }

    public int Method464() {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = Burrow.mc.player.inventory.getStackInSlot(i);
            if (itemStack == ItemStack.EMPTY || !(itemStack.getItem() instanceof ItemBlock)) continue;
            Block block = ((ItemBlock)itemStack.getItem()).getBlock();
            n = i;
            break;
        }
        return n;
    }

    public int getBlockInHotbar() {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = Burrow.mc.player.inventory.getStackInSlot(i);
            if (!(itemStack.getItem() instanceof ItemSkull)) continue;
            n = i;
            break;
        }
        return n;
    }

    public static enum FilterMode {
        NONE,
        WHITELIST,
        BLACKLIST;

    }

    public static enum CurrentStep {
        WAITING,
        DISABLING;

    }
}

