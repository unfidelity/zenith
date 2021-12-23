//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.misc;

import me.gopro336.zenith.event.network.PacketReceiveEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.Property;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.SoundEvent;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="AntiSound", description="", category=Category.MISC)
public class AntiSound
extends Feature {
    public final Property<Boolean> wither = new Property<Boolean>(this, "Wither Ambient", "", true);
    private final Property<Boolean> witherHurt = new Property<Boolean>(this, "Wither Hurt", "", true);
    public final Property<Boolean> witherSpawn = new Property<Boolean>(this, "Wither Spawn", "", false);
    private final Property<Boolean> witherDeath = new Property<Boolean>(this, "Wither Death", "", false);
    private final Property<Boolean> punches = new Property<Boolean>(this, "Punches", "", true);
    private final Property<Boolean> punchW = new Property<Boolean>(this, "Weak Punch", "", true);
    private final Property<Boolean> punchKB = new Property<Boolean>(this, "Knockback Punch", "", true);
    private final Property<Boolean> explosion = new Property<Boolean>(this, "Explosion", "", false);
    public final Property<Boolean> totem = new Property<Boolean>(this, "Totem Pop", "", false);
    public final Property<Boolean> elytra = new Property<Boolean>(this, "Elytra Wind", "", true);
    public final Property<Boolean> portal = new Property<Boolean>(this, "Nether Portal", "", true);

    @Listener
    public void onRecieve(PacketReceiveEvent event) {
        SPacketSoundEffect packet;
        if (event.getPacket() instanceof SPacketSoundEffect && this.shouldCancelSound((packet = (SPacketSoundEffect)event.getPacket()).getSound())) {
            event.setCanceled(true);
        }
    }

    private boolean shouldCancelSound(SoundEvent soundEvent) {
        if (soundEvent == SoundEvents.ENTITY_WITHER_AMBIENT && this.wither.getValue().booleanValue()) {
            return true;
        }
        if (soundEvent == SoundEvents.ENTITY_WITHER_SPAWN && this.witherSpawn.getValue().booleanValue()) {
            return true;
        }
        if (soundEvent == SoundEvents.ENTITY_WITHER_HURT && this.witherHurt.getValue().booleanValue()) {
            return true;
        }
        if (soundEvent == SoundEvents.ENTITY_WITHER_DEATH && this.witherDeath.getValue().booleanValue()) {
            return true;
        }
        if (soundEvent == SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE && this.punches.getValue().booleanValue()) {
            return true;
        }
        if (soundEvent == SoundEvents.ENTITY_PLAYER_ATTACK_WEAK && this.punchW.getValue().booleanValue()) {
            return true;
        }
        if (soundEvent == SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK && this.punchKB.getValue().booleanValue()) {
            return true;
        }
        return soundEvent == SoundEvents.ENTITY_GENERIC_EXPLODE && this.explosion.getValue() != false;
    }
}

