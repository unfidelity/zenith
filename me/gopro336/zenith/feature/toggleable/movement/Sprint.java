//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.movement;

import me.gopro336.zenith.event.EventStageable;
import me.gopro336.zenith.event.player.UpdateWalkingPlayerEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.Property;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="Sprint", description="automaticly sprints for you", category=Category.MOVEMENT)
public class Sprint
extends Feature {
    public Property<modes> mode = new Property<modes>(this, "Mode", "mode to use", modes.Rage);

    @Listener
    private void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent event) {
        if (event.getStage() == EventStageable.EventStage.PRE) {
            if (this.mode.getValue().equals((Object)modes.Rage)) {
                if (Sprint.mc.player.movementInput.moveForward != 0.0f || Sprint.mc.player.movementInput.moveStrafe != 0.0f) {
                    Sprint.mc.player.setSprinting(true);
                }
            } else if (Sprint.mc.player.movementInput.moveForward >= 0.8f && (float)Sprint.mc.player.getFoodStats().getFoodLevel() > 6.0f) {
                Sprint.mc.player.setSprinting(true);
            }
        }
    }

    public static enum modes {
        Rage,
        Legit;

    }
}

