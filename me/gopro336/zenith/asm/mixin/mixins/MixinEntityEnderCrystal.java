/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.event.entity.CrystalAttackEvent;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={EntityEnderCrystal.class})
public class MixinEntityEnderCrystal
extends EntityEnderCrystal {
    public MixinEntityEnderCrystal(World worldIn) {
        super(worldIn);
    }

    @Inject(method={"attackEntityFrom"}, at={@At(value="RETURN")}, cancellable=true)
    public void attackEntityFrom(DamageSource source, float amount, CallbackInfo info) {
        CrystalAttackEvent crystalAttackEvent = new CrystalAttackEvent(source);
        Zenith.INSTANCE.getEventManager().dispatchEvent(crystalAttackEvent);
        if (crystalAttackEvent.isCanceled()) {
            info.cancel();
        }
    }
}

