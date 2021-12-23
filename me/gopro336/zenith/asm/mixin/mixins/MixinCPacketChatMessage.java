/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.asm.mixin.imixin.ICPacketChatMessage;
import net.minecraft.network.play.client.CPacketChatMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={CPacketChatMessage.class})
public abstract class MixinCPacketChatMessage
implements ICPacketChatMessage {
    @Override
    @Accessor(value="message")
    public abstract void setMessage(String var1);
}

