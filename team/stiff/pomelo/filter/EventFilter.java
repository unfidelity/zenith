/*
 * Decompiled with CFR 0.152.
 */
package team.stiff.pomelo.filter;

import team.stiff.pomelo.handler.EventHandler;

public interface EventFilter<E> {
    public boolean test(EventHandler var1, E var2);
}

