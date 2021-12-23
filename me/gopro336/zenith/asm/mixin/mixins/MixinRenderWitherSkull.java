/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.event.render.RenderWitherSkullEvent;
import net.minecraft.client.renderer.entity.RenderWitherSkull;
import net.minecraft.entity.projectile.EntityWitherSkull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={RenderWitherSkull.class})
public class MixinRenderWitherSkull {
    @Inject(method={"doRender"}, at={@At(value="HEAD")}, cancellable=true)
    public void doRender(EntityWitherSkull entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo info) {
        RenderWitherSkullEvent renderWitherSkullEvent = new RenderWitherSkullEvent();
        Zenith.INSTANCE.getEventManager().dispatchEvent(renderWitherSkullEvent);
        if (renderWitherSkullEvent.isCanceled()) {
            info.cancel();
        }
    }
}

