/*
 * Decompiled with CFR 0.152.
 */
package team.stiff.pomelo.filter;

import java.util.Set;
import team.stiff.pomelo.filter.EventFilter;

public interface EventFilterScanner<T> {
    public Set<EventFilter> scan(T var1);
}

