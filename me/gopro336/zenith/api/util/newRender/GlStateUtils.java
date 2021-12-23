//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.newRender;

import java.util.ArrayList;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import me.gopro336.zenith.api.util.newRender.Quad;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u0006\n\u0002\b\u000b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004H\u0007J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004H\u0007J\u0010\u0010\u0005\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004H\u0007J\u000e\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004J\u0010\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004H\u0007J\u000e\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004J&\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001f2\u0006\u0010\u0016\u001a\u00020\u001fJ\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004H\u0007J\u0010\u0010#\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004H\u0007J\u0010\u0010$\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004H\u0007J\u0010\u0010%\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004H\u0007J\u000e\u0010&\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004J\b\u0010'\u001a\u00020\u0017H\u0007J\u0006\u0010(\u001a\u00020\u0017J\u0018\u0010)\u001a\u00020\u00172\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+H\u0007J\b\u0010-\u001a\u00020\u0017H\u0007J\b\u0010.\u001a\u00020\u0017H\u0007J\b\u0010/\u001a\u00020\u0017H\u0007J&\u00100\u001a\u00020\u00172\u0006\u00101\u001a\u00020\u000b2\u0006\u00102\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020\u000bJ\u0010\u00103\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004H\u0007J\u0010\u00104\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004H\u0007J\b\u00105\u001a\u00020\u0004H\u0007R&\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048\u0006@BX\u0087\u000e\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0002\u001a\u0004\b\u0007\u0010\bR(\u0010\t\u001a\u001c\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012RN\u0010\u0013\u001aB\u0012\u001c\u0012\u001a\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n0\u0014j \u0012\u001c\u0012\u001a\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n`\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00066"}, d2={"Lme/gopro336/zenith/api/util/newRender/GlStateUtils;", "", "()V", "<set-?>", "", "colorLock", "colorLock$annotations", "getColorLock", "()Z", "lastScissor", "Lme/gopro336/zenith/api/util/newRender/Quad;", "", "mc", "Lnet/minecraft/client/Minecraft;", "kotlin.jvm.PlatformType", "getMc", "()Lnet/minecraft/client/Minecraft;", "setMc", "(Lnet/minecraft/client/Minecraft;)V", "scissorList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "alpha", "", "state", "blend", "cull", "depth", "depthMask", "glColor", "red", "", "green", "blue", "hintPolygon", "lighting", "lineSmooth", "matrix", "polygon", "popScissor", "pushScissor", "rescale", "width", "", "height", "rescaleActual", "rescaleMc", "resetColour", "scissor", "x", "y", "smooth", "texture2d", "useVbo", "zenith"})
public final class GlStateUtils {
    private static Minecraft mc;
    private static Quad<Integer, Integer, Integer, Integer> lastScissor;
    private static final ArrayList<Quad<Integer, Integer, Integer, Integer>> scissorList;
    private static boolean colorLock;
    public static final GlStateUtils INSTANCE;

    public final Minecraft getMc() {
        return mc;
    }

    public final void setMc(Minecraft minecraft) {
        mc = minecraft;
    }

    public final void scissor(int x, int y, int width, int height) {
        lastScissor = new Quad<Integer, Integer, Integer, Integer>(x, y, width, height);
        GL11.glScissor((int)x, (int)y, (int)width, (int)height);
    }

    public final void pushScissor() {
        block0: {
            Quad<Integer, Integer, Integer, Integer> quad = lastScissor;
            if (quad == null) break block0;
            Quad<Integer, Integer, Integer, Integer> quad2 = quad;
            boolean bl = false;
            boolean bl2 = false;
            Quad<Integer, Integer, Integer, Integer> it = quad2;
            boolean bl3 = false;
            scissorList.add(it);
        }
    }

    @ExperimentalStdlibApi
    public final void popScissor() {
        block0: {
            Quad quad = (Quad)CollectionsKt.removeLastOrNull((List)scissorList);
            if (quad == null) break block0;
            Quad quad2 = quad;
            boolean bl = false;
            boolean bl2 = false;
            Quad it = quad2;
            boolean bl3 = false;
            INSTANCE.scissor(((Number)it.getFirst()).intValue(), ((Number)it.getSecond()).intValue(), ((Number)it.getThird()).intValue(), ((Number)it.getFourth()).intValue());
        }
    }

