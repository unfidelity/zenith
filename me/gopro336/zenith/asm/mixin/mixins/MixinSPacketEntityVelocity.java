/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.asm.mixin.imixin.ISPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={SPacketEntityVelocity.class})
public abstract class MixinSPacketEntityVelocity
implements ISPacketEntityVelocity {
    @Override
    @Accessor(value="motionX")
    public abstract void setMotionX(int var1);

    @Override
    @Accessor(value="motionY")
    public abstract void setMotionY(int var1);

    @Override
    @Accessor(value="motionZ")
    public abstract void setMotionZ(int var1);
}

