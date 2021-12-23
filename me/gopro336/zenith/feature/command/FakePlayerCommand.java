//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.command;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import me.gopro336.zenith.api.util.newUtil.ChatUtils;
import me.gopro336.zenith.feature.command.Command;
import me.gopro336.zenith.feature.command.FakePlayerCommand;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001d\u0010!\u001a\u00020\"2\u000e\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020%0$H\u0016\u00a2\u0006\u0002\u0010&R\u001d\u0010\u0003\u001a\u0004\u0018\u00010\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R#\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\b\u001a\u0004\b\f\u0010\rR#\u0010\u000f\u001a\n \u000b*\u0004\u0018\u00010\u00100\u00108BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\b\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0014\u001a\u0004\u0018\u00010\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\b\u001a\u0004\b\u0015\u0010\u0006R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\b\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b \u0010\b\u001a\u0004\b\u001e\u0010\u001f\u00a8\u0006'"}, d2={"Lme/gopro336/zenith/feature/command/FakePlayerCommand;", "Lme/gopro336/zenith/feature/command/Command;", "()V", "gameProfileF", "Ljava/lang/reflect/Field;", "getGameProfileF", "()Ljava/lang/reflect/Field;", "gameProfileF$delegate", "Lkotlin/Lazy;", "gameprofilerepository", "Lcom/mojang/authlib/GameProfileRepository;", "kotlin.jvm.PlatformType", "getGameprofilerepository", "()Lcom/mojang/authlib/GameProfileRepository;", "gameprofilerepository$delegate", "minecraftsessionservice", "Lcom/mojang/authlib/minecraft/MinecraftSessionService;", "getMinecraftsessionservice", "()Lcom/mojang/authlib/minecraft/MinecraftSessionService;", "minecraftsessionservice$delegate", "playerInfoMapF", "getPlayerInfoMapF", "playerInfoMapF$delegate", "playerprofilecache", "Lnet/minecraft/server/management/PlayerProfileCache;", "getPlayerprofilecache", "()Lnet/minecraft/server/management/PlayerProfileCache;", "playerprofilecache$delegate", "yggdrasilauthenticationservice", "Lcom/mojang/authlib/yggdrasil/YggdrasilAuthenticationService;", "getYggdrasilauthenticationservice", "()Lcom/mojang/authlib/yggdrasil/YggdrasilAuthenticationService;", "yggdrasilauthenticationservice$delegate", "call", "", "args", "", "", "([Ljava/lang/String;)V", "zenith"})
public final class FakePlayerCommand
extends Command {
    private static final Lazy yggdrasilauthenticationservice$delegate;
    private static final Lazy minecraftsessionservice$delegate;
    private static final Lazy gameprofilerepository$delegate;
    private static final Lazy playerprofilecache$delegate;
    private static final Lazy playerInfoMapF$delegate;
    private static final Lazy gameProfileF$delegate;
    public static final FakePlayerCommand INSTANCE;

    private final YggdrasilAuthenticationService getYggdrasilauthenticationservice() {
        Lazy lazy = yggdrasilauthenticationservice$delegate;
        FakePlayerCommand fakePlayerCommand = this;
        Object var3_3 = null;
        boolean bl = false;
        return (YggdrasilAuthenticationService)lazy.getValue();
    }

    private final MinecraftSessionService getMinecraftsessionservice() {
        Lazy lazy = minecraftsessionservice$delegate;
        FakePlayerCommand fakePlayerCommand = this;
        Object var3_3 = null;
        boolean bl = false;
        return (MinecraftSessionService)lazy.getValue();
    }

    private final GameProfileRepository getGameprofilerepository() {
        Lazy lazy = gameprofilerepository$delegate;
        FakePlayerCommand fakePlayerCommand = this;
        Object var3_3 = null;
        boolean bl = false;
        return (GameProfileRepository)lazy.getValue();
    }

    private final PlayerProfileCache getPlayerprofilecache() {
        Lazy lazy = playerprofilecache$delegate;
        FakePlayerCommand fakePlayerCommand = this;
        Object var3_3 = null;
        boolean bl = false;
        return (PlayerProfileCache)lazy.getValue();
    }

    private final Field getPlayerInfoMapF() {
        Lazy lazy = playerInfoMapF$delegate;
        FakePlayerCommand fakePlayerCommand = this;
        Object var3_3 = null;
        boolean bl = false;
        return (Field)lazy.getValue();
    }

    private final Field getGameProfileF() {
        Lazy lazy = gameProfileF$delegate;
        FakePlayerCommand fakePlayerCommand = this;
        Object var3_3 = null;
        boolean bl = false;
        return (Field)lazy.getValue();
    }

    @Override
    public void call(@NotNull String[] args2) {
        String name;
        String control;
        Intrinsics.checkParameterIsNotNull(args2, "args");
        if (Command.mc.world == null) {
            return;
        }
        String string = control = args2.length < 2 ? "add" : args2[0];
        String string2 = args2.length == 1 ? args2[0] : (name = args2.length > 1 ? args2[1] : "Player");
        if (Intrinsics.areEqual(control, "add")) {
            EntityPlayerSP entityPlayerSP = Command.mc.player;
            Intrinsics.checkExpressionValueIsNotNull(entityPlayerSP, "mc.player");
            float health = entityPlayerSP.getHealth();
            double posX = Command.mc.player.posX;
            double posY = Command.mc.player.posY;
            double posZ = Command.mc.player.posZ;
            double prevPosX = Command.mc.player.prevPosX;
            double prevPosY = Command.mc.player.prevPosY;
            double prevPosZ = Command.mc.player.prevPosZ;
            double lastTickPosX = Command.mc.player.lastTickPosX;
            double lastTickPosY = Command.mc.player.lastTickPosY;
            double lastTickPosZ = Command.mc.player.lastTickPosZ;
            float rotationYaw = Command.mc.player.rotationYaw;
            float rotationYawHead = Command.mc.player.rotationYawHead;
            float rotationPitch = Command.mc.player.rotationPitch;
            float prevRotationYaw = Command.mc.player.prevRotationYaw;
            float prevRotationYawHead = Command.mc.player.prevRotationYawHead;
            float prevRotationPitch = Command.mc.player.prevRotationPitch;
            ThreadsKt.thread$default(true, true, null, null, 0, new Function0<Unit>(name, health, posX, posY, posZ, prevPosX, prevPosY, prevPosZ, lastTickPosX, lastTickPosY, lastTickPosZ, rotationYaw, rotationYawHead, rotationPitch, prevRotationYaw, prevRotationYawHead, prevRotationPitch){
                final /* synthetic */ String $name;
                final /* synthetic */ float $health;
                final /* synthetic */ double $posX;
                final /* synthetic */ double $posY;
                final /* synthetic */ double $posZ;
                final /* synthetic */ double $prevPosX;
                final /* synthetic */ double $prevPosY;
                final /* synthetic */ double $prevPosZ;
                final /* synthetic */ double $lastTickPosX;
                final /* synthetic */ double $lastTickPosY;
                final /* synthetic */ double $lastTickPosZ;
                final /* synthetic */ float $rotationYaw;
                final /* synthetic */ float $rotationYawHead;
                final /* synthetic */ float $rotationPitch;
                final /* synthetic */ float $prevRotationYaw;
                final /* synthetic */ float $prevRotationYawHead;
                final /* synthetic */ float $prevRotationPitch;

                public final void invoke() {
                    boolean bl;
                    String string;
                    GameProfile gameProfile = FakePlayerCommand.access$getPlayerprofilecache$p(FakePlayerCommand.INSTANCE).getGameProfileForUsername(this.$name);
                    if (gameProfile == null) {
                        gameProfile = new GameProfile(EntityPlayer.getOfflineUUID((String)this.$name), this.$name);
                    }
                    Intrinsics.checkExpressionValueIsNotNull(gameProfile, "playerprofilecache.getGa\u2026tOfflineUUID(name), name)");
                    GameProfile profile = gameProfile;
                    String string2 = String.valueOf(FakePlayerCommand.access$getGameProfileF$p(FakePlayerCommand.INSTANCE));
                    boolean bl2 = false;
                    System.out.println((Object)string2);
                    if (Command.mc.world == null || Command.mc.player == null) {
                        return;
                    }
                    NetHandlerPlayClient connection = Command.mc.player.connection;
                    if (connection.getPlayerInfo(profile.getId()) == null && FakePlayerCommand.access$getPlayerInfoMapF$p(FakePlayerCommand.INSTANCE) != null) {
                        Field field = FakePlayerCommand.access$getPlayerInfoMapF$p(FakePlayerCommand.INSTANCE);
                        if (field == null) {
                            Intrinsics.throwNpe();
                        }
                        Object object = field.get(connection);
                        if (object == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<java.util.UUID, net.minecraft.client.network.NetworkPlayerInfo>");
                        }
                        Map map = TypeIntrinsics.asMutableMap(object);
                        UUID uUID = profile.getId();
                        Intrinsics.checkExpressionValueIsNotNull(uUID, "profile.id");
                        map.put(uUID, new NetworkPlayerInfo(profile));
                        string = "Added to map";
                        bl = false;
                        System.out.println((Object)string);
                    }
                    EntityOtherPlayerMP player = new EntityOtherPlayerMP((World)Command.mc.world, profile);
                    player.setHealth(this.$health);
                    player.posX = this.$posX;
                    player.posY = this.$posY;
                    player.posZ = this.$posZ;
                    player.prevPosX = this.$prevPosX;
                    player.prevPosY = this.$prevPosY;
                    player.prevPosZ = this.$prevPosZ;
                    player.lastTickPosX = this.$lastTickPosX;
                    player.lastTickPosY = this.$lastTickPosY;
                    player.lastTickPosZ = this.$lastTickPosZ;
                    player.rotationYaw = this.$rotationYaw;
                    player.rotationYawHead = this.$rotationYawHead;
                    player.rotationPitch = this.$rotationPitch;
                    player.prevRotationYaw = this.$prevRotationYaw;
                    player.prevRotationYawHead = this.$prevRotationYawHead;
                    player.prevRotationPitch = this.$prevRotationPitch;
                    string = new NBTTagList();
                    InventoryPlayer inventoryPlayer = player.inventory;
                    bl = false;
                    boolean bl3 = false;
                    String it = string;
                    boolean bl4 = false;
                    Command.mc.player.inventory.writeToNBT((NBTTagList)it);
                    String string3 = string;
                    inventoryPlayer.readFromNBT((NBTTagList)string3);
                    EntityPlayerSP entityPlayerSP = Command.mc.player;
                    Intrinsics.checkExpressionValueIsNotNull(entityPlayerSP, "mc.player");
                    player.setHeldItem(EnumHand.MAIN_HAND, entityPlayerSP.getHeldItemMainhand());
                    EntityPlayerSP entityPlayerSP2 = Command.mc.player;
                    Intrinsics.checkExpressionValueIsNotNull(entityPlayerSP2, "mc.player");
                    player.setHeldItem(EnumHand.OFF_HAND, entityPlayerSP2.getHeldItemOffhand());
                    Command.mc.addScheduledTask(new Runnable(player, profile){
                        final /* synthetic */ EntityOtherPlayerMP $player;
                        final /* synthetic */ GameProfile $profile;

                        /*
                         * WARNING - void declaration
                         */
                        public final void run() {
                            int n = -100;
                            int n2 = -500;
                            while (n >= n2) {
                                void i;
                                if (Command.mc.world.getEntityByID((int)i) == null) {
                                    Command.mc.world.addEntityToWorld((int)i, (Entity)this.$player);
                                    ChatUtils.message("Player [" + this.$profile.getName() + "] was added to the world");
                                    this.$player.setInvisible(false);
                                    this.$player.isDead = false;
                                    return;
                                }
                                --i;
                            }
                            ChatUtils.error("No available slots");
                        }
                        {
                            this.$player = entityOtherPlayerMP;
                            this.$profile = gameProfile;
                        }
                    });
                }
                {
                    this.$name = string;
                    this.$health = f;
                    this.$posX = d;
                    this.$posY = d2;
                    this.$posZ = d3;
                    this.$prevPosX = d4;
                    this.$prevPosY = d5;
                    this.$prevPosZ = d6;
                    this.$lastTickPosX = d7;
                    this.$lastTickPosY = d8;
                    this.$lastTickPosZ = d9;
                    this.$rotationYaw = f2;
                    this.$rotationYawHead = f3;
                    this.$rotationPitch = f4;
                    this.$prevRotationYaw = f5;
                    this.$prevRotationYawHead = f6;
                    this.$prevRotationPitch = f7;
                    super(0);
                }
            }, 28, null);
        } else if (Intrinsics.areEqual(control, "remove") || Intrinsics.areEqual(control, "delete")) {
            for (Entity entity : Command.mc.world.loadedEntityList) {
                if (!(entity instanceof EntityOtherPlayerMP) || !Intrinsics.areEqual(((EntityOtherPlayerMP)entity).getName(), name)) continue;
                Command.mc.world.removeEntity(entity);
                ChatUtils.message("Removed player [" + ((EntityOtherPlayerMP)entity).getName() + "] from the world");
                return;
            }
            ChatUtils.error("Couldnt find player [" + name + ']');
        } else {
            ChatUtils.error(this.getUsage());
            return;
        }
    }

    private FakePlayerCommand() {
        super("fakeplayer", "Spawn a fake player", "fakeplayer {add/remove}? {name}?");
    }

    static {
        FakePlayerCommand fakePlayerCommand;
        INSTANCE = fakePlayerCommand = new FakePlayerCommand();
        yggdrasilauthenticationservice$delegate = LazyKt.lazy(yggdrasilauthenticationservice.2.INSTANCE);
        minecraftsessionservice$delegate = LazyKt.lazy(minecraftsessionservice.2.INSTANCE);
        gameprofilerepository$delegate = LazyKt.lazy(gameprofilerepository.2.INSTANCE);
        playerprofilecache$delegate = LazyKt.lazy(playerprofilecache.2.INSTANCE);
        playerInfoMapF$delegate = LazyKt.lazy(playerInfoMapF.2.INSTANCE);
        gameProfileF$delegate = LazyKt.lazy(gameProfileF.2.INSTANCE);
    }

    public static final /* synthetic */ PlayerProfileCache access$getPlayerprofilecache$p(FakePlayerCommand $this) {
        return $this.getPlayerprofilecache();
    }

    public static final /* synthetic */ Field access$getGameProfileF$p(FakePlayerCommand $this) {
        return $this.getGameProfileF();
    }

    public static final /* synthetic */ Field access$getPlayerInfoMapF$p(FakePlayerCommand $this) {
        return $this.getPlayerInfoMapF();
    }

    public static final /* synthetic */ YggdrasilAuthenticationService access$getYggdrasilauthenticationservice$p(FakePlayerCommand $this) {
        return $this.getYggdrasilauthenticationservice();
    }

    public static final /* synthetic */ GameProfileRepository access$getGameprofilerepository$p(FakePlayerCommand $this) {
        return $this.getGameprofilerepository();
    }
}

