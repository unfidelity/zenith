//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.movement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.client.PlayerUtils;
import me.gopro336.zenith.api.util.time.Timer;
import me.gopro336.zenith.asm.mixin.imixin.ICPacketPlayer;
import me.gopro336.zenith.event.EventStageable;
import me.gopro336.zenith.event.entity.PlayerUseItemEvent;
import me.gopro336.zenith.event.network.PacketReceiveEvent;
import me.gopro336.zenith.event.network.PacketSendEvent;
import me.gopro336.zenith.event.player.MoveEvent;
import me.gopro336.zenith.event.player.PlayerUpdateEvent;
import me.gopro336.zenith.event.player.UpdateWalkingPlayerEvent;
import me.gopro336.zenith.event.world.UpdateEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockPackedIce;
import net.minecraft.entity.Entity;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="Speed", description="Makes you move faster", category=Category.MOVEMENT)
public class Speed
extends Feature {
    public NumberProperty<Double> speed = new NumberProperty<Double>((Feature)this, "Speed", "", 0.0, Double.valueOf(1.0), 10.0);
    public Property<Boolean> useTimer = new Property<Boolean>(this, "UseTimer", "", true);
    public NumberProperty<Float> timerFactor = new NumberProperty<Float>((Feature)this, this.useTimer, "Factor", "", Float.valueOf(0.0f), Float.valueOf(1.0f), Float.valueOf(10.0f));
    public Property<Mode> mode = new Property<Mode>(this, "Mode", "", Mode.STRAFE);
    public Property<Boolean> bypass = new Property<Boolean>((Feature)this, "Bypass", "", false, v -> this.getMode() == Mode.ONGROUND);
    public Property<Boolean> hypixel = new Property<Boolean>((Feature)this, "Hypixel", "", false, v -> this.getMode() == Mode.STRAFEOLD);
    public Property<Boolean> allowEat = new Property<Boolean>((Feature)this, "AllowEat", "", false, v -> this.getMode() == Mode.STRAFESTRICT);
    public Property<Boolean> strict = new Property<Boolean>((Feature)this, "Strict", "", false, v -> this.getMode() == Mode.STRAFE);
    public Property<Boolean> disableOnSneak = new Property<Boolean>(this, "DisableOnSneak", "", false);
    public Property<Boolean> forceSprint = new Property<Boolean>(this, "ForceSprint", "", false);
    public Property<Boolean> boost = new Property<Boolean>((Feature)this, "Boost", "", false, v -> this.getMode() == Mode.SMALLHOP || this.getMode() == Mode.STRAFE || this.getMode() == Mode.STRAFEOLD || this.getMode() == Mode.STRAFESTRICT);
    public NumberProperty<Double> boostFactor = new NumberProperty<Double>((Feature)this, "BoostFactor", "", 0.0, Double.valueOf(1.0), Double.valueOf(10.0), v -> this.getMode() == Mode.SMALLHOP && this.boost.getValue() != false);
    private int strafeStage = 1;
    public int hopStage;
    private double ncpPrevMotion = 0.0;
    private double horizontal;
    private double currentSpeed = 0.0;
    private double prevMotion = 0.0;
    private boolean oddStage = false;
    private int state = 4;
    private double aacSpeed = 0.2873;
    private int aacCounter;
    private int aacState = 4;
    private int ticksPassed = 0;
    private boolean sneaking;
    private double maxVelocity = 0.0;
    private Timer velocityTimer = new Timer();
    private Timer setbackTimer = new Timer();
    private int lowHopStage;
    private double lowHopSpeed;
    private boolean even;
    private int onGroundStage;
    private double onGroundSpeed;
    private boolean forceGround;

    private Mode getMode() {
        return this.mode.getValue();
    }

    @Listener
    public void onPlayerUseItem(PlayerUseItemEvent event) {
        if (!this.sneaking && this.getMode() == Mode.STRAFESTRICT && this.allowEat.getValue().booleanValue()) {
            Speed.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Speed.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            this.sneaking = true;
        }
    }

    @Listener
    public void onUpdate(UpdateEvent event) {
        if (Speed.mc.player == null || Speed.mc.world == null) {
            return;
        }
        if (event.getPhase() == TickEvent.Phase.START && this.getMode() == Mode.ONGROUND) {
            double dX = Speed.mc.player.posX - Speed.mc.player.prevPosX;
            double dZ = Speed.mc.player.posZ - Speed.mc.player.prevPosZ;
            this.prevMotion = Math.sqrt(dX * dX + dZ * dZ);
        }
        int minY = MathHelper.floor((double)(Speed.mc.player.getEntityBoundingBox().minY - 0.2));
        this.setFeatureMetadata(this.getMode().name().substring(0, 1).toUpperCase() + this.getMode().name().substring(1).toLowerCase());
        if (this.disableOnSneak.getValue().booleanValue() && Speed.mc.player.isSneaking()) {
            return;
        }
        if ((this.getMode() == Mode.STRAFEOLD || this.getMode() == Mode.STRAFE || this.getMode() == Mode.LOWHOP) && this.useTimer.getValue().booleanValue()) {
            Zenith.INSTANCE.timerManager.updateTimer(this, 10, 1.08f + 0.008f * ((Float)this.timerFactor.getValue()).floatValue());
        } else if (this.getMode() != Mode.STRAFESTRICT && this.getMode() != Mode.SMALLHOP) {
            Zenith.INSTANCE.timerManager.resetTimer(this);
        }
        switch (this.getMode()) {
            case STRAFEOLD: {
                if (this.forceSprint.getValue().booleanValue() && !Speed.mc.player.isSprinting() && PlayerUtils.isPlayerMoving()) {
                    Speed.mc.player.setSprinting(true);
                    Speed.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Speed.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                }
                this.ncpPrevMotion = Math.sqrt((Speed.mc.player.posX - Speed.mc.player.prevPosX) * (Speed.mc.player.posX - Speed.mc.player.prevPosX) + (Speed.mc.player.posZ - Speed.mc.player.prevPosZ) * (Speed.mc.player.posZ - Speed.mc.player.prevPosZ));
                break;
            }
            case SMALLHOP: {
                if (!PlayerUtils.isPlayerMoving() || Speed.mc.player.collidedHorizontally) {
                    Zenith.INSTANCE.timerManager.resetTimer(this);
                    return;
                }
                if (Speed.mc.player.onGround) {
                    Zenith.INSTANCE.timerManager.updateTimer(this, 10, 1.15f);
                    Speed.mc.player.jump();
                    boolean ice = Speed.mc.world.getBlockState(new BlockPos(Speed.mc.player.posX, Speed.mc.player.posY - 1.0, Speed.mc.player.posZ)).getBlock() instanceof BlockIce || Speed.mc.world.getBlockState(new BlockPos(Speed.mc.player.posX, Speed.mc.player.posY - 1.0, Speed.mc.player.posZ)).getBlock() instanceof BlockPackedIce;
                    double[] dirSpeed = PlayerUtils.directionSpeed(this.getBaseMotionSpeed() * (Double)this.speed.getValue() + (this.boost.getValue().booleanValue() ? (ice ? 0.3 : 0.06 * (Double)this.boostFactor.getValue()) : 0.0));
                    Speed.mc.player.motionX = dirSpeed[0];
                    Speed.mc.player.motionZ = dirSpeed[1];
                    break;
                }
                Speed.mc.player.motionY = -1.0;
                Zenith.INSTANCE.timerManager.resetTimer(this);
            }
        }
        Item item = Speed.mc.player.getActiveItemStack().getItem();
        if (this.getMode() == Mode.STRAFESTRICT && this.allowEat.getValue().booleanValue() && this.sneaking && (!Speed.mc.player.isHandActive() && item instanceof ItemFood || item instanceof ItemBow || item instanceof ItemPotion || !(item instanceof ItemFood) || !(item instanceof ItemBow) || !(item instanceof ItemPotion))) {
            Speed.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Speed.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.sneaking = false;
        }
    }

    @Listener
    public void onPlayerUpdate(PlayerUpdateEvent event) {
        if (Speed.mc.player == null || Speed.mc.world == null) {
            return;
        }
        switch (this.getMode()) {
            case TP: {
                for (double x = 0.0625; x < (Double)this.speed.getValue(); x += 0.262) {
                    double[] dir = PlayerUtils.directionSpeed(x);
                    Speed.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Speed.mc.player.posX + dir[0], Speed.mc.player.posY, Speed.mc.player.posZ + dir[1], Speed.mc.player.onGround));
                }
                Speed.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Speed.mc.player.posX + Speed.mc.player.motionX, Speed.mc.player.posY <= 10.0 ? 255.0 : 1.0, Speed.mc.player.posZ + Speed.mc.player.motionZ, Speed.mc.player.onGround));
            }
        }
    }

    @Listener
    public void onUpdateWalkingPlayerPre(UpdateWalkingPlayerEvent event) {
        if (event.getStage() != EventStageable.EventStage.PRE) {
            return;
        }
        if (!PlayerUtils.isPlayerMoving()) {
            this.currentSpeed = 0.0;
            if (this.getMode() != Mode.SMALLHOP) {
                Speed.mc.player.motionX = 0.0;
                Speed.mc.player.motionZ = 0.0;
                return;
            }
        }
        if (this.getMode() == Mode.STRAFE || this.getMode() == Mode.STRAFESTRICT || this.getMode() == Mode.LOWHOP) {
            double dX = Speed.mc.player.posX - Speed.mc.player.prevPosX;
            double dZ = Speed.mc.player.posZ - Speed.mc.player.prevPosZ;
            this.prevMotion = Math.sqrt(dX * dX + dZ * dZ);
        }
    }

    @Listener
    public void onPacketSend(PacketSendEvent event) {
        if (event.getPacket() instanceof CPacketPlayer && this.forceGround) {
            this.forceGround = false;
            ((ICPacketPlayer)event.getPacket()).setOnGround(true);
        }
    }

    @Listener
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacket() instanceof SPacketPlayerPosLook) {
            Zenith.INSTANCE.timerManager.resetTimer(this);
            this.ncpPrevMotion = 0.0;
            this.currentSpeed = 0.0;
            this.horizontal = 0.0;
            this.state = 4;
            this.aacSpeed = 0.2873;
            this.aacState = 4;
            this.prevMotion = 0.0;
            this.aacCounter = 0;
            this.maxVelocity = 0.0;
            this.setbackTimer.reset();
            this.lowHopStage = 4;
            this.onGroundStage = 2;
            this.onGroundSpeed = 0.0;
        } else if (event.getPacket() instanceof SPacketExplosion) {
            SPacketExplosion velocity = (SPacketExplosion)event.getPacket();
            this.maxVelocity = Math.sqrt(velocity.getMotionX() * velocity.getMotionX() + velocity.getMotionZ() * velocity.getMotionZ());
            this.velocityTimer.reset();
        }
    }

    @Listener
    public void onMove(MoveEvent event) {
        if (Speed.mc.player == null || Speed.mc.world == null) {
            return;
        }
        if (this.disableOnSneak.getValue().booleanValue() && Speed.mc.player.isSneaking()) {
            return;
        }
        int minY = MathHelper.floor((double)(Speed.mc.player.getEntityBoundingBox().minY - 0.2));
        switch (this.getMode()) {
            case STRAFE: {
                if (this.state != 1 || Speed.mc.player.moveForward == 0.0f || Speed.mc.player.moveStrafing == 0.0f) {
                    if (this.state == 2 && (Speed.mc.player.moveForward != 0.0f || Speed.mc.player.moveStrafing != 0.0f)) {
                        double jumpSpeed = 0.0;
                        if (Speed.mc.player.isPotionActive(MobEffects.JUMP_BOOST)) {
                            jumpSpeed += (double)((float)(Speed.mc.player.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1f);
                        }
                        Speed.mc.player.motionY = (this.hypixel.getValue() != false ? 0.3999999463558197 : 0.3999) + jumpSpeed;
                        event.setY(Speed.mc.player.motionY);
                        this.currentSpeed *= this.oddStage ? 1.6835 : 1.395;
                    } else if (this.state == 3) {
                        double adjustedMotion = 0.66 * (this.prevMotion - this.getBaseMotionSpeed());
                        this.currentSpeed = this.prevMotion - adjustedMotion;
                        this.oddStage = !this.oddStage;
                    } else {
                        List collisionBoxes = Speed.mc.world.getCollisionBoxes((Entity)Speed.mc.player, Speed.mc.player.getEntityBoundingBox().offset(0.0, Speed.mc.player.motionY, 0.0));
                        if ((collisionBoxes.size() > 0 || Speed.mc.player.collidedVertically) && this.state > 0) {
                            this.state = Speed.mc.player.moveForward == 0.0f && Speed.mc.player.moveStrafing == 0.0f ? 0 : 1;
                        }
                        this.currentSpeed = this.prevMotion - this.prevMotion / 159.0;
                    }
                } else {
                    this.currentSpeed = 1.35 * this.getBaseMotionSpeed() - 0.01;
                }
                this.currentSpeed = Math.max(this.currentSpeed, this.getBaseMotionSpeed());
                if (this.maxVelocity > 0.0 && this.boost.getValue().booleanValue() && !this.velocityTimer.hasPassed(75.0) && !Speed.mc.player.collidedHorizontally) {
                    this.currentSpeed = Math.max(this.currentSpeed, this.maxVelocity);
                } else if (this.strict.getValue().booleanValue()) {
                    this.currentSpeed = Math.min(this.currentSpeed, 0.433);
                }
                double forward = Speed.mc.player.movementInput.moveForward;
                double strafe = Speed.mc.player.movementInput.moveStrafe;
                float yaw = Speed.mc.player.rotationYaw;
                if (forward == 0.0 && strafe == 0.0) {
                    event.setX(0.0);
                    event.setZ(0.0);
                } else {
                    if (forward != 0.0) {
                        if (strafe > 0.0) {
                            yaw += (float)(forward > 0.0 ? -45 : 45);
                        } else if (strafe < 0.0) {
                            yaw += (float)(forward > 0.0 ? 45 : -45);
                        }
                        strafe = 0.0;
                        if (forward > 0.0) {
                            forward = 1.0;
                        } else if (forward < 0.0) {
                            forward = -1.0;
                        }
                    }
                    event.setX(forward * this.currentSpeed * Math.cos(Math.toRadians(yaw + 90.0f)) + strafe * this.currentSpeed * Math.sin(Math.toRadians(yaw + 90.0f)));
                    event.setZ(forward * this.currentSpeed * Math.sin(Math.toRadians(yaw + 90.0f)) - strafe * this.currentSpeed * Math.cos(Math.toRadians(yaw + 90.0f)));
                }
                if (Speed.mc.player.moveForward == 0.0f && Speed.mc.player.moveStrafing == 0.0f) {
                    return;
                }
                ++this.state;
                break;
            }
            case STRAFEOLD: {
                if (!Speed.mc.player.isSprinting()) {
                    Speed.mc.player.setSprinting(true);
                    Speed.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Speed.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                }
                double adjustedSpeed = (Double)this.speed.getValue() * 0.99;
                switch (this.strafeStage) {
                    case 0: {
                        ++this.strafeStage;
                        this.ncpPrevMotion = 0.0;
                        break;
                    }
                    case 2: {
                        double vertical;
                        double d = vertical = this.hypixel.getValue() != false ? 0.3999999463558197 : 0.40123128;
                        if (Speed.mc.player.moveForward == 0.0f && Speed.mc.player.moveStrafing == 0.0f || !Speed.mc.player.onGround) break;
                        Speed.mc.player.motionY = vertical;
                        event.setY(Speed.mc.player.motionY);
                        this.horizontal *= 2.149;
                        break;
                    }
                    case 3: {
                        this.horizontal = this.ncpPrevMotion - 0.76 * (this.ncpPrevMotion - this.getBaseMotionSpeed());
                        break;
                    }
                    default: {
                        this.horizontal = this.ncpPrevMotion - this.ncpPrevMotion / 159.0;
                        if (Speed.mc.world.getCollisionBoxes((Entity)Speed.mc.player, Speed.mc.player.getEntityBoundingBox().offset(0.0, Speed.mc.player.motionY, 0.0)).size() <= 0 && !Speed.mc.player.collidedVertically || this.strafeStage <= 0) break;
                        this.strafeStage = Speed.mc.player.moveForward != 0.0f || Speed.mc.player.moveStrafing != 0.0f ? 1 : 0;
                    }
                }
                this.horizontal = Math.max(this.horizontal, this.getBaseMotionSpeed());
                if (this.maxVelocity > 0.0 && this.boost.getValue().booleanValue() && !this.velocityTimer.hasPassed(75.0) && !Speed.mc.player.collidedHorizontally) {
                    this.horizontal = Math.max(this.horizontal, this.maxVelocity);
                }
                float forward = Speed.mc.player.movementInput.moveForward;
                float strafe = Speed.mc.player.movementInput.moveStrafe;
                if (forward == 0.0f && strafe == 0.0f) {
                    event.setX(0.0);
                    event.setZ(0.0);
                } else if ((double)forward != 0.0 && (double)strafe != 0.0) {
                    forward = (float)((double)forward * Math.sin(0.7853981633974483));
                    strafe = (float)((double)strafe * Math.cos(0.7853981633974483));
                }
                event.setX(((double)forward * this.horizontal * -Math.sin(Math.toRadians(Speed.mc.player.rotationYaw)) + (double)strafe * this.horizontal * Math.cos(Math.toRadians(Speed.mc.player.rotationYaw))) * adjustedSpeed);
                event.setZ(((double)forward * this.horizontal * Math.cos(Math.toRadians(Speed.mc.player.rotationYaw)) - (double)strafe * this.horizontal * -Math.sin(Math.toRadians(Speed.mc.player.rotationYaw))) * adjustedSpeed);
                ++this.strafeStage;
                break;
            }
            case STRAFESTRICT: {
                ++this.aacCounter;
                this.aacCounter %= 5;
                if (this.aacCounter != 0) {
                    Zenith.INSTANCE.timerManager.resetTimer(this);
                } else if (PlayerUtils.isPlayerMoving()) {
                    Zenith.INSTANCE.timerManager.updateTimer(this, 10, 1.3f);
                    Speed.mc.player.motionX *= (double)1.02f;
                    Speed.mc.player.motionZ *= (double)1.02f;
                }
                if (Speed.mc.player.onGround && PlayerUtils.isPlayerMoving()) {
                    this.aacState = 2;
                }
                if (this.round(Speed.mc.player.posY - (double)((int)Speed.mc.player.posY), 3) == this.round(0.138, 3)) {
                    Speed.mc.player.motionY -= 0.08;
                    event.setY(event.getY() - 0.09316090325960147);
                    Speed.mc.player.posY -= 0.09316090325960147;
                }
                if (this.aacState == 1 && (Speed.mc.player.moveForward != 0.0f || Speed.mc.player.moveStrafing != 0.0f)) {
                    this.aacState = 2;
                    this.aacSpeed = 1.38 * this.getBaseMotionSpeed() - 0.01;
                } else if (this.aacState == 2) {
                    this.aacState = 3;
                    Speed.mc.player.motionY = 0.3994f;
                    event.setY(0.3994f);
                    this.aacSpeed *= 2.149;
                } else if (this.aacState == 3) {
                    this.aacState = 4;
                    double adjustedMotion = 0.66 * (this.prevMotion - this.getBaseMotionSpeed());
                    this.aacSpeed = this.prevMotion - adjustedMotion;
                } else {
                    if (Speed.mc.world.getCollisionBoxes((Entity)Speed.mc.player, Speed.mc.player.getEntityBoundingBox().offset(0.0, Speed.mc.player.motionY, 0.0)).size() > 0 || Speed.mc.player.collidedVertically) {
                        this.aacState = 1;
                    }
                    this.aacSpeed = this.prevMotion - this.prevMotion / 159.0;
                }
                this.aacSpeed = Math.max(this.aacSpeed, this.getBaseMotionSpeed());
                this.aacSpeed = this.maxVelocity > 0.0 && this.boost.getValue() != false && !this.velocityTimer.hasPassed(75.0) && !Speed.mc.player.collidedHorizontally ? Math.max(this.aacSpeed, this.maxVelocity) : Math.min(this.aacSpeed, this.ticksPassed > 25 ? 0.449 : 0.433);
                float forward = Speed.mc.player.movementInput.moveForward;
                float strafe = Speed.mc.player.movementInput.moveStrafe;
                float yaw = Speed.mc.player.rotationYaw;
                ++this.ticksPassed;
                if (this.ticksPassed > 50) {
                    this.ticksPassed = 0;
                }
                if (forward == 0.0f && strafe == 0.0f) {
                    event.setX(0.0);
                    event.setZ(0.0);
                } else if (forward != 0.0f) {
                    if (strafe >= 1.0f) {
                        yaw += (float)(forward > 0.0f ? -45 : 45);
                        strafe = 0.0f;
                    } else if (strafe <= -1.0f) {
                        yaw += (float)(forward > 0.0f ? 45 : -45);
                        strafe = 0.0f;
                    }
                    if (forward > 0.0f) {
                        forward = 1.0f;
                    } else if (forward < 0.0f) {
                        forward = -1.0f;
                    }
                }
                double cos = Math.cos(Math.toRadians(yaw + 90.0f));
                double sin = Math.sin(Math.toRadians(yaw + 90.0f));
                event.setX((double)forward * this.aacSpeed * cos + (double)strafe * this.aacSpeed * sin);
                event.setZ((double)forward * this.aacSpeed * sin - (double)strafe * this.aacSpeed * cos);
                if (forward != 0.0f || strafe != 0.0f) break;
                event.setX(0.0);
                event.setZ(0.0);
                break;
            }
            case LOWHOP: {
                if (!this.setbackTimer.hasPassed(100.0)) {
                    return;
                }
                double jumpSpeed = 0.0;
                if (Speed.mc.player.isPotionActive(MobEffects.JUMP_BOOST)) {
                    jumpSpeed += (double)((float)(Speed.mc.player.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1f);
                }
                if (this.round(Speed.mc.player.posY - (double)((int)Speed.mc.player.posY), 3) == this.round(0.4, 3)) {
                    Speed.mc.player.motionY = 0.31 + jumpSpeed;
                    event.setY(Speed.mc.player.motionY);
                } else if (this.round(Speed.mc.player.posY - (double)((int)Speed.mc.player.posY), 3) == this.round(0.71, 3)) {
                    Speed.mc.player.motionY = 0.04 + jumpSpeed;
                    event.setY(Speed.mc.player.motionY);
                } else if (this.round(Speed.mc.player.posY - (double)((int)Speed.mc.player.posY), 3) == this.round(0.75, 3)) {
                    Speed.mc.player.motionY = -0.2 - jumpSpeed;
                    event.setY(Speed.mc.player.motionY);
                } else if (this.round(Speed.mc.player.posY - (double)((int)Speed.mc.player.posY), 3) == this.round(0.55, 3)) {
                    Speed.mc.player.motionY = -0.14 + jumpSpeed;
                    event.setY(Speed.mc.player.motionY);
                } else if (this.round(Speed.mc.player.posY - (double)((int)Speed.mc.player.posY), 3) == this.round(0.41, 3)) {
                    Speed.mc.player.motionY = -0.2 + jumpSpeed;
                    event.setY(Speed.mc.player.motionY);
                }
                if (this.lowHopStage == 1 && (Speed.mc.player.moveForward != 0.0f || Speed.mc.player.moveStrafing != 0.0f)) {
                    this.lowHopSpeed = 1.35 * this.getBaseMotionSpeed() - 0.01;
                } else if (this.lowHopStage == 2 && (Speed.mc.player.moveForward != 0.0f || Speed.mc.player.moveStrafing != 0.0f)) {
                    Speed.mc.player.motionY = (this.checkHeadspace() ? 0.2 : 0.3999) + jumpSpeed;
                    event.setY(Speed.mc.player.motionY);
                    this.lowHopSpeed *= this.even ? 1.5685 : 1.3445;
                } else if (this.lowHopStage == 3) {
                    double dV = 0.66 * (this.prevMotion - this.getBaseMotionSpeed());
                    this.lowHopSpeed = this.prevMotion - dV;
                    this.even = !this.even;
                } else {
                    if (Speed.mc.player.onGround && this.lowHopStage > 0) {
                        this.lowHopStage = Speed.mc.player.moveForward != 0.0f || Speed.mc.player.moveStrafing != 0.0f ? 1 : 0;
                    }
                    this.lowHopSpeed = this.prevMotion - this.prevMotion / 159.0;
                }
                this.lowHopSpeed = Math.max(this.lowHopSpeed, this.getBaseMotionSpeed());
                float forward = Speed.mc.player.movementInput.moveForward;
                float strafe = Speed.mc.player.movementInput.moveStrafe;
                if (forward == 0.0f && strafe == 0.0f) {
                    event.setX(0.0);
                    event.setZ(0.0);
                } else if ((double)forward != 0.0 && (double)strafe != 0.0) {
                    forward = (float)((double)forward * Math.sin(0.7853981633974483));
                    strafe = (float)((double)strafe * Math.cos(0.7853981633974483));
                }
                event.setX((double)forward * this.lowHopSpeed * -Math.sin(Math.toRadians(Speed.mc.player.rotationYaw)) + (double)strafe * this.lowHopSpeed * Math.cos(Math.toRadians(Speed.mc.player.rotationYaw)));
                event.setZ((double)forward * this.lowHopSpeed * Math.cos(Math.toRadians(Speed.mc.player.rotationYaw)) - (double)strafe * this.lowHopSpeed * -Math.sin(Math.toRadians(Speed.mc.player.rotationYaw)));
                if (Speed.mc.player.moveForward == 0.0f && Speed.mc.player.moveStrafing == 0.0f) {
                    return;
                }
                ++this.lowHopStage;
                break;
            }
            case ONGROUND: {
                if (!Speed.mc.player.onGround && this.onGroundStage != 3) {
                    return;
                }
                if (!Speed.mc.player.collidedHorizontally && Speed.mc.player.moveForward != 0.0f || Speed.mc.player.moveStrafing != 0.0f) {
                    if (this.onGroundStage == 2) {
                        Speed.mc.player.motionY = -0.5;
                        event.setY(this.checkHeadspace() ? 0.2 : 0.4);
                        this.onGroundSpeed *= 2.149;
                        this.onGroundStage = 3;
                        if (this.bypass.getValue().booleanValue()) {
                            this.forceGround = true;
                        }
                    } else if (this.onGroundStage == 3) {
                        double adjustedSpeed = 0.66 * (this.prevMotion - this.getBaseMotionSpeed());
                        this.onGroundSpeed = this.prevMotion - adjustedSpeed;
                        this.onGroundStage = 2;
                    } else if (this.checkHeadspace() || Speed.mc.player.collidedVertically) {
                        this.onGroundStage = 1;
                    }
                }
                this.onGroundSpeed = Math.min(Math.max(this.onGroundSpeed, this.getBaseMotionSpeed()), (Double)this.speed.getValue());
                float forward = Speed.mc.player.movementInput.moveForward;
                float strafe = Speed.mc.player.movementInput.moveStrafe;
                if (forward == 0.0f && strafe == 0.0f) {
                    event.setX(0.0);
                    event.setZ(0.0);
                } else if ((double)forward != 0.0 && (double)strafe != 0.0) {
                    forward = (float)((double)forward * Math.sin(0.7853981633974483));
                    strafe = (float)((double)strafe * Math.cos(0.7853981633974483));
                }
                event.setX((double)forward * this.onGroundSpeed * -Math.sin(Math.toRadians(Speed.mc.player.rotationYaw)) + (double)strafe * this.onGroundSpeed * Math.cos(Math.toRadians(Speed.mc.player.rotationYaw)));
                event.setZ((double)forward * this.onGroundSpeed * Math.cos(Math.toRadians(Speed.mc.player.rotationYaw)) - (double)strafe * this.onGroundSpeed * -Math.sin(Math.toRadians(Speed.mc.player.rotationYaw)));
                break;
            }
        }
    }

    private boolean checkHeadspace() {
        return Speed.mc.world.getCollisionBoxes((Entity)Speed.mc.player, Speed.mc.player.getEntityBoundingBox().offset(0.0, 0.21, 0.0)).size() > 0;
    }

    @Override
    public void onEnable() {
        if (Speed.mc.player == null || Speed.mc.world == null) {
            this.toggle();
            return;
        }
        this.maxVelocity = 0.0;
        this.hopStage = 1;
        this.lowHopStage = 4;
        this.onGroundStage = 2;
        switch (this.getMode()) {
            case STRAFEOLD: {
                if (!Speed.mc.player.isSprinting() && PlayerUtils.isPlayerMoving()) {
                    Speed.mc.player.setSprinting(true);
                    Speed.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Speed.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                }
            }
            case STRAFE: {
                this.state = 4;
                this.currentSpeed = this.getBaseMotionSpeed();
                this.prevMotion = 0.0;
            }
        }
    }

    @Override
    public void onDisable() {
        if (Speed.mc.player == null || Speed.mc.world == null) {
            return;
        }
        if (this.getMode() == Mode.SMALLHOP) {
            Speed.mc.player.setVelocity(0.0, 0.0, 0.0);
        }
        Zenith.INSTANCE.timerManager.resetTimer(this);
    }

    private double getBaseMotionSpeed() {
        double baseSpeed;
        double d = baseSpeed = this.getMode() == Mode.STRAFE || this.getMode() == Mode.STRAFESTRICT || this.getMode() == Mode.SMALLHOP || this.getMode() == Mode.ONGROUND || this.getMode() == Mode.LOWHOP ? 0.2873 : 0.272;
        if (Speed.mc.player.isPotionActive(MobEffects.SPEED)) {
            int amplifier = Speed.mc.player.getActivePotionEffect(MobEffects.SPEED).getAmplifier();
            baseSpeed *= 1.0 + 0.2 * ((double)amplifier + 1.0);
        }
        return baseSpeed;
    }

    private double round(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static enum Mode {
        STRAFE,
        STRAFESTRICT,
        ONGROUND,
        LOWHOP,
        SMALLHOP,
        TP,
        STRAFEOLD;

    }
}

