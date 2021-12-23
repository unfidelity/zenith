//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.Wrapper;
import me.gopro336.zenith.event.render.Render3DEvent;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.hudElement.Element;
import me.gopro336.zenith.feature.hudElement.hudElement.ActiveModulesElement;
import me.gopro336.zenith.feature.hudElement.hudElement.CoordinatesElement;
import me.gopro336.zenith.feature.hudElement.hudElement.CrystalViewerElement;
import me.gopro336.zenith.feature.hudElement.hudElement.CrystalsElement;
import me.gopro336.zenith.feature.hudElement.hudElement.GapplesElement;
import me.gopro336.zenith.feature.hudElement.hudElement.PingElement;
import me.gopro336.zenith.feature.hudElement.hudElement.TotemsElement;
import me.gopro336.zenith.feature.hudElement.hudElement.TpsElement;
import me.gopro336.zenith.feature.hudElement.hudElement.WatermarkElement;
import me.gopro336.zenith.feature.toggleable.Client.ClickGuiFeature;
import me.gopro336.zenith.feature.toggleable.Combat.AutoLog;
import me.gopro336.zenith.feature.toggleable.Combat.Burrow;
import me.gopro336.zenith.feature.toggleable.Combat.HoleFill;
import me.gopro336.zenith.feature.toggleable.Combat.HoleSnap;
import me.gopro336.zenith.feature.toggleable.Combat.Offhand;
import me.gopro336.zenith.feature.toggleable.Dummy;
import me.gopro336.zenith.feature.toggleable.exploit.AntiHunger;
import me.gopro336.zenith.feature.toggleable.exploit.OppSmoker;
import me.gopro336.zenith.feature.toggleable.exploit.PacketFly;
import me.gopro336.zenith.feature.toggleable.misc.AntiSound;
import me.gopro336.zenith.feature.toggleable.misc.AntiSpam;
import me.gopro336.zenith.feature.toggleable.misc.AutoFish;
import me.gopro336.zenith.feature.toggleable.misc.AutoReconnect;
import me.gopro336.zenith.feature.toggleable.misc.HotbarRefill;
import me.gopro336.zenith.feature.toggleable.misc.LazyItemSwitch;
import me.gopro336.zenith.feature.toggleable.misc.NoCompressionKick;
import me.gopro336.zenith.feature.toggleable.misc.Swing;
import me.gopro336.zenith.feature.toggleable.movement.Blink;
import me.gopro336.zenith.feature.toggleable.movement.BoatFly;
import me.gopro336.zenith.feature.toggleable.movement.EntityControl;
import me.gopro336.zenith.feature.toggleable.movement.FastSwim;
import me.gopro336.zenith.feature.toggleable.movement.NoSlow;
import me.gopro336.zenith.feature.toggleable.movement.Safewalk;
import me.gopro336.zenith.feature.toggleable.movement.Speed;
import me.gopro336.zenith.feature.toggleable.movement.Sprint;
import me.gopro336.zenith.feature.toggleable.movement.Step;
import me.gopro336.zenith.feature.toggleable.render.ChamsRw;
import me.gopro336.zenith.feature.toggleable.render.Chat;
import me.gopro336.zenith.feature.toggleable.render.CustomFont;
import me.gopro336.zenith.feature.toggleable.render.Fullbright;
import me.gopro336.zenith.feature.toggleable.render.HUDEditor;
import me.gopro336.zenith.feature.toggleable.render.KillDisplay;
import me.gopro336.zenith.feature.toggleable.render.MobOwner;
import me.gopro336.zenith.feature.toggleable.render.NewChams;
import me.gopro336.zenith.feature.toggleable.render.NewChunks;
import me.gopro336.zenith.feature.toggleable.render.NoRender;
import me.gopro336.zenith.feature.toggleable.render.RBandESP;
import me.gopro336.zenith.feature.toggleable.render.Radar;
import me.gopro336.zenith.feature.toggleable.render.RainbowFeature;
import me.gopro336.zenith.feature.toggleable.render.RotationViewer;
import me.gopro336.zenith.property.Property;
import me.gopro336.zenith.property.propertyrw.PropertyWR;
import org.lwjgl.input.Keyboard;

