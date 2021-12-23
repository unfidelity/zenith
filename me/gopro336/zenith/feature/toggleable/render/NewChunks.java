//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.render;

import java.util.HashSet;
import java.util.Set;
import me.gopro336.zenith.api.util.newRender.RenderUtils3D;
import me.gopro336.zenith.event.EventStageable;
import me.gopro336.zenith.event.network.PacketReceiveEvent;
import me.gopro336.zenith.event.render.Render3DEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.ChunkPos;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="NewChunks", description="Marks chunks that are new", category=Category.RENDER)
public class NewChunks
extends Feature {
    private ICamera frustum = new Frustum();
    private Set<ChunkPos> chunks = new HashSet<ChunkPos>();

    @Listener
    public void onReceive(PacketReceiveEvent event) {
        if (event.getStage() != EventStageable.EventStage.PRE) {
            return;
        }
        if (event.getPacket() instanceof SPacketChunkData) {
            SPacketChunkData packet = (SPacketChunkData)event.getPacket();
            if (packet.isFullChunk()) {
                return;
            }
            ChunkPos newChunk = new ChunkPos(packet.getChunkX(), packet.getChunkZ());
            this.chunks.add(newChunk);
        }
    }

    @Listener
    public void onRender(Render3DEvent event) {
        if (mc.getRenderViewEntity() == null) {
            return;
        }
        this.frustum.setPosition(NewChunks.mc.getRenderViewEntity().posX, NewChunks.mc.getRenderViewEntity().posY, NewChunks.mc.getRenderViewEntity().posZ);
        GlStateManager.pushMatrix();
        RenderUtils3D.beginRender();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();
        GlStateManager.depthMask((boolean)false);
        GlStateManager.glLineWidth((float)2.0f);
        for (ChunkPos chunk : this.chunks) {
            AxisAlignedBB chunkBox = new AxisAlignedBB((double)chunk.getXStart(), 0.0, (double)chunk.getZStart(), (double)chunk.getXEnd(), 0.0, (double)chunk.getZEnd());
            GlStateManager.pushMatrix();
            if (this.frustum.isBoundingBoxInFrustum(chunkBox)) {
                double x = NewChunks.mc.player.lastTickPosX + (NewChunks.mc.player.posX - NewChunks.mc.player.lastTickPosX) * (double)event.getPartialTicks();
                double y = NewChunks.mc.player.lastTickPosY + (NewChunks.mc.player.posY - NewChunks.mc.player.lastTickPosY) * (double)event.getPartialTicks();
                double z = NewChunks.mc.player.lastTickPosZ + (NewChunks.mc.player.posZ - NewChunks.mc.player.lastTickPosZ) * (double)event.getPartialTicks();
                RenderUtils3D.drawBoundingBox(chunkBox.offset(-x, -y, -z), 214.0f, 86.0f, 147.0f, 100.0f);
            }
            GlStateManager.popMatrix();
        }
        GlStateManager.glLineWidth((float)1.0f);
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableAlpha();
        RenderUtils3D.endRender();
        GlStateManager.popMatrix();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.chunks.clear();
    }
}

