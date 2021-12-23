/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.property;

public class OnChangedValue<T> {
    private final T _old;
    private final T _new;

    public OnChangedValue(T _old, T _new) {
        this._old = _old;
        this._new = _new;
    }

    public T getOld() {
        return this._old;
    }

    public T getNew() {
        return this._new;
    }
}

