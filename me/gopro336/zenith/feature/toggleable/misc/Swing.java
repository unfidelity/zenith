//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.misc;

import me.gopro336.zenith.event.network.PacketSendEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.Property;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.util.EnumHand;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="Swing", category=Category.MISC, description="Swings with your offhand")
public class Swing
extends Feature {
    public Property<Enum> mode = new Property<Modes>(this, "Mode", "", Modes.Offhand);
    public Property<Boolean> noAnimation = new Property<Boolean>(this, "CancelAnimation", "", true);
    public Property<Boolean> noReset = new Property<Boolean>(this, "NoReset", "", false);
    public Property<Boolean> dropSwing = new Property<Boolean>(this, "DropSwing", "", false);

    @Override
    public void onUpdate() {
        if (this.nullCheck()) {
            return;
        }
        if (Modes.Offhand.equals(this.mode.getValue())) {
            Swing.mc.player.swingingHand = EnumHand.OFF_HAND;
        } else if (Modes.Mainhand.equals(this.mode.getValue())) {
            Swing.mc.player.swingingHand = EnumHand.MAIN_HAND;
        }
        if (Swing.mc.gameSettings.keyBindDrop.isPressed() && this.dropSwing.getValue().booleanValue()) {
            Swing.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }

    @Listener
    public void onPacketSend(PacketSendEvent event) {
        if (event.getPacket() instanceof CPacketAnimation && this.noAnimation.getValue().booleanValue()) {
            event.setCanceled(true);
        }
    }

    private static enum Modes {
        Offhand,
        Mainhand,
        Switch;

    }
}

