/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.imixin;

import java.util.Map;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.client.renderer.RenderGlobal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={RenderGlobal.class})
public interface IMixinRenderGlobal {
    @Accessor
    public Map<Integer, DestroyBlockProgress> getDamagedBlocks();
}