public class FeatureManager {
    private static final ArrayList<Feature> FEATURES = new ArrayList();
    public static ArrayList<Feature> toggledFeatures = new ArrayList();
    private static final ArrayList<Element> HUD_ELEMENTS = new ArrayList();

    public static ArrayList<Feature> getToggledFeatures() {
        return toggledFeatures;
    }

    public void init() {
        Zenith.INSTANCE.getEventManager().removeEventListener(this);
        this.addSubscribe(new CustomFont());
        this.addSubscribe(new RainbowFeature());
        this.addSubscribe(new ClickGuiFeature());
        this.addSubscribe(new HUDEditor());
        this.addSubscribe(NoCompressionKick.INSTANCE);
        this.addSubscribe(new AutoFish());
        this.addSubscribe(new PacketFly());
        this.addSubscribe(new Safewalk());
        this.addSubscribe(new Sprint());
        this.addSubscribe(new AntiHunger());
        this.addSubscribe(new EntityControl());
        this.addSubscribe(new MobOwner());
        this.addSubscribe(new AntiSound());
        this.addSubscribe(new NewChunks());
        this.addSubscribe(new NoRender());
        this.addSubscribe(new ChamsRw());
        this.addSubscribe(new Swing());
        this.addSubscribe(new Blink());
        this.addSubscribe(new OppSmoker());
        this.addSubscribe(new RBandESP());
        this.addSubscribe(new Fullbright());
        this.addSubscribe(new Radar());
        this.addSubscribe(new NewChams());
        this.addSubscribe(new BoatFly());
        this.addSubscribe(new FastSwim());
        this.addSubscribe(new Dummy());
        this.addSubscribe(new Step());
        this.addSubscribe(new AntiSpam());
        this.addSubscribe(new HotbarRefill());
        this.addSubscribe(LazyItemSwitch.INSTANCE);
        this.addSubscribe(new Offhand());
        this.addSubscribe(new AutoLog());
        this.addSubscribe(new AutoReconnect());
        this.addSubscribe(new NoSlow());
        this.addSubscribe(new Burrow());
        this.addSubscribe(new Speed());
        this.addSubscribe(new HoleSnap());
        this.addSubscribe(new KillDisplay());
        this.addSubscribe(new Chat());
        this.addSubscribe(new HoleFill());
        this.addSubscribe(new RotationViewer());
        this.addSubscribeHud(new PingElement());
        this.addSubscribeHud(new TpsElement());
        this.addSubscribeHud(new CrystalsElement());
        this.addSubscribeHud(new TotemsElement());
        this.addSubscribeHud(new GapplesElement());
        this.addSubscribeHud(new WatermarkElement());
        this.addSubscribeHud(new CrystalViewerElement());
        this.addSubscribeHud(new CoordinatesElement());
        this.addSubscribeHud(new ActiveModulesElement());
        FEATURES.sort(FeatureManager::alphabetize);
    }

    private void addSubscribe(Feature feature) {
        FEATURES.add(feature);
    }

    private void addSubscribeHud(Feature feature) {
        FEATURES.add(feature);
        HUD_ELEMENTS.add((Element)feature);
    }

    private static int alphabetize(Feature feature1, Feature feature2) {
        return feature1.getName().compareTo(feature2.getName());
    }

    public static void onUpdate() {
    }

    public static void onClientDisconnect() {
        Zenith.featureManager.getModules().stream().filter(Feature::isEnabled).forEach(Feature::onClientDisconnect);
    }

    public static void onClientConnect() {
        Zenith.featureManager.getModules().stream().filter(Feature::isEnabled).forEach(Feature::onClientConnect);
    }

    public void onRender3D(Render3DEvent event) {
    }

    public static ArrayList<Element> getHudElements() {
        return HUD_ELEMENTS;
    }

