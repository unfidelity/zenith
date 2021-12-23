//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.misc;

import me.gopro336.zenith.event.client.LoadGuiEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.userInterface.screen.menu.ZenithGuiDisconnected;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.event.world.WorldEvent;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="AutoReconnect", category=Category.MISC)
public class AutoReconnect
extends Feature {
    private ServerData lastServer;
    public NumberProperty<Integer> delay = new NumberProperty<Integer>((Feature)this, "Delay", "", 1, Integer.valueOf(5), 30);

    @Listener
    public void onLoadGui(LoadGuiEvent event) {
        if (event.getGui() instanceof GuiDisconnected) {
            this.updateLastServer();
            event.setGui(new ZenithGuiDisconnected((GuiDisconnected)event.getGui(), this.lastServer, (Integer)this.delay.getValue()));
        }
    }

    @Listener
    public void onWorldUnload(WorldEvent.Unload event) {
        this.updateLastServer();
    }

    private void updateLastServer() {
        ServerData data = mc.getCurrentServerData();
        if (data != null) {
            this.lastServer = data;
        }
    }
}

