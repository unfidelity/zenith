/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.asm.service;

import java.net.URL;

public interface IClassProvider {
    public URL[] getClassPath();

    public Class<?> findClass(String var1) throws ClassNotFoundException;

    public Class<?> findClass(String var1, boolean var2) throws ClassNotFoundException;

    public Class<?> findAgentClass(String var1, boolean var2) throws ClassNotFoundException;
}

