//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.render;

import java.util.ArrayList;
import me.gopro336.zenith.event.network.PacketReceiveEvent;
import me.gopro336.zenith.event.render.BossOverlayEvent;
import me.gopro336.zenith.event.render.HurtCameraEffectEvent;
import me.gopro336.zenith.event.render.LayerArmorEvent;
import me.gopro336.zenith.event.render.ModifyFOVEvent;
import me.gopro336.zenith.event.render.RenderBeaconBeamEvent;
import me.gopro336.zenith.event.render.RenderEnchantmentTableBookEvent;
import me.gopro336.zenith.event.render.RenderMapEvent;
import me.gopro336.zenith.event.render.RenderSkylightEvent;
import me.gopro336.zenith.event.render.RenderWitherSkullEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.MobEffects;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="NoRender", category=Category.RENDER, description="Prevents certain things from rendering")
public class NoRender
extends Feature {
    public static NoRender INSTANCE;
    public Property<Boolean> overlays = new Property<Boolean>(this, "Overlays", "Prevents overlays from rendering", true);
    public Property<Boolean> overlayFire = new Property<Boolean>(this, "Fire", "Prevents fire overlay from rendering", true);
    public Property<Boolean> overlayLiquid = new Property<Boolean>(this, "Liquid", "Prevents liquid overlay from rendering", true);
    public Property<Boolean> overlayBlock = new Property<Boolean>(this, "Block", "Prevents block overlay from rendering", true);
    public Property<Boolean> overlayBoss = new Property<Boolean>(this, "Boss", "Prevents boss bar overlay from rendering", true);
    public Property<Boolean> fog = new Property<Boolean>(this, "Fog", "Prevents fog from rendering", true);
    public Property<Boolean> fogLiquid = new Property<Boolean>(this, "LiquidVision", "Clears fog in liquid", true);
    public NumberProperty<Double> fogDensity = new NumberProperty<Double>((Feature)this, "Density", "Density of the fog", 0.0, Double.valueOf(0.0), 20.0);
    public Property<Boolean> armor = new Property<Boolean>(this, "Armor", "Prevents armor from rendering", true);
    public Property<Boolean> items = new Property<Boolean>(this, "Items", "Prevents dropped items from rendering", true);
    public Property<Boolean> particles = new Property<Boolean>(this, "Particles", "Prevents laggy particles from rendering", true);
    public Property<Boolean> tileEntities = new Property<Boolean>(this, "TileEntities", "Prevents tile entity effects (enchantment table books, beacon beams, etc.) from rendering", false);
    public Property<Boolean> maps = new Property<Boolean>(this, "Maps", "Prevents maps from rendering", true);
    public Property<Boolean> skylight = new Property<Boolean>(this, "Skylight", "Prevents skylight updates from rendering", true);
    public Property<Boolean> hurtCamera = new Property<Boolean>(this, "HurtCamera", "Removes the hurt camera effect", true);
    public Property<Boolean> witherSkull = new Property<Boolean>(this, "WitherSkull", "Prevents flying wither skulls from rendering", true);
    public Property<Boolean> potion = new Property<Boolean>(this, "Potion", "Removes certain potion effects", true);
    public Property<Boolean> fov = new Property<Boolean>(this, "FOV", "Removes the FOV modifier effect", true);

    @Override
    public void onUpdate() {
        if (this.items.getValue().booleanValue()) {
            for (Entity entity : new ArrayList(NoRender.mc.world.loadedEntityList)) {
                if (!(entity instanceof EntityItem)) continue;
                NoRender.mc.world.removeEntity(entity);
            }
        }
        if (this.potion.getValue().booleanValue()) {
            if (NoRender.mc.player.isPotionActive(MobEffects.BLINDNESS)) {
                NoRender.mc.player.removePotionEffect(MobEffects.BLINDNESS);
            }
            if (NoRender.mc.player.isPotionActive(MobEffects.NAUSEA)) {
                NoRender.mc.player.removePotionEffect(MobEffects.NAUSEA);
            }
        }
    }

    @Listener
    public void onRenderBlockOverlay(RenderBlockOverlayEvent event) {
        if (this.nullCheck() && this.overlays.getValue().booleanValue()) {
            if (event.getOverlayType().equals((Object)RenderBlockOverlayEvent.OverlayType.FIRE) && this.overlayFire.getValue().booleanValue()) {
                event.setCanceled(true);
            }
            if (event.getOverlayType().equals((Object)RenderBlockOverlayEvent.OverlayType.WATER) && this.overlayLiquid.getValue().booleanValue()) {
                event.setCanceled(true);
            }
            if (event.getOverlayType().equals((Object)RenderBlockOverlayEvent.OverlayType.BLOCK) && this.overlayBlock.getValue().booleanValue()) {
                event.setCanceled(true);
            }
        }
    }

    @Listener
    public void onRenderBossOverlay(BossOverlayEvent event) {
        event.setCanceled(this.nullCheck() && this.overlayBoss.getValue() != false);
    }

    @Listener
    public void onRenderEnchantmentTableBook(RenderEnchantmentTableBookEvent event) {
        event.setCanceled(this.nullCheck() && this.tileEntities.getValue() != false);
    }

    @Listener
    public void onRenderBeaconBeam(RenderBeaconBeamEvent event) {
        event.setCanceled(this.nullCheck() && this.tileEntities.getValue() != false);
    }

    @Listener
    public void onRenderSkylight(RenderSkylightEvent event) {
        event.setCanceled(this.nullCheck() && this.skylight.getValue() != false);
    }

    @Listener
    public void onRenderMap(RenderMapEvent event) {
        event.setCanceled(this.nullCheck() && this.maps.getValue() != false);
    }

    @Listener
    public void onLayerArmor(LayerArmorEvent event) {
        if (this.nullCheck() && this.armor.getValue().booleanValue()) {
            event.setCanceled(true);
            switch (event.getEntityEquipmentSlot()) {
                case HEAD: {
                    event.getModelBiped().bipedHead.showModel = false;
                    event.getModelBiped().bipedHeadwear.showModel = false;
                    break;
                }
                case CHEST: {
                    event.getModelBiped().bipedBody.showModel = false;
                    event.getModelBiped().bipedRightArm.showModel = false;
                    event.getModelBiped().bipedLeftArm.showModel = false;
                    break;
                }
                case LEGS: {
                    event.getModelBiped().bipedBody.showModel = false;
                    event.getModelBiped().bipedRightLeg.showModel = false;
                    event.getModelBiped().bipedLeftLeg.showModel = false;
                    break;
                }
                case FEET: {
                    event.getModelBiped().bipedRightLeg.showModel = false;
                    event.getModelBiped().bipedLeftLeg.showModel = false;
                    break;
                }
            }
        }
    }

    @Listener
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacket() instanceof SPacketParticles && ((SPacketParticles)event.getPacket()).getParticleCount() > 200) {
            event.setCanceled(true);
        }
    }

    @Listener
    public void onHurtCamera(HurtCameraEffectEvent event) {
        event.setCanceled(this.nullCheck() && this.hurtCamera.getValue() != false);
    }

    @Listener
    public void onRenderWitherSkull(RenderWitherSkullEvent event) {
        event.setCanceled(this.nullCheck() && this.witherSkull.getValue() != false);
    }

    @Listener
    public void onRenderFog(EntityViewRenderEvent.FogDensity event) {
        if (this.nullCheck() && this.fog.getValue().booleanValue()) {
            if (!NoRender.isInLiquid() && this.fogLiquid.getValue().booleanValue()) {
                return;
            }
            event.setDensity((float)((Double)this.fogDensity.getValue()).doubleValue());
            event.setCanceled(true);
        }
    }

    @Listener
    public void onFOVModifier(ModifyFOVEvent event) {
        event.setCanceled(this.nullCheck() && this.fov.getValue() != false);
    }

    public static boolean isInLiquid() {
        return NoRender.mc.player.isInLava() || NoRender.mc.player.isInWater();
    }
}

