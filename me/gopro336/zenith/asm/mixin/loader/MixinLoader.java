/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.asm.mixin.loader;

import java.util.Map;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

public class MixinLoader
implements IFMLLoadingPlugin {
    private static boolean isObfuscatedEnvironment = false;

    public MixinLoader() {
        System.out.println("Zenith mixins initialized");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.zenith.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("name");
        System.out.println(MixinEnvironment.getDefaultEnvironment().getObfuscationContext());
        System.out.println("Zenith mixins finnished");
    }

    public String[] getASMTransformerClass() {
        return new String[0];
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> data) {
        isObfuscatedEnvironment = (Boolean)data.get("runtimeDeobfuscationEnabled");
    }

    public String getAccessTransformerClass() {
        return null;
    }
}

