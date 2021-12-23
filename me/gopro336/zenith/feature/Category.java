/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature;

public enum Category {
    COMBAT("Combat"),
    EXPLOIT("Exploit"),
    RENDER("Render"),
    MOVEMENT("Movement"),
    MISC("Miscellaneous"),
    CLIENT("Client"),
    HUD("Client");

    private String name;

    private Category(String name) {
        this.setName(name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

