/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.command;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.FeatureManager;
import me.gopro336.zenith.feature.command.Command;
import me.gopro336.zenith.managment.CommandManager;
import me.gopro336.zenith.property.Property;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006H\u0016\u00a2\u0006\u0002\u0010\b\u00a8\u0006\t"}, d2={"Lme/gopro336/zenith/feature/command/ModuleListCommand;", "Lme/gopro336/zenith/feature/command/Command;", "()V", "call", "", "args", "", "", "([Ljava/lang/String;)V", "zenith"})
public final class ModuleListCommand
extends Command {
    public static final ModuleListCommand INSTANCE;

    @Override
    public void call(@NotNull String[] args2) {
        Intrinsics.checkParameterIsNotNull(args2, "args");
        boolean bl = false;
        StringBuilder stringBuilder = new StringBuilder();
        boolean bl2 = false;
        boolean bl3 = false;
        StringBuilder $this$buildString = stringBuilder;
        boolean bl4 = false;
        $this$buildString.append("-------------Modules:-------------\n");
        Iterator<Object> iterator2 = FeatureManager.getEnabledModules().iterator();
        while (iterator2.hasNext()) {
            Feature module;
            Feature feature = module = iterator2.next();
            Intrinsics.checkExpressionValueIsNotNull(feature, "module");
            $this$buildString.append(feature.getName());
            $this$buildString.append(": ");
            $this$buildString.append(module.getDescription());
            $this$buildString.append('\n');
            for (Property<?> value : Zenith.SettingManager.getPropertiesByMod(module)) {
                $this$buildString.append("\t- ");
                Property<?> property = value;
                Intrinsics.checkExpressionValueIsNotNull(property, "value");
                $this$buildString.append(property.getName());
                $this$buildString.append(": ");
                $this$buildString.append(value.getValue().getClass().getSimpleName());
                $this$buildString.append('\n');
            }
        }
        $this$buildString.append("\n-------------Commands:-------------\n");
        CommandManager commandManager = Zenith.commandManager;
        Intrinsics.checkExpressionValueIsNotNull(commandManager, "Zenith.commandManager");
        for (Command command : commandManager.getCommandList()) {
            if (Intrinsics.areEqual(command, INSTANCE)) continue;
            Command command2 = command;
            Intrinsics.checkExpressionValueIsNotNull(command2, "command");
            $this$buildString.append(command2.getName());
            $this$buildString.append(": ");
            $this$buildString.append(command.getDescription());
            $this$buildString.append('\n');
        }
        String string = stringBuilder.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "StringBuilder().apply(builderAction).toString()");
        String string2 = string;
        boolean bl5 = false;
        System.out.println((Object)string2);
    }

    private ModuleListCommand() {
        super("modulelist", "print a module list", "modulelist");
    }

    static {
        ModuleListCommand moduleListCommand;
        INSTANCE = moduleListCommand = new ModuleListCommand();
    }
}

