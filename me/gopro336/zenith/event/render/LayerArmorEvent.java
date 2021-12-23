/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.render;

import me.gopro336.zenith.event.EventCancellable;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.inventory.EntityEquipmentSlot;

public class LayerArmorEvent
extends EventCancellable {
    private ModelBiped modelBiped;
    private EntityEquipmentSlot entityEquipmentSlot;

    public LayerArmorEvent(ModelBiped modelBiped, EntityEquipmentSlot entityEquipmentSlot) {
        this.modelBiped = modelBiped;
        this.entityEquipmentSlot = entityEquipmentSlot;
    }

    public EntityEquipmentSlot getEntityEquipmentSlot() {
        return this.entityEquipmentSlot;
    }

    public ModelBiped getModelBiped() {
        return this.modelBiped;
    }
}

