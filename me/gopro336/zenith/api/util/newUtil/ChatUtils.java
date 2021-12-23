//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.newUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import me.gopro336.zenith.api.util.Wrapper;
import me.gopro336.zenith.api.util.color.ColorTextUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class ChatUtils {
    public static void normalChat(String s) {
        ChatUtils.component((ITextComponent)new TextComponentTranslation(s, new Object[0]));
    }

    public static void component(ITextComponent textComponent) {
        if (Wrapper.getPlayer() != null && Wrapper.mc.ingameGUI.getChatGUI() != null) {
            Wrapper.mc.ingameGUI.getChatGUI().printChatMessage(new TextComponentTranslation("chat.type.admin", new Object[0]).appendSibling(textComponent));
        }
    }

    public static void normalMessage(String str) {
        ChatUtils.component((ITextComponent)new TextComponentTranslation("\u00a78[" + (Object)((Object)ColorTextUtils.colors.Lavender) + "Zenith\u00a78]\u00a77 " + str, new Object[0]));
    }

    public static void error(String str) {
        ChatUtils.message("\u00a78[\u00a74ERROR\u00a78]\u00a7c " + str);
    }

    public static void warning(String str) {
        ChatUtils.message("\u00a78[\u00a7eWARNING\u00a78]\u00a7e " + str);
    }

    public static void timeStampedChat(String str) {
        ChatUtils.component((ITextComponent)new TextComponentTranslation("\u00a7d<" + new SimpleDateFormat("HH:mm").format(new Date()) + ">\u00a7r" + str, new Object[0]));
    }

    public static void message(String str) {
        if (Minecraft.getMinecraft().player == null) {
            return;
        }
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)new TextComponentTranslation("\u00a78[" + (Object)((Object)ColorTextUtils.colors.Lavender) + "Zenith\u00a78]\u00a77 " + str, new Object[0]), 582956);
    }
}

