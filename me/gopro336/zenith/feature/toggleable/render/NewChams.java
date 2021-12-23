//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.render;

import java.awt.Color;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@AnnotationHelper(name="Chams", description="", category=Category.RENDER)
public class NewChams
extends Feature {
    public static NewChams INSTANCE = new NewChams();
    public static Property<Boolean> Field2096;
    public static Property<Boolean> Field2098;
    public static Property<Boolean> Field2099;
    public static Property<Class495> Field2100;
    public static Property<Boolean> Field2101;
    public static NumberProperty<Float> Field2102;
    public static NumberProperty<Float> Field2103;
    public static Property<Color> Field2104;
    public static Property<Boolean> Field2105;
    public static Property<Boolean> Field2106;
    public static Property<Boolean> Field2107;
    public static Property<Color> Field2108;
    public static Property<Boolean> Field2109;
    public static Property<Boolean> Field2110;
    public static NumberProperty<Float> Field2111;
    public static Property<Color> Field2112;
    public static Property<Boolean> Field2113;
    public static Property<Boolean> Field2114;
    public static Property<Boolean> Field2115;
    public static Property<Boolean> Field2116;
    public static Property<Boolean> Field2117;
    public static Property<Boolean> Field2118;
    public static Property<Boolean> Field2119;
    public static Property<Boolean> Field2120;
    public static Property<Boolean> Field2121;
    public static Property<Class495> Field2122;
    public static Property<Boolean> Field2123;
    public static NumberProperty<Float> Field2124;
    public static NumberProperty<Float> Field2125;
    public static Property<Color> Field2126;
    public static Property<Boolean> Field2127;
    public static Property<Boolean> Field2128;
    public static Property<Boolean> Field2129;
    public static Property<Color> Field2130;
    public static Property<Boolean> Field2131;
    public static Property<Boolean> Field2132;
    public static NumberProperty<Float> Field2133;
    public static Property<Color> Field2134;
    public static NumberProperty<Float> Field2135;
    public static NumberProperty<Float> Field2136;
    public static NumberProperty<Float> Field2137;
    public static Property<Boolean> Field2138;
    public static Property<Boolean> Field2139;
    public static Property<Color> Field2140;
    public static Property<Class495> Field2141;
    public static NumberProperty<Float> Field2142;
    public static NumberProperty<Float> Field2143;
    public static Property<Color> Field2144;
    public static ResourceLocation Field2145;
    public static ResourceLocation Field2146;
    public static boolean Field2147;

    public NewChams() {
        Field2096 = new Property<Boolean>(this, "Living", "", false);
        Field2098 = new Property<Boolean>((Feature)this, Field2096, "Render", "", false);
        Field2099 = new Property<Boolean>((Feature)this, Field2096, "RenderDepth", "", false);
        Field2100 = new Property<Class495>((Feature)this, Field2096, "Glint", "", Class495.NONE);
        Field2101 = new Property<Boolean>((Feature)this, Field2096, "GlintDepth", "", false);
        Field2102 = new NumberProperty<Float>((Feature)this, Field2096, "GlintSpeed ", "", Float.valueOf(0.1f), Float.valueOf(5.0f), Float.valueOf(20.0f));
        Field2103 = new NumberProperty<Float>((Feature)this, Field2096, "GlintScale ", "", Float.valueOf(0.1f), Float.valueOf(1.0f), Float.valueOf(10.0f));
        Field2104 = new Property<Color>((Feature)this, Field2096, "GlintColor", "", new Color(0x7700FFFF));
        Field2105 = new Property<Boolean>((Feature)this, Field2096, "Fill", "", false);
        Field2106 = new Property<Boolean>((Feature)this, Field2096, "FillDepth", "", false);
        Field2107 = new Property<Boolean>((Feature)this, Field2096, "Lighting", "", false);
        Field2108 = new Property<Color>((Feature)this, Field2096, "FillColor", "", new Color(0x7700FFFF));
        Field2109 = new Property<Boolean>((Feature)this, Field2096, "Outline", "", false);
        Field2110 = new Property<Boolean>((Feature)this, Field2096, "OutlineDepth", "", false);
        Field2111 = new NumberProperty<Float>((Feature)this, Field2096, "Width ", "", Float.valueOf(0.1f), Float.valueOf(1.0f), Float.valueOf(10.0f));
        Field2112 = new Property<Color>((Feature)this, Field2096, "GOutlineColor", "", new Color(-16711681));
        Field2113 = new Property<Boolean>((Feature)this, Field2096, "Players", "", true);
        Field2114 = new Property<Boolean>((Feature)this, Field2096, "Self", "", true);
        Field2115 = new Property<Boolean>((Feature)this, Field2096, "Animals", "", true);
        Field2116 = new Property<Boolean>((Feature)this, Field2096, "Monsters", "", true);
        Field2117 = new Property<Boolean>((Feature)this, Field2096, "Invis", "", true);
        Field2118 = new Property<Boolean>(this, "Crystal", "", false);
        Field2119 = new Property<Boolean>((Feature)this, Field2118, "Crystals", "", true);
        Field2120 = new Property<Boolean>((Feature)this, Field2118, "CRender", "", false);
        Field2121 = new Property<Boolean>((Feature)this, Field2118, "CRenderDepth", "", false);
        Field2122 = new Property<Class495>((Feature)this, Field2118, "CGlint", "", Class495.NONE);
        Field2123 = new Property<Boolean>((Feature)this, Field2118, "CGlintDepth", "", false);
        Field2124 = new NumberProperty<Float>((Feature)this, Field2118, "CGlintSpeed ", "", Float.valueOf(0.1f), Float.valueOf(5.0f), Float.valueOf(20.0f));
        Field2125 = new NumberProperty<Float>((Feature)this, Field2118, "CGlintScale ", "", Float.valueOf(0.1f), Float.valueOf(1.0f), Float.valueOf(10.0f));
        Field2126 = new Property<Color>((Feature)this, Field2118, "CGlintColor", "", new Color(0x770000FF));
        Field2127 = new Property<Boolean>((Feature)this, Field2118, "CFill", "", false);
        Field2128 = new Property<Boolean>((Feature)this, Field2118, "CFillDepth", "", false);
        Field2129 = new Property<Boolean>((Feature)this, Field2118, "CLighting", "", false);
        Field2130 = new Property<Color>((Feature)this, Field2118, "CFillColor", "", new Color(0x770000FF));
        Field2131 = new Property<Boolean>((Feature)this, Field2118, "COutline", "", false);
        Field2132 = new Property<Boolean>((Feature)this, Field2118, "COutlineDepth", "", false);
        Field2133 = new NumberProperty<Float>((Feature)this, Field2118, "CWidth ", "", Float.valueOf(0.1f), Float.valueOf(1.0f), Float.valueOf(10.0f));
        Field2134 = new Property<Color>((Feature)this, Field2118, "CGOutlineColor", "", new Color(-16776961));
        Field2135 = new NumberProperty<Float>((Feature)this, Field2118, "Scale ", "", Float.valueOf(0.1f), Float.valueOf(1.0f), Float.valueOf(10.0f));
        Field2136 = new NumberProperty<Float>((Feature)this, Field2118, "SpinSpeed ", "", Float.valueOf(0.1f), Float.valueOf(1.0f), Float.valueOf(10.0f));
        Field2137 = new NumberProperty<Float>((Feature)this, Field2118, "Bounciness ", "", Float.valueOf(0.1f), Float.valueOf(1.0f), Float.valueOf(10.0f));
        Field2138 = new Property<Boolean>(this, "Hand", "", false);
        Field2139 = new Property<Boolean>((Feature)this, Field2138, "Hands", "", true);
        Field2140 = new Property<Color>((Feature)this, Field2138, "HandColor", "", new Color(469762303));
        Field2141 = new Property<Class495>((Feature)this, Field2138, "HGlint", "", Class495.NONE);
        Field2142 = new NumberProperty<Float>((Feature)this, Field2138, "HGlintSpeed ", "", Float.valueOf(0.1f), Float.valueOf(5.0f), Float.valueOf(20.0f));
        Field2143 = new NumberProperty<Float>((Feature)this, Field2138, "HGlintScale ", "", Float.valueOf(0.1f), Float.valueOf(1.0f), Float.valueOf(10.0f));
        Field2144 = new Property<Color>((Feature)this, Field2138, "HGlintColor", "", new Color(0x770000FF));
        this.setInstance();
    }

    public static NewChams getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NewChams();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    public static boolean Method513(Entity entity) {
        if (entity == null) {
            return false;
        }
        if (entity.isInvisible() && !Field2117.getValue().booleanValue()) {
            return false;
        }
        if (entity.equals((Object)NewChams.mc.player) && !Field2114.getValue().booleanValue()) {
            return false;
        }
        if (entity instanceof EntityPlayer && !Field2113.getValue().booleanValue()) {
            return false;
        }
        if (NewChams.Method386(entity) && !Field2115.getValue().booleanValue()) {
            return false;
        }
        return NewChams.Method386(entity) || entity instanceof EntityPlayer || Field2116.getValue() != false;
    }

    public static void Method1948(Color class440, ModelBase modelBase, Entity entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)1048575);
        GL11.glPolygonMode((int)1032, (int)6914);
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(Field2100.getValue() == Class495.CUSTOM ? Field2146 : Field2145);
        GL11.glPolygonMode((int)1032, (int)6914);
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glColor4f((float)((float)NewChams.Method769(class440.getRGB()) / 255.0f), (float)((float)NewChams.Method770(class440.getRGB()) / 255.0f), (float)((float)NewChams.Method779(class440.getRGB()) / 255.0f), (float)((float)NewChams.Method782(class440.getRGB()) / 255.0f));
        GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_COLOR, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE);
        for (int i = 0; i < 2; ++i) {
            GlStateManager.matrixMode((int)5890);
            GlStateManager.loadIdentity();
            float f8 = 0.33333334f * ((Float)Field2103.getValue()).floatValue();
            GlStateManager.scale((float)f8, (float)f8, (float)f8);
            GlStateManager.rotate((float)(30.0f - (float)i * 60.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GlStateManager.translate((float)0.0f, (float)(((float)entity.ticksExisted + mc.getRenderPartialTicks()) * (0.001f + (float)i * 0.003f) * ((Float)Field2102.getValue()).floatValue()), (float)0.0f);
            GlStateManager.matrixMode((int)5888);
            GL11.glTranslatef((float)f7, (float)f7, (float)f7);
            GlStateManager.color((float)((float)NewChams.Method769(class440.getRGB()) / 255.0f), (float)((float)NewChams.Method770(class440.getRGB()) / 255.0f), (float)((float)NewChams.Method779(class440.getRGB()) / 255.0f), (float)((float)NewChams.Method782(class440.getRGB()) / 255.0f));
            if (Field2101.getValue().booleanValue()) {
                GL11.glDepthMask((boolean)true);
                GL11.glEnable((int)2929);
            }
            modelBase.render(entity, f, f2, f3, f4, f5, f6);
            if (!Field2101.getValue().booleanValue()) continue;
            GL11.glDisable((int)2929);
            GL11.glDepthMask((boolean)false);
        }
        GlStateManager.matrixMode((int)5890);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode((int)5888);
        GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public static void Method1950(Color class440, ModelBase modelBase, Entity entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)1048575);
        GL11.glPolygonMode((int)1032, (int)6914);
        GL11.glDisable((int)3553);
        if (Field2107.getValue().booleanValue()) {
            GL11.glEnable((int)2896);
        } else {
            GL11.glDisable((int)2896);
        }
        GL11.glDisable((int)2929);
        GL11.glEnable((int)2848);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glTranslatef((float)f7, (float)f7, (float)f7);
        GlStateManager.color((float)((float)NewChams.Method769(class440.getRGB()) / 255.0f), (float)((float)NewChams.Method770(class440.getRGB()) / 255.0f), (float)((float)NewChams.Method779(class440.getRGB()) / 255.0f), (float)((float)NewChams.Method782(class440.getRGB()) / 255.0f));
        if (Field2106.getValue().booleanValue()) {
            GL11.glDepthMask((boolean)true);
            GL11.glEnable((int)2929);
        }
        modelBase.render(entity, f, f2, f3, f4, f5, f6);
        if (Field2106.getValue().booleanValue()) {
            GL11.glDisable((int)2929);
            GL11.glDepthMask((boolean)false);
        }
        GlStateManager.resetColor();
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public static void Method1951(Color class440, float f, ModelBase modelBase, Entity entity, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)1048575);
        GL11.glPolygonMode((int)1032, (int)6913);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
        GL11.glEnable((int)2848);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glTranslatef((float)f8, (float)f8, (float)f8);
        GlStateManager.color((float)((float)NewChams.Method769(class440.getRGB()) / 255.0f), (float)((float)NewChams.Method770(class440.getRGB()) / 255.0f), (float)((float)NewChams.Method779(class440.getRGB()) / 255.0f), (float)((float)NewChams.Method782(class440.getRGB()) / 255.0f));
        GlStateManager.glLineWidth((float)f);
        if (Field2110.getValue().booleanValue()) {
            GL11.glDepthMask((boolean)true);
            GL11.glEnable((int)2929);
        }
        modelBase.render(entity, f2, f3, f4, f5, f6, f7);
        if (Field2110.getValue().booleanValue()) {
            GL11.glDisable((int)2929);
            GL11.glDepthMask((boolean)false);
        }
        GlStateManager.resetColor();
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public static boolean renderEntityChams(ModelBase modelBase, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
        if (!NewChams.Method513(entity)) {
            return false;
        }
        boolean bl = NewChams.mc.gameSettings.fancyGraphics;
        NewChams.mc.gameSettings.fancyGraphics = false;
        if (Field2098.getValue().booleanValue()) {
            if (!Field2099.getValue().booleanValue()) {
                OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
                GL11.glEnable((int)32823);
                GL11.glPolygonOffset((float)1.0f, (float)-1100000.0f);
            }
            modelBase.render(entity, f, f2, f3, f4, f5, f6);
            if (!Field2099.getValue().booleanValue()) {
                GL11.glDisable((int)32823);
                GL11.glPolygonOffset((float)1.0f, (float)1100000.0f);
            }
        }
        float f7 = NewChams.mc.gameSettings.gammaSetting;
        NewChams.mc.gameSettings.gammaSetting = 10000.0f;
        if (Field2105.getValue().booleanValue()) {
            NewChams.Method1950(Field2108.getValue(), modelBase, entity, f, f2, f3, f4, f5, f6, 0.0f);
        }
        if (Field2109.getValue().booleanValue()) {
            NewChams.Method1951(Field2112.getValue(), ((Float)Field2111.getValue()).floatValue(), modelBase, entity, f, f2, f3, f4, f5, f6, 0.0f);
        }
        if (Field2100.getValue() != Class495.NONE) {
            NewChams.Method1948(Field2104.getValue(), modelBase, entity, f, f2, f3, f4, f5, f6, 0.0f);
        }
        try {
            NewChams.mc.gameSettings.fancyGraphics = bl;
            NewChams.mc.gameSettings.gammaSetting = f7;
        }
        catch (Exception exception) {
            // empty catch block
        }
        return true;
    }

    public static boolean Method386(Entity entity) {
        if (entity instanceof EntityWolf) {
            return !((EntityWolf)entity).isAngry();
        }
        if (entity instanceof EntityAgeable || entity instanceof EntityTameable || entity instanceof EntityAmbientCreature || entity instanceof EntitySquid) {
            return true;
        }
        if (entity instanceof EntityIronGolem) {
            return ((EntityIronGolem)entity).getRevengeTarget() == null;
        }
        return false;
    }

    public static int Method769(int Field725) {
        return Field725 >> 16 & 0xFF;
    }

    public static int Method770(int Field725) {
        return Field725 >> 8 & 0xFF;
    }

    public static int Method779(int Field725) {
        return Field725 & 0xFF;
    }

    public static int Method782(int Field725) {
        return Field725 >> 24 & 0xFF;
    }

    static {
        Field2145 = new ResourceLocation("textures/misc/enchanted_item_glint.png");
        Field2146 = new ResourceLocation("konas/textures/enchant_glint.png");
        Field2147 = false;
    }

    public static enum Class495 {
        NONE,
        VANILLA,
        CUSTOM;

    }
}

