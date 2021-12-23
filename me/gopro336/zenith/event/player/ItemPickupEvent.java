/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.player;

import net.minecraft.entity.Entity;

public class ItemPickupEvent {
    private Entity entity;
    private int quantity;

    public ItemPickupEvent(Entity entity, int quantity) {
        this.entity = entity;
        this.quantity = quantity;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

