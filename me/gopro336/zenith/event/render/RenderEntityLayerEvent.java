/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.render;

import me.gopro336.zenith.event.EventCancellable;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;

public class RenderEntityLayerEvent
extends EventCancellable {
    private EntityLivingBase entity;
    private LayerRenderer<EntityLivingBase> layer;

    public RenderEntityLayerEvent(EntityLivingBase entity, LayerRenderer<EntityLivingBase> layer) {
        this.entity = entity;
        this.layer = layer;
    }

    public EntityLivingBase getEntity() {
        return this.entity;
    }

    public LayerRenderer<EntityLivingBase> getLayer() {
        return this.layer;
    }

    public void setEntity(EntityLivingBase e) {
        this.entity = e;
    }

    public void setLayer(LayerRenderer<EntityLivingBase> l) {
        this.layer = l;
    }
}

