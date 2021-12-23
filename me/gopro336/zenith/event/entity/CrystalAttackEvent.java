/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.entity;

import me.gopro336.zenith.event.EventCancellable;
import net.minecraft.util.DamageSource;

public class CrystalAttackEvent
extends EventCancellable {
    private final DamageSource damageSource;

    public CrystalAttackEvent(DamageSource damageSource) {
        this.damageSource = damageSource;
    }

    public DamageSource getDamageSource() {
        return this.damageSource;
    }
}

