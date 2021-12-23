/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.network;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.gopro336.zenith.event.EventCancellable;
import me.gopro336.zenith.event.EventStageable;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2={"Lme/gopro336/zenith/event/network/PacketExceptionEvent;", "Lme/gopro336/zenith/event/EventCancellable;", "eventStage", "Lme/gopro336/zenith/event/EventStageable$EventStage;", "(Lme/gopro336/zenith/event/EventStageable$EventStage;)V", "zenith"})
public final class PacketExceptionEvent
extends EventCancellable {
    public PacketExceptionEvent(@NotNull EventStageable.EventStage eventStage) {
        Intrinsics.checkParameterIsNotNull((Object)eventStage, "eventStage");
    }
}

