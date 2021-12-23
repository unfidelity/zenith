//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.misc;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt;
import me.gopro336.zenith.event.network.PacketReceiveEvent;
import me.gopro336.zenith.event.player.UpdateWalkingPlayerEvent;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.toggleable.misc.AntiAfk$WhenMappings;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketTabComplete;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.Display;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0007J\u0012\u0010\u001b\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001cH\u0007R(\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\n \u0006*\u0004\u0018\u00010\u00130\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n \u0006*\u0004\u0018\u00010\u00130\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2={"Lme/gopro336/zenith/feature/toggleable/misc/AntiAfk;", "Lme/gopro336/zenith/feature/Feature;", "()V", "autoReply", "Lme/gopro336/zenith/property/Property;", "", "kotlin.jvm.PlatformType", "getAutoReply", "()Lme/gopro336/zenith/property/Property;", "setAutoReply", "(Lme/gopro336/zenith/property/Property;)V", "delay", "Lme/gopro336/zenith/property/NumberProperty;", "", "getDelay", "()Lme/gopro336/zenith/property/NumberProperty;", "setDelay", "(Lme/gopro336/zenith/property/NumberProperty;)V", "lastKey", "Ljava/time/Instant;", "lastRan", "whisperPattern", "Ljava/util/regex/Pattern;", "onPacket", "", "event", "Lme/gopro336/zenith/event/network/PacketReceiveEvent;", "onUpdateWalkingPlayer", "Lme/gopro336/zenith/event/player/UpdateWalkingPlayerEvent;", "zenith"})
public final class AntiAfk
extends Feature {
    private static final Pattern whisperPattern;
    private static Instant lastRan;
    private static Instant lastKey;
    @NotNull
    private static Property<Boolean> autoReply;
    @NotNull
    private static NumberProperty<Integer> delay;
    public static final AntiAfk INSTANCE;

    @NotNull
    public final Property<Boolean> getAutoReply() {
        return autoReply;
    }

    public final void setAutoReply(@NotNull Property<Boolean> property) {
        Intrinsics.checkParameterIsNotNull(property, "<set-?>");
        autoReply = property;
    }

    @NotNull
    public final NumberProperty<Integer> getDelay() {
        return delay;
    }

    public final void setDelay(@NotNull NumberProperty<Integer> numberProperty) {
        Intrinsics.checkParameterIsNotNull(numberProperty, "<set-?>");
        delay = numberProperty;
    }

    @Listener
    public final void onUpdateWalkingPlayer(@Nullable UpdateWalkingPlayerEvent event) {
        Duration duration = Duration.between(lastKey, Instant.now());
        Intrinsics.checkExpressionValueIsNotNull(duration, "Duration.between(lastKey, Instant.now())");
        if (duration.getSeconds() >= (long)6) {
            lastKey = Instant.now();
            KeyBinding.setKeyBindState((int)Feature.mc.gameSettings.keyBindLeft.keyCode, (boolean)Random.Default.nextBoolean());
            KeyBinding.setKeyBindState((int)Feature.mc.gameSettings.keyBindForward.keyCode, (boolean)Random.Default.nextBoolean());
            KeyBinding.setKeyBindState((int)Feature.mc.gameSettings.keyBindRight.keyCode, (boolean)Random.Default.nextBoolean());
            KeyBinding.setKeyBindState((int)Feature.mc.gameSettings.keyBindBack.keyCode, (boolean)Random.Default.nextBoolean());
        }
        Duration duration2 = Duration.between(lastRan, Instant.now());
        Intrinsics.checkExpressionValueIsNotNull(duration2, "Duration.between(lastRan, Instant.now())");
        long l = duration2.getSeconds();
        Number number = (Number)delay.getValue();
        Intrinsics.checkExpressionValueIsNotNull(number, "delay.value");
        if (l >= number.longValue()) {
            lastRan = Instant.now();
            switch (Random.Default.nextInt(10)) {
                case 0: {
                    Feature.mc.player.rotationYaw = Random.Default.nextBoolean() ? (float)Random.Default.nextInt(90) : -((float)Random.Default.nextInt(90));
                    Feature.mc.player.rotationPitch = Random.Default.nextBoolean() ? (float)Random.Default.nextInt(90) : -((float)Random.Default.nextInt(90));
                    break;
                }
                case 1: {
                    Feature.mc.player.connection.sendPacket((Packet)new CPacketAnimation(Random.Default.nextBoolean() ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND));
                    break;
                }
                case 2: {
                    AntiAfk antiAfk = this;
                    boolean bl = false;
                    boolean bl2 = false;
                    AntiAfk $this$run = antiAfk;
                    boolean bl3 = false;
                    if (Feature.mc.objectMouseOver != null) {
                        RayTraceResult.Type type = Feature.mc.objectMouseOver.typeOfHit;
                        if (type == null) break;
                        switch (AntiAfk$WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                            case 1: {
                                Feature.mc.playerController.attackEntity((EntityPlayer)Feature.mc.player, Feature.mc.objectMouseOver.entityHit);
                                break;
                            }
                            case 2: {
                                RayTraceResult rayTraceResult = Feature.mc.objectMouseOver;
                                Intrinsics.checkExpressionValueIsNotNull(rayTraceResult, "mc.objectMouseOver");
                                BlockPos blockpos = rayTraceResult.getBlockPos();
                                if (!Feature.mc.world.isAirBlock(blockpos)) {
                                    Feature.mc.playerController.clickBlock(blockpos, Feature.mc.objectMouseOver.sideHit);
                                    break;
                                }
                                ForgeHooks.onEmptyLeftClick((EntityPlayer)((EntityPlayer)Feature.mc.player));
                                Feature.mc.player.swingArm(EnumHand.MAIN_HAND);
                                break;
                            }
                            case 3: {
                                ForgeHooks.onEmptyLeftClick((EntityPlayer)((EntityPlayer)Feature.mc.player));
                                Feature.mc.player.swingArm(EnumHand.MAIN_HAND);
                            }
                        }
                    }
                    break;
                }
                case 3: {
                    EntityPlayerSP entityPlayerSP = Feature.mc.player;
                    Intrinsics.checkExpressionValueIsNotNull(entityPlayerSP, "mc.player");
                    entityPlayerSP.setSneaking(Random.Default.nextBoolean());
                    break;
                }
                case 4: {
                    NetHandlerPlayClient netHandlerPlayClient = Feature.mc.player.connection;
                    StringBuilder stringBuilder = new StringBuilder().append("/");
                    String string = UUID.randomUUID().toString();
                    Intrinsics.checkExpressionValueIsNotNull(string, "UUID.randomUUID().toString()");
                    String string2 = stringBuilder.append(StringsKt.replace$default(string, '-', 'v', false, 4, null)).toString();
                    EntityPlayerSP entityPlayerSP = Feature.mc.player;
                    Intrinsics.checkExpressionValueIsNotNull(entityPlayerSP, "mc.player");
                    netHandlerPlayClient.sendPacket((Packet)new CPacketTabComplete(string2, entityPlayerSP.getPosition(), false));
                    break;
                }
                case 5: {
                    Feature.mc.player.jump();
                    break;
                }
                case 6: {
                    EntityPlayerSP entityPlayerSP = Feature.mc.player;
                    StringBuilder stringBuilder = new StringBuilder().append("/");
                    String string = UUID.randomUUID().toString();
                    Intrinsics.checkExpressionValueIsNotNull(string, "UUID.randomUUID().toString()");
                    entityPlayerSP.sendChatMessage(stringBuilder.append(StringsKt.replace$default(string, '-', 'v', false, 4, null)).toString());
                    break;
                }
                case 7: {
                    Feature.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(1, 1, 1, ClickType.CLONE, new ItemStack(Blocks.BEDROCK), (short)1));
                    break;
                }
                case 8: {
                    NetHandlerPlayClient netHandlerPlayClient = Feature.mc.player.connection;
                    EntityPlayerSP entityPlayerSP = Feature.mc.player;
                    Intrinsics.checkExpressionValueIsNotNull(entityPlayerSP, "mc.player");
                    netHandlerPlayClient.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.SWAP_HELD_ITEMS, entityPlayerSP.getPosition(), EnumFacing.DOWN));
                    break;
                }
                case 9: {
                    NetHandlerPlayClient netHandlerPlayClient = Feature.mc.player.connection;
                    EntityPlayerSP entityPlayerSP = Feature.mc.player;
                    Intrinsics.checkExpressionValueIsNotNull(entityPlayerSP, "mc.player");
                    netHandlerPlayClient.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, entityPlayerSP.getPosition(), EnumFacing.DOWN));
                    break;
                }
                case 10: {
                    int slot;
                    Feature.mc.player.inventory.currentItem = slot = Random.Default.nextInt(9);
                    Feature.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slot));
                    break;
                }
            }
        }
    }

    @Listener
    public final void onPacket(@Nullable PacketReceiveEvent event) {
        if (event != null) {
            Boolean bl = autoReply.getValue();
            Intrinsics.checkExpressionValueIsNotNull(bl, "autoReply.value");
            if (bl.booleanValue() && event.getPacket() instanceof SPacketChat && Display.isActive()) {
                Packet packet = event.getPacket();
                if (packet == null) {
                    throw new TypeCastException("null cannot be cast to non-null type net.minecraft.network.play.server.SPacketChat");
                }
                SPacketChat packet2 = (SPacketChat)packet;
                ITextComponent iTextComponent = packet2.chatComponent;
                Intrinsics.checkExpressionValueIsNotNull(iTextComponent, "packet.chatComponent");
                String message = iTextComponent.getUnformattedText();
                if (whisperPattern.matcher(message).matches()) {
                    Feature.mc.player.connection.sendPacket((Packet)new CPacketChatMessage("/r I am currently AFK using Zenith"));
                }
            }
        }
    }

    private AntiAfk() {
    }

    static {
        AntiAfk antiAfk;
        INSTANCE = antiAfk = new AntiAfk();
        Pattern pattern = Pattern.compile("/^([A-z0-9_])+ whispers.*/gm");
        Intrinsics.checkExpressionValueIsNotNull(pattern, "Pattern.compile(\"/^([A-z0-9_])+ whispers.*/gm\")");
        whisperPattern = pattern;
        lastRan = Instant.EPOCH;
        lastKey = Instant.EPOCH;
        autoReply = new Property<Boolean>(antiAfk, "AutoReply", "", true);
        delay = new NumberProperty<Number>((Feature)antiAfk, "Delay", "", 1, (Number)1, 10);
    }
}

