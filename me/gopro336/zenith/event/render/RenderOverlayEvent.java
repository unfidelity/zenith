/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.render;

import me.gopro336.zenith.event.EventCancellable;

public class RenderOverlayEvent
extends EventCancellable {
    private OverlayType type;

    public RenderOverlayEvent(OverlayType type) {
        this.type = type;
    }

    public OverlayType getType() {
        return this.type;
    }

    public void setType(OverlayType type) {
        this.type = type;
    }

    public static enum OverlayType {
        ITEM,
        LIQUID,
        FIRE;

    }
}

