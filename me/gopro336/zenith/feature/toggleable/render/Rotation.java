//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.render;

import me.gopro336.zenith.api.util.IGlobals;
import net.minecraft.entity.player.EntityPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Rotation
implements IGlobals {
    public float yaw;
    public float pitch;

    public float getPitch() {
        return this.pitch;
    }

    public void meth1(float var1) {
        this.yaw = var1;
    }

    @NotNull
    public Rotation c(float var1, float var2) {
        return new Rotation(var1, var2);
    }

    public float meth4() {
        return this.pitch;
    }

    public static Rotation c(Rotation var0, float var1, float var2, int var3, Object var4) {
        if ((var3 & 1) != 0) {
            var1 = var0.yaw;
        }
        if ((var3 & 2) != 0) {
            var2 = var0.pitch;
        }
        return var0.c(var1, var2);
    }

    public float meth2() {
        return this.yaw;
    }

    public Rotation(float var1, float var2) {
        this.yaw = var1;
        this.pitch = var2;
    }

    public void setPitch(float var1) {
        this.pitch = var1;
    }

    public boolean c(@NotNull Rotation var1) {
        return var1.yaw == this.yaw && var1.pitch == this.pitch;
    }

    public void setEntityRotation(@NotNull EntityPlayer var1) {
        float var2 = this.yaw;
        boolean var3 = false;
        if (!Float.isNaN(var2)) {
            var2 = this.pitch;
            var3 = false;
            if (!Float.isNaN(var2)) {
                this.fixedSensitivity(Rotation.mc.gameSettings.mouseSensitivity);
                var1.rotationYaw = this.yaw;
                var1.rotationPitch = this.pitch;
                return;
            }
        }
    }

    public int hashCode() {
        return Float.hashCode(this.yaw) * 31 + Float.hashCode(this.pitch);
    }

    public float meth3() {
        return this.yaw;
    }

    @NotNull
    public String toString() {
        return "Rotation(yaw=" + this.yaw + ", pitch=" + this.pitch + ")";
    }

    public void fixedSensitivity(float var1) {
        float var2 = var1 * 0.6f + 0.2f;
        float var3 = var2 * var2 * var2 * 1.2f;
        this.yaw -= this.yaw % var3;
        this.pitch -= this.pitch % var3;
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (var1 instanceof Rotation) {
                Rotation var2 = (Rotation)var1;
                if (Float.compare(this.yaw, var2.yaw) == 0 && Float.compare(this.pitch, var2.pitch) == 0) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}

