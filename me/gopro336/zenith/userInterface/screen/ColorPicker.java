//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.userInterface.screen;

import java.awt.Color;
import java.io.IOException;
import me.gopro336.zenith.api.util.color.RenderUtil;
import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.api.util.newRender.RenderUtils2D;
import me.gopro336.zenith.feature.toggleable.Client.ClickGuiFeature;
import me.gopro336.zenith.property.Property;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class ColorPicker
extends GuiScreen {
    int height = 201;
    int width = 153;
    int barH = 15;
    public int X = 0;
    public int Y = 0;
    public int dragX = 0;
    public int dragY = 0;
    public boolean open = false;
    private boolean isDragging = false;
    private boolean renderGui = true;
    private final GuiScreen prev;
    private int deltaX;
    private int deltaY;
    public static boolean ldown = false;
    public static boolean lheld = false;
    public static boolean rdown = false;
    public static int mX;
    public static int mY;
    Property<Color> colorProp;
    public static Color finalColor;

    public ColorPicker(Property<Color> color, GuiScreen click) {
        this.colorProp = color;
        this.prev = click;
        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        this.X = (int)((float)(res.getScaledWidth() / 2) - (float)this.width / 2.0f);
        this.Y = (int)((float)(res.getScaledHeight() / 2) - (float)this.height / 2.0f);
        ldown = false;
        lheld = false;
        rdown = false;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (this.isDragging) {
            this.X = mouseX - this.deltaX;
            this.Y = mouseY - this.deltaY;
        }
        if (this.renderGui) {
            this.prev.drawScreen(-1, -1, partialTicks);
        }
        RenderUtils2D.drawRect(this.X, (double)(this.Y + this.barH), this.X + this.width, (double)(this.Y + this.height), new Color(30, 30, 30, 160).getRGB());
        RenderUtils2D.drawRect(this.X, (double)this.Y, this.X + this.width, (double)(this.Y + this.barH), ClickGuiFeature.color.getValue().getRGB());
        if (ClickGuiFeature.thin.getValue().booleanValue()) {
            RenderUtil.drawRectOutline(this.X, this.Y, this.X + this.width, this.Y + this.height, 0.5, new Color(Math.min(ClickGuiFeature.color.getValue().getRed() + 10, 255), Math.min(ClickGuiFeature.color.getValue().getGreen() + 10, 255), Math.min(ClickGuiFeature.color.getValue().getBlue() + 10, 255), Math.min(ClickGuiFeature.color.getValue().getAlpha() + 10, 255)).getRGB());
        } else {
            RenderUtil.drawRectOutline(this.X, this.Y, this.X + this.width, this.Y + this.height, 1.0, new Color(Math.min(ClickGuiFeature.color.getValue().getRed() + 10, 255), Math.min(ClickGuiFeature.color.getValue().getGreen() + 10, 255), Math.min(ClickGuiFeature.color.getValue().getBlue() + 10, 255), Math.min(ClickGuiFeature.color.getValue().getAlpha() + 10, 255)).getRGB());
        }
        if (this.renderGui) {
            RenderUtil.drawRectOutline(this.X + this.width - 60, this.Y + 1, this.X + this.width - 10, this.Y + 14, 0.5, -1);
            FontUtil.drawCenteredString("Hide Gui", this.X + this.width - 35, this.Y + 2, -1);
        } else {
            RenderUtil.drawRectOutline(this.X + this.width - 60, this.Y + 3, this.X + this.width - 10, this.Y + 17, 0.5, -1);
            FontUtil.drawCenteredString("Show Gui", this.X + this.width - 35, this.Y + 5, -1);
        }
        FontUtil.drawCenteredString(this.colorProp.getName(), this.X + this.width / 2, this.Y + 5, -1);
        ColorPicker.drawColourPicker(this.colorProp, this.X + 3, this.Y + this.barH + 3, mouseX, mouseY);
        ColorPicker.mouseListen(mouseX, mouseY);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHover(this.X + this.width - 60, this.Y + 3, 50, 17, mouseX, mouseY) && mouseButton == 0) {
            boolean bl = this.renderGui = !this.renderGui;
        }
        if (this.isHover(this.X, this.Y, this.width, this.height, mouseX, mouseY)) {
            if (mouseY < this.Y + this.barH) {
                this.deltaX = mouseX - this.X;
                this.deltaY = mouseY - this.Y;
                this.isDragging = true;
            }
        } else if (!this.isDragging) {
            this.mc.displayGuiScreen(this.prev);
        }
        if (mouseButton == 0) {
            ColorPicker.lclickListen();
        }
        if (mouseButton == 1) {
            ColorPicker.rclickListen();
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        if (state == 0) {
            ColorPicker.releaseListen();
        }
        this.isDragging = false;
    }

    public int[] getInitialLocation() {
        int[] cords = new int[]{0, 0};
        cords[0] = new ScaledResolution(this.mc).getScaledWidth() / 2 - this.width / 2;
        cords[1] = new ScaledResolution(this.mc).getScaledHeight() / 2 - this.height / 2;
        return cords;
    }

    private boolean isHover(int X, int Y, int W, int H, int mX, int mY) {
        return mX >= X && mX <= X + W && mY >= Y && mY <= Y + H;
    }

    public void setDragging(boolean drag) {
        this.isDragging = drag;
    }

    public void close() {
        this.open = false;
    }

    public void open() {
        int[] cords = this.getInitialLocation();
        this.X = cords[0];
        this.Y = cords[1];
        this.open = true;
    }

    public boolean isOpen() {
        return this.open;
    }

    public static void drawPicker(Property<Color> colourProperty, int mouseX, int mouseY, int pickerX, int pickerY, int hueSliderX, int hueSliderY, int alphaSliderX, int alphaSliderY) {
        float restrictedX;
        float[] color = new float[]{Color.RGBtoHSB(colourProperty.getValue().getRed(), colourProperty.getValue().getGreen(), colourProperty.getValue().getBlue(), null)[0], Color.RGBtoHSB(colourProperty.getValue().getRed(), colourProperty.getValue().getGreen(), colourProperty.getValue().getBlue(), null)[1], Color.RGBtoHSB(colourProperty.getValue().getRed(), colourProperty.getValue().getGreen(), colourProperty.getValue().getBlue(), null)[2]};
        boolean pickingColour = false;
        boolean pickingHue = false;
        boolean pickingAlpha = false;
        int pickerWidth = 150;
        int pickerHeight = 150;
        int hueSliderWidth = 150;
        int hueSliderHeight = 10;
        int alphaSliderWidth = 150;
        int alphaSliderHeight = 10;
        if (lheld && ColorPicker.mouseOver(pickerX, pickerY, pickerX + pickerWidth, pickerY + pickerHeight)) {
            pickingColour = true;
        }
        if (lheld && ColorPicker.mouseOver(hueSliderX, hueSliderY, hueSliderX + hueSliderWidth, hueSliderY + hueSliderHeight)) {
            pickingHue = true;
        }
        if (lheld && ColorPicker.mouseOver(alphaSliderX, alphaSliderY, alphaSliderX + alphaSliderWidth, alphaSliderY + alphaSliderHeight)) {
            pickingAlpha = true;
        }
        if (pickingHue) {
            restrictedX = Math.min(Math.max(hueSliderX, mouseX), hueSliderX + hueSliderWidth);
            color[0] = (restrictedX - (float)hueSliderX) / (float)hueSliderWidth;
        }
        if (pickingAlpha) {
            restrictedX = Math.min(Math.max(alphaSliderX, mouseX), alphaSliderX + alphaSliderWidth);
            colourProperty.setAlpha(1.0f - (restrictedX - (float)alphaSliderX) / (float)alphaSliderWidth);
        }
        if (pickingColour) {
            restrictedX = Math.min(Math.max(pickerX, mouseX), pickerX + pickerWidth);
            float restrictedY = Math.min(Math.max(pickerY, mouseY), pickerY + pickerHeight);
            color[1] = (restrictedX - (float)pickerX) / (float)pickerWidth;
            color[2] = 1.0f - (restrictedY - (float)pickerY) / (float)pickerHeight;
        }
        int selectedColor = Color.HSBtoRGB(color[0], 1.0f, 1.0f);
        float selectedRed = (float)(selectedColor >> 16 & 0xFF) / 255.0f;
        float selectedGreen = (float)(selectedColor >> 8 & 0xFF) / 255.0f;
        float selectedBlue = (float)(selectedColor & 0xFF) / 255.0f;
        RenderUtils2D.drawPickerBase(pickerX, pickerY, pickerWidth, pickerHeight, selectedRed, selectedGreen, selectedBlue, colourProperty.getAlpha());
        ColorPicker.drawHueSlider(hueSliderX, hueSliderY, hueSliderWidth, hueSliderHeight, color[0]);
        int cursorX = (int)((float)pickerX + color[1] * (float)pickerWidth);
        int cursorY = (int)((float)(pickerY + pickerHeight) - color[2] * (float)pickerHeight);
        Gui.drawRect((int)(cursorX - 2), (int)(cursorY - 2), (int)(cursorX + 2), (int)(cursorY + 2), (int)-1);
        ColorPicker.drawAlphaSlider(alphaSliderX, alphaSliderY, alphaSliderWidth, alphaSliderHeight, selectedRed, selectedGreen, selectedBlue, colourProperty.getAlpha());
        finalColor = ColorPicker.integrateAlpha(new Color(Color.HSBtoRGB(color[0], color[1], color[2])), colourProperty.getAlpha());
    }

    public static Color getColorFromHex(int hexColor) {
        int r = (hexColor & 0xFF0000) >> 16;
        int g = (hexColor & 0xFF00) >> 8;
        int b = hexColor & 0xFF;
        return new Color(r, g, b);
    }

    public static Color integrateAlpha(Color color, float alpha) {
        float red = (float)color.getRed() / 255.0f;
        float green = (float)color.getGreen() / 255.0f;
        float blue = (float)color.getBlue() / 255.0f;
        return new Color(red, green, blue, alpha);
    }

    public static void drawHueSlider(int x, int y, int width, int height, float hue) {
        int step = 0;
        if (height > width) {
            Gui.drawRect((int)x, (int)y, (int)(x + width), (int)(y + 4), (int)-65536);
            y += 4;
            for (int colorIndex = 0; colorIndex < 5; ++colorIndex) {
                int previousStep = Color.HSBtoRGB((float)step / 5.0f, 1.0f, 1.0f);
                int nextStep = Color.HSBtoRGB((float)(step + 1) / 5.0f, 1.0f, 1.0f);
                RenderUtils2D.drawGradientRect(x, y + step * (height / 5), x + width, y + (step + 1) * (height / 5), previousStep, nextStep);
                ++step;
            }
            int sliderMinY = (int)((float)y + (float)height * hue) - 4;
            Gui.drawRect((int)x, (int)(sliderMinY - 1), (int)(x + width), (int)(sliderMinY + 1), (int)-1);
        } else {
            for (int colorIndex = 0; colorIndex < 5; ++colorIndex) {
                int previousStep = Color.HSBtoRGB((float)step / 5.0f, 1.0f, 1.0f);
                int nextStep = Color.HSBtoRGB((float)(step + 1) / 5.0f, 1.0f, 1.0f);
                RenderUtils2D.gradient(x + step * (width / 5), y, x + (step + 1) * (width / 5), y + height, previousStep, nextStep, true);
                ++step;
            }
            int sliderMinX = (int)((float)x + (float)width * hue);
            Gui.drawRect((int)(sliderMinX - 1), (int)y, (int)(sliderMinX + 1), (int)(y + height), (int)-1);
        }
    }

    public static void drawAlphaSlider(int x, int y, int width, int height, float red, float green, float blue, float alpha) {
        boolean left = true;
        int checkerBoardSquareSize = height / 2;
        for (int squareIndex = -checkerBoardSquareSize; squareIndex < width; squareIndex += checkerBoardSquareSize) {
            if (!left) {
                Gui.drawRect((int)(x + squareIndex), (int)y, (int)(x + squareIndex + checkerBoardSquareSize), (int)(y + height), (int)-1);
                Gui.drawRect((int)(x + squareIndex), (int)(y + checkerBoardSquareSize), (int)(x + squareIndex + checkerBoardSquareSize), (int)(y + height), (int)-7303024);
                if (squareIndex < width - checkerBoardSquareSize) {
                    int minX = x + squareIndex + checkerBoardSquareSize;
                    int maxX = Math.min(x + width, x + squareIndex + checkerBoardSquareSize * 2);
                    Gui.drawRect((int)minX, (int)y, (int)maxX, (int)(y + height), (int)-7303024);
                    Gui.drawRect((int)minX, (int)(y + checkerBoardSquareSize), (int)maxX, (int)(y + height), (int)-1);
                }
            }
            left = !left;
        }
        RenderUtils2D.drawLeftGradientRect(x, y, x + width, y + height, new Color(red, green, blue, 1.0f).getRGB(), 0);
        int sliderMinX = (int)((float)(x + width) - (float)width * alpha);
        Gui.drawRect((int)(sliderMinX - 1), (int)y, (int)(sliderMinX + 1), (int)(y + height), (int)-1);
    }

    public static void drawColourPicker(Property<Color> Property2, int x, int y, int mouseX, int mouseY) {
        ColorPicker.drawPicker(Property2, mouseX, mouseY, x, y, x, y + 153, x, y + 166);
        Property2.setValue(finalColor);
    }

    public static void lclickListen() {
        ldown = true;
        lheld = true;
    }

    public static void rclickListen() {
        rdown = true;
    }

    public static void releaseListen() {
        lheld = false;
    }

    public static void mouseListen(int mouseX, int mouseY) {
        mX = mouseX;
        mY = mouseY;
        ldown = false;
        rdown = false;
    }

    public static boolean mouseOver(int minX, int minY, int maxX, int maxY) {
        return mX >= minX && mY >= minY && mX <= maxX && mY <= maxY;
    }
}

