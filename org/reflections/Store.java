/*
 * Decompiled with CFR 0.152.
 */
package org.reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Store
extends HashMap<String, Map<String, Set<String>>> {
    public Store() {
    }

    public Store(Map<String, Map<String, Set<String>>> storeMap) {
        super(storeMap);
    }
}

