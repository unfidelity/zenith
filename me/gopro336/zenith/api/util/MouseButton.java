/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0006B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0007"}, d2={"Lme/gopro336/zenith/api/util/MouseButton;", "", "(Ljava/lang/String;I)V", "LEFT", "MIDDLE", "RIGHT", "Companion", "zenith"})
public final class MouseButton
extends Enum<MouseButton> {
    public static final /* enum */ MouseButton LEFT;
    public static final /* enum */ MouseButton MIDDLE;
    public static final /* enum */ MouseButton RIGHT;
    private static final /* synthetic */ MouseButton[] $VALUES;
    public static final Companion Companion;

    static {
        MouseButton[] mouseButtonArray = new MouseButton[3];
        MouseButton[] mouseButtonArray2 = mouseButtonArray;
        mouseButtonArray[0] = LEFT = new MouseButton();
        mouseButtonArray[1] = MIDDLE = new MouseButton();
        mouseButtonArray[2] = RIGHT = new MouseButton();
        $VALUES = mouseButtonArray;
        Companion = new Companion(null);
    }

    public static MouseButton[] values() {
        return (MouseButton[])$VALUES.clone();
    }

    public static MouseButton valueOf(String string) {
        return Enum.valueOf(MouseButton.class, string);
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2={"Lme/gopro336/zenith/api/util/MouseButton$Companion;", "", "()V", "fromState", "Lme/gopro336/zenith/api/util/MouseButton;", "buttonState", "", "zenith"})
    public static final class Companion {
        @NotNull
        public final MouseButton fromState(int buttonState) {
            MouseButton mouseButton;
            switch (buttonState) {
                case 0: {
                    mouseButton = LEFT;
                    break;
                }
                case 1: {
                    mouseButton = RIGHT;
                    break;
                }
                case 2: {
                    mouseButton = MIDDLE;
                    break;
                }
                default: {
                    String string = "Illegal state " + buttonState;
                    boolean bl = false;
                    throw (Throwable)new IllegalStateException(string.toString());
                }
            }
            return mouseButton;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

