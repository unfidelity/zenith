/*
 * Decompiled with CFR 0.152.
 */
package team.stiff.pomelo.impl.annotated.handler;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import team.stiff.pomelo.filter.EventFilter;
import team.stiff.pomelo.handler.EventHandler;
import team.stiff.pomelo.handler.ListenerPriority;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

public final class MethodEventHandler
implements EventHandler {
    private final Object listenerParent;
    private final Method method;
    private static final MethodHandles.Lookup lookup = MethodHandles.lookup();
    private MethodHandle methodHandle;
    private final Set<EventFilter> eventFilters;
    private final Listener listenerAnnotation;
    private static boolean failed = false;

    public MethodEventHandler(Object listenerParent, Method method, Set<EventFilter> eventFilters) {
        block3: {
            this.listenerParent = listenerParent;
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            this.method = method;
            try {
                this.methodHandle = lookup.unreflect(method);
            }
            catch (Throwable t) {
                if (failed) break block3;
                new RuntimeException("Couldnt unreflect handler", t).printStackTrace();
                failed = true;
            }
        }
        this.eventFilters = eventFilters;
        this.listenerAnnotation = method.getAnnotation(Listener.class);
    }

    @Override
    public <E> void handle(E event) {
        for (EventFilter filter : this.eventFilters) {
            if (filter.test(this, event)) continue;
            return;
        }
        if (this.methodHandle != null) {
            try {
                if (this.listenerParent != null) {
                    this.methodHandle.invoke(this.listenerParent, event);
                } else {
                    this.methodHandle.invoke(event);
                }
                return;
            }
            catch (Throwable t) {
                t.printStackTrace();
                return;
            }
        }
        try {
            this.method.invoke(this.listenerParent, event);
        }
        catch (IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Object getListener() {
        return this.method;
    }

    @Override
    public ListenerPriority getPriority() {
        return this.listenerAnnotation.priority();
    }

    @Override
    public Iterable<EventFilter> getFilters() {
        return this.eventFilters;
    }

    @Override
    public int compareTo(EventHandler eventHandler) {
        return Integer.compare(eventHandler.getPriority().getPriorityLevel(), this.getPriority().getPriorityLevel());
    }
}

