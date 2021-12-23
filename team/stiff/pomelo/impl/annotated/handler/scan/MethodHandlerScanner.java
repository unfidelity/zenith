/*
 * Decompiled with CFR 0.152.
 */
package team.stiff.pomelo.impl.annotated.handler.scan;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;
import team.stiff.pomelo.filter.EventFilterScanner;
import team.stiff.pomelo.handler.EventHandler;
import team.stiff.pomelo.handler.scan.EventHandlerScanner;
import team.stiff.pomelo.impl.annotated.filter.MethodFilterScanner;
import team.stiff.pomelo.impl.annotated.handler.MethodEventHandler;
import team.stiff.pomelo.impl.annotated.handler.scan.AnnotatedListenerPredicate;

public final class MethodHandlerScanner
implements EventHandlerScanner {
    private final AnnotatedListenerPredicate annotatedListenerPredicate = new AnnotatedListenerPredicate();
    private final EventFilterScanner<Method> filterScanner = new MethodFilterScanner();

    @Override
    public Map<Class<?>, Set<EventHandler>> locate(Object listenerContainer) {
        HashMap eventHandlers = new HashMap();
        Stream.of(listenerContainer.getClass().getDeclaredMethods()).filter(this.annotatedListenerPredicate).forEach(method -> eventHandlers.computeIfAbsent(method.getParameterTypes()[0], obj -> new TreeSet()).add(new MethodEventHandler(listenerContainer, (Method)method, this.filterScanner.scan((Method)method))));
        return eventHandlers;
    }
}

