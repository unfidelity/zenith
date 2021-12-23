/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.misc;

import kotlin.Metadata;
import net.minecraft.util.math.RayTraceResult;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=3)
public final class AntiAfk$WhenMappings {
    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

    static {
        $EnumSwitchMapping$0 = new int[RayTraceResult.Type.values().length];
        AntiAfk$WhenMappings.$EnumSwitchMapping$0[RayTraceResult.Type.ENTITY.ordinal()] = 1;
        AntiAfk$WhenMappings.$EnumSwitchMapping$0[RayTraceResult.Type.BLOCK.ordinal()] = 2;
        AntiAfk$WhenMappings.$EnumSwitchMapping$0[RayTraceResult.Type.MISS.ordinal()] = 3;
    }
}

