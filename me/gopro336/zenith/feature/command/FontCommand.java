/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.command;

import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.api.util.newUtil.ChatUtils;
import me.gopro336.zenith.feature.command.Command;

public class FontCommand
extends Command {
    public FontCommand() {
        super("Font", new String[]{"f", "cfont"}, "Change the custom font defaultinfo | currentfont | setfont", "font setfont [fontname]");
    }

    @Override
    public void call(String[] args2) {
        if (args2.length > 1) {
            if (args2[1].equalsIgnoreCase("defaultinfo")) {
                ChatUtils.message("Font: Verdana, Size: 18");
            }
            if (args2[1].equalsIgnoreCase("currentfont")) {
                ChatUtils.message("Font: " + FontUtil.customFontRenderer.getFont().getFontName() + ", Size: " + FontUtil.customFontRenderer.getFont().getSize());
            }
            if (args2[1].equalsIgnoreCase("fonts")) {
                ChatUtils.message("Fonts:");
                String out = "";
                boolean start = true;
                for (String s : FontUtil.getFonts()) {
                    out = start ? s : out + ", " + s;
                    start = false;
                }
                ChatUtils.message(out);
            }
            if (args2[1].equalsIgnoreCase("setfont")) {
                if (args2.length < 3) {
                    ChatUtils.message("Specify your font!");
                    return;
                }
                if (args2.length < 4) {
                    ChatUtils.message("Specify your font size!");
                    return;
                }
                if (Integer.parseInt(args2[3]) == 0) {
                    return;
                }
                FontUtil.setFontRenderer(args2[2], Integer.parseInt(args2[3]));
            }
        } else {
            ChatUtils.message("Do .customfont help for options");
        }
    }
}

