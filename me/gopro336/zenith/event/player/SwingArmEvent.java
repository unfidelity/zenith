/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.player;

import me.gopro336.zenith.event.EventCancellable;
import net.minecraft.util.EnumHand;

public class SwingArmEvent
extends EventCancellable {
    private EnumHand hand;

    public SwingArmEvent(EnumHand hand) {
        this.hand = hand;
    }

    public EnumHand getHand() {
        return this.hand;
    }

    public void setHand(EnumHand hand) {
        this.hand = hand;
    }
}

