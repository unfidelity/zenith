//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.IGlobals;
import me.gopro336.zenith.event.render.Render3DEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.FeatureManager;
import me.gopro336.zenith.feature.IToggleable;

public class Feature
implements IGlobals,
IToggleable {
    private final String name = this.getAnnotation().name();
    private final String description = this.getAnnotation().description();
    private Category category = this.getAnnotation().category();
    public boolean enabled;
    private boolean opened;
    private boolean visible = true;
    private boolean binding;
    public boolean isKeyDown;
    public float remainingAnimation = 0.0f;
    private String featureMetadata = null;
    public int key = this.getAnnotation().key();

    private AnnotationHelper getAnnotation() {
        if (this.getClass().isAnnotationPresent(AnnotationHelper.class)) {
            return this.getClass().getAnnotation(AnnotationHelper.class);
        }
        throw new IllegalStateException("Annotation '@AnnotationHelper' not found!");
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }

    public void onClientConnect() {
    }

    public void onClientDisconnect() {
    }

    @Override
    public boolean nullCheck() {
        return Feature.mc.player == null || Feature.mc.world == null;
    }

    public void enable() {
        this.enabled = true;
        Zenith.INSTANCE.getEventManager().addEventListener(this);
        FeatureManager.toggledFeatures.add(this);
        this.onEnable();
    }

    public void disable() {
        this.enabled = false;
        Zenith.INSTANCE.getEventManager().removeEventListener(this);
        FeatureManager.toggledFeatures.remove(this);
        this.remainingAnimation = 0.0f;
        this.onDisable();
    }

    public void toggle() {
        try {
            if (this.enabled) {
                this.disable();
            } else {
                this.enable();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void setEnabled(boolean enabled) {
        if (enabled) {
            this.enable();
        } else {
            this.disable();
        }
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onUpdate() {
    }

    public void onThread() {
    }

    public String getFeatureMetadata() {
        return this.featureMetadata;
    }

    public void setFeatureMetadata(String metaInfo) {
        this.featureMetadata = metaInfo;
    }

    public void onRender3D(Render3DEvent event) {
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isNotHUD() {
        return this.category != Category.HUD;
    }

    public String getHudInfo() {
        return null;
    }

    public boolean hasProperties() {
        return Zenith.SettingManager.getPropertiesByMod(this).size() > 0;
    }

    public String getName() {
        return this.name;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}

