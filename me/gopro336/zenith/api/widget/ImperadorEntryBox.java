//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.widget;

import java.awt.Color;
import me.gopro336.zenith.api.util.font.FontUtil;
import me.gopro336.zenith.api.widget.api.Widget;
import me.gopro336.zenith.api.widget.util.ClipboardUtil;
import me.rina.turok.hardware.mouse.TurokMouse;
import me.rina.turok.render.font.TurokFont;
import me.rina.turok.render.opengl.TurokGL;
import me.rina.turok.render.opengl.TurokRenderGL;
import me.rina.turok.render.opengl.TurokShaderGL;
import me.rina.turok.util.TurokMath;
import me.rina.turok.util.TurokRect;
import me.rina.turok.util.TurokTick;
import net.minecraft.util.ChatAllowedCharacters;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class ImperadorEntryBox
extends Widget {
    private String text;
    private String save;
    private int indexA;
    private int indexB;
    public int[] string = new int[]{255, 255, 255, 255};
    public int[] pressed = new int[]{255, 255, 255, 150};
    public int[] focused = new int[]{255, 255, 255, 255};
    public int[] outline = new int[]{255, 255, 255, 200};
    public int[] background = new int[]{0, 0, 0, 0};
    public int[] background_selected = new int[]{0, 0, 255, 100};
    public float pressedAlpha;
    public float lastTickPressedAlpha;
    public float focusedAlpha;
    public float lastTickFocusedAlpha;
    public float outlineAlpha;
    public float lastTickOutlineAlpha;
    private float kerning = 0.0f;
    private float partialTicks = 1.0f;
    private boolean isShadow;
    private boolean isRendering;
    private boolean isToDrawOutline;
    private boolean isFocused;
    private boolean isSplitRendering;
    private boolean isMouseOver;
    private boolean isPressed;
    private boolean isReleased;
    private boolean isDragging;
    private float offsetX;
    private float offsetY;
    private float offsetW;
    private float offsetH;
    private float scroll;
    private TurokFont font;
    protected int lastIndexCurrent;
    protected float lastSize;
    private final TurokTick splitTick = new TurokTick();
    private final TurokTick pressTick = new TurokTick();
    private final TurokRect scissor = new TurokRect(0.0f, 0.0f);

    public ImperadorEntryBox(String text) {
        super("Imperador:Entry:Box");
        this.text = text;
        this.setIsShadow(true);
    }

    public TurokRect getScissor() {
        return this.scissor;
    }

    public void setKerning(float kerning) {
        this.kerning = kerning;
    }

    public float getKerning() {
        return this.kerning;
    }

    public void setPartialTicks(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }

    public void setFont(TurokFont font) {
        this.font = font;
    }

    public TurokFont getFont() {
        return this.font;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public boolean isEmpty() {
        if (this.text.isEmpty()) {
            return true;
        }
        boolean empty = true;
        for (String c : this.text.split("")) {
            if (c.equals(" ")) continue;
            empty = false;
            break;
        }
        return empty;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public String getSave() {
        return this.save;
    }

    public void setMouseOver(boolean isMouseOver) {
        this.isMouseOver = isMouseOver;
    }

    public boolean isMouseOver() {
        return this.isMouseOver;
    }

    public void setIsShadow(boolean isShadow) {
        this.isShadow = isShadow;
    }

    public boolean getIsShadow() {
        return this.isShadow;
    }

    public void setSplitRendering(boolean splitRendering) {
        this.isSplitRendering = splitRendering;
    }

    public boolean isSplitRendering() {
        return this.isSplitRendering;
    }

    public void setRendering(boolean rendering) {
        this.isRendering = rendering;
    }

    public boolean isRendering() {
        return this.isRendering;
    }

    public void setPressed(boolean pressed) {
        this.isPressed = pressed;
    }

    public boolean isPressed() {
        return this.isPressed;
    }

    public void setReleased(boolean released) {
        this.isReleased = released;
    }

    public boolean isReleased() {
        return this.isReleased;
    }

    public void setToDrawOutline(boolean toDrawOutline) {
        this.isToDrawOutline = toDrawOutline;
    }

    public boolean isToDrawOutline() {
        return this.isToDrawOutline;
    }

    public void setFocused(boolean focused) {
        this.isFocused = focused;
    }

    public boolean isFocused() {
        return this.isFocused;
    }

    public void setDragging(boolean dragging) {
        this.isDragging = dragging;
    }

    public boolean isDragging() {
        return this.isDragging;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public float getOffsetX() {
        return this.offsetX;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public float getOffsetY() {
        return this.offsetY;
    }

    public void setOffsetW(float offsetW) {
        this.offsetW = offsetW;
    }

    public float getOffsetW() {
        return this.offsetW;
    }

    public void setOffsetH(float offsetH) {
        this.offsetH = offsetH;
    }

    public float getOffsetH() {
        return this.offsetH;
    }

    public void setIndexA(int indexA) {
        this.indexA = indexA;
    }

    public int getIndexA() {
        return this.indexA;
    }

    public void setIndexB(int indexB) {
        this.indexB = indexB;
    }

    public int getIndexB() {
        return this.indexB;
    }

    public float getLastSize() {
        return this.lastSize;
    }

    public void scissor() {
        this.scissor.copy(this.rect);
    }

    public void center() {
        this.offsetX = this.rect.getWidth() / 2.0f - (float)FontUtil.getStringWidth(this.text) / 2.0f;
    }

    public void left(float difference) {
        this.offsetX = difference;
    }

    public void right(float difference) {
        this.offsetX = this.rect.getWidth() - (float)FontUtil.getStringWidth(this.text) - difference;
    }

    public int getIndexByPosition(float x) {
        int index = this.text.isEmpty() ? 0 : this.text.length();
        int i = 0;
        float size = 0.0f;
        for (String c : this.text.split("")) {
            float w = FontUtil.getStringWidth(c);
            if (this.isCollidingWithMouse((int)x, 0, size, w + this.getKerning())) {
                index = i;
                break;
            }
            size += w + this.getKerning();
            ++i;
        }
        return TurokMath.clamp(index, 0, this.text.length());
    }

    public float getSizeByIndex(int index) {
        if (index == -1) {
            return 0.0f;
        }
        int i = -1;
        float size = 0.0f;
        for (String c : this.text.split("")) {
            float w = FontUtil.getStringWidth(c);
            if (i == index) break;
            size += w + this.getKerning();
            ++i;
        }
        return size;
    }

    public void doMouseOver(TurokMouse mouse) {
        this.isMouseOver = this.rect.collideWithMouse(mouse) && this.isRendering();
    }

    public boolean isCollidingWithMouse(int mouseX, int mouseY, float s, float size) {
        return (float)mouseX >= this.rect.getX() + this.offsetX + s && (float)mouseX <= this.rect.getX() + this.offsetX + s + size;
    }

    public void doSetIndexAB(TurokMouse mouse) {
        if (!this.isMouseOver()) {
            return;
        }
        this.offsetW = TurokMath.clamp((float)mouse.getX(), this.rect.getX() + this.offsetX, this.rect.getX() + this.rect.getWidth());
        this.offsetH = this.getIndexByPosition(this.offsetW);
        this.splitTick.reset();
        int index = 0;
        if (this.text.isEmpty()) {
            this.setIndexA(index);
            this.setIndexB(index);
            return;
        }
        index = this.getIndexByPosition(mouse.getX());
        if (index == -1) {
            index = this.text.length();
        }
        this.setIndexA(index);
        this.setIndexB(index);
    }

    public void doMouseScroll(TurokMouse mouse) {
        float speed;
        float m = this.rect.getWidth() - 2.0f - TurokMath.min(this.lastSize, this.rect.getWidth());
        float x = TurokMath.clamp((float)mouse.getX(), this.rect.getX() + this.offsetX, this.rect.getX() + this.rect.getWidth());
        float size = 2.0f + this.getSizeByIndex(this.indexA);
        if (2.0f + this.lastSize < this.rect.getWidth()) {
            this.scroll = 2.0f;
        }
        if ((this.rect.getX() + this.offsetX + size <= this.rect.getX() + 2.0f || this.rect.getX() + this.offsetX + size >= this.rect.getX() + this.rect.getWidth()) && !this.isDragging()) {
            float value;
            this.scroll = value + ((value = this.rect.getWidth() - TurokMath.min(size, this.rect.getWidth())) >= 0.0f ? 2.0f : 0.0f);
        }
        if (this.scroll >= 2.0f) {
            this.scroll = 2.0f;
        } else if (this.scroll <= m) {
            this.scroll = m;
        }
        this.offsetX = TurokMath.lerp(this.offsetX, this.scroll, this.getPartialTicks());
        if (!this.isDragging()) {
            this.lastIndexCurrent = -1;
            this.offsetW = x;
            this.offsetH = -1.0f;
            return;
        }
        if (this.offsetH == -1.0f) {
            return;
        }
        this.splitTick.reset();
        int indexCurrent = this.getIndexByPosition(x);
        if (indexCurrent != -1) {
            this.lastIndexCurrent = indexCurrent;
        }
        if (this.pressTick.isPassedMS(250.0f)) {
            if ((float)this.lastIndexCurrent > this.offsetH) {
                this.indexA = (int)this.offsetH;
                this.indexB = this.lastIndexCurrent;
            } else {
                this.indexA = this.lastIndexCurrent;
                this.indexB = (int)this.offsetH;
            }
        }
        if ((float)mouse.getX() >= this.rect.getX() + this.rect.getWidth() - 1.0f && this.lastSize + 2.0f >= this.rect.getWidth()) {
            speed = this.rect.getX() + this.rect.getWidth() - 1.0f - (float)mouse.getX();
            this.scroll -= TurokMath.sqrt(speed * speed);
        }
        if ((float)mouse.getX() <= this.rect.getX() + 1.0f) {
            speed = this.rect.getX() - (float)mouse.getX();
            this.scroll += TurokMath.sqrt(speed * speed);
        }
    }

    public float size() {
        if (this.text.isEmpty()) {
            return -1.0f;
        }
        float s = 0.0f;
        for (int i = 0; i < this.text.length(); ++i) {
            String c = CharUtils.toString((char)this.text.charAt(i));
            float w = FontUtil.getStringWidth(c);
            s += w + this.getKerning();
        }
        return s;
    }

    public void split(float k) {
        if (!this.splitTick.isPassedMS(500.0f)) {
            this.setSplitRendering(true);
        }
        if (this.splitTick.isPassedMS(500.0f)) {
            this.setSplitRendering(false);
        }
        if (this.splitTick.isPassedMS(1000.0f)) {
            this.splitTick.reset();
        }
        if (this.isSplitRendering()) {
            Color stringColor = TurokGL.arrayColorToColorClass(this.string);
            TurokGL.color(stringColor.getRed(), stringColor.getGreen(), stringColor.getBlue(), stringColor.getAlpha());
            TurokRenderGL.drawSolidRect(this.rect.getX() + this.offsetX + k, this.rect.getY() + 1.0f, 1.0f, this.rect.getHeight() - 2.0f);
        }
    }

    @Override
    public void onScreenClosed() {
    }

    @Override
    public void onCustomScreenClosed() {
    }

    @Override
    public void onScreenOpened() {
    }

    @Override
    public void onCustomScreenOpened() {
    }

    @Override
    public void onKeyboardPressed(char character, int key) {
        try {
            if (this.isFocused()) {
                String cache = this.text;
                int a = this.indexA;
                switch (key) {
                    case 1: {
                        this.setFocused(false);
                        break;
                    }
                    case 28: {
                        this.setFocused(false);
                        break;
                    }
                    case 203: {
                        --a;
                        break;
                    }
                    case 205: {
                        ++a;
                        break;
                    }
                    case 14: {
                        String memory;
                        if (this.getIndexA() != this.getIndexB()) {
                            String affectedBySubString;
                            cache = affectedBySubString = cache.substring(0, this.indexA) + cache.substring(this.indexB, this.text.length());
                            this.indexB = this.indexA;
                            break;
                        }
                        int skip = 0;
                        if (Keyboard.isKeyDown((int)157) || Keyboard.isKeyDown((int)29)) {
                            memory = cache.substring(0, this.indexA);
                            if (memory.length() != 0) {
                                boolean foundFirstSpace = false;
                                boolean foundLetter = false;
                                int index = memory.length() - 1;
                                for (int i = 0; i < memory.length(); ++i) {
                                    index = memory.length() - 1 - i;
                                    String c = CharUtils.toString((char)memory.charAt(index));
                                    if (!c.equals(" ")) {
                                        foundLetter = true;
                                    }
                                    if (!c.equals(" ")) continue;
                                    if (foundLetter || foundFirstSpace) break;
                                    foundFirstSpace = true;
                                }
                                for (String c : memory.substring(index, memory.length()).split("")) {
                                    ++skip;
                                }
                                memory = memory.substring(0, index);
                            }
                        } else {
                            memory = StringUtils.chop((String)cache.substring(0, this.indexA));
                            skip = 1;
                        }
                        cache = memory + cache.substring(this.indexA, this.text.length());
                        a -= skip;
                        break;
                    }
                    case 211: {
                        if (this.getIndexA() != this.getIndexB()) {
                            String affectedBySubString;
                            cache = affectedBySubString = cache.substring(0, this.indexA) + cache.substring(this.indexB, this.text.length());
                            this.indexB = this.indexA;
                            break;
                        }
                        String memory = cache.substring(this.indexA, this.text.length());
                        if (memory.length() == 0) break;
                        if (Keyboard.isKeyDown((int)29) || Keyboard.isKeyDown((int)157)) {
                            int i = 0;
                            boolean foundFirstSpace = false;
                            boolean foundLetter = false;
                            for (String s : memory.split("")) {
                                if (!s.equals(" ")) {
                                    foundLetter = true;
                                }
                                if (s.equals(" ")) {
                                    if (foundLetter || foundFirstSpace) break;
                                    foundFirstSpace = true;
                                }
                                ++i;
                            }
                            memory = memory.substring(i, memory.length());
                        } else {
                            memory = memory.substring(1);
                        }
                        cache = cache.substring(0, this.indexA) + memory;
                        break;
                    }
                    default: {
                        if (Keyboard.isKeyDown((int)29) || Keyboard.isKeyDown((int)157)) {
                            if (key == 30) {
                                this.setIndexA(0);
                                this.setIndexB(cache.length());
                                a = 0;
                            }
                            String copy = ClipboardUtil.get();
                            if (key == 47 && copy != null) {
                                for (String i : copy.split("")) {
                                    ++a;
                                }
                                if (this.getIndexA() != this.getIndexB()) {
                                    cache = cache.substring(0, this.indexA) + cache.substring(this.indexB, cache.length());
                                    this.indexB = this.indexA;
                                }
                                cache = cache.substring(0, this.indexA) + copy + cache.substring(this.indexA, cache.length());
                            }
                            if (key == 46 && this.getIndexA() != this.getIndexB()) {
                                ClipboardUtil.set(cache.substring(this.indexA, this.indexB));
                            }
                            if (key != 45 || this.getIndexA() == this.getIndexB()) break;
                            ClipboardUtil.set(cache.substring(this.indexA, this.indexB));
                            cache = cache.substring(0, this.indexA) + cache.substring(this.indexB, this.text.length());
                            this.indexB = this.indexA;
                            break;
                        }
                        if (!ChatAllowedCharacters.isAllowedCharacter((char)character)) break;
                        if (this.getIndexA() != this.getIndexB()) {
                            cache = cache.substring(0, this.indexA) + cache.substring(this.indexB, cache.length());
                            this.indexB = this.indexA;
                        }
                        cache = cache.substring(0, this.indexA) + CharUtils.toString((char)character) + cache.substring(this.indexA, cache.length());
                        ++a;
                    }
                }
                if (!cache.equals(this.text)) {
                    this.text = cache;
                    this.splitTick.reset();
                }
                if (a != this.indexA) {
                    this.indexB = this.indexA = TurokMath.clamp(a, 0, this.text.length());
                    this.splitTick.reset();
                }
            }
        }
        catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
            // empty catch block
        }
    }

    @Override
    public void onCustomKeyboardPressed(char character, int key) {
    }

    @Override
    public void onMouseReleased(int button) {
        if (!this.isRendering()) {
            return;
        }
        if (this.isPressed()) {
            this.setReleased(this.isMouseOver());
            this.setDragging(false);
            this.setPressed(false);
        }
    }

    @Override
    public void onCustomMouseReleased(int button) {
    }

    @Override
    public void onMouseClicked(int button) {
        if (!this.isRendering()) {
            return;
        }
        if (!this.isMouseOver() && this.isFocused()) {
            this.setFocused(false);
        }
        if (this.isMouseOver() && (button == 0 || button == 1)) {
            this.splitTick.reset();
            this.setFocused(this.isMouseOver());
            if (this.pressTick.isPassedMS(500.0f)) {
                this.pressTick.reset();
            } else {
                this.setIndexA(0);
                this.setIndexB(this.text.length());
            }
            this.setPressed(true);
            this.setDragging(true);
        }
    }

    @Override
    public void onCustomMouseClicked(int button) {
    }

    @Override
    public void onRender() {
        if (!this.isRendering()) {
            return;
        }
        this.focusedAlpha = this.isFocused() ? (float)this.focused[3] : 0.0f;
        this.pressedAlpha = this.isPressed() ? (float)this.pressed[3] : 0.0f;
        this.outlineAlpha = this.outline[3];
        this.lastTickFocusedAlpha = TurokMath.lerp(this.lastTickFocusedAlpha, this.focusedAlpha, this.getPartialTicks());
        this.lastTickPressedAlpha = TurokMath.lerp(this.lastTickPressedAlpha, this.pressedAlpha, this.getPartialTicks());
        this.lastTickOutlineAlpha = TurokMath.lerp(this.lastTickOutlineAlpha, this.outlineAlpha, this.getPartialTicks());
        boolean flag = !GL11.glIsEnabled((int)3089);
        Color backColor = TurokGL.arrayColorToColorClass(this.background);
        TurokGL.color(backColor.getRed(), backColor.getGreen(), backColor.getBlue(), backColor.getAlpha());
        TurokRenderGL.drawSolidRect(this.rect);
        if (this.lastTickOutlineAlpha != 0.0f && this.isToDrawOutline()) {
            TurokGL.color(this.outline[0], this.outline[1], this.outline[2], this.lastTickOutlineAlpha);
            TurokRenderGL.drawOutlineRect(this.rect);
        }
        if (this.lastTickFocusedAlpha != 0.0f) {
            TurokGL.color(this.focused[0], this.focused[1], this.focused[2], this.lastTickFocusedAlpha);
            TurokRenderGL.drawSolidRect(this.rect);
        }
        if (this.lastTickPressedAlpha != 0.0f) {
            TurokGL.color(this.pressed[0], this.pressed[1], this.pressed[2], this.lastTickPressedAlpha);
            TurokRenderGL.drawSolidRect(this.rect);
        }
        if (flag) {
            GL11.glEnable((int)3089);
        }
        if (!this.isFocused()) {
            this.save = this.text;
        }
        if (this.isFocused()) {
            this.lastSize = (int)this.size();
        }
        Color color = TurokGL.arrayColorToColorClass(this.string);
        TurokShaderGL.drawScissor(this.scissor);
        float l = 0.0f;
        float k = 0.0f;
        int tl = this.text.isEmpty() ? -1 : this.text.length();
        int i = 0;
        int j = 0;
        for (String c : this.text.split("")) {
            ++j;
            if (tl == -1) {
                if (!this.isFocused()) continue;
                this.split(0.0f);
                continue;
            }
            float w = FontUtil.getStringWidth(c);
            boolean r = true;
            if (j > this.getIndexA() && j <= this.getIndexB() && this.getIndexB() != this.getIndexA() && this.isFocused()) {
                Color bSelectedColor = TurokGL.arrayColorToColorClass(this.background_selected);
                TurokGL.color(bSelectedColor.getRed(), bSelectedColor.getGreen(), bSelectedColor.getBlue(), bSelectedColor.getAlpha());
                TurokRenderGL.drawSolidRect(this.rect.getX() + this.offsetX + k, this.rect.getY() + this.offsetY / 2.0f, w + this.getKerning(), this.rect.getHeight() - this.offsetY / 2.0f * 2.0f);
                l = k + w + this.getKerning();
                r = false;
            }
            FontUtil.drawString(c, this.rect.getX() + this.offsetX + k, this.rect.getY() + this.offsetY, color.getRGB());
            if (r && i == this.getIndexA() && this.isFocused()) {
                this.split(k);
            }
            k += w + this.getKerning();
            ++i;
        }
        if (this.indexA == tl && this.isFocused()) {
            this.split(k);
        }
        if (flag) {
            GL11.glDisable((int)3089);
        }
    }

    @Override
    public void onCustomRender() {
    }
}

