//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.event.render.BlockStateAtEntityViewpointEvent;
import me.gopro336.zenith.event.render.HurtCameraEffectEvent;
import me.gopro336.zenith.event.render.LightmapUpdateEvent;
import me.gopro336.zenith.event.render.OrientCameraEvent;
import me.gopro336.zenith.event.render.Render3DEvent;
import me.gopro336.zenith.event.render.SetupFogEvent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={EntityRenderer.class})
public abstract class MixinEntityRenderer {
    @Inject(method={"updateLightmap"}, at={@At(value="RETURN", target="Lnet/minecraft/client/renderer/texture/DynamicTexture;updateDynamicTexture()V")}, require=1)
    private void updateLightmapWrapper(float partialTicks, CallbackInfo ci) {
        Zenith.INSTANCE.getEventManager().dispatchEvent(new LightmapUpdateEvent());
    }

    @Inject(method={"renderWorldPass"}, at={@At(value="FIELD", target="Lnet/minecraft/client/renderer/EntityRenderer;renderHand:Z")})
    private void renderWorldPass(int pass, float partialTicks, long finishTimeNano, CallbackInfo ci) {
        Render3DEvent event = new Render3DEvent(partialTicks, finishTimeNano);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
    }

    @Inject(method={"hurtCameraEffect"}, at={@At(value="HEAD")}, cancellable=true)
    public void hurtCameraEffect(float partialTicks, CallbackInfo ci) {
        HurtCameraEffectEvent event = new HurtCameraEffectEvent();
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method={"setupFog"}, at={@At(value="HEAD")}, cancellable=true)
    public void setupFog(int startCoords, float partialTicks, CallbackInfo ci) {
        SetupFogEvent event = new SetupFogEvent();
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method={"orientCamera"}, at={@At(value="HEAD")}, cancellable=true)
    private void orientCamera(float partialTicks, CallbackInfo ci) {
        OrientCameraEvent event = new OrientCameraEvent();
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Redirect(method={"setupFog"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/renderer/ActiveRenderInfo;getBlockStateAtEntityViewpoint(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;F)Lnet/minecraft/block/state/IBlockState;"))
    public IBlockState getBlockStateAtEntityViewpoint(World worldIn, Entity entityIn, float p_186703_2_) {
        BlockStateAtEntityViewpointEvent event = new BlockStateAtEntityViewpointEvent(ActiveRenderInfo.getBlockStateAtEntityViewpoint((World)worldIn, (Entity)entityIn, (float)p_186703_2_));
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        return event.getiBlockState();
    }
}

