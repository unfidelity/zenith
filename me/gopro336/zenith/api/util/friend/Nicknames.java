/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.friend;

import java.util.HashMap;
import java.util.Map;

public class Nicknames {
    private static Map<String, String> aliases = new HashMap<String, String>();

    public static void addNickname(String name, String nick) {
        aliases.put(name, nick);
    }

    public static void removeNickname(String name) {
        aliases.remove(name);
    }

    public static String getNickname(String name) {
        return aliases.get(name);
    }

    public static boolean hasNickname(String name) {
        return aliases.containsKey(name);
    }

    public static Map<String, String> getAliases() {
        return aliases;
    }
}

