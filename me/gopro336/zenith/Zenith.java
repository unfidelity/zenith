/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith;

import com.google.common.reflect.ClassPath;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import me.gopro336.zenith.api.util.IGlobals;
import me.gopro336.zenith.api.util.Logger;
import me.gopro336.zenith.api.util.newRotations.RotationManager;
import me.gopro336.zenith.api.util.time.TickRateUtil;
import me.gopro336.zenith.api.util.time.TimerManager;
import me.gopro336.zenith.core.Init;
import me.gopro336.zenith.core.InitStage;
import me.gopro336.zenith.event.EventProcessor;
import me.gopro336.zenith.event.TotemPopListener;
import me.gopro336.zenith.feature.FeatureManager;
import me.gopro336.zenith.feature.SettingManager;
import me.gopro336.zenith.managment.CommandManager;
import me.gopro336.zenith.managment.ThreadManager;
import me.gopro336.zenith.managment.TickManager;
import me.gopro336.zenith.managment.TpsManager;
import me.gopro336.zenith.userInterface.clickgui.ClickGUI;
import me.gopro336.zenith.userInterface.screen.HUDEditor;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.opengl.Display;
import team.stiff.pomelo.impl.annotated.AnnotatedEventManager;

@Mod(modid="zenith", name="Zenith", version="1.2.4")
public class Zenith
implements IGlobals {
    public static final String name = "Zenith";
    public static final String version = "1.2.4-release";
    public static final String creator = "Gopro336";
    @Mod.Instance
    public static Zenith INSTANCE;
    public static Logger logger;
    public static SettingManager SettingManager;
    public static FeatureManager featureManager;
    public RotationManager rotationManager;
    public static TpsManager tpsManager;
    public static TickManager tickManager;
    public static CommandManager commandManager;
    public static TotemPopListener popListener;
    private AnnotatedEventManager eventManager = new AnnotatedEventManager();
    public ThreadManager threadManager;
    public static ClickGUI clickGUI;
    public static HUDEditor hudEditor;
    private static boolean unloaded;
    public TimerManager timerManager;

    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event) throws IOException {
        Zenith.init(InitStage.Pre);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) throws IOException {
        System.out.println("[ZENITH] initilize");
        Display.setTitle((String)"Gopro is god");
        System.out.println("[ZENITH] Starting client, v2.3, created by Gopro336");
        SettingManager = new SettingManager();
        MinecraftForge.EVENT_BUS.register((Object)new EventProcessor());
        System.out.println("EventManager loaded.");
        this.load();
    }

    @Mod.EventHandler
    public void init(FMLPostInitializationEvent event) {
        try {
            Zenith.init(InitStage.Post);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        System.out.println("[ZENITH] Saved!");
    }

    public void msg() {
        System.out.println("[ZENITH] initilizeeeeee");
    }

    public void load() {
        logger = new Logger();
        this.threadManager = new ThreadManager();
        this.eventManager = new AnnotatedEventManager();
        featureManager = new FeatureManager();
        featureManager.init();
        this.rotationManager = new RotationManager();
        INSTANCE.getEventManager().addEventListener(this.rotationManager);
        tpsManager = new TpsManager();
        popListener = new TotemPopListener();
        tickManager = new TickManager();
        commandManager = new CommandManager();
        clickGUI = new ClickGUI();
        hudEditor = new HUDEditor();
        this.timerManager = new TimerManager();
        INSTANCE.getEventManager().addEventListener(this.timerManager);
        TickRateUtil.INSTANCE = new TickRateUtil();
        System.out.println("[ZENITH] Loaded!");
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public AnnotatedEventManager getEventManager() {
        return this.eventManager;
    }

    public ThreadManager getThreadManager() {
        return this.threadManager;
    }

    private static void init(InitStage initStage) throws IOException {
        Set initTasks = Zenith.getAnnotatedMethods().collect(Collectors.toSet());
        if (initTasks == null || initTasks.size() < 1) {
            return;
        }
        for (Method initTask : initTasks) {
            Init annotation = initTask.getAnnotation(Init.class);
            if (annotation == null || !annotation.stage().equals((Object)initStage)) continue;
            Zenith.reflectInit(initTask, annotation);
        }
    }

    private static void reflectInit(Method task, Init annotation) {
        Class<?>[] preTasks = annotation.dependencies();
        try {
            if (preTasks == null || preTasks.length < 1) {
                task.invoke(null, new Object[0]);
            } else {
                for (Class<?> aClass : preTasks) {
                    Set preInits = Zenith.getMethods(aClass).filter(method -> method.isAnnotationPresent(Init.class)).collect(Collectors.toSet());
                    for (Method preInit : preInits) {
                        Init preInitAnnotation = preInit.getAnnotation(Init.class);
                        if (preInitAnnotation == null || !preInitAnnotation.stage().equals((Object)annotation.stage())) continue;
                        Zenith.reflectInit(preInit, preInitAnnotation);
                    }
                    task.invoke(null, new Object[0]);
                }
            }
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static Stream<Method> getAnnotatedMethods() throws IOException {
        ArrayList out = new ArrayList();
        Zenith.getClasses().forEach(clazz -> Zenith.getMethods(clazz).filter(method -> method.isAnnotationPresent(Init.class)).forEach(method -> out.add(method)));
        return out.stream();
    }

    public static Stream<Method> getMethods(Class classFile) {
        return Arrays.stream(classFile.getDeclaredMethods()).filter(method -> method.isAnnotationPresent(Init.class));
    }

    public static Stream<Class> getClasses() throws IOException {
        ArrayList out = new ArrayList();
        ClassPath.from((ClassLoader)Launch.classLoader).getAllClasses().stream().forEach(classInfo -> out.add(classInfo.load()));
        return out.stream();
    }
}

