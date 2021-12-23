/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.asm.mixin.imixin.ICPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={CPacketPlayer.class})
public abstract class MixinCPacketPlayer
implements ICPacketPlayer {
    @Override
    @Accessor(value="yaw")
    public abstract void setYaw(float var1);

    @Override
    @Accessor(value="pitch")
    public abstract void setPitch(float var1);

    @Override
    @Accessor(value="x")
    public abstract void setX(double var1);

    @Override
    @Accessor(value="y")
    public abstract void setY(double var1);

    @Override
    @Accessor(value="z")
    public abstract void setZ(double var1);

    @Override
    @Accessor(value="onGround")
    public abstract void setOnGround(boolean var1);
}

