//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.rina.turok.render.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

public class TurokImage {
    private String path;
    private BufferedImage bufferedImage;
    private ResourceLocation resourceLocation;
    private DynamicTexture dynamicTexture;

    public TurokImage(String path) {
        this.path = path;
        try {
            this.bufferedImage = ImageIO.read(TurokImage.class.getResourceAsStream(this.path));
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
        this.dynamicTexture = new DynamicTexture(this.bufferedImage);
        this.resourceLocation = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation("", this.dynamicTexture);
    }

    public int getWidth() {
        return this.bufferedImage.getWidth();
    }

    public int getHeight() {
        return this.bufferedImage.getHeight();
    }

    public String getPath() {
        return this.path;
    }

    public BufferedImage getBufferedImage() {
        return this.bufferedImage;
    }

    public ResourceLocation getResourceLocation() {
        return this.resourceLocation;
    }

    public DynamicTexture getDynamicTexture() {
        return this.dynamicTexture;
    }
}

