//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.feature.toggleable.render.NewChams;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEnderCrystal;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={RenderEnderCrystal.class}, priority=0x7FFFFFFE)
public abstract class MixinRenderEnderCrystal
extends Render<EntityEnderCrystal> {
    @Shadow
    public ModelBase modelEnderCrystal;
    @Shadow
    public ModelBase modelEnderCrystalNoBase;
    @Final
    @Shadow
    private static ResourceLocation ENDER_CRYSTAL_TEXTURES;

    protected MixinRenderEnderCrystal(RenderManager renderManager) {
        super(renderManager);
    }

    @Redirect(method={"doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
    private void doRender(ModelBase modelBase, Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (NewChams.INSTANCE.isEnabled() && NewChams.Field2119.getValue().booleanValue()) {
            if (NewChams.Field2119.getValue().booleanValue() && NewChams.Field2120.getValue().booleanValue()) {
                GL11.glScalef((float)((Float)NewChams.Field2135.getValue()).floatValue(), (float)((Float)NewChams.Field2135.getValue()).floatValue(), (float)((Float)NewChams.Field2135.getValue()).floatValue());
                if (!NewChams.Field2121.getValue().booleanValue()) {
                    GL11.glPushAttrib((int)1048575);
                    GL11.glDepthMask((boolean)false);
                    GL11.glDisable((int)2929);
                }
                modelBase.render(entityIn, limbSwing, limbSwingAmount * ((Float)NewChams.Field2136.getValue()).floatValue(), ageInTicks * ((Float)NewChams.Field2137.getValue()).floatValue(), netHeadYaw, headPitch, scale);
                if (!NewChams.Field2121.getValue().booleanValue()) {
                    GL11.glEnable((int)2929);
                    GL11.glDepthMask((boolean)true);
                    GL11.glPopAttrib();
                }
                GL11.glScalef((float)(1.0f / ((Float)NewChams.Field2135.getValue()).floatValue()), (float)(1.0f / ((Float)NewChams.Field2135.getValue()).floatValue()), (float)(1.0f / ((Float)NewChams.Field2135.getValue()).floatValue()));
            } else if (!NewChams.Field2119.getValue().booleanValue()) {
                modelBase.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            }
        } else if (NewChams.INSTANCE.isEnabled() && NewChams.Field2119.getValue().booleanValue()) {
            GL11.glScalef((float)((Float)NewChams.Field2135.getValue()).floatValue(), (float)((Float)NewChams.Field2135.getValue()).floatValue(), (float)((Float)NewChams.Field2135.getValue()).floatValue());
            modelBase.render(entityIn, limbSwing, limbSwingAmount * ((Float)NewChams.Field2136.getValue()).floatValue(), ageInTicks * ((Float)NewChams.Field2137.getValue()).floatValue(), netHeadYaw, headPitch, scale);
            GL11.glScalef((float)(1.0f / ((Float)NewChams.Field2135.getValue()).floatValue()), (float)(1.0f / ((Float)NewChams.Field2135.getValue()).floatValue()), (float)(1.0f / ((Float)NewChams.Field2135.getValue()).floatValue()));
        } else {
            modelBase.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }
}