    @Deprecated
    public Feature getModuleByName(String name) {
        return FEATURES.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public static ArrayList<Feature> getModulesInCategory(Category category) {
        ArrayList<Feature> modulesInCategory = new ArrayList<Feature>();
        for (Feature feature : FEATURES) {
            if (!feature.getCategory().equals((Object)category)) continue;
            modulesInCategory.add(feature);
        }
        return modulesInCategory;
    }

    public ArrayList<Feature> getModules() {
        return FEATURES;
    }

    public static ArrayList<Feature> getModulesStatic() {
        return FEATURES;
    }

    public static ArrayList<Feature> getEnabledVisibleModules() {
        return (ArrayList)FEATURES.stream().filter(Feature::isEnabled).filter(Feature::isVisible).collect(Collectors.toList());
    }

    public static Feature getFeatureByAlias(String s) {
        for (Feature feature : FEATURES) {
            if (feature.getName().equalsIgnoreCase(s)) {
                return feature;
            }
            if (!feature.getName().toLowerCase().startsWith(s.toLowerCase())) continue;
            return feature;
        }
        return null;
    }

    public static PropertyWR<?> isPropertyInFeature(String s, String s1) {
        if (FeatureManager.getFeatureByAlias(s) != null) {
            ArrayList<PropertyWR<?>> PropertiesFromFeature = FeatureManager.getPropertiesFromFeature(Objects.requireNonNull(FeatureManager.getFeatureByAlias(s)));
            for (PropertyWR<?> propertyWR : PropertiesFromFeature) {
                if (!propertyWR.getName().equalsIgnoreCase(s1)) continue;
                return propertyWR;
            }
        }
        return null;
    }

    public static Feature getFeatureByClass(Class<? extends Feature> clazz) {
        for (Feature Feature2 : FEATURES) {
            if (Feature2.getClass() != clazz) continue;
            return Feature2;
        }
        return null;
    }

    @Deprecated
    public static Feature getModule(String name) {
        for (Feature feature : FEATURES) {
            if (!feature.getName().equalsIgnoreCase(name)) continue;
            return feature;
        }
        return null;
    }

    public static ArrayList<Feature> getModules(Category category) {
        ArrayList<Feature> modulesInCategory = new ArrayList<Feature>();
        FEATURES.forEach(feature -> {
            if (feature.getCategory() == category) {
                modulesInCategory.add((Feature)feature);
            }
        });
        return modulesInCategory;
    }

    public static ArrayList<Feature> getEnabledModules() {
        ArrayList<Feature> enabledModules = new ArrayList<Feature>();
        FEATURES.forEach(feature -> {
            if (feature.isEnabled()) {
                enabledModules.add((Feature)feature);
            }
        });
        return enabledModules;
    }

    public static ArrayList<PropertyWR<?>> getPropertiesFromFeature(Feature feature) {
        Feature cast = (Feature)feature.getClass().getSuperclass().cast(feature);
        ArrayList list = new ArrayList();
        for (Field field : cast.getClass().getDeclaredFields()) {
            if (!PropertyWR.class.isAssignableFrom(field.getType())) continue;
            field.setAccessible(true);
        }
        for (Field field : cast.getClass().getSuperclass().getDeclaredFields()) {
            if (!PropertyWR.class.isAssignableFrom(field.getType())) continue;
            field.setAccessible(true);
            try {
                list.add((PropertyWR)field.get(cast));
            }
            catch (IllegalAccessException var8) {
                var8.printStackTrace();
            }
        }
        return list;
    }

    public static ArrayList<Property<?>> getOldPropertiesFromFeature(Feature feature) {
        Feature cast = (Feature)feature.getClass().getSuperclass().cast(feature);
        ArrayList list = new ArrayList();
        Field[] declaredFields = cast.getClass().getDeclaredFields();
        int length = declaredFields.length;
        for (Field field : declaredFields) {
            if (!PropertyWR.class.isAssignableFrom(field.getType())) continue;
            field.setAccessible(true);
        }
        for (Field field : declaredFields = cast.getClass().getSuperclass().getDeclaredFields()) {
            if (!Property.class.isAssignableFrom(field.getType())) continue;
            field.setAccessible(true);
            try {
                list.add((Property)field.get(cast));
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void keyListen() {
        if (Wrapper.mc.currentScreen == null) {
            for (Feature m : Zenith.featureManager.getModules()) {
                try {
                    if (Keyboard.isKeyDown((int)0) || Keyboard.isKeyDown((int)0)) {
                        return;
                    }
                    if (Keyboard.isKeyDown((int)m.getKey()) && !m.isKeyDown) {
                        m.isKeyDown = true;
                        m.toggle();
                        continue;
                    }
                    if (Keyboard.isKeyDown((int)m.getKey())) continue;
                    m.isKeyDown = false;
                }
                catch (Exception exception) {}
            }
        }
    }
}

