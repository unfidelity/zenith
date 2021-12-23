/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.misc;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.gopro336.zenith.api.util.newUtil.ChatUtils;
import me.gopro336.zenith.event.EventStageable;
import me.gopro336.zenith.event.network.PacketExceptionEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import org.jetbrains.annotations.NotNull;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="NoCompKick", description="Stops the exception from being thrown for badly compressed packets", category=Category.MISC)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0007"}, d2={"Lme/gopro336/zenith/feature/toggleable/misc/NoCompressionKick;", "Lme/gopro336/zenith/feature/Feature;", "()V", "onBadPacket", "", "event", "Lme/gopro336/zenith/event/network/PacketExceptionEvent;", "zenith"})
public final class NoCompressionKick
extends Feature {
    public static final NoCompressionKick INSTANCE;

    @Listener
    public final void onBadPacket(@NotNull PacketExceptionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (event.getStage() == EventStageable.EventStage.PRE) {
            event.setCanceled(true);
            ChatUtils.message("Prevented packet exception from being thrown");
        } else if (event.getStage() == EventStageable.EventStage.POST) {
            event.setCanceled(true);
            ChatUtils.message("Prevented thrown exception from disconnect");
        }
    }

    private NoCompressionKick() {
    }

    static {
        NoCompressionKick noCompressionKick;
        INSTANCE = noCompressionKick = new NoCompressionKick();
    }
}

