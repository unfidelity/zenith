/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.client;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.network.NetworkPlayerInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006\u0016"}, d2={"Lme/gopro336/zenith/event/client/GetPlayerTabTextureEvent;", "", "networkPlayerInfo", "Lnet/minecraft/client/network/NetworkPlayerInfo;", "shouldLoad", "", "(Lnet/minecraft/client/network/NetworkPlayerInfo;Z)V", "getNetworkPlayerInfo", "()Lnet/minecraft/client/network/NetworkPlayerInfo;", "getShouldLoad", "()Z", "setShouldLoad", "(Z)V", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "zenith"})
public final class GetPlayerTabTextureEvent {
    @NotNull
    private final NetworkPlayerInfo networkPlayerInfo;
    private boolean shouldLoad;

    @NotNull
    public final NetworkPlayerInfo getNetworkPlayerInfo() {
        return this.networkPlayerInfo;
    }

    public final boolean getShouldLoad() {
        return this.shouldLoad;
    }

    public final void setShouldLoad(boolean bl) {
        this.shouldLoad = bl;
    }

    public GetPlayerTabTextureEvent(@NotNull NetworkPlayerInfo networkPlayerInfo, boolean shouldLoad) {
        Intrinsics.checkParameterIsNotNull(networkPlayerInfo, "networkPlayerInfo");
        this.networkPlayerInfo = networkPlayerInfo;
        this.shouldLoad = shouldLoad;
    }

    @NotNull
    public final NetworkPlayerInfo component1() {
        return this.networkPlayerInfo;
    }

    public final boolean component2() {
        return this.shouldLoad;
    }

    @NotNull
    public final GetPlayerTabTextureEvent copy(@NotNull NetworkPlayerInfo networkPlayerInfo, boolean shouldLoad) {
        Intrinsics.checkParameterIsNotNull(networkPlayerInfo, "networkPlayerInfo");
        return new GetPlayerTabTextureEvent(networkPlayerInfo, shouldLoad);
    }

    public static /* synthetic */ GetPlayerTabTextureEvent copy$default(GetPlayerTabTextureEvent getPlayerTabTextureEvent, NetworkPlayerInfo networkPlayerInfo, boolean bl, int n, Object object) {
        if ((n & 1) != 0) {
            networkPlayerInfo = getPlayerTabTextureEvent.networkPlayerInfo;
        }
        if ((n & 2) != 0) {
            bl = getPlayerTabTextureEvent.shouldLoad;
        }
        return getPlayerTabTextureEvent.copy(networkPlayerInfo, bl);
    }

    @NotNull
    public String toString() {
        return "GetPlayerTabTextureEvent(networkPlayerInfo=" + this.networkPlayerInfo + ", shouldLoad=" + this.shouldLoad + ")";
    }

    public int hashCode() {
        NetworkPlayerInfo networkPlayerInfo = this.networkPlayerInfo;
        int n = (networkPlayerInfo != null ? networkPlayerInfo.hashCode() : 0) * 31;
        int n2 = this.shouldLoad ? 1 : 0;
        if (n2 != 0) {
            n2 = 1;
        }
        return n + n2;
    }

    public boolean equals(@Nullable Object object) {
        block3: {
            block2: {
                if (this == object) break block2;
                if (!(object instanceof GetPlayerTabTextureEvent)) break block3;
                GetPlayerTabTextureEvent getPlayerTabTextureEvent = (GetPlayerTabTextureEvent)object;
                if (!Intrinsics.areEqual(this.networkPlayerInfo, getPlayerTabTextureEvent.networkPlayerInfo) || this.shouldLoad != getPlayerTabTextureEvent.shouldLoad) break block3;
            }
            return true;
        }
        return false;
    }
}

