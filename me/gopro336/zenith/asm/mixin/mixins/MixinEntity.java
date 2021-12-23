//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.event.entity.AddEntityVelocityEvent;
import me.gopro336.zenith.event.entity.ShouldWalkOffEdgeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(value={Entity.class})
public abstract class MixinEntity {
    @Shadow
    public double motionY;
    @Shadow
    public double motionX;
    @Shadow
    public double motionZ;
    @Shadow
    protected boolean inPortal;

    @Shadow
    protected abstract void setFlag(int var1, boolean var2);

    @Shadow
    public void move(MoverType type, double x, double y, double z) {
    }

    public void setFlag0(int flag, boolean set) {
        this.setFlag(flag, set);
    }

    @Accessor(value="isInWeb")
    public abstract void setIsInWeb(boolean var1);

    @Accessor(value="inPortal")
    public abstract void setInPortal(boolean var1);

    @ModifyVariable(method={"addVelocity"}, at=@At(value="HEAD"), ordinal=0)
    private double modifyVariable1(double x) {
        AddEntityVelocityEvent event = new AddEntityVelocityEvent((Entity)this, x, x, x);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        return event.x;
    }

    @ModifyVariable(method={"addVelocity"}, at=@At(value="HEAD"), ordinal=1)
    private double modifyVariable2(double y) {
        AddEntityVelocityEvent event = new AddEntityVelocityEvent((Entity)this, y, y, y);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        return event.y;
    }

    @ModifyVariable(method={"addVelocity"}, at=@At(value="HEAD"), ordinal=2)
    private double modifyVariable3(double z) {
        AddEntityVelocityEvent event = new AddEntityVelocityEvent((Entity)this, z, z, z);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        return event.z;
    }

    @Redirect(method={"move"}, slice=@Slice(from=@At(value="FIELD", target="Lnet/minecraft/entity/Entity;onGround:Z", ordinal=0)), at=@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;isSneaking()Z", ordinal=0))
    private boolean isSneaking(Entity entity) {
        ShouldWalkOffEdgeEvent event = new ShouldWalkOffEdgeEvent();
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        return event.isCanceled() || entity.isSneaking();
    }
}

