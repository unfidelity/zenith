/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.network;

import com.mojang.authlib.GameProfile;

public class PlayerJoinEvent {
    private GameProfile gameProfile;

    public PlayerJoinEvent(GameProfile gameProfile) {
        this.gameProfile = gameProfile;
    }

    public GameProfile getGameProfile() {
        return this.gameProfile;
    }

    public void setGameProfile(GameProfile gameProfile) {
        this.gameProfile = gameProfile;
    }
}

