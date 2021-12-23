/*
 * Decompiled with CFR 0.152.
 */
package team.stiff.pomelo.impl.annotated.handler.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import team.stiff.pomelo.filter.EventFilter;
import team.stiff.pomelo.handler.ListenerPriority;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface Listener {
    public Class<? extends EventFilter>[] filters() default {};

    public ListenerPriority priority() default ListenerPriority.NORMAL;
}

