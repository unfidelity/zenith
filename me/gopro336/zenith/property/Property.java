/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.property;

import java.awt.Color;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.OnChangedValue;

public class Property<T> {
    private final String name;
    private final String description;
    private boolean extended;
    private Feature feature;
    public Property<?> parentProperty = null;
    public boolean isSubProperty = false;
    private Mode mode;
    private T value;
    private float alpha = 0.2f;
    private final ArrayList<Property<?>> subProperties = new ArrayList();
    public Consumer<OnChangedValue<T>> changeTask = null;
    private Predicate<T> visibleCheck = null;

    public Mode getMode() {
        return this.mode;
    }

    public Property(Feature feature, String name, String description, T value) {
        this.name = name;
        this.extended = false;
        this.description = description;
        this.value = value;
        this.feature = feature;
        Zenith.SettingManager.add(this);
        if (value instanceof Boolean) {
            this.mode = Mode.BOOLEAN;
        } else if (value instanceof Color) {
            this.mode = Mode.COLOR;
        } else if (value instanceof String) {
            this.mode = Mode.STRING;
        } else if (value instanceof Enum) {
            this.mode = Mode.ENUM;
        } else if (value instanceof Number) {
            this.mode = Mode.NUMBER;
        }
    }

    public Property(Feature feature, Property<?> parent, String name, String description, T value) {
        this.name = name;
        this.extended = false;
        this.description = description;
        this.value = value;
        this.isSubProperty = true;
        this.parentProperty = parent;
        this.feature = feature;
        Zenith.SettingManager.add(this);
        if (parent.getValue() instanceof Boolean) {
            Property<?> booleanProperty = parent;
            booleanProperty.addSubProperty(this);
            this.mode = Mode.BOOLEAN;
        }
        if (parent.getValue() instanceof String) {
            Property<?> stringProperty = parent;
            stringProperty.addSubProperty(this);
            this.mode = Mode.STRING;
        }
        if (parent.getValue() instanceof Enum) {
            Property<?> enumProperty = parent;
            enumProperty.addSubProperty(this);
            this.mode = Mode.ENUM;
        }
        if (parent.getValue() instanceof Color) {
            Property<?> colorProperty = parent;
            colorProperty.addSubProperty(this);
            this.mode = Mode.COLOR;
        }
        if (parent.getValue() instanceof Integer) {
            NumberProperty integerNumberProperty = (NumberProperty)parent;
            integerNumberProperty.addSubProperty(this);
            this.mode = Mode.NUMBER;
        }
        if (parent.getValue() instanceof Double) {
            NumberProperty doubleNumberProperty = (NumberProperty)parent;
            doubleNumberProperty.addSubProperty(this);
            this.mode = Mode.NUMBER;
        }
        if (parent.getValue() instanceof Float) {
            NumberProperty floatNumberProperty = (NumberProperty)parent;
            floatNumberProperty.addSubProperty(this);
            this.mode = Mode.NUMBER;
        }
    }

    public Property(Feature feature, String name, String description, T value, Predicate<T> visibility) {
        this.name = name;
        this.extended = false;
        this.description = description;
        this.value = value;
        this.visibleCheck = visibility;
        this.feature = feature;
        Zenith.SettingManager.add(this);
        if (value instanceof Boolean) {
            this.mode = Mode.BOOLEAN;
        } else if (value instanceof Color) {
            this.mode = Mode.COLOR;
        } else if (value instanceof String) {
            this.mode = Mode.STRING;
        } else if (value instanceof Enum) {
            this.mode = Mode.ENUM;
        } else if (value instanceof Number) {
            this.mode = Mode.NUMBER;
        }
    }

    public Property(Feature feature, Property<?> parent, String name, String description, T value, Predicate<T> visibility) {
        this.name = name;
        this.extended = false;
        this.description = description;
        this.value = value;
        this.visibleCheck = visibility;
        this.isSubProperty = true;
        this.parentProperty = parent;
        this.feature = feature;
        Zenith.SettingManager.add(this);
        if (parent.getValue() instanceof Boolean) {
            Property<?> booleanProperty = parent;
            booleanProperty.addSubProperty(this);
            this.mode = Mode.BOOLEAN;
        }
        if (parent.getValue() instanceof String) {
            Property<?> stringProperty = parent;
            stringProperty.addSubProperty(this);
            this.mode = Mode.STRING;
        }
        if (parent.getValue() instanceof Enum) {
            Property<?> enumProperty = parent;
            enumProperty.addSubProperty(this);
            this.mode = Mode.ENUM;
        }
        if (parent.getValue() instanceof Color) {
            Property<?> colorProperty = parent;
            colorProperty.addSubProperty(this);
            this.mode = Mode.COLOR;
        }
        if (parent.getValue() instanceof Integer) {
            NumberProperty integerNumberProperty = (NumberProperty)parent;
            integerNumberProperty.addSubProperty(this);
            this.mode = Mode.NUMBER;
        }
        if (parent.getValue() instanceof Double) {
            NumberProperty doubleNumberProperty = (NumberProperty)parent;
            doubleNumberProperty.addSubProperty(this);
            this.mode = Mode.NUMBER;
        }
        if (parent.getValue() instanceof Float) {
            NumberProperty floatNumberProperty = (NumberProperty)parent;
            floatNumberProperty.addSubProperty(this);
            this.mode = Mode.NUMBER;
        }
    }

    public void setValueEnumString(String value) {
        Enum[] array = (Enum[])this.getValue().getClass().getEnumConstants();
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            if (!array[i].name().equalsIgnoreCase(value)) continue;
            this.value = array[i];
        }
    }

    public String getFixedValue() {
        return Character.toString(this.getName().charAt(0)) + this.getName().toLowerCase().replaceFirst(Character.toString(this.getName().charAt(0)).toLowerCase(), "");
    }

    public boolean isBoolean() {
        return this.mode == Mode.BOOLEAN;
    }

    public boolean isString() {
        return this.mode == Mode.STRING;
    }

    public boolean isEnum() {
        return this.mode == Mode.ENUM;
    }

    public boolean isColor() {
        return this.mode == Mode.COLOR;
    }

    public boolean isNumber() {
        return this.mode == Mode.NUMBER;
    }

    public void setValue(T value) {
        T old = this.value;
        this.value = value;
        if (this.changeTask != null) {
            this.changeTask.accept(new OnChangedValue<T>(old, value));
        }
    }

    public Property<T> onChanged(Consumer<OnChangedValue<T>> run) {
        this.changeTask = run;
        return this;
    }

    public boolean isVisible() {
        if (this.visibleCheck == null) {
            return true;
        }
        return this.visibleCheck.test(this.value);
    }

    public Property<?> getParentProperty() {
        return this.parentProperty;
    }

    public void addSubProperty(Property<?> subProperty) {
        this.subProperties.add(subProperty);
    }

    public T getValue() {
        return this.value;
    }

    public void toggleOpenState() {
        this.extended = !this.extended;
    }

    public boolean isExtended() {
        return this.extended;
    }

    public Feature getFeature() {
        return this.feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public boolean isSubProperty() {
        return this.isSubProperty;
    }

    public ArrayList<Property<?>> getSubProperties() {
        return this.subProperties;
    }

    public boolean hasSubProperties() {
        return this.subProperties.size() > 0;
    }

    public void addSubSetting(Property<?> subProperty) {
        this.subProperties.add(subProperty);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public float getAlpha() {
        return this.alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    static enum Mode {
        BOOLEAN,
        STRING,
        ENUM,
        COLOR,
        NUMBER;

    }
}

