/*
 * Decompiled with CFR 0.152.
 */
package team.stiff.pomelo.handler.scan;

import java.util.Map;
import java.util.Set;
import team.stiff.pomelo.handler.EventHandler;

public interface EventHandlerScanner {
    public Map<Class<?>, Set<EventHandler>> locate(Object var1);
}

