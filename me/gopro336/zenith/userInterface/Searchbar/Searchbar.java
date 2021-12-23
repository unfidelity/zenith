/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.userInterface.Searchbar;

import java.util.ArrayList;
import me.gopro336.zenith.userInterface.clickgui.ClickGUI;
import me.gopro336.zenith.userInterface.clickgui.Frame;

public class Searchbar {
    public boolean open = false;
    public float offset = 0.0f;
    public float width = 145.0f;
    public float buffer = 15.0f;

    public void render() {
        if (!this.open) {
            return;
        }
    }

    public void init() {
        ArrayList<Frame> moveList = new ArrayList<Frame>();
        for (Frame f : ClickGUI.getFrames()) {
            if (!((float)f.getPosX() <= this.width + this.buffer)) continue;
            moveList.add(f);
        }
    }

    public void open() {
        this.open = true;
    }

    public void close() {
        this.open = false;
    }
}

