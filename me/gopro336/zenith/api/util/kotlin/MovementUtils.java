//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.kotlin;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.gopro336.zenith.api.util.kotlin.SurroundUtils;
import me.gopro336.zenith.api.util.kotlin.VectorUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0013J$\u0010\u0019\u001a\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\n2\b\b\u0002\u0010\u001b\u001a\u00020\n2\b\b\u0002\u0010\u001c\u001a\u00020\nJ\u0010\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\nH\u0002J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010\u0016\u001a\u00020\u0013J\n\u0010!\u001a\u00020\u0004*\u00020\"J\u0012\u0010#\u001a\u00020\u0004*\u00020\u00102\u0006\u0010$\u001a\u00020%J\n\u0010&\u001a\u00020 *\u00020'J\n\u0010(\u001a\u00020 *\u00020'R\u0011\u0010\u0003\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\n8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0015\u0010\u000f\u001a\u00020\u0004*\u00020\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u0015\u0010\u0012\u001a\u00020\u0013*\u00020\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\u0016\u001a\u00020\u0013*\u00020\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015\u00a8\u0006)"}, d2={"Lme/gopro336/zenith/api/util/kotlin/MovementUtils;", "", "()V", "isInputting", "", "()Z", "mc", "Lnet/minecraft/client/Minecraft;", "kotlin.jvm.PlatformType", "roundedForward", "", "getRoundedForward", "()F", "roundedStrafing", "getRoundedStrafing", "isMoving", "Lnet/minecraft/entity/Entity;", "(Lnet/minecraft/entity/Entity;)Z", "realSpeed", "", "getRealSpeed", "(Lnet/minecraft/entity/Entity;)D", "speed", "getSpeed", "applySpeedPotionEffects", "calcMoveYaw", "yawIn", "moveForward", "moveString", "getRoundedMovementInput", "input", "setSpeed", "", "centerPlayer", "Lnet/minecraft/client/entity/EntityPlayerSP;", "isCentered", "pos", "Lnet/minecraft/util/math/BlockPos;", "resetJumpSneak", "Lnet/minecraft/util/MovementInput;", "resetMove", "zenith"})
public final class MovementUtils {
    private static final Minecraft mc;
    public static final MovementUtils INSTANCE;

    public final boolean isInputting() {
        boolean bl;
        EntityPlayerSP entityPlayerSP = MovementUtils.mc.player;
        if (entityPlayerSP != null && (entityPlayerSP = entityPlayerSP.movementInput) != null) {
            EntityPlayerSP entityPlayerSP2 = entityPlayerSP;
            boolean bl2 = false;
            boolean bl3 = false;
            EntityPlayerSP it = entityPlayerSP2;
            boolean bl4 = false;
            bl = it.moveForward != 0.0f || it.moveStrafe != 0.0f;
        } else {
            bl = false;
        }
        return bl;
    }

    public final boolean isMoving(@NotNull Entity $this$isMoving) {
        Intrinsics.checkParameterIsNotNull($this$isMoving, "$this$isMoving");
        return this.getSpeed($this$isMoving) > 1.0E-4;
    }

    public final double getSpeed(@NotNull Entity $this$speed) {
        Intrinsics.checkParameterIsNotNull($this$speed, "$this$speed");
        double d = $this$speed.motionX;
        double d2 = $this$speed.motionZ;
        boolean bl = false;
        return Math.hypot(d, d2);
    }

    public final double getRealSpeed(@NotNull Entity $this$realSpeed) {
        Intrinsics.checkParameterIsNotNull($this$realSpeed, "$this$realSpeed");
        double d = $this$realSpeed.posX - $this$realSpeed.prevPosX;
        double d2 = $this$realSpeed.posZ - $this$realSpeed.prevPosZ;
        boolean bl = false;
        return Math.hypot(d, d2);
    }

    public final double calcMoveYaw(float yawIn, float moveForward, float moveString) {
        float strafe = (float)90 * moveString;
        float yaw = yawIn - (strafe *= moveForward != 0.0f ? moveForward * 0.5f : 1.0f);
        return Math.toRadians(yaw -= (float)(moveForward < 0.0f ? 180 : 0));
    }

    public static /* synthetic */ double calcMoveYaw$default(MovementUtils movementUtils, float f, float f2, float f3, int n, Object object) {
        if ((n & 1) != 0) {
            f = MovementUtils.mc.player.rotationYaw;
        }
        if ((n & 2) != 0) {
            f2 = movementUtils.getRoundedForward();
        }
        if ((n & 4) != 0) {
            f3 = movementUtils.getRoundedStrafing();
        }
        return movementUtils.calcMoveYaw(f, f2, f3);
    }

