//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.newRender;

import java.awt.Color;
import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.asm.mixin.imixin.IRenderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class RenderUtils3D {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final Tessellator tessellator = Tessellator.getInstance();

    public static void prepareGL() {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
    }

    public static void releaseGL() {
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static AxisAlignedBB generateBB(long x, long y, long z) {
        BlockPos blockPos = new BlockPos((double)x, (double)y, (double)z);
        return new AxisAlignedBB((double)blockPos.getX() - RenderUtils3D.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtils3D.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtils3D.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtils3D.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtils3D.mc.getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - RenderUtils3D.mc.getRenderManager().viewerPosZ);
    }

    public static void draw(BlockPos blockPos, boolean box, boolean outline, double boxHeight, double outlineHeight, Color colour) {
        AxisAlignedBB axisAlignedBB = RenderUtils3D.generateBB(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        RenderUtils3D.prepareGL();
        if (box) {
            RenderUtils3D.drawFilledBox(axisAlignedBB, boxHeight, (float)colour.getRed() / 255.0f, (float)colour.getGreen() / 255.0f, (float)colour.getBlue() / 255.0f, (float)colour.getAlpha() / 255.0f);
        }
        if (outline) {
            RenderUtils3D.drawBoundingBox(axisAlignedBB, outlineHeight, (float)colour.getRed() / 255.0f, (float)colour.getGreen() / 255.0f, (float)colour.getBlue() / 255.0f, (float)colour.getAlpha() / 255.0f);
        }
        RenderUtils3D.releaseGL();
    }

    public static void drawBoundingBox(BufferBuilder bufferBuilder, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
        bufferBuilder.pos(minX, minY, minZ).color(red, green, blue, 0.0f).endVertex();
        bufferBuilder.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(minX, maxY, maxZ).color(red, green, blue, 0.0f).endVertex();
        bufferBuilder.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(maxX, maxY, maxZ).color(red, green, blue, 0.0f).endVertex();
        bufferBuilder.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(maxX, maxY, minZ).color(red, green, blue, 0.0f).endVertex();
        bufferBuilder.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(maxX, minY, minZ).color(red, green, blue, 0.0f).endVertex();
    }

    public static void addChainedFilledBoxVertices(BufferBuilder bufferBuilder, double x1, double y1, double z1, double x2, double y2, double z2, float red, float green, float blue, float alpha) {
        bufferBuilder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x1, y2, z2).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x1, y2, z2).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x2, y2, z1).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x2, y2, z1).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x1, y2, z2).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x2, y2, z1).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
        bufferBuilder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
    }

    public static void renderFilledBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, double height, float red, float green, float blue, float alpha) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        RenderUtils3D.addChainedFilledBoxVertices(bufferbuilder, minX, minY, minZ, maxX, maxY + height, maxZ, red, green, blue, alpha);
        tessellator.draw();
    }

    public static void renderBoundingBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, double height, float red, float green, float blue, float alpha) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        RenderUtils3D.drawBoundingBox(bufferbuilder, minX, minY, minZ, maxX, maxY + height, maxZ, red, green, blue, alpha);
        tessellator.draw();
    }

    public static void drawFilledBox(AxisAlignedBB axisAlignedBB, double height, float red, float green, float blue, float alpha) {
        RenderUtils3D.renderFilledBox(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, height, red, green, blue, alpha);
    }

    public static void drawBoundingBox(AxisAlignedBB axisAlignedBB, double height, float red, float green, float blue, float alpha) {
        RenderUtils3D.renderBoundingBox(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, height, red, green, blue, alpha);
    }

    public static void drawNametagFromBlockPos(BlockPos pos, float height, String text) {
        GlStateManager.pushMatrix();
        RenderUtils3D.glBillboardDistanceScaled((float)pos.getX() + 0.5f, (float)pos.getY() + height, (float)pos.getZ() + 0.5f, (EntityPlayer)RenderUtils3D.mc.player, 1.0f);
        GlStateManager.disableDepth();
        GlStateManager.translate((double)(-((double)RenderUtils3D.mc.fontRenderer.getStringWidth(text) / 2.0)), (double)0.0, (double)0.0);
        FontUtil.drawString(text, 0.0, 0.0, -1);
        GlStateManager.popMatrix();
    }

    public static void glBillboardDistanceScaled(float x, float y, float z, EntityPlayer player, float scale) {
        RenderUtils3D.glBillboard(x, y, z);
        int distance = (int)player.getDistance((double)x, (double)y, (double)z);
        float scaleDistance = (float)distance / 2.0f / (2.0f + (2.0f - scale));
        if (scaleDistance < 1.0f) {
            scaleDistance = 1.0f;
        }
        GlStateManager.scale((float)scaleDistance, (float)scaleDistance, (float)scaleDistance);
    }

    public static void glBillboard(float x, float y, float z) {
        float scale = 0.02666667f;
        GlStateManager.translate((double)((double)x - ((IRenderManager)mc.getRenderManager()).getRenderPosX()), (double)((double)y - ((IRenderManager)mc.getRenderManager()).getRenderPosY()), (double)((double)z - ((IRenderManager)mc.getRenderManager()).getRenderPosZ()));
        GlStateManager.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)(-RenderUtils3D.mc.player.rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)RenderUtils3D.mc.player.rotationPitch, (float)(RenderUtils3D.mc.gameSettings.thirdPersonView == 2 ? -1.0f : 1.0f), (float)0.0f, (float)0.0f);
        GlStateManager.scale((float)(-scale), (float)(-scale), (float)scale);
    }

    public static void drawBoundingBox(AxisAlignedBB box, float red, float green, float blue, float alpha) {
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, 0.0f).endVertex();
        buffer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(box.maxX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(box.maxX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(box.minX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(box.maxX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(box.maxX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(box.minX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(box.minX, box.maxY, box.maxZ).color(red, green, blue, 0.0f).endVertex();
        buffer.pos(box.minX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(box.maxX, box.maxY, box.maxZ).color(red, green, blue, 0.0f).endVertex();
        buffer.pos(box.maxX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(box.maxX, box.maxY, box.minZ).color(red, green, blue, 0.0f).endVertex();
        buffer.pos(box.maxX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(box.maxX, box.minY, box.minZ).color(red, green, blue, 0.0f).endVertex();
        tessellator.draw();
    }

    public static void beginRender() {
        GlStateManager.blendFunc((int)770, (int)771);
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.enableBlend();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.resetColor();
    }

    public static void endRender() {
        GlStateManager.resetColor();
        GlStateManager.enableCull();
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
    }
}

