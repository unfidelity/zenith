//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.time;

import java.util.Arrays;
import java.util.EventListener;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.event.network.PacketReceiveEvent;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraft.util.math.MathHelper;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

public class TickRateUtil
implements EventListener {
    public static TickRateUtil INSTANCE;
    private final float[] tickRates = new float[20];
    private int nextIndex = 0;
    private long timeLastTimeUpdate;

    @Listener
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacket() instanceof SPacketTimeUpdate) {
            INSTANCE.onTimeUpdate();
        }
    }

    public TickRateUtil() {
        Zenith.INSTANCE.getEventManager().addEventListener(this);
        Zenith.INSTANCE.getEventManager().addEventListener(this);
        this.reset();
    }

    public void reset() {
        this.nextIndex = 0;
        this.timeLastTimeUpdate = -1L;
        Arrays.fill(this.tickRates, 0.0f);
    }

    public float getTickRate() {
        float numTicks = 0.0f;
        float sumTickRates = 0.0f;
        for (float tickRate : this.tickRates) {
            if (!(tickRate > 0.0f)) continue;
            sumTickRates += tickRate;
            numTicks += 1.0f;
        }
        return MathHelper.clamp((float)(sumTickRates / numTicks), (float)0.0f, (float)20.0f);
    }

    public float getMinTickRate() {
        float minTick = 20.0f;
        for (float tickRate : this.tickRates) {
            if (!(tickRate > 0.0f) || !(tickRate < minTick)) continue;
            minTick = tickRate;
        }
        return MathHelper.clamp((float)minTick, (float)0.0f, (float)20.0f);
    }

    public float getLatestTickRate() {
        try {
            return MathHelper.clamp((float)this.tickRates[this.tickRates.length - 1], (float)0.0f, (float)20.0f);
        }
        catch (Exception e) {
            e.printStackTrace();
            return 20.0f;
        }
    }

    public void onTimeUpdate() {
        if (this.timeLastTimeUpdate != -1L) {
            float timeElapsed = (float)(System.currentTimeMillis() - this.timeLastTimeUpdate) / 1000.0f;
            this.tickRates[this.nextIndex % this.tickRates.length] = MathHelper.clamp((float)(20.0f / timeElapsed), (float)0.0f, (float)20.0f);
            ++this.nextIndex;
        }
        this.timeLastTimeUpdate = System.currentTimeMillis();
    }
}

