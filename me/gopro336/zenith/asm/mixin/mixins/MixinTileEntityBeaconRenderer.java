/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.event.render.RenderBeaconBeamEvent;
import net.minecraft.client.renderer.tileentity.TileEntityBeaconRenderer;
import net.minecraft.tileentity.TileEntityBeacon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={TileEntityBeaconRenderer.class})
public class MixinTileEntityBeaconRenderer {
    @Inject(method={"render"}, at={@At(value="INVOKE")}, cancellable=true)
    private void renderBeaconBeam(TileEntityBeacon tileEntityBeacon, double x, double y, double z, float partialTicks, int destroyStage, float alpha, CallbackInfo info) {
        RenderBeaconBeamEvent renderBeaconBeamEvent = new RenderBeaconBeamEvent();
        Zenith.INSTANCE.getEventManager().dispatchEvent(renderBeaconBeamEvent);
        if (renderBeaconBeamEvent.isCanceled()) {
            info.cancel();
        }
    }
}

