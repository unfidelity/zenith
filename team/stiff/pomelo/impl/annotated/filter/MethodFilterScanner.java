/*
 * Decompiled with CFR 0.152.
 */
package team.stiff.pomelo.impl.annotated.filter;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import team.stiff.pomelo.filter.EventFilter;
import team.stiff.pomelo.filter.EventFilterScanner;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

public final class MethodFilterScanner
implements EventFilterScanner<Method> {
    @Override
    public Set<EventFilter> scan(Method listener) {
        if (!listener.isAnnotationPresent(Listener.class)) {
            return Collections.emptySet();
        }
        HashSet<EventFilter> filters = new HashSet<EventFilter>();
        for (Class<? extends EventFilter> filter : listener.getDeclaredAnnotation(Listener.class).filters()) {
            try {
                filters.add(filter.newInstance());
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return filters;
    }
}

