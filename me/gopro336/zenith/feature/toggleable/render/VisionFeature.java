//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.render;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.toggleable.render.VisionFeature;
import me.gopro336.zenith.property.Property;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.block.model.IBakedModel;
import org.jetbrains.annotations.NotNull;

@AnnotationHelper(name="Vision", description="Makes things easy to see", category=Category.RENDER)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R(\u0010\t\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R(\u0010\u0011\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010\u00a8\u0006\u0017"}, d2={"Lme/gopro336/zenith/feature/toggleable/render/VisionFeature;", "Lme/gopro336/zenith/feature/Feature;", "()V", "barrierModel", "Lnet/minecraft/client/renderer/block/model/IBakedModel;", "getBarrierModel", "()Lnet/minecraft/client/renderer/block/model/IBakedModel;", "barrierModel$delegate", "Lkotlin/Lazy;", "barriers", "Lme/gopro336/zenith/property/Property;", "", "kotlin.jvm.PlatformType", "getBarriers", "()Lme/gopro336/zenith/property/Property;", "setBarriers", "(Lme/gopro336/zenith/property/Property;)V", "brightness", "getBrightness", "setBrightness", "onDisable", "", "onEnable", "zenith"})
public final class VisionFeature
extends Feature {
    @NotNull
    private static Property<Boolean> brightness;
    @NotNull
    private static Property<Boolean> barriers;
    @NotNull
    private static final Lazy barrierModel$delegate;
    public static final VisionFeature INSTANCE;

    @NotNull
    public final Property<Boolean> getBrightness() {
        return brightness;
    }

    public final void setBrightness(@NotNull Property<Boolean> property) {
        Intrinsics.checkParameterIsNotNull(property, "<set-?>");
        brightness = property;
    }

    @NotNull
    public final Property<Boolean> getBarriers() {
        return barriers;
    }

    public final void setBarriers(@NotNull Property<Boolean> property) {
        Intrinsics.checkParameterIsNotNull(property, "<set-?>");
        barriers = property;
    }

    @NotNull
    public final IBakedModel getBarrierModel() {
        Lazy lazy = barrierModel$delegate;
        VisionFeature visionFeature = this;
        Object var3_3 = null;
        boolean bl = false;
        return (IBakedModel)lazy.getValue();
    }

    @Override
    public void onEnable() {
        block0: {
            RenderGlobal renderGlobal = Feature.mc.renderGlobal;
            if (renderGlobal == null) break block0;
            renderGlobal.loadRenderers();
        }
    }

    @Override
    public void onDisable() {
        block0: {
            RenderGlobal renderGlobal = Feature.mc.renderGlobal;
            if (renderGlobal == null) break block0;
            renderGlobal.loadRenderers();
        }
    }

    private VisionFeature() {
    }

    static {
        VisionFeature visionFeature;
        INSTANCE = visionFeature = new VisionFeature();
        brightness = new Property<Boolean>(visionFeature, "Brightness", "", true);
        barriers = new Property<Boolean>(visionFeature, "Barriers", "", true);
        barrierModel$delegate = LazyKt.lazy(barrierModel.2.INSTANCE);
    }
}

