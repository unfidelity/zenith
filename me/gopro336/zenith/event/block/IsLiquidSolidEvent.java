/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.block;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004\u00a8\u0006\u0010"}, d2={"Lme/gopro336/zenith/event/block/IsLiquidSolidEvent;", "", "solid", "", "(Z)V", "getSolid", "()Z", "setSolid", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "zenith"})
public final class IsLiquidSolidEvent {
    private boolean solid;

    public final boolean getSolid() {
        return this.solid;
    }

    public final void setSolid(boolean bl) {
        this.solid = bl;
    }

    public IsLiquidSolidEvent(boolean solid) {
        this.solid = solid;
    }

    public final boolean component1() {
        return this.solid;
    }

    @NotNull
    public final IsLiquidSolidEvent copy(boolean solid) {
        return new IsLiquidSolidEvent(solid);
    }

    public static /* synthetic */ IsLiquidSolidEvent copy$default(IsLiquidSolidEvent isLiquidSolidEvent, boolean bl, int n, Object object) {
        if ((n & 1) != 0) {
            bl = isLiquidSolidEvent.solid;
        }
        return isLiquidSolidEvent.copy(bl);
    }

    @NotNull
    public String toString() {
        return "IsLiquidSolidEvent(solid=" + this.solid + ")";
    }

    public int hashCode() {
        int n = this.solid ? 1 : 0;
        if (n != 0) {
            n = 1;
        }
        return n;
    }

    public boolean equals(@Nullable Object object) {
        block3: {
            block2: {
                if (this == object) break block2;
                if (!(object instanceof IsLiquidSolidEvent)) break block3;
                IsLiquidSolidEvent isLiquidSolidEvent = (IsLiquidSolidEvent)object;
                if (this.solid != isLiquidSolidEvent.solid) break block3;
            }
            return true;
        }
        return false;
    }
}

