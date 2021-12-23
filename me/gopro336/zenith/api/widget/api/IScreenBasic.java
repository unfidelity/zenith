/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.widget.api;

import me.rina.turok.util.TurokRect;

public interface IScreenBasic {
    public boolean isEnabled();

    public TurokRect getRect();

    public boolean verifyFocus(int var1, int var2);

    public void onScreenOpened();

    public void onCustomScreenOpened();

    public void onScreenClosed();

    public void onCustomScreenClosed();

    public void onKeyboardPressed(char var1, int var2);

    public void onCustomKeyboardPressed(char var1, int var2);

    public void onMouseClicked(int var1);

    public void onCustomMouseClicked(int var1);

    public void onMouseReleased(int var1);

    public void onCustomMouseReleased(int var1);

    public void onRender();

    public void onCustomRender();

    public void onSave();

    public void onLoad();
}

