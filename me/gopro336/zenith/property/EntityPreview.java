/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.property;

import java.util.function.Consumer;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.OnChangedValue;
import me.gopro336.zenith.property.Property;

public class EntityPreview<T extends preview>
extends Property<T> {
    public EntityPreview(Feature feature, String name, String description, T value) {
        super(feature, name, description, value);
    }

    public EntityPreview(Feature feature, Property<?> parent, String name, String description, T value) {
        super(feature, parent, name, description, value);
        this.isSubProperty = true;
    }

    @Override
    public EntityPreview<T> onChanged(Consumer<OnChangedValue<T>> run) {
        this.changeTask = run;
        return this;
    }

    public static enum preview {
        Crystal,
        Player;

    }
}

