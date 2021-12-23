/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.player;

import net.minecraft.util.EnumHandSide;

public class TransformFirstPersonEvent {
    private final EnumHandSide enumHandSide;

    public TransformFirstPersonEvent(EnumHandSide enumHandSide) {
        this.enumHandSide = enumHandSide;
    }

    public EnumHandSide getEnumHandSide() {
        return this.enumHandSide;
    }

    public static class Post
    extends TransformFirstPersonEvent {
        public Post(EnumHandSide enumHandSide) {
            super(enumHandSide);
        }
    }

    public static class Pre
    extends TransformFirstPersonEvent {
        public Pre(EnumHandSide enumHandSide) {
            super(enumHandSide);
        }
    }
}

