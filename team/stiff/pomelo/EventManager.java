/*
 * Decompiled with CFR 0.152.
 */
package team.stiff.pomelo;

public interface EventManager {
    public <E> E dispatchEvent(E var1);

    public boolean isRegisteredListener(Object var1);

    public boolean addEventListener(Object var1);

    public boolean removeEventListener(Object var1);
}

