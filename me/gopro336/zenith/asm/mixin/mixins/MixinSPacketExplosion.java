/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.asm.mixin.imixin.ISPacketExplosion;
import net.minecraft.network.play.server.SPacketExplosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={SPacketExplosion.class})
public abstract class MixinSPacketExplosion
implements ISPacketExplosion {
    @Override
    @Accessor(value="motionX")
    public abstract void setMotionX(float var1);

    @Override
    @Accessor(value="motionY")
    public abstract void setMotionY(float var1);

    @Override
    @Accessor(value="motionZ")
    public abstract void setMotionZ(float var1);
}

