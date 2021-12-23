/*
 * Decompiled with CFR 0.152.
 */
package org.reflections;

import java.net.URL;
import java.util.Set;
import java.util.function.Predicate;
import org.reflections.scanners.Scanner;

public interface Configuration {
    public Set<Scanner> getScanners();

    public Set<URL> getUrls();

    public Predicate<String> getInputsFilter();

    public boolean isParallel();

    public ClassLoader[] getClassLoaders();

    public boolean shouldExpandSuperTypes();
}

