/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.imixin;

import net.minecraft.network.play.client.CPacketUseEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={CPacketUseEntity.class})
public interface ICPacketUseEntity {
    @Accessor(value="entityID")
    public void setID(int var1);

    @Accessor(value="action")
    public void setAction(CPacketUseEntity.Action var1);
}

