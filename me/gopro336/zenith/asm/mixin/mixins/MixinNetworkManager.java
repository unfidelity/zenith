/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.event.EventStageable;
import me.gopro336.zenith.event.network.HandleDisconnectionEvent;
import me.gopro336.zenith.event.network.PacketExceptionEvent;
import me.gopro336.zenith.event.network.PacketReceiveEvent;
import me.gopro336.zenith.event.network.PacketSendEvent;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={NetworkManager.class})
public abstract class MixinNetworkManager
extends SimpleChannelInboundHandler {
    @Inject(method={"handleDisconnection"}, at={@At(value="HEAD")}, cancellable=true)
    private void injectDisconnection(CallbackInfo ci) {
        HandleDisconnectionEvent event = new HandleDisconnectionEvent();
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method={"sendPacket(Lnet/minecraft/network/Packet;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void onSendPacketPre(Packet<?> packet, CallbackInfo ci) {
        PacketSendEvent event = new PacketSendEvent(EventStageable.EventStage.PRE, packet, this);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method={"sendPacket(Lnet/minecraft/network/Packet;)V"}, at={@At(value="TAIL")}, cancellable=true)
    private void onSendPacketPost(Packet<?> packet, CallbackInfo ci) {
        PacketSendEvent event = new PacketSendEvent(EventStageable.EventStage.POST, packet, this);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method={"channelRead0"}, at={@At(value="HEAD")}, cancellable=true)
    private void onChannelReadPre(ChannelHandlerContext context, Packet<?> packet, CallbackInfo ci) {
        PacketReceiveEvent event = new PacketReceiveEvent(EventStageable.EventStage.PRE, packet);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method={"channelRead0"}, at={@At(value="TAIL")}, cancellable=true)
    private void onChannelReadPost(ChannelHandlerContext context, Packet<?> packet, CallbackInfo ci) {
        PacketReceiveEvent event = new PacketReceiveEvent(EventStageable.EventStage.POST, packet);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method={"exceptionCaught"}, at={@At(value="HEAD")}, cancellable=true)
    private void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable, CallbackInfo ci) {
        PacketExceptionEvent event = new PacketExceptionEvent(EventStageable.EventStage.POST);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }
}

