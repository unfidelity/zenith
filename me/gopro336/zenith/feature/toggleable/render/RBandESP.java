//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.render;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import me.gopro336.zenith.event.network.PacketReceiveEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="RBandESP", description="renders ruberbands", category=Category.RENDER)
public class RBandESP
extends Feature {
    public NumberProperty<Integer> fadeTime = new NumberProperty<Integer>((Feature)this, "FadeTime", "time for rendered objects to fade", 1, Integer.valueOf(3000), 5000);
    public NumberProperty<Float> lineWidth = new NumberProperty<Float>((Feature)this, "LineWidth", "line width for render", Float.valueOf(0.1f), Float.valueOf(1.0f), Float.valueOf(3.0f));
    public Property<Color> color = new Property<Color>(this, "Color", "render color", new Color(0, 255, 255, 255));
    private final HashMap<EntityOtherPlayerMP, Long> popFakePlayerMap = new HashMap();

    @SubscribeEvent
    public void onRenderLast(RenderWorldLastEvent event) {
        for (Map.Entry<EntityOtherPlayerMP, Long> entry : new HashMap<EntityOtherPlayerMP, Long>(this.popFakePlayerMap).entrySet()) {
            if (System.currentTimeMillis() - entry.getValue() > (long)((Integer)this.fadeTime.getValue()).intValue()) {
                this.popFakePlayerMap.remove(entry.getKey());
                continue;
            }
            GL11.glPushMatrix();
            GL11.glDepthRange((double)0.0, (double)0.01);
            GL11.glDisable((int)2896);
            GL11.glDisable((int)3553);
            GL11.glPolygonMode((int)1032, (int)6913);
            GL11.glEnable((int)3008);
            GL11.glEnable((int)3042);
            GL11.glLineWidth((float)((Float)this.lineWidth.getValue()).floatValue());
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            this.glColor();
            this.renderEntity((Entity)entry.getKey(), event.getPartialTicks(), false);
            GL11.glHint((int)3154, (int)4352);
            GL11.glPolygonMode((int)1032, (int)6914);
            GL11.glEnable((int)2896);
            GL11.glDepthRange((double)0.0, (double)1.0);
            GL11.glEnable((int)3553);
            GL11.glPopMatrix();
        }
    }

    @Listener
    public void onPacket(PacketReceiveEvent event) {
        for (EntityPlayer e : RBandESP.mc.world.playerEntities) {
            Entity entity;
            if (!(event.getPacket() instanceof SPacketPlayerPosLook) || RBandESP.mc.world.getEntityByID(e.getEntityId()) == null || !((entity = RBandESP.mc.world.getEntityByID(e.getEntityId())) instanceof EntityPlayer)) continue;
            EntityPlayer player = (EntityPlayer)entity;
            EntityOtherPlayerMP fakeEntity = new EntityOtherPlayerMP((World)RBandESP.mc.world, player.getGameProfile());
            fakeEntity.copyLocationAndAnglesFrom((Entity)player);
            fakeEntity.rotationYawHead = player.rotationYawHead;
            fakeEntity.prevRotationYawHead = player.rotationYawHead;
            fakeEntity.rotationYaw = player.rotationYaw;
            fakeEntity.prevRotationYaw = player.rotationYaw;
            fakeEntity.rotationPitch = player.rotationPitch;
            fakeEntity.prevRotationPitch = player.rotationPitch;
            fakeEntity.cameraYaw = fakeEntity.rotationYaw;
            fakeEntity.cameraPitch = fakeEntity.rotationPitch;
            this.popFakePlayerMap.put(fakeEntity, System.currentTimeMillis());
        }
    }

    private void glColor() {
        Color clr = this.color.getValue();
        GL11.glColor4f((float)((float)clr.getRed() / 255.0f), (float)((float)clr.getGreen() / 255.0f), (float)((float)clr.getBlue() / 255.0f), (float)((float)this.color.getValue().getAlpha() / 255.0f));
    }

    public void renderEntity(Entity entityIn, float partialTicks, boolean p_188388_3_) {
        if (entityIn.ticksExisted == 0) {
            entityIn.lastTickPosX = entityIn.posX;
            entityIn.lastTickPosY = entityIn.posY;
            entityIn.lastTickPosZ = entityIn.posZ;
        }
        double d0 = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double)partialTicks;
        double d1 = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double)partialTicks;
        double d2 = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double)partialTicks;
        float f = entityIn.prevRotationYaw + (entityIn.rotationYaw - entityIn.prevRotationYaw) * partialTicks;
        int i = entityIn.getBrightnessForRender();
        if (entityIn.isBurning()) {
            i = 0xF000F0;
        }
        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
        mc.getRenderManager().renderEntity(entityIn, d0 - RBandESP.mc.getRenderManager().viewerPosX, d1 - RBandESP.mc.getRenderManager().viewerPosY, d2 - RBandESP.mc.getRenderManager().viewerPosZ, f, partialTicks, p_188388_3_);
    }
}

