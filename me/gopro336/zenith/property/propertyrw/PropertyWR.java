/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.property.propertyrw;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.FeatureManager;
import me.gopro336.zenith.property.propertyrw.OnChangedValue;

public class PropertyWR<T> {
    public String name;
    public String description = "";
    public T value;
    public T minimum;
    public T maximum;
    private boolean opened;
    private Feature feature;
    public BooleanSupplier isVis = PropertyWR::isTrue;
    public PropertyWR<PropertyWR<?>> parentProperty = null;
    public Consumer<OnChangedValue<T>> changeTask = null;

    public PropertyWR(String string, T value) {
        this.name = string;
        this.value = value;
    }

    public PropertyWR(String string, T min, T value, T max) {
        this.name = string;
        this.value = value;
        this.maximum = max;
        this.minimum = min;
    }

    public PropertyWR<T> withParent(PropertyWR<PropertyWR<?>> property) {
        this.parentProperty = property;
        return this;
    }

    public PropertyWR<T> withDescription(String string) {
        this.description = string;
        return this;
    }

    public PropertyWR<T> withVisibility(BooleanSupplier booleanSupplier) {
        this.isVis = booleanSupplier;
        return this;
    }

    public void setValue(T value) {
        T old = this.value;
        if (this.minimum != null && this.maximum != null) {
            T number = value;
            Number number2 = (Number)this.minimum;
            Number number3 = (Number)this.maximum;
            this.value = number;
        } else {
            this.value = value;
        }
        if (this.changeTask != null) {
            this.changeTask.accept(new OnChangedValue<T>(old, value));
        }
    }

    public PropertyWR<T> onChanged(Consumer<OnChangedValue<T>> run) {
        this.changeTask = run;
        return this;
    }

    public boolean isInvisible() {
        if (this.parentProperty != null && this.parentProperty.getValue().isOpened()) {
            return false;
        }
        return this.isVis.getAsBoolean();
    }

    public Feature getParentFeature() {
        for (Feature feature : Zenith.featureManager.getModules()) {
            if (FeatureManager.isPropertyInFeature(feature.getName(), this.getName()) == null) continue;
            return feature;
        }
        return null;
    }

    public static boolean isTrue() {
        return true;
    }

    public T getValue() {
        return this.value;
    }

    public T getMinimum() {
        return this.minimum;
    }

    public T getMaximum() {
        return this.maximum;
    }

    public void toggleOpenState() {
        this.opened = !this.opened;
    }

    public boolean isOpened() {
        return this.opened;
    }

    public Feature getFeature() {
        return this.feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isSubProperty() {
        return this.parentProperty != null;
    }

    public PropertyWR<PropertyWR<?>> getParentProperty() {
        return this.parentProperty;
    }
}

