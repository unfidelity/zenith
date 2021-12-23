/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.event.render.ModifyFOVEvent;
import net.minecraft.client.entity.AbstractClientPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={AbstractClientPlayer.class})
public class MixinAbstractClientPlayer {
    @Inject(method={"getFovModifier"}, at={@At(value="HEAD")}, cancellable=true)
    public void getFOVModifier(CallbackInfoReturnable<Float> info) {
        ModifyFOVEvent modifyFOVEvent = new ModifyFOVEvent();
        Zenith.INSTANCE.getEventManager().dispatchEvent(modifyFOVEvent);
        if (modifyFOVEvent.isCanceled()) {
            info.cancel();
            info.setReturnValue(Float.valueOf(1.0f));
        }
    }
}

