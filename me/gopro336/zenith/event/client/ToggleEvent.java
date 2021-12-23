/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.client;

import me.gopro336.zenith.feature.Feature;

public class ToggleEvent {
    private Feature feature;

    public ToggleEvent(Feature feature) {
        this.feature = feature;
    }

    public Feature getFeature() {
        return this.feature;
    }
}

