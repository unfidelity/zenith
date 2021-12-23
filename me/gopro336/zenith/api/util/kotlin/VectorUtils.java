//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.kotlin;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import me.gopro336.zenith.api.util.kotlin.floor;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J>\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\rH\u0002J&\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0014j\b\u0012\u0004\u0012\u00020\u000b`\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\bJ\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000bJ\u0012\u0010\u001c\u001a\u00020\u0006*\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0012\u0010\u001c\u001a\u00020\u0006*\u00020\u001d2\u0006\u0010 \u001a\u00020\u0017J\u0012\u0010\u001c\u001a\u00020\u0006*\u00020\u001d2\u0006\u0010!\u001a\u00020\"J\u0012\u0010\u001c\u001a\u00020\u0006*\u00020\u00172\u0006\u0010!\u001a\u00020\"J\u0012\u0010\u001c\u001a\u00020\u0006*\u00020\"2\u0006\u0010!\u001a\u00020\"J\u0015\u0010#\u001a\u00020\u0017*\u00020\u00172\u0006\u0010 \u001a\u00020\u0017H\u0086\u0006J\u0012\u0010$\u001a\u00020\"*\u00020\"2\u0006\u0010%\u001a\u00020\rJ\u0015\u0010&\u001a\u00020\u0017*\u00020\u00172\u0006\u0010 \u001a\u00020\u0017H\u0086\u0006J\u0015\u0010'\u001a\u00020\u0017*\u00020\u00172\u0006\u0010%\u001a\u00020\u0006H\u0086\u0006J\u0015\u0010'\u001a\u00020\u0017*\u00020\u00172\u0006\u0010 \u001a\u00020\u0017H\u0086\u0006J\n\u0010(\u001a\u00020\u000b*\u00020\u0017J\n\u0010)\u001a\u00020\u0017*\u00020\"J\"\u0010)\u001a\u00020\u0017*\u00020\"2\u0006\u0010*\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u0006J\u0012\u0010)\u001a\u00020\u0017*\u00020\"2\u0006\u0010-\u001a\u00020\u0017J\n\u0010.\u001a\u00020\u0017*\u00020\"J\"\u0010.\u001a\u00020\u0017*\u00020\"2\u0006\u0010*\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u0006J\u0012\u0010.\u001a\u00020\u0017*\u00020\"2\u0006\u0010-\u001a\u00020\u0017\u00a8\u0006/"}, d2={"Lme/gopro336/zenith/api/util/kotlin/VectorUtils;", "", "()V", "getAxisRange", "Lkotlin/ranges/IntRange;", "d1", "", "d2", "", "getBlockPos", "", "Lnet/minecraft/util/math/BlockPos;", "minX", "", "maxX", "minY", "maxY", "minZ", "maxZ", "getBlockPosInSphere", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "center", "Lnet/minecraft/util/math/Vec3d;", "radius", "getBlockPositionsInArea", "pos1", "pos2", "distanceTo", "Lnet/minecraft/entity/Entity;", "chunkPos", "Lnet/minecraft/util/math/ChunkPos;", "vec3d", "vec3i", "Lnet/minecraft/util/math/Vec3i;", "minus", "multiply", "multiplier", "plus", "times", "toBlockPos", "toVec3d", "xOffset", "yOffset", "zOffset", "offSet", "toVec3dCenter", "zenith"})
public final class VectorUtils {
    public static final VectorUtils INSTANCE;

