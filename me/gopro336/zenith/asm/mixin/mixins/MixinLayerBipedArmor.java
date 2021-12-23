/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.event.render.LayerArmorEvent;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.inventory.EntityEquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={LayerBipedArmor.class})
public class MixinLayerBipedArmor {
    @Inject(method={"setModelSlotVisible"}, at={@At(value="HEAD")}, cancellable=true)
    protected void setModelSlotVisible(ModelBiped model2, EntityEquipmentSlot slotIn, CallbackInfo info) {
        LayerArmorEvent layerArmorEvent = new LayerArmorEvent(model2, slotIn);
        Zenith.INSTANCE.getEventManager().dispatchEvent(layerArmorEvent);
        if (layerArmorEvent.isCanceled()) {
            info.cancel();
        }
    }
}

