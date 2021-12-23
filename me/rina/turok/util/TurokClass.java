/*
 * Decompiled with CFR 0.152.
 */
package me.rina.turok.util;

public class TurokClass {
    public static Enum getEnumByName(Enum _enum, String name) {
        for (Enum enums : (Enum[])_enum.getClass().getEnumConstants()) {
            if (!enums.name().equalsIgnoreCase(name)) continue;
            return enums;
        }
        return _enum;
    }

    public static boolean isAnnotationPreset(Class clazz, Class clazz1) {
        return clazz.isAnnotationPresent(clazz1);
    }
}

