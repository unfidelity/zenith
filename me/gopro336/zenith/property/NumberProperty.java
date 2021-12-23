/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.property;

import java.util.function.Consumer;
import java.util.function.Predicate;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.OnChangedValue;
import me.gopro336.zenith.property.Property;

public class NumberProperty<T extends Number>
extends Property<T> {
    private final T min;
    private final T max;

    public NumberProperty(Feature feature, String name, String description, T min, T value, T max) {
        super(feature, name, description, value);
        this.min = min;
        this.max = max;
    }

    public NumberProperty(Feature feature, Property<?> parent, String name, String description, T min, T value, T max) {
        super(feature, parent, name, description, value);
        this.isSubProperty = true;
        this.min = min;
        this.max = max;
    }

    public NumberProperty(Feature feature, String name, String description, T min, T value, T max, Predicate<T> visibility) {
        super(feature, name, description, value);
        this.min = min;
        this.max = max;
    }

    public NumberProperty(Feature feature, Property<?> parent, String name, String description, T min, T value, T max, Predicate<T> visibility) {
        super(feature, parent, name, description, value);
        this.isSubProperty = true;
        this.min = min;
        this.max = max;
    }

    @Override
    public NumberProperty<T> onChanged(Consumer<OnChangedValue<T>> run) {
        this.changeTask = run;
        return this;
    }

    public T getMin() {
        return this.min;
    }

    public T getMax() {
        return this.max;
    }
}

