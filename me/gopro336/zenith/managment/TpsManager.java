//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.managment;

import java.util.Arrays;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.IGlobals;
import me.gopro336.zenith.event.network.PacketReceiveEvent;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraft.util.math.MathHelper;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

public class TpsManager
implements IGlobals {
    static final float[] ticks = new float[20];
    private long last_update_tick;
    private int next_index = 0;

    public TpsManager() {
        Zenith.INSTANCE.getEventManager().addEventListener(this);
    }

    @Listener
    public void onPacketSend(PacketReceiveEvent event) {
        if (event.getPacket() instanceof SPacketTimeUpdate) {
            this.update_time();
        }
    }

    public float get_tick_rate() {
        float num_ticks = 0.0f;
        float sum_ticks = 0.0f;
        for (float tick : ticks) {
            if (!(tick > 0.0f)) continue;
            sum_ticks += tick;
            num_ticks += 1.0f;
        }
        return MathHelper.clamp((float)(sum_ticks / num_ticks), (float)0.0f, (float)20.0f);
    }

    public void reset_tick() {
        this.next_index = 0;
        this.last_update_tick = -1L;
        Arrays.fill(ticks, 0.0f);
    }

    public void update_time() {
        if (this.last_update_tick != -1L) {
            float time = (float)(System.currentTimeMillis() - this.last_update_tick) / 1000.0f;
            TpsManager.ticks[this.next_index % TpsManager.ticks.length] = MathHelper.clamp((float)(20.0f / time), (float)0.0f, (float)20.0f);
            ++this.next_index;
        }
        this.last_update_tick = System.currentTimeMillis();
    }
}

