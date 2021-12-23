//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.hudElement.hudElement;

import java.awt.Color;
import java.text.DecimalFormat;
import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.command.Command;
import me.gopro336.zenith.feature.hudElement.Element;
import me.gopro336.zenith.property.Property;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumFacing;

@AnnotationHelper(name="Coordinates", description="Showes your current coordinates", category=Category.HUD)
public class CoordinatesElement
extends Element {
    private final Property<Boolean> freecam = new Property<Boolean>(this, "FreecamCoords", "", true);
    public Property<Color> textColor = new Property<Color>(this, "TextColor", "", new Color(255, 85, 255, 255));

    public CoordinatesElement() {
        ScaledResolution displayResolution = new ScaledResolution(Minecraft.getMinecraft());
        this.setX(4.0f);
        this.setY(displayResolution.getScaledHeight() - 4);
    }

    @Override
    public void onRender() {
        super.onRender();
        String facing = this.mc.player.getHorizontalFacing().getName().substring(0, 1).toUpperCase() + this.mc.player.getHorizontalFacing().getName().substring(1) + Command.SECTIONSIGN + "7 [" + Command.SECTIONSIGN + "r" + this.getAxis(this.mc.player.getHorizontalFacing()) + Command.SECTIONSIGN + "7]";
        DecimalFormat df = new DecimalFormat("#.#");
        double x = Double.parseDouble(df.format(this.freecam.getValue() != false ? this.mc.getRenderViewEntity().posX : this.mc.player.posX));
        double y = Double.parseDouble(df.format(this.freecam.getValue() != false ? this.mc.getRenderViewEntity().posY : this.mc.player.posY));
        double z = Double.parseDouble(df.format(this.freecam.getValue() != false ? this.mc.getRenderViewEntity().posZ : this.mc.player.posZ));
        double convertedX = Double.parseDouble(df.format(this.convertCoords(this.mc.player.posX)));
        double convertedZ = Double.parseDouble(df.format(this.convertCoords(this.mc.player.posZ)));
        String coords = Command.SECTIONSIGN + "7XYZ" + Command.SECTIONSIGN + "r " + x + ", " + y + ", " + z + Command.SECTIONSIGN + "7 [" + Command.SECTIONSIGN + "r" + convertedX + ", " + convertedZ + Command.SECTIONSIGN + "7]";
        float currentWidth = Math.max(FontUtil.getStringWidth(coords), FontUtil.getStringWidth(facing));
        this.setWidth((int)currentWidth + 1);
        this.setHeight(FontUtil.getStringHeight() + FontUtil.getStringHeight() + 1);
        FontUtil.drawStringWithShadow(facing, (int)this.getX(), (int)this.getY(), this.textColor.getValue().getRGB());
        FontUtil.drawStringWithShadow(coords, (int)this.getX(), (int)this.getY() + FontUtil.getStringHeight(), this.textColor.getValue().getRGB());
    }

    private double convertCoords(double coord) {
        boolean inHell = this.mc.world.getBiome(this.mc.player.getPosition()).getBiomeName().equals("Hell");
        return inHell ? coord * 8.0 : coord / 8.0;
    }

    private String getAxis(EnumFacing facing) {
        if (facing == EnumFacing.SOUTH) {
            return "+Z";
        }
        if (facing == EnumFacing.WEST) {
            return "-X";
        }
        if (facing == EnumFacing.NORTH) {
            return "-Z";
        }
        if (facing == EnumFacing.EAST) {
            return "+X";
        }
        return null;
    }
}

