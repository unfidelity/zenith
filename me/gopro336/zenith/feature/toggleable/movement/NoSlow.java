//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.movement;

import me.gopro336.zenith.asm.mixin.imixin.ICPacketPlayer;
import me.gopro336.zenith.event.entity.PlayerUseItemEvent;
import me.gopro336.zenith.event.network.PacketSendEvent;
import me.gopro336.zenith.event.player.MoveInputEvent;
import me.gopro336.zenith.event.player.UpdateWalkingPlayerEvent;
import me.gopro336.zenith.event.world.UpdateEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.Property;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="NoSlow", description="Makes you not slow down while i. e. eating", category=Category.MOVEMENT)
public class NoSlow
extends Feature {
    private final Property<Boolean> glide = new Property<Boolean>(this, "Glide", "", false);
    private final Property<Boolean> strict = new Property<Boolean>(this, "Strict", "", false);
    private final Property<Boolean> airStrict = new Property<Boolean>(this, "AirStrict", "", false);
    private final Property<Boolean> ncp = new Property<Boolean>(this, "NCP", "", false);
    private boolean serverSneaking;
    private boolean gliding;

    @Listener
    public void onMoveInput(MoveInputEvent event) {
        if (NoSlow.mc.player.isHandActive() && !NoSlow.mc.player.isRiding()) {
            event.getInput().moveStrafe *= 5.0f;
            event.getInput().moveForward *= 5.0f;
        }
    }

    @Listener
    public void onUpdate(UpdateEvent event) {
        if (NoSlow.mc.player == null || NoSlow.mc.world == null) {
            return;
        }
        Item item = NoSlow.mc.player.getActiveItemStack().getItem();
        if (!NoSlow.mc.player.isHandActive() && item instanceof ItemFood || item instanceof ItemBow || item instanceof ItemPotion || !(item instanceof ItemFood) || !(item instanceof ItemBow) || !(item instanceof ItemPotion)) {
            if (this.serverSneaking && this.strict.getValue().booleanValue()) {
                NoSlow.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)NoSlow.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                this.serverSneaking = false;
            }
            if (this.gliding) {
                this.gliding = false;
            }
        }
    }

    @Listener
    public void onUpdateWalkingPlayerPre(UpdateWalkingPlayerEvent.Pre event) {
        if (NoSlow.mc.world == null || NoSlow.mc.player == null) {
            return;
        }
        if (NoSlow.mc.player.getActiveItemStack().getItem() instanceof ItemBow) {
            return;
        }
        if (this.ncp.getValue().booleanValue() && NoSlow.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSword) {
            NoSlow.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
        }
    }

    @Listener
    public void onUpdateWalkingPlayerPost(UpdateWalkingPlayerEvent.Post event) {
        if (NoSlow.mc.world == null || NoSlow.mc.player == null) {
            return;
        }
        if (NoSlow.mc.player.getActiveItemStack().getItem() instanceof ItemBow) {
            return;
        }
        if (this.ncp.getValue().booleanValue() && NoSlow.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSword) {
            NoSlow.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(new BlockPos(-1, -1, -1), EnumFacing.DOWN, EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
        }
    }

    @Listener
    public void onPlayerUseItemEvent(PlayerUseItemEvent event) {
        if (NoSlow.mc.player.getActiveItemStack().getItem() instanceof ItemBow) {
            return;
        }
        if (this.glide.getValue().booleanValue()) {
            if (!this.gliding) {
                NoSlow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(NoSlow.mc.player.rotationYaw, NoSlow.mc.player.rotationPitch, false));
            }
            this.gliding = true;
        }
        if (!(this.serverSneaking || !this.strict.getValue().booleanValue() || this.airStrict.getValue().booleanValue() && NoSlow.mc.player.onGround && !this.glide.getValue().booleanValue())) {
            NoSlow.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)NoSlow.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            this.serverSneaking = true;
        }
    }

    @Listener
    public void onPacketSend(PacketSendEvent event) {
        if (event.getPacket() instanceof CPacketPlayer && this.gliding) {
            ((ICPacketPlayer)event.getPacket()).setOnGround(false);
        }
    }
}

