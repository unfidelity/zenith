/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.render;

import me.gopro336.zenith.event.EventCancellable;
import net.minecraft.entity.EntityLivingBase;

public class RenderNameEvent
extends EventCancellable {
    double x;
    double y;
    double z;
    private EntityLivingBase entity;

    public RenderNameEvent(EntityLivingBase entity, double x, double y, double z) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public EntityLivingBase getEntity() {
        return this.entity;
    }

    public void setEntity(EntityLivingBase entity) {
        this.entity = entity;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return this.z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}

