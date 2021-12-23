//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.hudElement.hudElement;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Objects;
import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.hudElement.Element;
import me.gopro336.zenith.property.NumberProperty;

@AnnotationHelper(name="Ping", category=Category.HUD)
public class PingElement
extends Element {
    public NumberProperty<Integer> pingOffset = new NumberProperty<Integer>((Feature)this, "PingOffset", "", -50, Integer.valueOf(0), 50);

    @Override
    public void onRender() {
        String pingString = "Ping " + ChatFormatting.WHITE + (this.getPing() + (Integer)this.pingOffset.getValue()) + "ms";
        this.setWidth(FontUtil.getStringWidth(pingString));
        this.setHeight(FontUtil.getFontHeight());
        FontUtil.drawStringWithShadow(pingString, this.getX(), this.getY(), -1);
    }

    private int getPing() {
        if (this.mc.player != null && this.mc.getConnection() != null && this.mc.getConnection().getPlayerInfo(this.mc.player.getName()) != null) {
            return Objects.requireNonNull(this.mc.getConnection().getPlayerInfo(this.mc.player.getName())).getResponseTime();
        }
        return -1;
    }
}

