//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.rina.turok.minecraft;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import me.rina.turok.hardware.mouse.TurokMouse;
import me.rina.turok.render.opengl.TurokGL;
import me.rina.turok.render.opengl.TurokShaderGL;
import me.rina.turok.util.TurokClass;
import me.rina.turok.util.TurokDisplay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

@GUI(name="HDU editor")
public class TurokGUI
extends GuiScreen {
    private final String name = this.get().name();
    private final String author = this.get().author();
    protected TurokMouse mouse = new TurokMouse();
    protected TurokDisplay display = new TurokDisplay(Minecraft.getMinecraft());
    protected float partialTicks;

    public TurokGUI() {
        TurokShaderGL.init(this.display, this.mouse);
        this.init();
    }

    public GUI get() {
        if (TurokClass.isAnnotationPreset(((Object)((Object)this)).getClass(), GUI.class)) {
            return ((Object)((Object)this)).getClass().getAnnotation(GUI.class);
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setMouse(TurokMouse mouse) {
        this.mouse = mouse;
    }

    public TurokMouse getMouse() {
        return this.mouse;
    }

    public void setDisplay(TurokDisplay display) {
        this.display = display;
    }

    public TurokDisplay getDisplay() {
        return this.display;
    }

    public void setPartialTicks(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }

    public void init() {
    }

    public void closeGUI() {
        this.onClose();
        this.mc.setIngameFocus();
        this.mc.displayGuiScreen(null);
    }

    public boolean pauseGameWhenActive() {
        return false;
    }

    public void onOpen() {
    }

    public void onClose() {
    }

    public void onKeyboard(char character, int key) {
    }

    public void onMouseClicked(int button) {
    }

    public void onMouseReleased(int button) {
    }

    public void onRender() {
    }

    public boolean doesGuiPauseGame() {
        return this.pauseGameWhenActive();
    }

    public void initGui() {
        this.onOpen();
    }

    public void keyTyped(char charCode, int keyCode) {
        this.onKeyboard(charCode, keyCode);
    }

    public void mouseClicked(int mousePositionX, int mousePositionY, int mouseButtonUp) {
        this.onMouseClicked(mouseButtonUp);
    }

    public void mouseReleased(int mousePositionX, int mousePositionY, int mouseButtonDown) {
        this.onMouseReleased(mouseButtonDown);
    }

    public void drawScreen(int mousePositionX, int mousePositionY, float partialTicks) {
        this.display = new TurokDisplay(Minecraft.getMinecraft());
        TurokShaderGL.init(this.display, this.mouse);
        this.mouse.setPos(mousePositionX, mousePositionY);
        this.partialTicks = partialTicks;
        TurokGL.pushMatrix();
        TurokGL.translate(this.display.getWidth(), this.display.getHeight());
        TurokGL.scale(0.5f, 0.5f, 0.5f);
        TurokGL.popMatrix();
        TurokGL.disable(3553);
        this.onRender();
        TurokGL.enable(3553);
        TurokGL.disable(3553);
        TurokGL.disable(3042);
        TurokGL.enable(3553);
        TurokGL.color(255.0f, 255.0f, 255.0f);
    }

    @Retention(value=RetentionPolicy.RUNTIME)
    public static @interface GUI {
        public String name() default "Random";

        public String author() default "Random";
    }
}

