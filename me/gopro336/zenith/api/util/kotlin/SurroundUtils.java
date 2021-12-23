//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.kotlin;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.gopro336.zenith.api.util.Wrapper;
import me.gopro336.zenith.api.util.kotlin.floor;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u0018B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\rJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\bR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0015\u0010\f\u001a\u00020\b*\u00020\r8F\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0019"}, d2={"Lme/gopro336/zenith/api/util/kotlin/SurroundUtils;", "", "()V", "mc", "Lnet/minecraft/client/Minecraft;", "kotlin.jvm.PlatformType", "surroundOffset", "", "Lnet/minecraft/util/math/BlockPos;", "getSurroundOffset", "()[Lnet/minecraft/util/math/BlockPos;", "[Lnet/minecraft/util/math/BlockPos;", "flooredPosition", "Lnet/minecraft/entity/Entity;", "getFlooredPosition", "(Lnet/minecraft/entity/Entity;)Lnet/minecraft/util/math/BlockPos;", "checkBlock", "", "block", "Lnet/minecraft/block/Block;", "checkHole", "Lme/gopro336/zenith/api/util/kotlin/SurroundUtils$HoleType;", "entity", "pos", "HoleType", "zenith"})
public final class SurroundUtils {
    private static final Minecraft mc;
    @NotNull
    private static final BlockPos[] surroundOffset;
    public static final SurroundUtils INSTANCE;

    @NotNull
    public final BlockPos getFlooredPosition(@NotNull Entity $this$flooredPosition) {
        Intrinsics.checkParameterIsNotNull($this$flooredPosition, "$this$flooredPosition");
        return new BlockPos(floor.floorToInt($this$flooredPosition.posX), floor.floorToInt($this$flooredPosition.posY), floor.floorToInt($this$flooredPosition.posZ));
    }

    @NotNull
    public final BlockPos[] getSurroundOffset() {
        return surroundOffset;
    }

    @NotNull
    public final HoleType checkHole(@NotNull Entity entity) {
        Intrinsics.checkParameterIsNotNull(entity, "entity");
        return this.checkHole(this.getFlooredPosition(entity));
    }

    @NotNull
    public final HoleType checkHole(@NotNull BlockPos pos) {
        Intrinsics.checkParameterIsNotNull(pos, "pos");
        if (!(SurroundUtils.mc.world.isAirBlock(pos) && SurroundUtils.mc.world.isAirBlock(pos.up()) && SurroundUtils.mc.world.isAirBlock(pos.up().up()))) {
            return HoleType.NONE;
        }
        HoleType type = HoleType.BEDROCK;
        for (BlockPos offset : surroundOffset) {
            Block block;
            IBlockState iBlockState = SurroundUtils.mc.world.getBlockState(pos.add((Vec3i)offset));
            Intrinsics.checkExpressionValueIsNotNull(iBlockState, "mc.world.getBlockState(pos.add(offset))");
            Block block2 = block = iBlockState.getBlock();
            Intrinsics.checkExpressionValueIsNotNull(block2, "block");
            if (!this.checkBlock(block2)) {
                type = HoleType.NONE;
                break;
            }
            if (!(Intrinsics.areEqual(block, Blocks.BEDROCK) ^ true)) continue;
            type = HoleType.OBBY;
        }
        return type;
    }

    private final boolean checkBlock(Block block) {
        return Intrinsics.areEqual(block, Blocks.BEDROCK) || Intrinsics.areEqual(block, Blocks.OBSIDIAN) || Intrinsics.areEqual(block, Blocks.ENDER_CHEST) || Intrinsics.areEqual(block, Blocks.ANVIL);
    }

    private SurroundUtils() {
    }

    static {
        SurroundUtils surroundUtils;
        INSTANCE = surroundUtils = new SurroundUtils();
        mc = Wrapper.mc;
        surroundOffset = new BlockPos[]{new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0)};
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2={"Lme/gopro336/zenith/api/util/kotlin/SurroundUtils$HoleType;", "", "(Ljava/lang/String;I)V", "NONE", "OBBY", "BEDROCK", "zenith"})
    public static final class HoleType
    extends Enum<HoleType> {
        public static final /* enum */ HoleType NONE;
        public static final /* enum */ HoleType OBBY;
        public static final /* enum */ HoleType BEDROCK;
        private static final /* synthetic */ HoleType[] $VALUES;

        static {
            HoleType[] holeTypeArray = new HoleType[3];
            HoleType[] holeTypeArray2 = holeTypeArray;
            holeTypeArray[0] = NONE = new HoleType();
            holeTypeArray[1] = OBBY = new HoleType();
            holeTypeArray[2] = BEDROCK = new HoleType();
            $VALUES = holeTypeArray;
        }

        public static HoleType[] values() {
            return (HoleType[])$VALUES.clone();
        }

        public static HoleType valueOf(String string) {
            return Enum.valueOf(HoleType.class, string);
        }
    }
}

