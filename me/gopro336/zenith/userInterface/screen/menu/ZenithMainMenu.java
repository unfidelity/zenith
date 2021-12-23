//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.userInterface.screen.menu;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.userInterface.clickgui.ClickGUI;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0016\u00a8\u0006\b"}, d2={"Lme/gopro336/zenith/userInterface/screen/menu/ZenithMainMenu;", "Lnet/minecraft/client/gui/GuiMainMenu;", "()V", "actionPerformed", "", "button", "Lnet/minecraft/client/gui/GuiButton;", "initGui", "zenith"})
public final class ZenithMainMenu
extends GuiMainMenu {
    public void initGui() {
        super.initGui();
        int startHeight = this.height / 4 + 48;
        int buttonHeight = 24;
        this.buttonList.add(new GuiButton(30, this.width / 2 - 100, startHeight + buttonHeight * 5, "Zenith GUI"));
    }

    protected void actionPerformed(@NotNull GuiButton button) {
        Intrinsics.checkParameterIsNotNull(button, "button");
        if (button.id == 30) {
            ClickGUI gui = Zenith.clickGUI;
            this.mc.displayGuiScreen((GuiScreen)gui);
            return;
        }
        super.actionPerformed(button);
    }
}

