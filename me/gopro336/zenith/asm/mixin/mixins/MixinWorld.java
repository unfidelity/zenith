/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.event.render.RenderSkylightEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={World.class})
public class MixinWorld {
    @Inject(method={"checkLightFor"}, at={@At(value="HEAD")}, cancellable=true)
    public void checkLightFor(EnumSkyBlock lightType, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
        RenderSkylightEvent renderSkylightEvent = new RenderSkylightEvent();
        Zenith.INSTANCE.getEventManager().dispatchEvent(renderSkylightEvent);
        if (renderSkylightEvent.isCanceled()) {
            info.cancel();
            info.setReturnValue(true);
        }
    }
}

