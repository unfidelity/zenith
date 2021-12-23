/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature;

import java.util.ArrayList;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.Property;

@Deprecated
public class SettingManager {
    private final ArrayList<Property<?>> Properties = new ArrayList();

    public ArrayList<Property<?>> getProperties() {
        return this.Properties;
    }

    public ArrayList<Property<?>> getPropertiesByMod(Feature mod) {
        ArrayList out = new ArrayList();
        for (Property<?> s : this.getProperties()) {
            if (!s.getFeature().equals(mod)) continue;
            out.add(s);
        }
        return out;
    }

    public <T> void add(Property<T> tProperty) {
        this.Properties.add(tProperty);
    }
}

