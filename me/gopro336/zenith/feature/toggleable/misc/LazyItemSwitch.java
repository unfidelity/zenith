//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.misc;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.NetworkManager;
import org.jetbrains.annotations.NotNull;

@AnnotationHelper(name="LazyItemSwitch", description="Spoof your server side item until necessary", category=Category.COMBAT)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2={"Lme/gopro336/zenith/feature/toggleable/misc/LazyItemSwitch;", "Lme/gopro336/zenith/feature/Feature;", "()V", "updatePlayerControllerOnTick", "", "playerController", "Lnet/minecraft/client/multiplayer/PlayerControllerMP;", "zenith"})
public final class LazyItemSwitch
extends Feature {
    public static final LazyItemSwitch INSTANCE;

    public final void updatePlayerControllerOnTick(@NotNull PlayerControllerMP playerController) {
        Intrinsics.checkParameterIsNotNull(playerController, "playerController");
        if (!this.isEnabled()) {
            playerController.updateController();
        } else {
            NetHandlerPlayClient conn;
            NetHandlerPlayClient netHandlerPlayClient = conn = Feature.mc.player.connection;
            Intrinsics.checkExpressionValueIsNotNull(netHandlerPlayClient, "conn");
            NetworkManager networkManager = netHandlerPlayClient.getNetworkManager();
            Intrinsics.checkExpressionValueIsNotNull(networkManager, "conn.networkManager");
            if (networkManager.isChannelOpen()) {
                conn.getNetworkManager().processReceivedPackets();
            } else {
                conn.getNetworkManager().handleDisconnection();
            }
        }
    }

    private LazyItemSwitch() {
    }

    static {
        LazyItemSwitch lazyItemSwitch;
        INSTANCE = lazyItemSwitch = new LazyItemSwitch();
    }
}

