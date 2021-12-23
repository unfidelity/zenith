/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.event.render.RenderEnchantmentTableBookEvent;
import net.minecraft.client.renderer.tileentity.TileEntityEnchantmentTableRenderer;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={TileEntityEnchantmentTableRenderer.class})
public class MixinTileEntityEnchantmentTableRenderer {
    @Inject(method={"render"}, at={@At(value="INVOKE")}, cancellable=true)
    private void renderEnchantingTableBook(TileEntityEnchantmentTable tileEntityEnchantmentTable, double x, double y, double z, float partialTicks, int destroyStage, float alpha, CallbackInfo info) {
        RenderEnchantmentTableBookEvent renderEnchantmentTableBookEvent = new RenderEnchantmentTableBookEvent();
        Zenith.INSTANCE.getEventManager().dispatchEvent(renderEnchantmentTableBookEvent);
        if (renderEnchantmentTableBookEvent.isCanceled()) {
            info.cancel();
        }
    }
}

