/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event;

public class EventStageable {
    private EventStage stage;

    public EventStageable() {
    }

    public EventStageable(EventStage stage) {
        this.stage = stage;
    }

    public EventStage getStage() {
        return this.stage;
    }

    public void setStage(EventStage stage) {
        this.stage = stage;
    }

    public static enum EventStage {
        PRE,
        POST;

    }
}

