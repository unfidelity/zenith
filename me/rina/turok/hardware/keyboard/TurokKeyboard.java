/*
 * Decompiled with CFR 0.152.
 */
package me.rina.turok.hardware.keyboard;

import org.lwjgl.input.Keyboard;

public class TurokKeyboard {
    public static String toString(int key) {
        return key != -1 ? Keyboard.getKeyName((int)key) : "NONE";
    }
}

