/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.player;

import me.gopro336.zenith.event.EventCancellable;
import net.minecraft.entity.Entity;

public class ApplyCollisionEvent
extends EventCancellable {
    Entity entity;

    public ApplyCollisionEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}

