//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.Combat;

import me.gopro336.zenith.api.util.interaction.InteractionUtil;
import me.gopro336.zenith.api.util.time.Timer;
import me.gopro336.zenith.asm.mixin.mixins.accessor.IEntityPlayerSP;
import me.gopro336.zenith.event.player.StopUsingItemEvent;
import me.gopro336.zenith.event.player.UpdateWalkingPlayerEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="SelfBow", description="Shoots yourself", category=Category.COMBAT)
public class Quiver
extends Feature {
    public final Property<Boolean> speed = new Property<Boolean>(this, "Swiftness", "", false);
    public final Property<Boolean> strength = new Property<Boolean>(this, "Strength", "", false);
    public final Property<Boolean> toggelable = new Property<Boolean>(this, "Toggelable", "", false);
    public final Property<Boolean> autoSwitch = new Property<Boolean>(this, "AutoSwitch", "", false);
    public final Property<Boolean> rearrange = new Property<Boolean>(this, "Rearrange", "", false);
    public final Property<Boolean> noGapSwitch = new Property<Boolean>(this, "NoGapSwitch", "", false);
    public final NumberProperty<Integer> health = new NumberProperty<Integer>((Feature)this, "MinHealth", "", 0, Integer.valueOf(20), 36);
    private Timer timer = new Timer();
    private boolean cancelStopUsingItem = false;

    @Listener
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent.Pre event) {
        if (Quiver.mc.player == null || Quiver.mc.world == null) {
            return;
        }
        if (event.isCanceled() || !InteractionUtil.canPlaceNormally()) {
            return;
        }
        if (!this.timer.hasPassed(2500.0)) {
            return;
        }
        if (Quiver.mc.player.getHealth() + Quiver.mc.player.getAbsorptionAmount() < (float)((Integer)this.health.getValue()).intValue()) {
            return;
        }
        if (this.noGapSwitch.getValue().booleanValue() && Quiver.mc.player.getActiveItemStack().getItem() instanceof ItemFood) {
            return;
        }
        if (this.strength.getValue().booleanValue() && !Quiver.mc.player.isPotionActive(MobEffects.STRENGTH)) {
            if (this.isFirstAmmoValid("Arrow of Strength")) {
                this.shootBow(event);
            } else if (this.toggelable.getValue().booleanValue()) {
                this.toggle();
            }
        }
        if (this.speed.getValue().booleanValue() && !Quiver.mc.player.isPotionActive(MobEffects.SPEED)) {
            if (this.isFirstAmmoValid("Arrow of Swiftness")) {
                this.shootBow(event);
            } else if (this.toggelable.getValue().booleanValue()) {
                this.toggle();
            }
        }
    }

    @Listener
    public void onStopUsingItem(StopUsingItemEvent event) {
        if (this.cancelStopUsingItem) {
            event.setCanceled(true);
        }
    }

    @Override
    public void onEnable() {
        this.cancelStopUsingItem = false;
    }

    private void shootBow(UpdateWalkingPlayerEvent event) {
        int bowSlot;
        if (Quiver.mc.player.inventory.getCurrentItem().getItem() == Items.BOW) {
            Quiver.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(0.0f, -90.0f, Quiver.mc.player.onGround));
            ((IEntityPlayerSP)Quiver.mc.player).setLastReportedYaw(0.0f);
            ((IEntityPlayerSP)Quiver.mc.player).setLastReportedPitch(-90.0f);
            if (Quiver.mc.player.getItemInUseMaxCount() >= 3) {
                this.cancelStopUsingItem = false;
                Quiver.mc.playerController.onStoppedUsingItem((EntityPlayer)Quiver.mc.player);
                if (this.toggelable.getValue().booleanValue()) {
                    this.toggle();
                }
                this.timer.reset();
            } else if (Quiver.mc.player.getItemInUseMaxCount() == 0) {
                Quiver.mc.playerController.processRightClick((EntityPlayer)Quiver.mc.player, (World)Quiver.mc.world, EnumHand.MAIN_HAND);
                this.cancelStopUsingItem = true;
            }
        } else if (this.autoSwitch.getValue().booleanValue() && (bowSlot = this.getBowSlot()) != -1 && bowSlot != Quiver.mc.player.inventory.currentItem) {
            Quiver.mc.player.inventory.currentItem = bowSlot;
            Quiver.mc.playerController.updateController();
        }
    }

    public int getBowSlot() {
        int bowSlot = -1;
        if (Quiver.mc.player.getHeldItemMainhand().getItem() == Items.BOW) {
            bowSlot = Feature.mc.player.inventory.currentItem;
        }
        if (bowSlot == -1) {
            for (int l = 0; l < 9; ++l) {
                if (Quiver.mc.player.inventory.getStackInSlot(l).getItem() != Items.BOW) continue;
                bowSlot = l;
                break;
            }
        }
        return bowSlot;
    }

    private boolean isFirstAmmoValid(String type) {
        for (int i = 0; i < 36; ++i) {
            ItemStack itemStack = Quiver.mc.player.inventory.getStackInSlot(i);
            if (itemStack.getItem() != Items.TIPPED_ARROW) continue;
            boolean matches = itemStack.getDisplayName().equalsIgnoreCase(type);
            if (matches) {
                return true;
            }
            if (this.rearrange.getValue().booleanValue()) {
                return this.rearrangeArrow(i, type);
            }
            return false;
        }
        return false;
    }

    private boolean rearrangeArrow(int fakeSlot, String type) {
        for (int i = 0; i < 36; ++i) {
            ItemStack itemStack = Quiver.mc.player.inventory.getStackInSlot(i);
            if (itemStack.getItem() != Items.TIPPED_ARROW || !itemStack.getDisplayName().equalsIgnoreCase(type)) continue;
            Quiver.mc.playerController.windowClick(0, fakeSlot, 0, ClickType.PICKUP, (EntityPlayer)Quiver.mc.player);
            Quiver.mc.playerController.windowClick(0, i, 0, ClickType.PICKUP, (EntityPlayer)Quiver.mc.player);
            Quiver.mc.playerController.windowClick(0, fakeSlot, 0, ClickType.PICKUP, (EntityPlayer)Quiver.mc.player);
            return true;
        }
        return false;
    }
}

