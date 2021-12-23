/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.client;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.gopro336.zenith.api.util.MouseButton;
import me.gopro336.zenith.event.EventCancellable;
import me.gopro336.zenith.event.EventStageable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2={"Lme/gopro336/zenith/event/client/ClickMouseButtonEvent;", "Lme/gopro336/zenith/event/EventCancellable;", "mouseButton", "Lme/gopro336/zenith/api/util/MouseButton;", "(Lme/gopro336/zenith/api/util/MouseButton;)V", "getMouseButton", "()Lme/gopro336/zenith/api/util/MouseButton;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "zenith"})
public final class ClickMouseButtonEvent
extends EventCancellable {
    @NotNull
    private final MouseButton mouseButton;

    @NotNull
    public final MouseButton getMouseButton() {
        return this.mouseButton;
    }

    public ClickMouseButtonEvent(@NotNull MouseButton mouseButton) {
        Intrinsics.checkParameterIsNotNull((Object)mouseButton, "mouseButton");
        super(EventStageable.EventStage.PRE);
        this.mouseButton = mouseButton;
    }

    @NotNull
    public final MouseButton component1() {
        return this.mouseButton;
    }

    @NotNull
    public final ClickMouseButtonEvent copy(@NotNull MouseButton mouseButton) {
        Intrinsics.checkParameterIsNotNull((Object)mouseButton, "mouseButton");
        return new ClickMouseButtonEvent(mouseButton);
    }

    public static /* synthetic */ ClickMouseButtonEvent copy$default(ClickMouseButtonEvent clickMouseButtonEvent, MouseButton mouseButton, int n, Object object) {
        if ((n & 1) != 0) {
            mouseButton = clickMouseButtonEvent.mouseButton;
        }
        return clickMouseButtonEvent.copy(mouseButton);
    }

    @NotNull
    public String toString() {
        return "ClickMouseButtonEvent(mouseButton=" + (Object)((Object)this.mouseButton) + ")";
    }

    public int hashCode() {
        MouseButton mouseButton = this.mouseButton;
        return mouseButton != null ? ((Object)((Object)mouseButton)).hashCode() : 0;
    }

    public boolean equals(@Nullable Object object) {
        block3: {
            block2: {
                if (this == object) break block2;
                if (!(object instanceof ClickMouseButtonEvent)) break block3;
                ClickMouseButtonEvent clickMouseButtonEvent = (ClickMouseButtonEvent)object;
                if (!Intrinsics.areEqual((Object)this.mouseButton, (Object)clickMouseButtonEvent.mouseButton)) break block3;
            }
            return true;
        }
        return false;
    }
}

