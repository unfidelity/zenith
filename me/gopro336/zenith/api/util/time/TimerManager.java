//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.time;

import me.gopro336.zenith.api.util.time.TickRateUtil;
import me.gopro336.zenith.asm.mixin.imixin.IMinecraft;
import me.gopro336.zenith.asm.mixin.imixin.ITimer;
import me.gopro336.zenith.event.world.UpdateEvent;
import me.gopro336.zenith.feature.Feature;
import net.minecraft.client.Minecraft;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

public class TimerManager {
    private Feature currentModule;
    private int priority;
    private float timerSpeed;
    private boolean active = false;
    private boolean tpsSync = false;

    public void updateTimer(Feature module, int priority, float timerSpeed) {
        if (module == this.currentModule) {
            this.priority = priority;
            this.timerSpeed = timerSpeed;
            this.active = true;
        } else if (priority > this.priority || !this.active) {
            this.currentModule = module;
            this.priority = priority;
            this.timerSpeed = timerSpeed;
            this.active = true;
        }
    }

    public void resetTimer(Feature module) {
        if (this.currentModule == module) {
            this.active = false;
        }
    }

    @Listener
    public void onUpdate(UpdateEvent event) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null) {
            ((ITimer)((IMinecraft)Minecraft.getMinecraft()).getTimer()).setTickLength(50.0f);
            return;
        }
        if (this.tpsSync && (double)TickRateUtil.INSTANCE.getLatestTickRate() > 0.125) {
            ((ITimer)((IMinecraft)Minecraft.getMinecraft()).getTimer()).setTickLength(Math.min(500.0f, 50.0f * (20.0f / TickRateUtil.INSTANCE.getLatestTickRate())));
        } else {
            ((ITimer)((IMinecraft)Minecraft.getMinecraft()).getTimer()).setTickLength(this.active ? 50.0f / this.timerSpeed : 50.0f);
        }
    }

    public boolean isTpsSync() {
        return this.tpsSync;
    }

    public void setTpsSync(boolean tpsSync) {
        this.tpsSync = tpsSync;
    }
}

