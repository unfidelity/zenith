/*
 * Decompiled with CFR 0.152.
 */
package team.stiff.pomelo.impl.annotated.dispatch;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import team.stiff.pomelo.dispatch.EventDispatcher;
import team.stiff.pomelo.handler.EventHandler;

public final class MethodEventDispatcher
implements EventDispatcher {
    private final Map<Class<?>, Set<EventHandler>> eventHandlers;

    public MethodEventDispatcher(Map<Class<?>, Set<EventHandler>> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    @Override
    public <E> void dispatch(E event) {
        for (EventHandler eventHandler : this.eventHandlers.getOrDefault(event.getClass(), Collections.emptySet())) {
            eventHandler.handle(event);
        }
    }
}

