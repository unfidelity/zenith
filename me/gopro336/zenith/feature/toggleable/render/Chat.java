//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.render;

import java.text.SimpleDateFormat;
import java.util.Date;
import me.gopro336.zenith.api.util.color.ColorTextUtils;
import me.gopro336.zenith.api.util.newUtil.ChatUtils;
import me.gopro336.zenith.event.network.PacketReceiveEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.command.Command;
import me.gopro336.zenith.property.Property;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentString;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="Chat", description="Tampers with chat", category=Category.RENDER)
public class Chat
extends Feature {
    public final Property<Boolean> customFont = new Property<Boolean>(this, "CustomFont", "", false);
    public final Property<Boolean> ncb = new Property<Boolean>(this, "NoBackground", "", false);
    public static Property<Boolean> nochatshadow;
    private final Property<Boolean> namehighlight = new Property<Boolean>(this, "NameHighlight", "", false);
    private final Property<highlightMode> namemode = new Property<highlightMode>((Feature)this, this.namehighlight, "HighlightMode", "", highlightMode.Highlight);
    private final Property<playerNameMode> playername = new Property<playerNameMode>(this, "PlayerTag", "", playerNameMode.ankleBr);
    private final Property<ColorTextUtils.colors> playerColor = new Property<ColorTextUtils.colors>(this, "PlayerColor", "", ColorTextUtils.colors.White);
    private final Property<Boolean> timestamps = new Property<Boolean>(this, "TimeStamps", "", false);
    private final Property<Boolean> mode = new Property<Boolean>(this, "24HTime", "", false);
    private final Property<bracketmodes> bracketmode = new Property<bracketmodes>(this, "BracketType", "", bracketmodes.ankleBr);
    private final Property<ColorTextUtils.colors> color = new Property<ColorTextUtils.colors>(this, "Color", "", ColorTextUtils.colors.LightGray);
    public static Chat INSTANCE;
    public static TextComponentString componentStringOld;

    public Chat() {
        nochatshadow = new Property<Boolean>(this, "No Chat Shadow", "", false);
        INSTANCE = this;
    }

    @Listener
    public void onPacket(PacketReceiveEvent event) {
        SPacketChat packet;
        if (event.getPacket() instanceof SPacketChat && (packet = (SPacketChat)event.getPacket()).getType() != ChatType.GAME_INFO && this.tryProcessChat(packet.getChatComponent().getFormattedText(), packet.getChatComponent().getUnformattedText())) {
            event.setCanceled(true);
        }
    }

    private boolean tryProcessChat(String message, String unformatted) {
        String out = message;
        String[] parts = out.split(" ");
        String[] partsUnformatted = unformatted.split(" ");
        parts[0] = partsUnformatted[0];
        if (parts[0].startsWith("<") && parts[0].endsWith(">")) {
            String temp;
            parts[0] = parts[0].replaceAll("<", "");
            parts[0] = parts[0].replaceAll(">", "");
            parts[0] = Command.SECTIONSIGN() + ColorTextUtils.getColor(this.playerColor.getValue()).substring(1) + parts[0] + Command.SECTIONSIGN() + "r";
            if (this.playername.getValue().equals((Object)playerNameMode.ankleBr)) {
                temp = "<" + parts[0] + ">";
                for (int i = 1; i < parts.length; ++i) {
                    temp = temp + " " + parts[i];
                }
                message = temp;
            } else if (this.playername.getValue().equals((Object)playerNameMode.Brackets)) {
                temp = "[" + parts[0] + "]:";
                for (int i = 1; i < parts.length; ++i) {
                    temp = temp + " " + parts[i];
                }
                message = temp;
            } else if (this.playername.getValue().equals((Object)playerNameMode.None)) {
                temp = parts[0] + ":";
                for (int i = 1; i < parts.length; ++i) {
                    temp = temp + " " + parts[i];
                }
                message = temp;
            } else if (this.playername.getValue().equals((Object)playerNameMode.Arrow)) {
                temp = parts[0] + " ->";
                for (int i = 1; i < parts.length; ++i) {
                    temp = temp + " " + parts[i];
                }
                message = temp;
            } else {
                temp = "<" + parts[0] + ">";
                for (int i = 1; i < parts.length; ++i) {
                    temp = temp + " " + parts[i];
                }
                message = temp;
            }
        }
        out = message;
        if (this.timestamps.getValue().booleanValue()) {
            String date = "";
            date = this.mode.getValue() != false ? new SimpleDateFormat("k:mm").format(new Date()) : new SimpleDateFormat("h:mm a").format(new Date());
            if (this.bracketmode.getValue().equals((Object)bracketmodes.ankleBr)) {
                out = "\u00a7" + ColorTextUtils.getColor(this.color.getValue()).substring(1) + "<" + date + ">\u00a7r " + message;
            } else if (this.bracketmode.getValue().equals((Object)bracketmodes.Parenth)) {
                out = "\u00a7" + ColorTextUtils.getColor(this.color.getValue()).substring(1) + "(" + date + ")\u00a7r " + message;
            } else if (this.bracketmode.getValue().equals((Object)bracketmodes.Brackets)) {
                out = "\u00a7" + ColorTextUtils.getColor(this.color.getValue()).substring(1) + "[" + date + "]\u00a7r " + message;
            } else if (this.bracketmode.getValue().equals((Object)bracketmodes.Brace)) {
                out = "\u00a7" + ColorTextUtils.getColor(this.color.getValue()).substring(1) + "{" + date + "}\u00a7r " + message;
            }
        }
        if (this.namehighlight.getValue().booleanValue()) {
            if (Chat.mc.player == null) {
                return false;
            }
            out = this.namemode.getValue().equals((Object)highlightMode.Hide) ? out.replace(Chat.mc.player.getName(), "HIDDEN") : out.replace(Chat.mc.player.getName(), "\u00a7b" + Chat.mc.player.getName() + "\u00a7r");
        }
        ChatUtils.message(out);
        return true;
    }

    public static enum highlightMode {
        Highlight,
        Hide;

    }

    public static enum bracketmodes {
        ankleBr,
        Parenth,
        Brackets,
        Brace;

    }

    public static enum playerNameMode {
        ankleBr,
        Brackets,
        None,
        Arrow;

    }
}