    private final float getRoundedForward() {
        return this.getRoundedMovementInput(MovementUtils.mc.player.movementInput.moveForward);
    }

    private final float getRoundedStrafing() {
        return this.getRoundedMovementInput(MovementUtils.mc.player.movementInput.moveStrafe);
    }

    private final float getRoundedMovementInput(float input) {
        return input > 0.0f ? 1.0f : (input < 0.0f ? -1.0f : 0.0f);
    }

    public final void setSpeed(double speed) {
        double yaw = MovementUtils.calcMoveYaw$default(this, 0.0f, 0.0f, 0.0f, 7, null);
        EntityPlayerSP entityPlayerSP = MovementUtils.mc.player;
        boolean bl = false;
        double d = Math.sin(yaw);
        entityPlayerSP.motionX = -d * speed;
        entityPlayerSP = MovementUtils.mc.player;
        bl = false;
        d = Math.cos(yaw);
        entityPlayerSP.motionZ = d * speed;
    }

    public final double applySpeedPotionEffects(double speed) {
        double d;
        PotionEffect potionEffect = MovementUtils.mc.player.getActivePotionEffect(MobEffects.SPEED);
        if (potionEffect != null) {
            PotionEffect potionEffect2 = potionEffect;
            boolean bl = false;
            boolean bl2 = false;
            PotionEffect it = potionEffect2;
            boolean bl3 = false;
            PotionEffect potionEffect3 = it;
            Intrinsics.checkExpressionValueIsNotNull(potionEffect3, "it");
            d = speed * (1.0 + (double)(potionEffect3.getAmplifier() + 1) * 0.2);
        } else {
            d = speed;
        }
        return d;
    }

    public final boolean centerPlayer(@NotNull EntityPlayerSP $this$centerPlayer) {
        Intrinsics.checkParameterIsNotNull($this$centerPlayer, "$this$centerPlayer");
        Vec3d center = VectorUtils.INSTANCE.toVec3d((Vec3i)SurroundUtils.INSTANCE.getFlooredPosition((Entity)$this$centerPlayer), 0.5, 0.0, 0.5);
        boolean centered = this.isCentered((Entity)$this$centerPlayer, SurroundUtils.INSTANCE.getFlooredPosition((Entity)$this$centerPlayer));
        if (!centered) {
            $this$centerPlayer.motionX = (center.x - $this$centerPlayer.posX) / 2.0;
            $this$centerPlayer.motionZ = (center.z - $this$centerPlayer.posZ) / 2.0;
            double speed = this.getSpeed((Entity)$this$centerPlayer);
            if (speed > 0.2805) {
                double multiplier = 0.2805 / speed;
                $this$centerPlayer.motionX *= multiplier;
                $this$centerPlayer.motionZ *= multiplier;
            }
        }
        return centered;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean isCentered(@NotNull Entity $this$isCentered, @NotNull BlockPos pos) {
        Intrinsics.checkParameterIsNotNull($this$isCentered, "$this$isCentered");
        Intrinsics.checkParameterIsNotNull(pos, "pos");
        double d = (double)pos.x + 0.31;
        double d2 = (double)pos.x + 0.69;
        double d3 = $this$isCentered.posX;
        if (!(d3 >= d)) return false;
        if (!(d3 <= d2)) return false;
        d = (double)pos.z + 0.31;
        d2 = (double)pos.z + 0.69;
        d3 = $this$isCentered.posZ;
        if (!(d3 >= d)) return false;
        if (!(d3 <= d2)) return false;
        return true;
    }

    public final void resetMove(@NotNull MovementInput $this$resetMove) {
        Intrinsics.checkParameterIsNotNull($this$resetMove, "$this$resetMove");
        $this$resetMove.moveForward = 0.0f;
        $this$resetMove.moveStrafe = 0.0f;
        $this$resetMove.forwardKeyDown = false;
        $this$resetMove.backKeyDown = false;
        $this$resetMove.leftKeyDown = false;
        $this$resetMove.rightKeyDown = false;
    }

    public final void resetJumpSneak(@NotNull MovementInput $this$resetJumpSneak) {
        Intrinsics.checkParameterIsNotNull($this$resetJumpSneak, "$this$resetJumpSneak");
        $this$resetJumpSneak.jump = false;
        $this$resetJumpSneak.sneak = false;
    }

    private MovementUtils() {
    }

    static {
        MovementUtils movementUtils;
        INSTANCE = movementUtils = new MovementUtils();
        mc = Minecraft.getMinecraft();
    }
}

