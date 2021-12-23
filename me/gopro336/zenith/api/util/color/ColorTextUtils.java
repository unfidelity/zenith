/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.color;

public class ColorTextUtils {
    public static String getColor(colors value) {
        String prefix = value.equals((Object)colors.White) ? "&f" : (value.equals((Object)colors.Red) ? "&4" : (value.equals((Object)colors.Blue) ? "&1" : (value.equals((Object)colors.Cyan) ? "&3" : (value.equals((Object)colors.Pink) ? "&d" : (value.equals((Object)colors.Black) ? "&0" : (value.equals((Object)colors.Green) ? "&2" : (value.equals((Object)colors.Purple) ? "&5" : (value.equals((Object)colors.Yellow) ? "&e" : (value.equals((Object)colors.LightRed) ? "&c" : (value.equals((Object)colors.LightBlue) ? "&b" : (value.equals((Object)colors.LightGreen) ? "&a" : (value.equals((Object)colors.Gold) ? "&6" : (value.equals((Object)colors.Gray) ? "&8" : (value.equals((Object)colors.Lavender) ? "&9" : (value.equals((Object)colors.LightGray) ? "&7" : "&r")))))))))))))));
        return prefix;
    }

    public static enum colors {
        White,
        Black,
        Blue,
        Green,
        Cyan,
        Red,
        Purple,
        Gold,
        LightGray,
        Gray,
        Lavender,
        LightGreen,
        LightBlue,
        LightRed,
        Pink,
        Yellow;

    }
}

