/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.entity;

import javax.annotation.Nullable;
import net.minecraft.entity.Entity;

public class AddEntityVelocityEvent {
    @Nullable
    public Entity entity;
    public double x;
    public double y;
    public double z;

    public AddEntityVelocityEvent(@Nullable Entity entity, double x, double y, double z) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

