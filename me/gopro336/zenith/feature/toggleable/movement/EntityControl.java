//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.movement;

import me.gopro336.zenith.asm.mixin.mixins.accessor.IEntityPlayerSP;
import me.gopro336.zenith.event.entity.AbstractHorseSaddledEvent;
import me.gopro336.zenith.event.entity.CanBeSteeredEvent;
import me.gopro336.zenith.event.entity.PigTravelEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.Property;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="EntityControl", description="Allows you to control a rideable entity", category=Category.MOVEMENT)
public class EntityControl
extends Feature {
    public Property<Boolean> ai = new Property<Boolean>(this, "PigAI", "", true);
    public Property<Boolean> jump = new Property<Boolean>(this, "JumpStrength", "", true);

    @Override
    @Listener
    public void onUpdate() {
        if (this.jump.getValue().booleanValue()) {
            IEntityPlayerSP player = (IEntityPlayerSP)EntityControl.mc.player;
            player.setHorseJumpPower(1.0f);
        }
    }

    @Listener
    private void canBeSteered(CanBeSteeredEvent event) {
        event.setCanceled(true);
    }

    @Listener
    private void travel(PigTravelEvent event) {
        if (this.ai.getValue().booleanValue()) {
            event.setCanceled(true);
        }
    }

    @Listener
    private void isSaddled(AbstractHorseSaddledEvent event) {
        event.setCanceled(true);
    }
}

