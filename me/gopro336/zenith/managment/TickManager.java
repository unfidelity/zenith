//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.managment;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.IGlobals;
import me.gopro336.zenith.api.util.MathUtil;
import me.gopro336.zenith.asm.mixin.imixin.IMinecraft;
import me.gopro336.zenith.asm.mixin.imixin.ITimer;
import me.gopro336.zenith.event.network.PacketReceiveEvent;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraft.util.math.MathHelper;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

public class TickManager
implements IGlobals {
    public long prevTime = -1L;
    public float[] TPS = new float[20];
    public int currentTick;

    public TickManager() {
        int len = this.TPS.length;
        for (int i = 0; i < len; ++i) {
            this.TPS[i] = 0.0f;
        }
        Zenith.INSTANCE.getEventManager().addEventListener(this);
    }

    public float getTPS(TPS tps) {
        switch (tps) {
            case CURRENT: {
                return mc.isSingleplayer() ? 20.0f : (float)MathUtil.roundDouble(MathHelper.clamp((float)this.TPS[0], (float)0.0f, (float)20.0f), 2);
            }
            case AVERAGE: {
                int tickCount = 0;
                float tickRate = 0.0f;
                for (float tick : this.TPS) {
                    if (!(tick > 0.0f)) continue;
                    tickRate += tick;
                    ++tickCount;
                }
                return mc.isSingleplayer() ? 20.0f : (float)MathUtil.roundDouble(MathHelper.clamp((float)(tickRate / (float)tickCount), (float)0.0f, (float)20.0f), 2);
            }
        }
        return 0.0f;
    }

    @Listener
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacket() instanceof SPacketTimeUpdate) {
            if (this.prevTime != -1L) {
                this.TPS[this.currentTick % this.TPS.length] = MathHelper.clamp((float)(20.0f / ((float)(System.currentTimeMillis() - this.prevTime) / 1000.0f)), (float)0.0f, (float)20.0f);
                ++this.currentTick;
            }
            this.prevTime = System.currentTimeMillis();
        }
    }

    public void setClientTicks(double ticks) {
        ((ITimer)((IMinecraft)mc).getTimer()).setTickLength((float)(50.0 / ticks));
    }

    public static enum TPS {
        CURRENT,
        AVERAGE,
        NONE;

    }
}

