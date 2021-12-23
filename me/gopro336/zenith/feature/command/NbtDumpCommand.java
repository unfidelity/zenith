//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.command;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import me.gopro336.zenith.api.util.newUtil.ChatUtils;
import me.gopro336.zenith.feature.command.Command;
import me.gopro336.zenith.feature.command.NbtDumpCommand;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001f\u0010\u000e\u001a\u00020\u000f2\u0010\u0010\u0010\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0012\u0018\u00010\u0011H\u0016\u00a2\u0006\u0002\u0010\u0013R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R#\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\b8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0014"}, d2={"Lme/gopro336/zenith/feature/command/NbtDumpCommand;", "Lme/gopro336/zenith/feature/command/Command;", "()V", "out", "Ljava/io/File;", "getOut", "()Ljava/io/File;", "write", "Ljava/lang/reflect/Method;", "kotlin.jvm.PlatformType", "getWrite", "()Ljava/lang/reflect/Method;", "write$delegate", "Lkotlin/Lazy;", "call", "", "args", "", "", "([Ljava/lang/String;)V", "zenith"})
public final class NbtDumpCommand
extends Command {
    @NotNull
    private static final File out;
    private static final Lazy write$delegate;
    public static final NbtDumpCommand INSTANCE;

    @NotNull
    public final File getOut() {
        return out;
    }

    public final Method getWrite() {
        Lazy lazy = write$delegate;
        NbtDumpCommand nbtDumpCommand = this;
        Object var3_3 = null;
        boolean bl = false;
        return (Method)lazy.getValue();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void call(@Nullable String[] args2) {
        ItemStack itemStack;
        EntityPlayerSP entityPlayerSP = Command.mc.player;
        Intrinsics.checkExpressionValueIsNotNull(entityPlayerSP, "mc.player");
        if (!entityPlayerSP.getHeldItemMainhand().isEmpty) {
            EntityPlayerSP entityPlayerSP2 = Command.mc.player;
            Intrinsics.checkExpressionValueIsNotNull(entityPlayerSP2, "mc.player");
            itemStack = entityPlayerSP2.getHeldItemMainhand();
        } else {
            EntityPlayerSP entityPlayerSP3 = Command.mc.player;
            Intrinsics.checkExpressionValueIsNotNull(entityPlayerSP3, "mc.player");
            if (!entityPlayerSP3.getHeldItemOffhand().isEmpty) {
                EntityPlayerSP entityPlayerSP4 = Command.mc.player;
                Intrinsics.checkExpressionValueIsNotNull(entityPlayerSP4, "mc.player");
                itemStack = entityPlayerSP4.getHeldItemOffhand();
            } else {
                ChatUtils.error("Please hold an item");
                return;
            }
        }
        ItemStack item = itemStack;
        NBTTagCompound nbt = new NBTTagCompound();
        item.writeToNBT(nbt);
        Object object = out;
        boolean bl = false;
        FileOutputStream fileOutputStream = new FileOutputStream((File)object);
        OutputStream outputStream = fileOutputStream;
        object = new DataOutputStream(outputStream);
        bl = false;
        Throwable throwable = null;
        try {
            DataOutputStream it = (DataOutputStream)object;
            boolean bl2 = false;
            Object object2 = INSTANCE.getWrite().invoke(nbt, it);
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            CloseableKt.closeFinally((Closeable)object, throwable);
        }
    }

    private NbtDumpCommand() {
        super("nbtdump", "nbtdump", "nbtdump");
    }

    static {
        NbtDumpCommand nbtDumpCommand;
        INSTANCE = nbtDumpCommand = new NbtDumpCommand();
        out = new File("raion/nbtdump.nbt");
        write$delegate = LazyKt.lazy(write.2.INSTANCE);
    }
}