    @JvmStatic
    public static /* synthetic */ void colorLock$annotations() {
    }

    public static final boolean getColorLock() {
        return colorLock;
    }

    @JvmStatic
    public static final boolean useVbo() {
        return GlStateUtils.mc.gameSettings.useVbo;
    }

    @JvmStatic
    public static final void matrix(boolean state) {
        if (state) {
            GL11.glPushMatrix();
        } else {
            GL11.glPopMatrix();
        }
    }

    @JvmStatic
    public static final void blend(boolean state) {
        if (state) {
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        } else {
            GlStateManager.disableBlend();
        }
    }

    @JvmStatic
    public static final void alpha(boolean state) {
        if (state) {
            GlStateManager.enableAlpha();
        } else {
            GlStateManager.disableAlpha();
        }
    }

    @JvmStatic
    public static final void smooth(boolean state) {
        if (state) {
            GlStateManager.shadeModel((int)7425);
        } else {
            GlStateManager.shadeModel((int)7424);
        }
    }

    @JvmStatic
    public static final void lineSmooth(boolean state) {
        if (state) {
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
        } else {
            GL11.glDisable((int)2848);
            GL11.glHint((int)3154, (int)4352);
        }
    }

    @JvmStatic
    public static final void hintPolygon(boolean state) {
        if (state) {
            GL11.glHint((int)3155, (int)4354);
        } else {
            GL11.glHint((int)3155, (int)4352);
        }
    }

    @JvmStatic
    public static final void depth(boolean state) {
        if (state) {
            GlStateManager.enableDepth();
        } else {
            GlStateManager.disableDepth();
        }
    }

    public final void depthMask(boolean state) {
        if (state) {
            GlStateManager.depthMask((boolean)true);
        } else {
            GlStateManager.depthMask((boolean)false);
        }
    }

    @JvmStatic
    public static final void texture2d(boolean state) {
        if (state) {
            GlStateManager.enableTexture2D();
        } else {
            GlStateManager.disableTexture2D();
        }
    }

    public final void cull(boolean state) {
        if (state) {
            GlStateManager.enableCull();
        } else {
            GlStateManager.disableCull();
        }
    }

    @JvmStatic
    public static final void lighting(boolean state) {
        if (state) {
            GlStateManager.enableLighting();
        } else {
            GlStateManager.disableLighting();
        }
    }

    public final void polygon(boolean state) {
        if (state) {
            GlStateManager.enablePolygonOffset();
            GlStateManager.doPolygonOffset((float)1.0f, (float)-1500000.0f);
        } else {
            GlStateManager.disablePolygonOffset();
            GlStateManager.doPolygonOffset((float)1.0f, (float)1500000.0f);
        }
    }

    @JvmStatic
    public static final void resetColour() {
        INSTANCE.glColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public final void glColor(float red, float green, float blue, float alpha) {
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)alpha);
    }

    @JvmStatic
    public static final void colorLock(boolean state) {
        colorLock = state;
    }

    @JvmStatic
    public static final void rescale(double width, double height) {
        GlStateManager.clear((int)256);
        GlStateManager.viewport((int)0, (int)0, (int)GlStateUtils.mc.displayWidth, (int)GlStateUtils.mc.displayHeight);
        GlStateManager.matrixMode((int)5889);
        GlStateManager.loadIdentity();
        GlStateManager.ortho((double)0.0, (double)width, (double)height, (double)0.0, (double)1000.0, (double)3000.0);
        GlStateManager.matrixMode((int)5888);
        GlStateManager.loadIdentity();
        GlStateManager.translate((float)0.0f, (float)0.0f, (float)-2000.0f);
    }

    @JvmStatic
    public static final void rescaleActual() {
        GlStateUtils.rescale(GlStateUtils.mc.displayWidth, GlStateUtils.mc.displayHeight);
    }

    @JvmStatic
    public static final void rescaleMc() {
        ScaledResolution resolution = new ScaledResolution(mc);
        GlStateUtils.rescale(resolution.getScaledWidth_double(), resolution.getScaledHeight_double());
    }

    private GlStateUtils() {
    }

    static {
        GlStateUtils glStateUtils;
        INSTANCE = glStateUtils = new GlStateUtils();
        mc = Minecraft.getMinecraft();
        scissorList = new ArrayList();
    }
}

