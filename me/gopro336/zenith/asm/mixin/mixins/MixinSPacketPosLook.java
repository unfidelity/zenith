/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.asm.mixin.imixin.ISPacketPlayerPosLook;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={SPacketPlayerPosLook.class})
public abstract class MixinSPacketPosLook
implements ISPacketPlayerPosLook {
    @Override
    @Accessor(value="yaw")
    public abstract float getYaw();

    @Override
    @Accessor(value="yaw")
    public abstract void setYaw(float var1);

    @Override
    @Accessor(value="pitch")
    public abstract float getPitch();

    @Override
    @Accessor(value="pitch")
    public abstract void setPitch(float var1);
}

