/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.client;

import net.minecraft.client.settings.KeyBinding;

public class GetKeyStateEvent {
    public final KeyBinding keyBinding;
    public boolean value;

    public GetKeyStateEvent(KeyBinding keyBinding, boolean value) {
        this.keyBinding = keyBinding;
        this.value = value;
    }
}

