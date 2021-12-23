//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.event;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.IGlobals;
import me.gopro336.zenith.api.util.client.PlayerUtils;
import me.gopro336.zenith.event.client.LoadGuiEvent;
import me.gopro336.zenith.event.entity.PlayerUseItemEvent;
import me.gopro336.zenith.event.player.MoveInputEvent;
import me.gopro336.zenith.event.render.Render2DEvent;
import me.gopro336.zenith.event.world.TickEvent;
import me.gopro336.zenith.event.world.UpdateEvent;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.FeatureManager;
import me.gopro336.zenith.feature.hudElement.Element;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.lwjgl.opengl.GL11;

public class EventProcessor
implements IGlobals {
    public static boolean inGame = false;

    private void Catch(String Evnt, RuntimeException ex) {
        System.err.println(Evnt + ex);
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onForgeEvent(Event event) {
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onGui(GuiOpenEvent event) {
    }

    @SubscribeEvent
    public void on2dRender(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.HOTBAR) {
            return;
        }
        Zenith.INSTANCE.getEventManager().dispatchEvent(new Render2DEvent(event.getPartialTicks(), new ScaledResolution(Minecraft.getMinecraft())));
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        PlayerUtils.lastLookAt = null;
        UpdateEvent updateEvent = UpdateEvent.get(event.phase);
        Zenith.INSTANCE.getEventManager().dispatchEvent(updateEvent);
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onClientTick(TickEvent.ClientTickEvent event) {
        FeatureManager.keyListen();
        if (!inGame) {
            return;
        }
        Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        Zenith.clickGUI.onUpdate();
        FeatureManager.getToggledFeatures().forEach(Feature::onUpdate);
        FeatureManager.onUpdate();
    }

    @SubscribeEvent
    public void setLivingEntityUseItemEvent(LivingEntityUseItemEvent livingEntityUseItemEvent) {
        if (livingEntityUseItemEvent.getEntity() instanceof EntityPlayerSP) {
            Zenith.INSTANCE.getEventManager().dispatchEvent(new PlayerUseItemEvent());
        }
    }

    @SubscribeEvent
    public void onInput(InputUpdateEvent event) {
        MoveInputEvent moveInputEvent = new MoveInputEvent(event.getEntityPlayer(), event.getMovementInput());
        Zenith.INSTANCE.getEventManager().dispatchEvent(moveInputEvent);
    }

    @SubscribeEvent
    public void onLoadGui(GuiOpenEvent event) {
        LoadGuiEvent loadGuiEvent = new LoadGuiEvent(event.getGui());
        Zenith.INSTANCE.getEventManager().dispatchEvent(loadGuiEvent);
        event.setGui(loadGuiEvent.getGui());
        event.setCanceled(loadGuiEvent.isCanceled());
    }

    @SubscribeEvent
    public void setClientTickEvent(TickEvent.ClientTickEvent clientTickEvent) {
        TickEvent tickEvent = TickEvent.Method325(clientTickEvent.phase);
        Zenith.INSTANCE.getEventManager().dispatchEvent(tickEvent);
    }

    @SubscribeEvent
    public void onClientDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        FeatureManager.onClientDisconnect();
    }

    @SubscribeEvent
    public void onClientConnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        FeatureManager.onClientConnect();
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.isCanceled()) {
            return;
        }
        RenderGameOverlayEvent.ElementType target = RenderGameOverlayEvent.ElementType.EXPERIENCE;
        if (!EventProcessor.mc.player.isCreative() && EventProcessor.mc.player.getRidingEntity() instanceof AbstractHorse) {
            target = RenderGameOverlayEvent.ElementType.HEALTHMOUNT;
        }
        if (event.getType() == target) {
            FeatureManager.getHudElements().stream().filter(Feature::isEnabled).forEach(Element::onRender);
            GL11.glPushMatrix();
            GL11.glPopMatrix();
            EventProcessor.releaseGL();
        }
    }

    public static void releaseGL() {
        GlStateManager.enableCull();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
    }

    @SubscribeEvent
    public void onChatMessage(ClientChatEvent event) {
        if (event.getMessage().startsWith(Zenith.commandManager.getPrefix())) {
            String message = event.getMessage();
            event.setCanceled(true);
            Zenith.commandManager.executeCommand(message.substring(Zenith.commandManager.getPrefix().length()));
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        if (!inGame) {
            return;
        }
        try {
            Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        }
        catch (RuntimeException ex) {
            this.Catch("RenderWorldLastEvent", ex);
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (EventProcessor.mc.player == null || EventProcessor.mc.world == null) {
            return;
        }
        try {
            Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onWorldLoad(WorldEvent.Load event) {
        inGame = true;
        try {
            Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        }
        catch (RuntimeException ex) {
            this.Catch("onWorldLoad", ex);
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onWorldUnload(WorldEvent.Unload event) {
        try {
            inGame = false;
            Zenith.INSTANCE.getEventManager().dispatchEvent(event);
        }
        catch (RuntimeException ex) {
            this.Catch("onWorldUnload", ex);
        }
    }
}

