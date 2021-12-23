//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import javax.annotation.Nullable;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.MouseButton;
import me.gopro336.zenith.asm.mixin.imixin.IMinecraft;
import me.gopro336.zenith.event.client.ClickMouseButtonEvent;
import me.gopro336.zenith.event.render.DisplayGuiScreenEvent;
import me.gopro336.zenith.event.world.PreTickEvent;
import me.gopro336.zenith.feature.toggleable.misc.LazyItemSwitch;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Minecraft.class})
public abstract class MixinMinecraft
implements IMinecraft {
    @Shadow
    public PlayerControllerMP playerController;

    @Override
    @Accessor(value="rightClickDelayTimer")
    public abstract int getRightClickDelayTimer();

    @Override
    @Accessor(value="rightClickDelayTimer")
    public abstract void setRightClickDelayTimer(int var1);

    @Override
    @Accessor(value="timer")
    public abstract Timer getTimer();

    @Inject(method={"displayGuiScreen"}, at={@At(value="HEAD")}, cancellable=true)
    public void displayGuiScreen(@Nullable GuiScreen guiScreenIn, CallbackInfo ci) {
        DisplayGuiScreenEvent event = new DisplayGuiScreenEvent(guiScreenIn);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method={"rightClickMouse"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRightClickMouse(CallbackInfo ci) {
        ClickMouseButtonEvent event = new ClickMouseButtonEvent(MouseButton.RIGHT);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method={"clickMouse"}, at={@At(value="HEAD")}, cancellable=true)
    private void onLeftClickMouse(CallbackInfo ci) {
        ClickMouseButtonEvent event = new ClickMouseButtonEvent(MouseButton.LEFT);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method={"middleClickMouse"}, at={@At(value="HEAD")}, cancellable=true)
    private void onMiddleClickMouse(CallbackInfo ci) {
        ClickMouseButtonEvent event = new ClickMouseButtonEvent(MouseButton.MIDDLE);
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method={"runGameLoop"}, at={@At(value="HEAD")})
    private void Method1747(CallbackInfo callbackInfo) {
        PreTickEvent event = new PreTickEvent();
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
    }

    @Redirect(method={"runTick"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/multiplayer/PlayerControllerMP;updateController()V"))
    private void updateControllerRedir(PlayerControllerMP playerControllerMP) {
        LazyItemSwitch.INSTANCE.updatePlayerControllerOnTick(playerControllerMP);
    }
}

