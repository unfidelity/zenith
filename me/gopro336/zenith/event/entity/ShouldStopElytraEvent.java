/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.entity;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000b\u001a\u00020\fH\u00d6\u0001J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0005\"\u0004\b\u0006\u0010\u0004\u00a8\u0006\u000f"}, d2={"Lme/gopro336/zenith/event/entity/ShouldStopElytraEvent;", "", "isWorldRemote", "", "(Z)V", "()Z", "setWorldRemote", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "zenith"})
public final class ShouldStopElytraEvent {
    private boolean isWorldRemote;

    public final boolean isWorldRemote() {
        return this.isWorldRemote;
    }

    public final void setWorldRemote(boolean bl) {
        this.isWorldRemote = bl;
    }

    public ShouldStopElytraEvent(boolean isWorldRemote) {
        this.isWorldRemote = isWorldRemote;
    }

    public final boolean component1() {
        return this.isWorldRemote;
    }

    @NotNull
    public final ShouldStopElytraEvent copy(boolean isWorldRemote) {
        return new ShouldStopElytraEvent(isWorldRemote);
    }

    public static /* synthetic */ ShouldStopElytraEvent copy$default(ShouldStopElytraEvent shouldStopElytraEvent, boolean bl, int n, Object object) {
        if ((n & 1) != 0) {
            bl = shouldStopElytraEvent.isWorldRemote;
        }
        return shouldStopElytraEvent.copy(bl);
    }

    @NotNull
    public String toString() {
        return "ShouldStopElytraEvent(isWorldRemote=" + this.isWorldRemote + ")";
    }

    public int hashCode() {
        int n = this.isWorldRemote ? 1 : 0;
        if (n != 0) {
            n = 1;
        }
        return n;
    }

    public boolean equals(@Nullable Object object) {
        block3: {
            block2: {
                if (this == object) break block2;
                if (!(object instanceof ShouldStopElytraEvent)) break block3;
                ShouldStopElytraEvent shouldStopElytraEvent = (ShouldStopElytraEvent)object;
                if (this.isWorldRemote != shouldStopElytraEvent.isWorldRemote) break block3;
            }
            return true;
        }
        return false;
    }
}

