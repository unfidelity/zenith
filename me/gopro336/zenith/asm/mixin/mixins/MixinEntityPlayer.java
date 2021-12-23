//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.asm.mixin.mixins.MixinEntityLivingBase;
import me.gopro336.zenith.event.player.PlayerTravelEvent;
import me.gopro336.zenith.feature.FeatureManager;
import me.gopro336.zenith.feature.toggleable.render.RotationViewer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={EntityPlayer.class})
public abstract class MixinEntityPlayer
extends MixinEntityLivingBase {
    private float renderPitch;
    private float renderYaw;
    private float renderHeadYaw;
    private float prevRenderHeadYaw;
    private float lastRenderHeadYaw = 0.0f;
    private float prevRenderPitch;
    private float lastRenderPitch = 0.0f;

    @Inject(method={"travel"}, at={@At(value="HEAD")}, cancellable=true)
    public void travel(float strafe, float vertical, float forward, CallbackInfo info) {
        if (this.equals(Minecraft.getMinecraft().player)) {
            PlayerTravelEvent event = new PlayerTravelEvent(strafe, vertical, forward);
            Zenith.INSTANCE.getEventManager().dispatchEvent(event);
            if (event.isCanceled()) {
                this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                info.cancel();
            }
        }
    }

    @Shadow
    public abstract ModelPlayer getMainModel();

    @Shadow
    protected abstract void setModelVisibilities(AbstractClientPlayer var1);

    @Inject(method={"doRender"}, at={@At(value="HEAD")})
    private void rotateBegin(AbstractClientPlayer entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci) {
        if (FeatureManager.getFeatureByClass(RotationViewer.class).isEnabled() && entity == Minecraft.getMinecraft().player) {
            this.prevRenderHeadYaw = entity.prevRotationYawHead;
            this.prevRenderPitch = entity.prevRotationPitch;
            this.renderPitch = entity.rotationPitch;
            this.renderYaw = entity.rotationYaw;
            this.renderHeadYaw = entity.rotationYawHead;
            entity.rotationPitch = RotationViewer.getPitch();
            entity.prevRotationPitch = this.lastRenderPitch;
            entity.rotationYaw = RotationViewer.getYaw();
            entity.rotationYawHead = RotationViewer.getYaw();
            entity.prevRotationYawHead = this.lastRenderHeadYaw;
        }
    }

    @Inject(method={"doRender"}, at={@At(value="RETURN")})
    private void rotateEnd(AbstractClientPlayer entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci) {
        if (FeatureManager.getFeatureByClass(RotationViewer.class).isEnabled() && entity == Minecraft.getMinecraft().player) {
            this.lastRenderHeadYaw = entity.rotationYawHead;
            this.lastRenderPitch = entity.rotationPitch;
            entity.rotationPitch = this.renderPitch;
            entity.rotationYaw = this.renderYaw;
            entity.rotationYawHead = this.renderHeadYaw;
            entity.prevRotationYawHead = this.prevRenderHeadYaw;
            entity.prevRotationPitch = this.prevRenderPitch;
        }
    }
}

