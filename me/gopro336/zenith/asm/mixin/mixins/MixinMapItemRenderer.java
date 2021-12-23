/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.event.render.RenderMapEvent;
import net.minecraft.client.gui.MapItemRenderer;
import net.minecraft.world.storage.MapData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={MapItemRenderer.class})
public class MixinMapItemRenderer {
    @Inject(method={"renderMap"}, at={@At(value="HEAD")}, cancellable=true)
    public void renderMap(MapData mapdataIn, boolean noOverlayRendering, CallbackInfo info) {
        RenderMapEvent renderMapEvent = new RenderMapEvent();
        Zenith.INSTANCE.getEventManager().dispatchEvent(renderMapEvent);
        if (renderMapEvent.isCanceled()) {
            info.cancel();
        }
    }
}

