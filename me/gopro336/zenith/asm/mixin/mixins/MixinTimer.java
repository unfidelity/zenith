/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.asm.mixin.imixin.ITimer;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={Timer.class})
public abstract class MixinTimer
implements ITimer {
    @Override
    @Accessor(value="tickLength")
    public abstract float getTickLength();

    @Override
    @Accessor(value="tickLength")
    public abstract void setTickLength(float var1);
}

