/*
 * Decompiled with CFR 0.152.
 */
package me.rina.turok.util;

public class TurokGeneric<S> {
    private S value;

    public TurokGeneric(S value) {
        this.value = value;
    }

    public void setValue(S value) {
        this.value = value;
    }

    public S getValue() {
        return this.value;
    }
}

