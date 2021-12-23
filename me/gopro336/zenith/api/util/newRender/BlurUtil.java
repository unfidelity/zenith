//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.newRender;

import me.gopro336.zenith.api.util.IGlobals;
import me.gopro336.zenith.api.util.newRender.GlStateUtils;
import me.gopro336.zenith.asm.mixin.mixins.accessor.IShaderGroup;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class BlurUtil
implements IGlobals {
    private static ShaderGroup blurShader;
    private static Framebuffer buffer;
    private static int lastScale;
    private static int lastScaleWidth;
    private static int lastScaleHeight;
    private static final ResourceLocation BLUR_LOCATION;

    public static void initFboAndShader() {
        try {
            blurShader = new ShaderGroup(mc.getTextureManager(), mc.getResourceManager(), mc.getFramebuffer(), BLUR_LOCATION);
            blurShader.createBindFramebuffers(BlurUtil.mc.displayWidth, BlurUtil.mc.displayHeight);
            buffer = ((IShaderGroup)blurShader).getMainFramebuffer();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setShaderConfigs(float intensity, float blurWidth, float blurHeight) {
        ((IShaderGroup)blurShader).getShaderList().get(0).getShaderManager().getShaderUniform("Radius").set(intensity);
        ((IShaderGroup)blurShader).getShaderList().get(1).getShaderManager().getShaderUniform("Radius").set(intensity);
        ((IShaderGroup)blurShader).getShaderList().get(0).getShaderManager().getShaderUniform("BlurDir").set(blurWidth, blurHeight);
        ((IShaderGroup)blurShader).getShaderList().get(1).getShaderManager().getShaderUniform("BlurDir").set(blurHeight, blurWidth);
    }

    public static void blurArea(int x, int y, int width, int height, float intensity, float blurWidth, float blurHeight) {
        ScaledResolution sr = new ScaledResolution(mc);
        int factor = sr.getScaleFactor();
        int factor2 = sr.getScaledWidth();
        int factor3 = sr.getScaledHeight();
        if (lastScale != factor || lastScaleWidth != factor2 || lastScaleHeight != factor3 || buffer == null || blurShader == null) {
            BlurUtil.initFboAndShader();
        }
        lastScale = factor;
        lastScaleWidth = factor2;
        lastScaleHeight = factor3;
        if (OpenGlHelper.isFramebufferEnabled()) {
            GL11.glScissor((int)(x * factor), (int)(BlurUtil.mc.displayHeight - y * factor - height * factor), (int)(width * factor), (int)(height * factor - 12));
            GL11.glEnable((int)3089);
            BlurUtil.setShaderConfigs(intensity, blurWidth, blurHeight);
            buffer.bindFramebuffer(true);
            blurShader.render(mc.getRenderPartialTicks());
            mc.getFramebuffer().bindFramebuffer(true);
            GL11.glDisable((int)3089);
            GlStateUtils.blend(true);
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
            buffer.framebufferRenderExt(BlurUtil.mc.displayWidth, BlurUtil.mc.displayHeight, false);
            GlStateUtils.blend(false);
            GL11.glScalef((float)factor, (float)factor, (float)0.0f);
        }
    }

    public static void blurArea(int x, int y, int width, int height, float intensity) {
        ScaledResolution scale = new ScaledResolution(mc);
        int factor = scale.getScaleFactor();
        int factor2 = scale.getScaledWidth();
        int factor3 = scale.getScaledHeight();
        if (lastScale != factor || lastScaleWidth != factor2 || lastScaleHeight != factor3 || buffer == null || blurShader == null) {
            BlurUtil.initFboAndShader();
        }
        lastScale = factor;
        lastScaleWidth = factor2;
        lastScaleHeight = factor3;
        buffer.framebufferClear();
        GL11.glScissor((int)(x * factor), (int)(BlurUtil.mc.displayHeight - y * factor - height * factor), (int)(width * factor), (int)(height * factor));
        GL11.glEnable((int)3089);
        BlurUtil.setShaderConfigs(intensity, 1.0f, 0.0f);
        buffer.bindFramebuffer(true);
        blurShader.render(mc.getRenderPartialTicks());
        mc.getFramebuffer().bindFramebuffer(true);
        GL11.glDisable((int)3089);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        buffer.framebufferRenderExt(BlurUtil.mc.displayWidth, BlurUtil.mc.displayHeight, false);
        GlStateManager.disableBlend();
        GL11.glScalef((float)factor, (float)factor, (float)0.0f);
        RenderHelper.enableGUIStandardItemLighting();
    }

    public static void blurAreaGey(int x, int y, int width, int height, float intensity) {
        ScaledResolution scale = new ScaledResolution(mc);
        int factor = scale.getScaleFactor();
        int factor2 = scale.getScaledWidth();
        int factor3 = scale.getScaledHeight();
        if (lastScale != factor || lastScaleWidth != factor2 || lastScaleHeight != factor3 || buffer == null || blurShader == null) {
            BlurUtil.initFboAndShader();
        }
        lastScale = factor;
        lastScaleWidth = factor2;
        lastScaleHeight = factor3;
        buffer.framebufferClear();
        GL11.glScissor((int)(x * factor), (int)(BlurUtil.mc.displayHeight - y * factor - height * factor), (int)(width * factor), (int)(height * factor));
        GL11.glEnable((int)3089);
        BlurUtil.setShaderConfigs(intensity, 1.0f, 0.0f);
        buffer.bindFramebuffer(true);
        blurShader.render(mc.getRenderPartialTicks());
        mc.getFramebuffer().bindFramebuffer(true);
        GL11.glDisable((int)3089);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        buffer.framebufferRenderExt(BlurUtil.mc.displayWidth, BlurUtil.mc.displayHeight, false);
        GlStateManager.disableBlend();
        GL11.glScalef((float)factor, (float)factor, (float)0.0f);
        RenderHelper.enableGUIStandardItemLighting();
    }

    public static void blurAreaBoarder(float x, float f, float width, float height, float intensity, float blurWidth, float blurHeight) {
        ScaledResolution scale = new ScaledResolution(mc);
        int factor = scale.getScaleFactor();
        int factor2 = scale.getScaledWidth();
        int factor3 = scale.getScaledHeight();
        if (lastScale != factor || lastScaleWidth != factor2 || lastScaleHeight != factor3 || buffer == null || blurShader == null) {
            BlurUtil.initFboAndShader();
        }
        lastScale = factor;
        lastScaleWidth = factor2;
        lastScaleHeight = factor3;
        GL11.glScissor((int)((int)(x * (float)factor)), (int)((int)((float)BlurUtil.mc.displayHeight - f * (float)factor - height * (float)factor) + 1), (int)((int)(width * (float)factor)), (int)((int)(height * (float)factor)));
        GL11.glEnable((int)3089);
        BlurUtil.setShaderConfigs(intensity, 1.0f, 0.0f);
        buffer.bindFramebuffer(true);
        blurShader.render(mc.getRenderPartialTicks());
        mc.getFramebuffer().bindFramebuffer(true);
        GL11.glDisable((int)3089);
    }

    public static void blurShape(float g, float f, float h, float height, float intensity, float blurWidth, float blurHeight) {
        ScaledResolution scale = new ScaledResolution(mc);
        int factor = scale.getScaleFactor();
        int factor2 = scale.getScaledWidth();
        int factor3 = scale.getScaledHeight();
        if (lastScale != factor || lastScaleWidth != factor2 || lastScaleHeight != factor3 || buffer == null || blurShader == null) {
            BlurUtil.initFboAndShader();
        }
        lastScale = factor;
        lastScaleWidth = factor2;
        lastScaleHeight = factor3;
        GL11.glScissor((int)((int)(g * (float)factor)), (int)((int)((float)BlurUtil.mc.displayHeight - f * (float)factor - height * (float)factor) + 1), (int)((int)(h * (float)factor)), (int)((int)(height * (float)factor)));
        GL11.glEnable((int)3089);
        BlurUtil.setShaderConfigs(intensity, 1.0f, 0.0f);
        buffer.bindFramebuffer(true);
        blurShader.render(mc.getRenderPartialTicks());
        mc.getFramebuffer().bindFramebuffer(true);
        GL11.glDisable((int)3089);
    }

    public static void blurAreaBoarder(int x, int y, int width, int height, float intensity, float blurWidth, float blurHeight) {
        ScaledResolution sr = new ScaledResolution(mc);
        int factor = sr.getScaleFactor();
        int factor2 = sr.getScaledWidth();
        int factor3 = sr.getScaledHeight();
        if (lastScale != factor || lastScaleWidth != factor2 || lastScaleHeight != factor3 || buffer == null || blurShader == null) {
            BlurUtil.initFboAndShader();
        }
        lastScale = factor;
        lastScaleWidth = factor2;
        lastScaleHeight = factor3;
        if (OpenGlHelper.isFramebufferEnabled()) {
            GL11.glScissor((int)(x * factor), (int)(BlurUtil.mc.displayHeight - y * factor - height * factor), (int)(width * factor), (int)(height * factor));
            GL11.glEnable((int)3089);
            BlurUtil.setShaderConfigs(intensity, blurWidth, blurHeight);
            buffer.bindFramebuffer(true);
            blurShader.render(mc.getRenderPartialTicks());
            mc.getFramebuffer().bindFramebuffer(true);
            GL11.glDisable((int)3089);
            GlStateUtils.matrix(true);
            GlStateUtils.blend(true);
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
            buffer.framebufferRenderExt(BlurUtil.mc.displayWidth, BlurUtil.mc.displayHeight, false);
            GlStateUtils.blend(false);
            GL11.glScalef((float)factor, (float)factor, (float)0.0f);
            RenderHelper.enableGUIStandardItemLighting();
            GlStateUtils.matrix(false);
        }
    }

    public static void blurAll(float intensity) {
        ScaledResolution scale = new ScaledResolution(mc);
        int factor = scale.getScaleFactor();
        int factor2 = scale.getScaledWidth();
        int factor3 = scale.getScaledHeight();
        if (lastScale != factor || lastScaleWidth != factor2 || lastScaleHeight != factor3 || buffer == null || blurShader == null) {
            BlurUtil.initFboAndShader();
        }
        lastScale = factor;
        lastScaleWidth = factor2;
        lastScaleHeight = factor3;
        BlurUtil.setShaderConfigs(intensity, 1.0f, 0.0f);
        buffer.bindFramebuffer(true);
        blurShader.render(mc.getRenderPartialTicks());
        mc.getFramebuffer().bindFramebuffer(true);
    }

    public static void boxBlurArea(int x, int y, int width, int height) {
        ScaledResolution scale = new ScaledResolution(mc);
        int factor = scale.getScaleFactor();
        int factor2 = scale.getScaledWidth();
        int factor3 = scale.getScaledHeight();
        lastScale = factor;
        lastScaleWidth = factor2;
        lastScaleHeight = factor3;
        GL11.glScissor((int)(x * factor), (int)(BlurUtil.mc.displayHeight - y * factor - height * factor), (int)(width * factor), (int)(height * factor));
        GL11.glEnable((int)3089);
        BlurUtil.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
        GL11.glDisable((int)3089);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableBlend();
        GL11.glScalef((float)factor, (float)factor, (float)0.0f);
        RenderHelper.enableGUIStandardItemLighting();
    }

    static {
        BLUR_LOCATION = new ResourceLocation("zenith/shader/blur.json");
    }
}

