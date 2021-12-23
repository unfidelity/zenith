/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.world;

import me.gopro336.zenith.event.EventCancellable;
import net.minecraft.entity.Entity;

public class RemoveEntityEvent
extends EventCancellable {
    private Entity entity;

    public RemoveEntityEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}

