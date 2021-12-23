//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.asm.mixin.imixin.IMixinItemRenderer;
import me.gopro336.zenith.event.render.RenderOverlayEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ItemRenderer.class})
public abstract class MixinItemRenderer
implements IMixinItemRenderer {
    @Inject(method={"renderWaterOverlayTexture"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderWaterOverlayTexture(float partialTicks, CallbackInfo ci) {
        RenderOverlayEvent event = new RenderOverlayEvent(RenderOverlayEvent.OverlayType.LIQUID);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderFireInFirstPerson"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderFireInFirstPerson(CallbackInfo ci) {
        RenderOverlayEvent event = new RenderOverlayEvent(RenderOverlayEvent.OverlayType.FIRE);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderItemInFirstPerson(F)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderItemInFirstPerson(float partialTicks, CallbackInfo ci) {
        RenderOverlayEvent event = new RenderOverlayEvent(RenderOverlayEvent.OverlayType.ITEM);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Override
    @Accessor(value="equippedProgressOffHand")
    public abstract void setEquippedProgressOffHand(float var1);

    @Override
    @Accessor(value="equippedProgressMainHand")
    public abstract void setEquippedProgressMainHand(float var1);

    @Redirect(method={"renderItemInFirstPerson(F)V"}, at=@At(value="FIELD", target="Lnet/minecraft/client/entity/AbstractClientPlayer;prevRotationPitch:F"))
    private float redirectPrevPitch(AbstractClientPlayer player) {
        Minecraft mc = Minecraft.getMinecraft();
        Entity entity = mc.getRenderViewEntity();
        if (entity != null) {
            return entity.prevRotationPitch;
        }
        return mc.player.prevRotationPitch;
    }

    @Redirect(method={"renderItemInFirstPerson(F)V"}, at=@At(value="FIELD", target="Lnet/minecraft/client/entity/AbstractClientPlayer;rotationPitch:F"))
    private float redirectPitch(AbstractClientPlayer player) {
        Minecraft mc = Minecraft.getMinecraft();
        Entity entity = mc.getRenderViewEntity();
        if (entity != null) {
            return entity.rotationPitch;
        }
        return mc.player.rotationPitch;
    }

    @Redirect(method={"renderItemInFirstPerson(F)V"}, at=@At(value="FIELD", target="Lnet/minecraft/client/entity/AbstractClientPlayer;prevRotationYaw:F"))
    private float redirectPrevYaw(AbstractClientPlayer player) {
        Minecraft mc = Minecraft.getMinecraft();
        Entity entity = mc.getRenderViewEntity();
        if (entity != null) {
            return entity.prevRotationYaw;
        }
        return mc.player.prevRotationYaw;
    }

    @Redirect(method={"renderItemInFirstPerson(F)V"}, at=@At(value="FIELD", target="Lnet/minecraft/client/entity/AbstractClientPlayer;rotationYaw:F"))
    private float redirectYaw(AbstractClientPlayer player) {
        Minecraft mc = Minecraft.getMinecraft();
        Entity entity = mc.getRenderViewEntity();
        if (entity != null) {
            return entity.rotationYaw;
        }
        return mc.player.rotationYaw;
    }
}

