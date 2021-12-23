//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.hudElement.hudElement;

import me.gopro336.zenith.api.util.newRender.RenderUtils2D;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.hudElement.Element;
import me.gopro336.zenith.property.NumberProperty;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.world.World;

@AnnotationHelper(name="CrystalViewer", category=Category.HUD)
public class CrystalViewerElement
extends Element {
    public NumberProperty<Integer> scale = new NumberProperty<Integer>((Feature)this, "Scale", "", 0, Integer.valueOf(30), 100);
    EntityEnderCrystal crystal;

    public CrystalViewerElement() {
        this.crystal = new EntityEnderCrystal((World)this.mc.world);
        this.crystal.setShowBottom(false);
    }

    @Override
    public void onRender() {
        if (this.mc.world == null) {
            return;
        }
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
        RenderUtils2D.drawEntityOnScreen(this.x + 28.0f, this.y + 67.0f, (float)((Integer)this.scale.getValue()).intValue(), this.y + 13.0f, (Entity)this.crystal);
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
    }
}

