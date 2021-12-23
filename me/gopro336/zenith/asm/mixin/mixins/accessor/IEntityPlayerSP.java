/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins.accessor;

import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={EntityPlayerSP.class})
public interface IEntityPlayerSP {
    @Accessor(value="horseJumpPower")
    public void setHorseJumpPower(float var1);

    @Accessor(value="serverSprintState")
    public boolean getServerSprintState();

    @Accessor(value="serverSneakState")
    public boolean getServerSneakState();

    @Accessor(value="positionUpdateTicks")
    public int getPositionUpdateTicks();

    @Accessor(value="lastReportedPosX")
    public double getLastReportedPosX();

    @Accessor(value="lastReportedPosY")
    public double getLastReportedPosY();

    @Accessor(value="lastReportedPosZ")
    public double getLastReportedPosZ();

    @Accessor(value="lastReportedYaw")
    public float getLastReportedYaw();

    @Accessor(value="lastReportedPitch")
    public float getLastReportedPitch();

    @Accessor(value="prevOnGround")
    public boolean getPreviousOnGround();

    @Accessor(value="serverSprintState")
    public void setServerSprintState(boolean var1);

    @Accessor(value="serverSneakState")
    public void setServerSneakState(boolean var1);

    @Accessor(value="positionUpdateTicks")
    public void setPositionUpdateTicks(int var1);

    @Accessor(value="lastReportedPosX")
    public void setLastReportedPosX(double var1);

    @Accessor(value="lastReportedPosY")
    public void setLastReportedPosY(double var1);

    @Accessor(value="lastReportedPosZ")
    public void setLastReportedPosZ(double var1);

    @Accessor(value="lastReportedYaw")
    public void setLastReportedYaw(float var1);

    @Accessor(value="lastReportedPitch")
    public void setLastReportedPitch(float var1);

    @Accessor(value="prevOnGround")
    public void setPreviousOnGround(boolean var1);

    @Accessor(value="prevOnGround")
    public void setPrevOnGround(boolean var1);

    @Accessor(value="prevOnGround")
    public boolean getPrevOnGround();

    @Accessor(value="autoJumpEnabled")
    public void setAutoJumpEnabled(boolean var1);

    @Accessor(value="autoJumpEnabled")
    public boolean getAutoJumpEnabled();
}

