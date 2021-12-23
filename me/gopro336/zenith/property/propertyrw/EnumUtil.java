/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.property.propertyrw;

import me.gopro336.zenith.property.propertyrw.PropertyWR;

public final class EnumUtil {
    public static String getNextEnumValue(PropertyWR<Enum<?>> Property2, boolean reverse) {
        Enum e;
        int i;
        Enum<?> currentValue = Property2.getValue();
        for (i = 0; i < ((Enum[])Property2.getValue().getClass().getEnumConstants()).length && !(e = ((Enum[])Property2.getValue().getClass().getEnumConstants())[i]).name().equalsIgnoreCase(currentValue.name()); ++i) {
        }
        return ((Enum[])Property2.getValue().getClass().getEnumConstants())[(reverse ? (i != 0 ? i - 1 : ((Enum[])Property2.getValue().getClass().getEnumConstants()).length - 1) : i + 1) % ((Enum[])Property2.getValue().getClass().getEnumConstants()).length].toString();
    }

    public static void setEnumValue(PropertyWR<Enum<?>> Property2, String value) {
        for (Enum e : (Enum[])Property2.getValue().getClass().getEnumConstants()) {
            if (!e.name().equalsIgnoreCase(value)) continue;
            Property2.setValue(e);
            break;
        }
    }
}