    @NotNull
    public final List<BlockPos> getBlockPositionsInArea(@NotNull BlockPos pos1, @NotNull BlockPos pos2) {
        Intrinsics.checkParameterIsNotNull(pos1, "pos1");
        Intrinsics.checkParameterIsNotNull(pos2, "pos2");
        int n = pos1.x;
        int n2 = pos2.x;
        int n3 = 0;
        int minX = Math.min(n, n2);
        n2 = pos1.x;
        n3 = pos2.x;
        int n4 = 0;
        int maxX = Math.max(n2, n3);
        n3 = pos1.y;
        n4 = pos2.y;
        int n5 = 0;
        int minY = Math.min(n3, n4);
        n4 = pos1.y;
        n5 = pos2.y;
        int n6 = 0;
        int maxY = Math.max(n4, n5);
        n5 = pos1.z;
        n6 = pos2.z;
        int n7 = 0;
        int minZ = Math.min(n5, n6);
        n6 = pos1.z;
        n7 = pos2.z;
        boolean bl = false;
        int maxZ = Math.max(n6, n7);
        return this.getBlockPos(minX, maxX, minY, maxY, minZ, maxZ);
    }

    /*
     * WARNING - void declaration
     */
    private final List<BlockPos> getBlockPos(int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
        ArrayList<BlockPos> returnList = new ArrayList<BlockPos>();
        int n = minX;
        int n2 = maxX;
        if (n <= n2) {
            while (true) {
                void x;
                int n3;
                int n4;
                if ((n4 = minZ) <= (n3 = maxZ)) {
                    while (true) {
                        void z;
                        int n5;
                        int n6;
                        if ((n6 = minY) <= (n5 = maxY)) {
                            while (true) {
                                void y;
                                returnList.add(new BlockPos((int)x, (int)y, (int)z));
                                if (y == n5) break;
                                ++y;
                            }
                        }
                        if (z == n3) break;
                        ++z;
                    }
                }
                if (x == n2) break;
                ++x;
            }
        }
        return returnList;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public final ArrayList<BlockPos> getBlockPosInSphere(@NotNull Vec3d center, float radius) {
        Intrinsics.checkParameterIsNotNull(center, "center");
        float f = radius;
        int n = 2;
        int n2 = 0;
        float squaredRadius = (float)Math.pow(f, n);
        ArrayList<BlockPos> posList = new ArrayList<BlockPos>();
        IntRange intRange = this.getAxisRange(center.x, radius);
        n = intRange.getFirst();
        n2 = intRange.getLast();
        if (n <= n2) {
            while (true) {
                void x;
                int n3;
                IntRange intRange2 = this.getAxisRange(center.y, radius);
                int n4 = intRange2.getFirst();
                if (n4 <= (n3 = intRange2.getLast())) {
                    while (true) {
                        void y;
                        int n5;
                        IntRange intRange3 = this.getAxisRange(center.z, radius);
                        int n6 = intRange3.getFirst();
                        if (n6 <= (n5 = intRange3.getLast())) {
                            while (true) {
                                void z;
                                BlockPos blockPos;
                                if (!((blockPos = new BlockPos((int)x, (int)y, (int)z)).distanceSqToCenter(center.x, center.y, center.z) > (double)squaredRadius)) {
                                    posList.add(blockPos);
                                }
                                if (z == n5) break;
                                ++z;
                            }
                        }
                        if (y == n3) break;
                        ++y;
                    }
                }
                if (x == n2) break;
                ++x;
            }
        }
        return posList;
    }

    private final IntRange getAxisRange(double d1, float d2) {
        return new IntRange(floor.floorToInt(d1 - (double)d2), floor.ceilToInt(d1 + (double)d2));
    }

    @NotNull
    public final BlockPos toBlockPos(@NotNull Vec3d $this$toBlockPos) {
        Intrinsics.checkParameterIsNotNull($this$toBlockPos, "$this$toBlockPos");
        return new BlockPos(floor.floorToInt($this$toBlockPos.x), floor.floorToInt($this$toBlockPos.y), floor.floorToInt($this$toBlockPos.z));
    }

    @NotNull
    public final Vec3d toVec3d(@NotNull Vec3i $this$toVec3d) {
        Intrinsics.checkParameterIsNotNull($this$toVec3d, "$this$toVec3d");
        return this.toVec3d($this$toVec3d, 0.0, 0.0, 0.0);
    }

    @NotNull
    public final Vec3d toVec3d(@NotNull Vec3i $this$toVec3d, @NotNull Vec3d offSet) {
        Intrinsics.checkParameterIsNotNull($this$toVec3d, "$this$toVec3d");
        Intrinsics.checkParameterIsNotNull(offSet, "offSet");
        return new Vec3d((double)$this$toVec3d.x + offSet.x, (double)$this$toVec3d.y + offSet.y, (double)$this$toVec3d.z + offSet.z);
    }

    @NotNull
    public final Vec3d toVec3d(@NotNull Vec3i $this$toVec3d, double xOffset, double yOffset, double zOffset) {
        Intrinsics.checkParameterIsNotNull($this$toVec3d, "$this$toVec3d");
        return new Vec3d((double)$this$toVec3d.x + xOffset, (double)$this$toVec3d.y + yOffset, (double)$this$toVec3d.z + zOffset);
    }

    @NotNull
    public final Vec3d toVec3dCenter(@NotNull Vec3i $this$toVec3dCenter) {
        Intrinsics.checkParameterIsNotNull($this$toVec3dCenter, "$this$toVec3dCenter");
        return this.toVec3dCenter($this$toVec3dCenter, 0.0, 0.0, 0.0);
    }

    @NotNull
    public final Vec3d toVec3dCenter(@NotNull Vec3i $this$toVec3dCenter, @NotNull Vec3d offSet) {
        Intrinsics.checkParameterIsNotNull($this$toVec3dCenter, "$this$toVec3dCenter");
        Intrinsics.checkParameterIsNotNull(offSet, "offSet");
        return new Vec3d((double)$this$toVec3dCenter.x + 0.5 + offSet.x, (double)$this$toVec3dCenter.y + 0.5 + offSet.y, (double)$this$toVec3dCenter.z + 0.5 + offSet.z);
    }

    @NotNull
    public final Vec3d toVec3dCenter(@NotNull Vec3i $this$toVec3dCenter, double xOffset, double yOffset, double zOffset) {
        Intrinsics.checkParameterIsNotNull($this$toVec3dCenter, "$this$toVec3dCenter");
        return new Vec3d((double)$this$toVec3dCenter.x + 0.5 + xOffset, (double)$this$toVec3dCenter.y + 0.5 + yOffset, (double)$this$toVec3dCenter.z + 0.5 + zOffset);
    }

    public final double distanceTo(@NotNull Vec3i $this$distanceTo, @NotNull Vec3i vec3i) {
        Intrinsics.checkParameterIsNotNull($this$distanceTo, "$this$distanceTo");
        Intrinsics.checkParameterIsNotNull(vec3i, "vec3i");
        int xDiff = vec3i.x - $this$distanceTo.x;
        int yDiff = vec3i.y - $this$distanceTo.y;
        int zDiff = vec3i.z - $this$distanceTo.z;
        double d = xDiff * xDiff + yDiff * yDiff + zDiff * zDiff;
        boolean bl = false;
        return Math.sqrt(d);
    }

    public final double distanceTo(@NotNull Vec3d $this$distanceTo, @NotNull Vec3i vec3i) {
        Intrinsics.checkParameterIsNotNull($this$distanceTo, "$this$distanceTo");
        Intrinsics.checkParameterIsNotNull(vec3i, "vec3i");
        double xDiff = (double)vec3i.x + 0.5 - $this$distanceTo.x;
        double yDiff = (double)vec3i.y + 0.5 - $this$distanceTo.y;
        double zDiff = (double)vec3i.z + 0.5 - $this$distanceTo.z;
        double d = xDiff * xDiff + yDiff * yDiff + zDiff * zDiff;
        boolean bl = false;
        return Math.sqrt(d);
    }

    public final double distanceTo(@NotNull Entity $this$distanceTo, @NotNull Vec3i vec3i) {
        Intrinsics.checkParameterIsNotNull($this$distanceTo, "$this$distanceTo");
        Intrinsics.checkParameterIsNotNull(vec3i, "vec3i");
        double xDiff = (double)vec3i.x + 0.5 - $this$distanceTo.posX;
        double yDiff = (double)vec3i.y + 0.5 - $this$distanceTo.posY;
        double zDiff = (double)vec3i.z + 0.5 - $this$distanceTo.posZ;
        double d = xDiff * xDiff + yDiff * yDiff + zDiff * zDiff;
        boolean bl = false;
        return Math.sqrt(d);
    }

    public final double distanceTo(@NotNull Entity $this$distanceTo, @NotNull Vec3d vec3d) {
        Intrinsics.checkParameterIsNotNull($this$distanceTo, "$this$distanceTo");
        Intrinsics.checkParameterIsNotNull(vec3d, "vec3d");
        double xDiff = vec3d.x - $this$distanceTo.posX;
        double yDiff = vec3d.y - $this$distanceTo.posY;
        double zDiff = vec3d.z - $this$distanceTo.posZ;
        double d = xDiff * xDiff + yDiff * yDiff + zDiff * zDiff;
        boolean bl = false;
        return Math.sqrt(d);
    }

    public final double distanceTo(@NotNull Entity $this$distanceTo, @NotNull ChunkPos chunkPos) {
        Intrinsics.checkParameterIsNotNull($this$distanceTo, "$this$distanceTo");
        Intrinsics.checkParameterIsNotNull(chunkPos, "chunkPos");
        double d = (double)(chunkPos.x * 16 + 8) - $this$distanceTo.posX;
        double d2 = (double)(chunkPos.z * 16 + 8) - $this$distanceTo.posZ;
        boolean bl = false;
        return Math.hypot(d, d2);
    }

    @NotNull
    public final Vec3i multiply(@NotNull Vec3i $this$multiply, int multiplier) {
        Intrinsics.checkParameterIsNotNull($this$multiply, "$this$multiply");
        return new Vec3i($this$multiply.x * multiplier, $this$multiply.y * multiplier, $this$multiply.z * multiplier);
    }

    @NotNull
    public final Vec3d times(@NotNull Vec3d $this$times, @NotNull Vec3d vec3d) {
        Intrinsics.checkParameterIsNotNull($this$times, "$this$times");
        Intrinsics.checkParameterIsNotNull(vec3d, "vec3d");
        return new Vec3d($this$times.x * vec3d.x, $this$times.y * vec3d.y, $this$times.z * vec3d.z);
    }

    @NotNull
    public final Vec3d times(@NotNull Vec3d $this$times, double multiplier) {
        Intrinsics.checkParameterIsNotNull($this$times, "$this$times");
        return new Vec3d($this$times.x * multiplier, $this$times.y * multiplier, $this$times.z * multiplier);
    }

    @NotNull
    public final Vec3d plus(@NotNull Vec3d $this$plus, @NotNull Vec3d vec3d) {
        Intrinsics.checkParameterIsNotNull($this$plus, "$this$plus");
        Intrinsics.checkParameterIsNotNull(vec3d, "vec3d");
        Vec3d vec3d2 = $this$plus.add(vec3d);
        Intrinsics.checkExpressionValueIsNotNull(vec3d2, "add(vec3d)");
        return vec3d2;
    }

    @NotNull
    public final Vec3d minus(@NotNull Vec3d $this$minus, @NotNull Vec3d vec3d) {
        Intrinsics.checkParameterIsNotNull($this$minus, "$this$minus");
        Intrinsics.checkParameterIsNotNull(vec3d, "vec3d");
        Vec3d vec3d2 = $this$minus.subtract(vec3d);
        Intrinsics.checkExpressionValueIsNotNull(vec3d2, "subtract(vec3d)");
        return vec3d2;
    }

    private VectorUtils() {
    }

    static {
        VectorUtils vectorUtils;
        INSTANCE = vectorUtils = new VectorUtils();
    }
}

