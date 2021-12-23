/*
 * Decompiled with CFR 0.152.
 */
package team.stiff.pomelo.impl.annotated;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import team.stiff.pomelo.EventManager;
import team.stiff.pomelo.dispatch.EventDispatcher;
import team.stiff.pomelo.handler.EventHandler;
import team.stiff.pomelo.handler.scan.EventHandlerScanner;
import team.stiff.pomelo.impl.annotated.dispatch.MethodEventDispatcher;
import team.stiff.pomelo.impl.annotated.handler.scan.MethodHandlerScanner;

public final class AnnotatedEventManager
implements EventManager {
    private final EventHandlerScanner eventHandlerScanner = new MethodHandlerScanner();
    private final Map<Object, EventDispatcher> listenerDispatchers = new ConcurrentHashMap<Object, EventDispatcher>();

    @Override
    public <E> E dispatchEvent(E event) {
        for (EventDispatcher dispatcher : this.listenerDispatchers.values()) {
            dispatcher.dispatch(event);
        }
        return event;
    }

    @Override
    public boolean isRegisteredListener(Object listener) {
        return this.listenerDispatchers.containsKey(listener);
    }

    @Override
    public boolean addEventListener(Object listenerContainer) {
        if (this.listenerDispatchers.containsKey(listenerContainer)) {
            return false;
        }
        Map<Class<?>, Set<EventHandler>> eventHandlers = this.eventHandlerScanner.locate(listenerContainer);
        if (eventHandlers.isEmpty()) {
            return false;
        }
        return this.listenerDispatchers.put(listenerContainer, new MethodEventDispatcher(eventHandlers)) == null;
    }

    @Override
    public boolean removeEventListener(Object listenerContainer) {
        return this.listenerDispatchers.remove(listenerContainer) != null;
    }
}

