/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.core.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassParser {
    private Field findField(Class clazz, String name) {
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            if (!fields[i].getName().equalsIgnoreCase(name)) continue;
            if (fields[i].getType().isPrimitive()) {
                return fields[i];
            }
            if (fields[i].getType() != String.class) continue;
            return fields[i];
        }
        return null;
    }

    private Method findMethod(Class clazz, String name) {
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; ++i) {
            Method method;
            Class<?>[] params;
            if (!methods[i].getName().equalsIgnoreCase(name) || (params = (method = methods[i]).getParameterTypes()).length != 1) continue;
            return method;
        }
        return null;
    }

    private Method findMethod(Class clazz, String name, Class parameter) {
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; ++i) {
            Method method;
            Class<?>[] params;
            if (!methods[i].getName().equalsIgnoreCase(name) || (params = (method = methods[i]).getParameterTypes()).length != 1 || !method.getParameterTypes()[0].isAssignableFrom(parameter)) continue;
            return method;
        }
        return null;
    }
}

