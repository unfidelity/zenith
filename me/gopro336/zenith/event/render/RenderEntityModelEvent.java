/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.render;

import me.gopro336.zenith.event.EventCancellable;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

public class RenderEntityModelEvent
extends EventCancellable {
    public ModelBase modelBase;
    public Entity entity;
    public float limbSwing;
    public float limbSwingAmount;
    public float age;
    public float headYaw;
    public float headPitch;
    public float scale;

    public RenderEntityModelEvent(ModelBase modelBase, Entity entity, float limbSwing, float limbSwingAmount, float age, float headYaw, float headPitch, float scale) {
        this.modelBase = modelBase;
        this.entity = entity;
        this.limbSwing = limbSwing;
        this.limbSwingAmount = limbSwingAmount;
        this.age = age;
        this.headYaw = headYaw;
        this.headPitch = headPitch;
        this.scale = scale;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public ModelBase getModel() {
        return this.modelBase;
    }

    public Float getAge() {
        return Float.valueOf(this.age);
    }

    public float getHeadYaw() {
        return this.headYaw;
    }

    public void setHeadYaw(float yaw) {
        this.headYaw = yaw;
    }

    public float getHeadPitch() {
        return this.headPitch;
    }

    public void setHeadPitch(int p) {
        this.headPitch = p;
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(int s) {
        this.scale = s;
    }

    public float getLimbSwingAmount() {
        return this.limbSwingAmount;
    }

    public float getLimbSwing() {
        return this.limbSwing;
    }

    public void setHeadPitch(float p) {
        this.headPitch = p;
    }
}

