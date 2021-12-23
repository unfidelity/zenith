//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.mixins;

import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.feature.toggleable.render.Chat;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={GuiNewChat.class})
public abstract class MixinGuiNewChat {
    @Redirect(method={"drawChat"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/GuiNewChat;drawRect(IIIII)V"))
    private void drawRectBackgroundClean(int var1, int var2, int var3, int var4, int var5) {
        if (!Chat.INSTANCE.isEnabled() || !Chat.INSTANCE.ncb.getValue().booleanValue()) {
            Gui.drawRect((int)var1, (int)var2, (int)var3, (int)var4, (int)var5);
        }
    }

    @Redirect(method={"drawChat"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;FFI)I"))
    private int drawStringWithShadowMaybe(FontRenderer fontRenderer, String message, float x, float y, int color) {
        if (!Chat.INSTANCE.isEnabled()) {
            return fontRenderer.drawStringWithShadow(message, x, y, color);
        }
        if (Chat.INSTANCE.customFont.getValue().booleanValue()) {
            if (Chat.nochatshadow.getValue().booleanValue()) {
                return FontUtil.drawStringInt(message, x, y, color);
            }
            return FontUtil.drawStringWithShadowInt(message, x, y, color);
        }
        if (Chat.nochatshadow.getValue().booleanValue()) {
            return fontRenderer.drawString(message, (int)x, (int)y, color);
        }
        return fontRenderer.drawStringWithShadow(message, x, y, color);
    }
}

