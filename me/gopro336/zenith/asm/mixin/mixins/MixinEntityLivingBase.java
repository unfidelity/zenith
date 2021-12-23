/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.asm.mixin.mixins.MixinEntity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={EntityLivingBase.class}, priority=0x7FFFFFFF)
public abstract class MixinEntityLivingBase
extends MixinEntity {
}

