//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.movement;

import me.gopro336.zenith.api.util.client.PlayerUtils;
import me.gopro336.zenith.event.player.MoveEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="FastSwim", category=Category.MOVEMENT)
public class FastSwim
extends Feature {
    private NumberProperty<Double> speed = new NumberProperty<Double>((Feature)this, "Speed", "", 0.1, Double.valueOf(2.0), 10.0);
    private Property<Boolean> antiKick = new Property<Boolean>(this, "AntiKick", "", true);

    @Listener
    public void onPlayerMove(MoveEvent event) {
        if (!FastSwim.mc.player.isInWater() || !PlayerUtils.isPlayerMoving()) {
            return;
        }
        double[] dir = FastSwim.mc.player.ticksExisted % 4 == 0 && this.antiKick.getValue() != false ? PlayerUtils.directionSpeed((Double)this.speed.getValue() / 40.0) : PlayerUtils.directionSpeed((Double)this.speed.getValue() / 10.0);
        event.setX(dir[0]);
        event.setZ(dir[1]);
    }
}

