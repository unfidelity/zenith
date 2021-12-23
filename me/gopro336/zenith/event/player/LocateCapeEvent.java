/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.player;

import java.util.UUID;
import me.gopro336.zenith.event.EventCancellable;
import net.minecraft.util.ResourceLocation;

public class LocateCapeEvent
extends EventCancellable {
    ResourceLocation resourceLocation;
    UUID uuid;

    public LocateCapeEvent(UUID uuid) {
        this.uuid = uuid;
        this.resourceLocation = null;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public ResourceLocation getResourceLocation() {
        return this.resourceLocation;
    }

    public void setResourceLocation(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }
}

