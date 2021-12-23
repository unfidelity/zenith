//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import com.mojang.authlib.GameProfile;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.newRotations.RotationUtil;
import me.gopro336.zenith.event.player.MotionUpdateEvent;
import me.gopro336.zenith.event.player.MoveEvent;
import me.gopro336.zenith.event.player.PlayerDismountEvent;
import me.gopro336.zenith.event.player.PlayerUpdateEvent;
import me.gopro336.zenith.event.player.PushOutOfBlocksEvent;
import me.gopro336.zenith.event.player.SwingArmEvent;
import me.gopro336.zenith.event.player.UpdateWalkingPlayerEvent;
import me.gopro336.zenith.event.render.CloseScreenEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketInput;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraft.util.EnumHand;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={EntityPlayerSP.class}, priority=0x7FFFFFFF)
public abstract class MixinEntityPlayerSP
extends EntityPlayer {
    @Shadow
    protected Minecraft mc;
    @Shadow
    @Final
    public NetHandlerPlayClient connection;
    @Shadow
    public MovementInput movementInput;

    public MixinEntityPlayerSP(World worldIn, GameProfile gameProfileIn) {
        super(worldIn, gameProfileIn);
    }

    @Inject(method={"onUpdateWalkingPlayer"}, at={@At(value="TAIL")})
    public void onUpdateWalkingPlayerPost(CallbackInfo ci) {
        Minecraft mc = Minecraft.getMinecraft();
        UpdateWalkingPlayerEvent.Post postEvent = UpdateWalkingPlayerEvent.Post.get(mc.player.posX, mc.player.posY, mc.player.posY, mc.player.rotationYaw, mc.player.rotationPitch, mc.player.onGround);
        Zenith.INSTANCE.getEventManager().dispatchEvent(postEvent);
    }

    @Inject(method={"onUpdate"}, at={@At(value="HEAD")})
    public void onPlayerUpdate(CallbackInfo ci) {
        PlayerUpdateEvent playerUpdateEvent = new PlayerUpdateEvent();
        Zenith.INSTANCE.getEventManager().dispatchEvent(playerUpdateEvent);
    }

    @Redirect(method={"onUpdate"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/entity/AbstractClientPlayer;onUpdate()V"))
    public void redirectUpdateSuper(AbstractClientPlayer abstractClientPlayer) {
    }

    @Inject(method={"onUpdate"}, at={@At(value="HEAD")}, cancellable=true)
    public void onUpdate(CallbackInfo ci) {
        if (this.world.isBlockLoaded(new BlockPos(this.posX, 0.0, this.posZ))) {
            super.onUpdate();
            UpdateWalkingPlayerEvent.Pre event = UpdateWalkingPlayerEvent.Pre.get(this.mc.player.posX, this.mc.player.getEntityBoundingBox().minY, this.mc.player.posY, this.mc.player.rotationYaw, this.mc.player.rotationPitch, this.mc.player.onGround);
            Zenith.INSTANCE.getEventManager().dispatchEvent(event);
            if (Zenith.INSTANCE.rotationManager.isRotationsSet()) {
                ci.cancel();
                if (this.isRiding()) {
                    this.connection.sendPacket((Packet)new CPacketPlayer.Rotation(Zenith.INSTANCE.rotationManager.getYaw(), Zenith.INSTANCE.rotationManager.getPitch(), this.onGround));
                    this.connection.sendPacket((Packet)new CPacketInput(this.moveStrafing, this.moveForward, this.movementInput.jump, this.movementInput.sneak));
                    Entity entity = this.getLowestRidingEntity();
                    if (entity != this && entity.canPassengerSteer()) {
                        this.connection.sendPacket((Packet)new CPacketVehicleMove(entity));
                    }
                } else {
                    RotationUtil.update(Zenith.INSTANCE.rotationManager.getYaw(), Zenith.INSTANCE.rotationManager.getPitch());
                }
                UpdateWalkingPlayerEvent.Post postEvent = UpdateWalkingPlayerEvent.Post.get(this.mc.player.posX, this.mc.player.posY, this.mc.player.posY, this.mc.player.rotationYaw, this.mc.player.rotationPitch, this.mc.player.onGround);
                Zenith.INSTANCE.getEventManager().dispatchEvent(postEvent);
            }
        }
        Zenith.INSTANCE.rotationManager.reset();
    }

    @Inject(method={"sendChatMessage"}, at={@At(value="HEAD")}, cancellable=true)
    public void sendChatMessage(String message, CallbackInfo callback) {
    }

    @Inject(method={"closeScreen"}, at={@At(value="HEAD")}, cancellable=true)
    public void closeScreen(CallbackInfo ci) {
        CloseScreenEvent event = new CloseScreenEvent();
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method={"swingArm"}, at={@At(value="HEAD")}, cancellable=true)
    public void swingArm(EnumHand hand, CallbackInfo ci) {
        SwingArmEvent event = new SwingArmEvent(hand);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method={"move"}, at={@At(value="HEAD")}, cancellable=true)
    public void move(MoverType type, double x, double y, double z, CallbackInfo ci) {
        MoveEvent event = new MoveEvent(type, x, y, z);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method={"pushOutOfBlocks"}, at={@At(value="HEAD")}, cancellable=true)
    public void pushOutOfBlocks(double x, double y, double z, CallbackInfoReturnable<Boolean> ci) {
        PushOutOfBlocksEvent event = new PushOutOfBlocksEvent(x, y, z);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.setReturnValue(false);
        }
    }

    @Redirect(method={"onUpdateWalkingPlayer"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/entity/EntityPlayerSP;isCurrentViewEntity()Z"))
    private boolean redirectIsCurrentViewEntity(EntityPlayerSP entityPlayerSP) {
        Minecraft mc = Minecraft.getMinecraft();
        return mc.getRenderViewEntity() == entityPlayerSP;
    }

    @Redirect(method={"updateEntityActionState"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/entity/EntityPlayerSP;isCurrentViewEntity()Z"))
    private boolean redirectIsCurrentViewEntity2(EntityPlayerSP entityPlayerSP) {
        Minecraft mc = Minecraft.getMinecraft();
        return mc.getRenderViewEntity() == entityPlayerSP;
    }

    @Inject(method={"onUpdateWalkingPlayer"}, at={@At(value="HEAD")}, cancellable=true)
    public void onUpdateMovingPlayer(CallbackInfo info) {
        MotionUpdateEvent motionUpdateEvent = new MotionUpdateEvent();
        Zenith.INSTANCE.getEventManager().dispatchEvent(motionUpdateEvent);
        if (motionUpdateEvent.isCanceled()) {
            info.cancel();
        }
    }

    @Inject(method={"dismountRidingEntity"}, at={@At(value="HEAD")}, cancellable=true)
    public void dismountRidingEntity(CallbackInfo ci) {
        PlayerDismountEvent event = PlayerDismountEvent.getEvent();
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }
}

