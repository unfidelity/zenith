/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.math;

public class AnimationUtil {
    public static float moveTowards(float current, float end, float smoothSpeed, float minSpeed) {
        float movement = (end - current) * smoothSpeed;
        if (movement > 0.0f) {
            movement = Math.max(minSpeed, movement);
            movement = Math.min(end - current, movement);
        } else if (movement < 0.0f) {
            movement = Math.min(-minSpeed, movement);
            movement = Math.max(end - current, movement);
        }
        return current + movement;
    }

    public static double moveTowards(double target, double current, double speed) {
        boolean larger = target > current;
        double dif = Math.max(target, current) - Math.min(target, current);
        double factor = dif * speed;
        if (factor < 0.1) {
            factor = 0.1;
        }
        current = larger ? (current += factor) : (current -= factor);
        return current;
    }

    public static double expand(double target, double current, double speed) {
        if (current > target) {
            current = target;
        }
        if (current < -target) {
            current = -target;
        }
        return current += speed;
    }
}

