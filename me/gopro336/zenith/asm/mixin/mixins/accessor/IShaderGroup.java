/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins.accessor;

import java.util.List;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={ShaderGroup.class})
public interface IShaderGroup {
    @Accessor(value="listShaders")
    public List<Shader> getShaderList();

    @Accessor(value="mainFramebuffer")
    public Framebuffer getMainFramebuffer();
}

