/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.asm.mixin.imixin.IRenderManager;
import me.gopro336.zenith.event.EventStageable;
import me.gopro336.zenith.event.render.RenderEntityEvent;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={RenderManager.class})
public abstract class MixinRenderManager
implements IRenderManager {
    @Override
    @Accessor(value="renderPosX")
    public abstract double getRenderPosX();

    @Override
    @Accessor(value="renderPosY")
    public abstract double getRenderPosY();

    @Override
    @Accessor(value="renderPosZ")
    public abstract double getRenderPosZ();

    @Inject(method={"renderEntity"}, at={@At(value="HEAD")}, cancellable=true)
    public void renderEntityPre(Entity entityIn, double x, double y, double z, float yaw, float partialTicks, boolean p_188391_10_, CallbackInfo ci) {
        RenderEntityEvent event = new RenderEntityEvent(EventStageable.EventStage.PRE, entityIn, x, y, z, yaw, partialTicks);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderEntity"}, at={@At(value="TAIL")}, cancellable=true)
    public void renderEntityPost(Entity entityIn, double x, double y, double z, float yaw, float partialTicks, boolean p_188391_10_, CallbackInfo ci) {
        RenderEntityEvent event = new RenderEntityEvent(EventStageable.EventStage.POST, entityIn, x, y, z, yaw, partialTicks);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
    }
}

