//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.render;

import java.awt.Color;
import me.gopro336.zenith.api.util.newRender.ColorUtil;
import me.gopro336.zenith.event.render.RenderCrystalEvent;
import me.gopro336.zenith.event.render.RenderEntityModelEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.EntityPreview;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="Chams", description="", category=Category.RENDER)
public class ChamsRw
extends Feature {
    public EntityPreview<EntityPreview.preview> cPreview = new EntityPreview<EntityPreview.preview>(this, "ChamsPreview", "", EntityPreview.preview.Crystal);
    public Property<Mode> mode = new Property<Mode>(this, "Mode", "Mode for Chams", Mode.MODEL);
    public NumberProperty<Double> width = new NumberProperty<Double>((Feature)this, "Width", "Line width for the model", 0.0, Double.valueOf(3.0), Double.valueOf(5.0), v -> this.mode.getValue().equals((Object)Mode.MODEL) || this.mode.getValue().equals((Object)Mode.BOTH));
    public Property<Boolean> players = new Property<Boolean>(this, "Players", "Renders chams on players", true);
    public Property<Boolean> local = new Property<Boolean>((Feature)this, "Self", "Renders chams on the local player", false, v -> this.players.getValue());
    public Property<Boolean> mobs = new Property<Boolean>(this, "Mobs", "Renders chams on mobs", true);
    public Property<Boolean> monsters = new Property<Boolean>((Feature)this, this.mobs, "Monsters", "Renders chams on monsters", true);
    public Property<Boolean> crystals = new Property<Boolean>(this, "Crystals", "Renders chams on crystals", true);
    public NumberProperty<Double> scale = new NumberProperty<Double>((Feature)this, this.crystals, "Scale", "Scale for crystal model", Double.valueOf(0.0), Double.valueOf(1.0), 2.0);
    public Property<Boolean> render = new Property<Boolean>(this, "Render", "renders chams", true);
    public Property<Boolean> texture = new Property<Boolean>((Feature)this, this.render, "Texture", "Enables entity texture", false);
    public Property<Boolean> lighting = new Property<Boolean>((Feature)this, this.render, "Lighting", "Disables vanilla lighting", true);
    public Property<Boolean> blend = new Property<Boolean>((Feature)this, this.render, "Blend", "Enables blended texture", false);
    public Property<Boolean> transparent = new Property<Boolean>((Feature)this, this.render, "Transparent", "Makes entity models transparent", true);
    public Property<Boolean> depth = new Property<Boolean>((Feature)this, this.render, "Depth", "Enables entity depth", true);
    public Property<Boolean> walls = new Property<Boolean>((Feature)this, this.render, "Walls", "Renders chams models through walls", true);
    public Property<Boolean> highlight = new Property<Boolean>(this, this.render, "Color", "Colors chams models when visible", true, v -> false);
    public Property<Color> highlightColor = new Property<Color>(this, this.render, "HighlightColor", "Color of models when visible", new Color(250, 0, 250, 50), v -> this.highlight.getValue());
    public Property<Boolean> xqz = new Property<Boolean>((Feature)this, "C", "Colors chams models through walls", true, v -> false);
    public Property<Color> xqzColor = new Property<Color>(this, "Color", "Color of models through walls", new Color(0, 70, 250, 50));

    @Listener
    public static void onRenderLivingEntity(RenderEntityModelEvent event) {
    }

    @Listener
    public void onRenderCrystalPre(RenderCrystalEvent.RenderCrystalPreEvent event) {
        event.setCanceled(!this.nullCheck() && this.isEnabled() && this.crystals.getValue() != false);
    }

    @Listener
    public void onRenderCrystalPost(RenderCrystalEvent.RenderCrystalPostEvent event) {
        if (!this.nullCheck() && this.crystals.getValue().booleanValue()) {
            if (this.transparent.getValue().booleanValue()) {
                GlStateManager.enableBlendProfile((GlStateManager.Profile)GlStateManager.Profile.TRANSPARENT_MODEL);
            }
            GL11.glPushMatrix();
            GL11.glPushAttrib((int)1048575);
            float rotation = (float)event.getEntityEnderCrystal().innerRotation + event.getPartialTicks();
            float rotationMoved = MathHelper.sin((float)(rotation * 0.2f)) / 2.0f + 0.5f;
            rotationMoved = (float)((double)rotationMoved + Math.pow(rotationMoved, 2.0));
            GL11.glTranslated((double)event.getX(), (double)event.getY(), (double)event.getZ());
            GL11.glScaled((double)((Double)this.scale.getValue()), (double)((Double)this.scale.getValue()), (double)((Double)this.scale.getValue()));
            if (!this.texture.getValue().booleanValue() && !this.mode.getValue().equals((Object)Mode.TEXTURE)) {
                GL11.glDisable((int)3553);
            }
            if (this.blend.getValue().booleanValue()) {
                GL11.glEnable((int)3042);
            }
            if (this.lighting.getValue().booleanValue()) {
                GL11.glDisable((int)2896);
            }
            if (this.depth.getValue().booleanValue()) {
                GL11.glDepthMask((boolean)false);
            }
            if (this.walls.getValue().booleanValue()) {
                GL11.glDisable((int)2929);
            }
            switch (this.mode.getValue()) {
                case WIREFRAME: {
                    GL11.glPolygonMode((int)1032, (int)6913);
                    break;
                }
                case BOTH: 
                case MODEL: {
                    GL11.glPolygonMode((int)1032, (int)6914);
                }
            }
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            GL11.glLineWidth((float)((float)((Double)this.width.getValue()).doubleValue()));
            if (this.xqz.getValue().booleanValue()) {
                ColorUtil.setColor(this.xqzColor.getValue());
            }
            if (event.getEntityEnderCrystal().shouldShowBottom()) {
                event.getModelBase().render((Entity)event.getEntityEnderCrystal(), 0.0f, rotation * 3.0f, rotationMoved * 0.2f, 0.0f, 0.0f, 0.0625f);
            } else {
                event.getModelNoBase().render((Entity)event.getEntityEnderCrystal(), 0.0f, rotation * 3.0f, rotationMoved * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            if (this.walls.getValue().booleanValue() && !this.mode.getValue().equals((Object)Mode.BOTH)) {
                GL11.glEnable((int)2929);
            }
            if (this.mode.getValue().equals((Object)Mode.BOTH)) {
                GL11.glPolygonMode((int)1032, (int)6913);
            }
            if (this.highlight.getValue().booleanValue()) {
                ColorUtil.setColor(this.mode.getValue().equals((Object)Mode.BOTH) ? new Color(this.xqzColor.getValue().getRed(), this.xqzColor.getValue().getGreen(), this.xqzColor.getValue().getBlue(), 255) : this.xqzColor.getValue());
            }
            if (event.getEntityEnderCrystal().shouldShowBottom()) {
                event.getModelBase().render((Entity)event.getEntityEnderCrystal(), 0.0f, rotation * 3.0f, rotationMoved * 0.2f, 0.0f, 0.0f, 0.0625f);
            } else {
                event.getModelNoBase().render((Entity)event.getEntityEnderCrystal(), 0.0f, rotation * 3.0f, rotationMoved * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            if (this.walls.getValue().booleanValue() && this.mode.getValue().equals((Object)Mode.BOTH)) {
                GL11.glEnable((int)2929);
            }
            if (this.lighting.getValue().booleanValue()) {
                GL11.glEnable((int)2896);
            }
            if (this.depth.getValue().booleanValue()) {
                GL11.glDepthMask((boolean)true);
            }
            if (this.blend.getValue().booleanValue()) {
                GL11.glDisable((int)3042);
            }
            if (!this.texture.getValue().booleanValue() && !this.mode.getValue().equals((Object)Mode.TEXTURE)) {
                GL11.glEnable((int)3553);
            }
            GL11.glScaled((double)(1.0 / (Double)this.scale.getValue()), (double)(1.0 / (Double)this.scale.getValue()), (double)(1.0 / (Double)this.scale.getValue()));
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
    }

    public static boolean isPassiveMob(Entity entity) {
        if (entity instanceof EntityWolf && ((EntityWolf)entity).isAngry()) {
            return false;
        }
        if (entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntitySquid) {
            return true;
        }
        return entity instanceof EntityIronGolem && ((EntityIronGolem)entity).getRevengeTarget() == null;
    }

    public static boolean isVehicleMob(Entity entity) {
        return entity instanceof EntityBoat || entity instanceof EntityMinecart;
    }

    public static boolean isHostileMob(Entity entity) {
        return entity.isCreatureType(EnumCreatureType.MONSTER, false) && !ChamsRw.isNeutralMob(entity) || entity instanceof EntitySpider;
    }

    public static boolean isNeutralMob(Entity entity) {
        return entity instanceof EntityPigZombie || entity instanceof EntityWolf || entity instanceof EntityEnderman;
    }

    public static enum Mode {
        MODEL,
        WIREFRAME,
        BOTH,
        TEXTURE;

    }
}

