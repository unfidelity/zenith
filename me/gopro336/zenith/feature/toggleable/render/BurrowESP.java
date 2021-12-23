//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="BurrowESP", description="Shows blocks that players are burrowed in", category=Category.RENDER)
public final class BurrowESP
extends Feature {
    public final Property<Boolean> renderOwn = new Property<Boolean>(this, "Render Own", "Renders your own burrow block", false);
    public final NumberProperty<Integer> range = new NumberProperty<Integer>((Feature)this, "Range", "The range to search for burrow blocks in", 1, Integer.valueOf(5), 10);
    public final Property<Boolean> renderBlock = new Property<Boolean>(this, "Render", "Allows the burrow blocks to be rendered", true);
    public final Property<RenderModes> renderMode = new Property<RenderModes>(this, "Render Mode", "The type of box to render", RenderModes.Full);
    public final NumberProperty<Double> outlineWidth = new NumberProperty<Double>((Feature)this, "Outline Width", "The width of the outline", 1.0, Double.valueOf(2.0), 5.0);
    public final Property<Color> renderColour = new Property<Color>(this, "Render Colour", "The colour for the burrow blocks", new Color(91, 79, 208, 220));
    private final List<BlockPos> burrowBlocksList = new ArrayList<BlockPos>();

    @Override
    public void onEnable() {
        if (this.nullCheck()) {
            return;
        }
        this.burrowBlocksList.clear();
    }

    @Override
    public void onDisable() {
        if (this.nullCheck()) {
            return;
        }
        this.burrowBlocksList.clear();
    }

    @Override
    public void onUpdate() {
        if (this.nullCheck()) {
            return;
        }
        this.burrowBlocksList.clear();
        for (EntityPlayer entityPlayer : BurrowESP.mc.world.playerEntities) {
            if (!(entityPlayer.getDistance((Entity)BurrowESP.mc.player) <= (float)((Integer)this.range.getValue()).intValue())) continue;
            if (entityPlayer == BurrowESP.mc.player && this.renderOwn.getValue().booleanValue()) {
                if (BurrowESP.mc.world.getBlockState(new BlockPos(BurrowESP.mc.player.posX, BurrowESP.mc.player.posY, BurrowESP.mc.player.posZ)).getBlock() != Blocks.OBSIDIAN) continue;
                this.burrowBlocksList.add(new BlockPos(BurrowESP.mc.player.posX, BurrowESP.mc.player.posY, BurrowESP.mc.player.posZ));
                continue;
            }
            if (entityPlayer == BurrowESP.mc.player || this.renderOwn.getValue().booleanValue() || BurrowESP.mc.world.getBlockState(new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ)).getBlock() != Blocks.OBSIDIAN) continue;
            this.burrowBlocksList.add(new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ));
        }
    }

    @Listener
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        if (this.nullCheck()) {
            return;
        }
        if (this.renderBlock.getValue().booleanValue()) {
            for (BlockPos burrowBlock : this.burrowBlocksList) {
                if (burrowBlock == null) continue;
                GL11.glLineWidth((float)((Double)this.outlineWidth.getValue()).floatValue());
            }
        }
    }

    public static enum RenderModes {
        Box,
        Outline,
        Full;

    }
}

