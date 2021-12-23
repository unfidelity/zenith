/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins.accessor;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={PlayerControllerMP.class})
public interface IPlayerControllerMP {
    @Accessor(value="curBlockDamageMP")
    public void setCurrentBlockDamage(float var1);

    @Accessor(value="blockHitDelay")
    public void setBlockHitDelay(int var1);

    @Invoker(value="syncCurrentPlayItem")
    public void syncCurrentPlayItem();
}

