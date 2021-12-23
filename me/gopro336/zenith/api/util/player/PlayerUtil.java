//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.player;

import me.gopro336.zenith.api.util.IGlobals;
import me.gopro336.zenith.api.util.InventoryUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.input.Mouse;

public class PlayerUtil
implements IGlobals {
    public static double getHealth() {
        return PlayerUtil.mc.player.getHealth() + PlayerUtil.mc.player.getAbsorptionAmount();
    }

    public static BlockPos getPosition() {
        return new BlockPos(PlayerUtil.mc.player.posX + 0.5, PlayerUtil.mc.player.posY, PlayerUtil.mc.player.posZ + 0.5);
    }

    public static void attackEntity(Entity entity, boolean packet, Hand hand, double variation) {
        if (Math.random() <= variation / 100.0) {
            if (packet) {
                PlayerUtil.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
            } else {
                PlayerUtil.mc.playerController.attackEntity((EntityPlayer)PlayerUtil.mc.player, entity);
            }
        }
        PlayerUtil.swingArm(hand);
        PlayerUtil.mc.player.resetCooldown();
    }

    public static void swingArm(Hand hand) {
        switch (hand) {
            case MAINHAND: {
                PlayerUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
                break;
            }
            case OFFHAND: {
                PlayerUtil.mc.player.swingArm(EnumHand.OFF_HAND);
                break;
            }
            case PACKET: {
                PlayerUtil.mc.player.connection.sendPacket((Packet)new CPacketAnimation(PlayerUtil.mc.player.getHeldItemMainhand().getItem().equals(Items.END_CRYSTAL) ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND));
                break;
            }
        }
    }

    public static void lockLimbs() {
        PlayerUtil.mc.player.prevLimbSwingAmount = 0.0f;
        PlayerUtil.mc.player.limbSwingAmount = 0.0f;
        PlayerUtil.mc.player.limbSwing = 0.0f;
    }

    public static boolean isPressingMoveBinds() {
        return PlayerUtil.mc.gameSettings.keyBindForward.isKeyDown() || PlayerUtil.mc.gameSettings.keyBindBack.isKeyDown() || PlayerUtil.mc.gameSettings.keyBindRight.isKeyDown() || PlayerUtil.mc.gameSettings.keyBindLeft.isKeyDown();
    }

    public static boolean isEating() {
        return PlayerUtil.mc.player.getHeldItemMainhand().getItemUseAction().equals((Object)EnumAction.EAT) || PlayerUtil.mc.player.getHeldItemMainhand().getItemUseAction().equals((Object)EnumAction.DRINK);
    }

    public static boolean isMending() {
        return InventoryUtils.isHolding(Items.EXPERIENCE_BOTTLE) && Mouse.isButtonDown((int)1);
    }

    public static boolean isMining() {
        return InventoryUtils.isHolding(Items.DIAMOND_PICKAXE) && PlayerUtil.mc.playerController.getIsHittingBlock();
    }

    public static boolean isInLiquid() {
        return PlayerUtil.mc.player.isInLava() || PlayerUtil.mc.player.isInWater();
    }

    public static boolean isCollided() {
        return PlayerUtil.mc.player.collidedHorizontally || PlayerUtil.mc.player.collidedVertically;
    }

    public static enum Hand {
        MAINHAND,
        OFFHAND,
        PACKET,
        NONE;

    }
}

