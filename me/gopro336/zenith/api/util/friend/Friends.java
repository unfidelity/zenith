/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.friend;

import io.netty.util.internal.ConcurrentSet;
import me.gopro336.zenith.api.util.friend.Friend;

public class Friends {
    private static ConcurrentSet<Friend> friends = new ConcurrentSet();

    public static void addFriend(String name) {
        friends.add((Object)new Friend(name));
    }

    public static void delFriend(String name) {
        friends.remove((Object)Friends.getFriendByName(name));
    }

    public static Friend getFriendByName(String name) {
        for (Friend f : friends) {
            if (!f.username.equalsIgnoreCase(name)) continue;
            return f;
        }
        return null;
    }

    public static ConcurrentSet<Friend> getFriends() {
        return friends;
    }

    public static boolean isFriend(String name) {
        return friends.stream().anyMatch(friend -> friend.username.equalsIgnoreCase(name));
    }
}

