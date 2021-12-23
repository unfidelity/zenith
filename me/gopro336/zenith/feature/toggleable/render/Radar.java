//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.render;

import java.awt.Color;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import me.gopro336.zenith.api.util.color.RenderUtil;
import me.gopro336.zenith.asm.mixin.imixin.IRenderManager;
import me.gopro336.zenith.asm.mixin.mixins.accessor.IEntityRenderer;
import me.gopro336.zenith.event.render.Render3DEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.toggleable.render.Rotation;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="Radar", description="ArrowRadar", category=Category.RENDER)
public class Radar
extends Feature {
    public Property<Boolean> players = new Property<Boolean>(this, "players", "Players", true);
    public Property<Boolean> friends = new Property<Boolean>(this, "friends", "Friends", true);
    public Property<Boolean> bosses = new Property<Boolean>(this, "bosses", "Bosses", true);
    public Property<Boolean> hostiles = new Property<Boolean>(this, "hostiles", "Hostiles", true);
    public Property<Boolean> passives = new Property<Boolean>(this, "passives", "Passives", true);
    public Property<Boolean> items = new Property<Boolean>(this, "items", "Items", false);
    public Property<Boolean> other = new Property<Boolean>(this, "other", "Other", false);
    public Property<Color> nearColor = new Property<Color>(this, "NearColor", "", new Color(255, 255, 255, 255));
    public Property<Color> farColor = new Property<Color>(this, "FarColor", "", new Color(255, 255, 255, 255));
    public NumberProperty<Double> scale = new NumberProperty<Double>((Feature)this, "Scale", "", 0.0, Double.valueOf(1.0), 10.0);
    public NumberProperty<Double> distance = new NumberProperty<Double>((Feature)this, "Distance", "", 0.0, Double.valueOf(1.0), 10.0);
    public NumberProperty<Double> changeradius = new NumberProperty<Double>((Feature)this, "Color Change Radius", "", 0.0, Double.valueOf(75.0), 1000.0);
    public Property<Boolean> hideFrustrum = new Property<Boolean>(this, "Hide In Frustrum", "Hide entities you can see", false);
    public NumberProperty<Integer> tilt = new NumberProperty<Integer>((Feature)this, "Tilt", "", -90, Integer.valueOf(50), 90);
    public Property<Boolean> unlockTilt = new Property<Boolean>(this, "Unlock Tilt", "Unlock tilt when you look down", false);

    @Override
    @Listener
    public void onRender3D(Render3DEvent event) {
        if (Radar.mc.player != null) {
            Entity entity3;
            Entity entity2;
            Entity entity = mc.getRenderViewEntity();
            if (entity == null) {
                Intrinsics.throwNpe();
            }
            if ((entity2 = mc.getRenderViewEntity()) == null) {
                Intrinsics.throwNpe();
            }
            if ((entity3 = mc.getRenderViewEntity()) == null) {
                Intrinsics.throwNpe();
            }
            RenderUtil.camera.setPosition(entity.posX, entity2.posY, entity3.posZ);
            GlStateManager.pushMatrix();
            WorldClient worldClient = Radar.mc.world;
            if (worldClient == null) {
                Intrinsics.throwNpe();
            }
            for (Entity entity4 : worldClient.loadedEntityList) {
                if (entity4 == mc.getRenderViewEntity() || this.hideFrustrum.getValue().booleanValue() && RenderUtil.camera.isBoundingBoxInFrustum(entity4.getEntityBoundingBox())) continue;
                if (entity4 instanceof EntityPlayer) {
                    if (!this.players.getValue().booleanValue()) continue;
                    this.drawArrow(entity4);
                    continue;
                }
                if (entity4 instanceof EntityDragon || entity4 instanceof EntityWither) {
                    if (!this.bosses.getValue().booleanValue()) continue;
                    this.drawArrow(entity4);
                    continue;
                }
                if (entity4.isCreatureType(EnumCreatureType.MONSTER, false)) {
                    if (!this.hostiles.getValue().booleanValue()) continue;
                    this.drawArrow(entity4);
                    continue;
                }
                if (entity4 instanceof EntityItem) {
                    if (!this.items.getValue().booleanValue()) continue;
                    this.drawArrow(entity4);
                    continue;
                }
                if (!this.other.getValue().booleanValue()) continue;
                this.drawArrow(entity4);
            }
            GlStateManager.popMatrix();
        }
    }

    @NotNull
    public Rotation getRotation(@NotNull Vec3d vec3d, @NotNull Vec3d vec3d2) {
        double d = vec3d2.x - vec3d.x;
        double d2 = vec3d2.y - vec3d.y;
        double d3 = vec3d2.z - vec3d.z;
        double d4 = MathHelper.sqrt((double)(d * d + d3 * d3));
        return new Rotation(MathHelper.wrapDegrees((float)((float)Math.toDegrees(MathHelper.atan2((double)d3, (double)d)) - 90.0f)), MathHelper.wrapDegrees((float)((float)(-Math.toDegrees(MathHelper.atan2((double)d2, (double)d4))))));
    }

    @NotNull
    public Vec3d getEntityVector(@NotNull Entity entity) {
        RenderManager renderManager = mc.getRenderManager();
        double d = this.c(entity.posX, entity.lastTickPosX) - ((IRenderManager)renderManager).getRenderPosX();
        RenderManager renderManager2 = mc.getRenderManager();
        double d2 = this.c(entity.posY, entity.lastTickPosY) - ((IRenderManager)renderManager2).getRenderPosY();
        RenderManager renderManager3 = mc.getRenderManager();
        double d3 = this.c(entity.posZ, entity.lastTickPosZ) - ((IRenderManager)renderManager3).getRenderPosZ();
        return new Vec3d(d, d2, d3);
    }

    public void arrow(float f, float f2, float f3, float f4) {
        GlStateManager.glBegin((int)6);
        GlStateManager.glVertex3f((float)f, (float)f2, (float)f3);
        GlStateManager.glVertex3f((float)(f + 0.1f * f4), (float)f2, (float)(f3 - 0.2f * f4));
        GlStateManager.glVertex3f((float)f, (float)f2, (float)(f3 - 0.12f * f4));
        GlStateManager.glVertex3f((float)(f - 0.1f * f4), (float)f2, (float)(f3 - 0.2f * f4));
        GlStateManager.glEnd();
    }

    public void drawArrow(Entity var1x) {
        if (Radar.mc.entityRenderer != null) {
            Color var5x = this.nearColor.getValue();
            Rotation var8 = this.getRotation(Vec3d.ZERO, this.getEntityVector(var1x));
            float var6 = var8.meth2();
            float var7 = var8.getPitch();
            float var16 = 180.0f - var6;
            Entity var10001 = mc.getRenderViewEntity();
            if (var10001 == null) {
                Intrinsics.throwNpe();
            }
            var6 = var16 + var10001.rotationYaw;
            Vec3d var14 = new Vec3d(0.0, 0.0, 1.0).rotateYaw((float)Math.toRadians(var6)).rotatePitch((float)Math.toRadians(180.0));
            GlStateManager.blendFunc((int)770, (int)771);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask((boolean)false);
            GlStateManager.disableDepth();
            float var9 = (float)((double)var1x.getDistance(mc.getRenderViewEntity()) / ((Number)this.changeradius.getValue()).doubleValue());
            GlStateManager.color((float)var5x.getRed(), (float)var5x.getGreen(), (float)var5x.getBlue(), (float)var5x.getAlpha());
            GlStateManager.disableLighting();
            GlStateManager.loadIdentity();
            EntityRenderer var17 = Radar.mc.entityRenderer;
            if (var17 == null) {
                throw new TypeCastException("null cannot be cast to non-null type dev.nuker.pyro.mixin.EntityRendererAccessor");
            }
            ((IEntityRenderer)var17).orientCam(mc.getRenderPartialTicks());
            float var10 = (float)((Number)this.scale.getValue()).doubleValue() * 0.2f;
            float var11 = (float)((Number)this.distance.getValue()).doubleValue() * 0.2f;
            var10001 = mc.getRenderViewEntity();
            if (var10001 == null) {
                Intrinsics.throwNpe();
            }
            GlStateManager.translate((float)0.0f, (float)var10001.getEyeHeight(), (float)0.0f);
            Entity var18 = mc.getRenderViewEntity();
            if (var18 == null) {
                Intrinsics.throwNpe();
            }
            GlStateManager.rotate((float)(-var18.rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
            var18 = mc.getRenderViewEntity();
            if (var18 == null) {
                Intrinsics.throwNpe();
            }
            GlStateManager.rotate((float)var18.rotationPitch, (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.translate((float)0.0f, (float)0.0f, (float)1.0f);
            float var12 = ((Number)this.tilt.getValue()).intValue();
            if (this.unlockTilt.getValue().booleanValue()) {
                var16 = 90.0f;
                var10001 = mc.getRenderViewEntity();
                if (var10001 == null) {
                    Intrinsics.throwNpe();
                }
                if (var16 - var10001.rotationPitch < var12) {
                    var16 = 90.0f;
                    var10001 = mc.getRenderViewEntity();
                    if (var10001 == null) {
                        Intrinsics.throwNpe();
                    }
                    var12 = var16 - var10001.rotationPitch;
                }
            }
            GlStateManager.rotate((float)var12, (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.rotate((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GlStateManager.rotate((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.translate((float)0.0f, (float)0.0f, (float)1.0f);
            GlStateManager.rotate((float)var6, (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.translate((float)0.0f, (float)0.0f, (float)var11);
            NumberProperty<Double> var10000 = this.scale;
            if (var10000 == null) {
                Intrinsics.throwNpe();
            }
            float var13 = Float.valueOf(String.valueOf(var10000.getValue())).floatValue() * var10 * 2.0f;
            this.arrow((float)var14.x, (float)var14.y, (float)var14.z, var13);
            GlStateManager.enableTexture2D();
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableDepth();
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.enableLighting();
        }
    }

    public double c(double d, double d2) {
        return d2 + (d - d2) * (double)mc.getRenderPartialTicks();
    }
}

