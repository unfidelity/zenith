/*
 * Decompiled with CFR 0.152.
 */
package team.stiff.pomelo.handler;

import team.stiff.pomelo.filter.EventFilter;
import team.stiff.pomelo.handler.ListenerPriority;

public interface EventHandler
extends Comparable<EventHandler> {
    public <E> void handle(E var1);

    public Object getListener();

    public ListenerPriority getPriority();

    public Iterable<EventFilter> getFilters();
}

