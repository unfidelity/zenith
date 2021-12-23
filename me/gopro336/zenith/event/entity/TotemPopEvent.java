//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event.entity;

import net.minecraft.entity.player.EntityPlayer;

public class TotemPopEvent {
    private EntityPlayer entity;
    private final int popCount;

    public TotemPopEvent(EntityPlayer entity, int count) {
        this.entity = entity;
        this.popCount = count;
    }

    public EntityPlayer getEntity() {
        return this.entity;
    }

    public String getName() {
        return this.entity.getName();
    }

    public int getPopCount() {
        return this.popCount;
    }

    public int getEntityId() {
        return this.entity.entityId;
    }
}

