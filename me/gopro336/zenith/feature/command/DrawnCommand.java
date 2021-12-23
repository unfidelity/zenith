/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.command;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.gopro336.zenith.api.util.newUtil.ChatUtils;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.FeatureManager;
import me.gopro336.zenith.feature.command.Command;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001b\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016\u00a2\u0006\u0002\u0010\b\u00a8\u0006\t"}, d2={"Lme/gopro336/zenith/feature/command/DrawnCommand;", "Lme/gopro336/zenith/feature/command/Command;", "()V", "call", "", "args", "", "", "([Ljava/lang/String;)V", "zenith"})
public final class DrawnCommand
extends Command {
    public static final DrawnCommand INSTANCE;

    @Override
    public void call(@NotNull String[] args2) {
        Intrinsics.checkParameterIsNotNull(args2, "args");
        String[] stringArray = args2;
        boolean bl = false;
        if (stringArray.length == 0) {
            ChatUtils.message("Please specify a module");
            return;
        }
        Feature m = FeatureManager.getModule(args2[0]);
        if (m == null) {
            ChatUtils.message(args2[0] + " is not a valid module");
        }
    }

    private DrawnCommand() {
        super("Drawn", new String[]{"d", "visible"}, "Disable modules being drawn on the arraylist", "drawn (module)");
    }

    static {
        DrawnCommand drawnCommand;
        INSTANCE = drawnCommand = new DrawnCommand();
    }
}

