/*
 * Decompiled with CFR 0.152.
 */
package team.stiff.pomelo.impl.annotated.handler.scan;

import java.lang.reflect.Method;
import java.util.function.Predicate;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

public final class AnnotatedListenerPredicate
implements Predicate<Method> {
    @Override
    public boolean test(Method method) {
        return method.isAnnotationPresent(Listener.class) && method.getParameterCount() == 1;
    }
}

