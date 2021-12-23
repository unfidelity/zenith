//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.Combat;

import com.google.common.collect.Lists;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.StreamSupport;
import me.gopro336.zenith.api.util.combat.CrystalUtils;
import me.gopro336.zenith.api.util.newRender.RenderUtils3D;
import me.gopro336.zenith.api.util.newRotations.Placement;
import me.gopro336.zenith.api.util.newRotations.Rotation;
import me.gopro336.zenith.asm.mixin.mixins.accessor.IEntityPlayerSP;
import me.gopro336.zenith.event.client.BlockUtils;
import me.gopro336.zenith.event.network.PacketReceiveEvent;
import me.gopro336.zenith.event.player.UpdateWalkingPlayerEvent;
import me.gopro336.zenith.event.render.Render3DEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.FeatureManager;
import me.gopro336.zenith.feature.toggleable.exploit.PacketFly;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import net.minecraft.block.BlockWeb;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import team.stiff.pomelo.handler.ListenerPriority;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="HoleFill", category=Category.COMBAT)
public class HoleFill
extends Feature {
    private Property<Boolean> rotate = new Property<Boolean>(this, "Rotate", "", true);
    private Property<Boolean> swing = new Property<Boolean>(this, "Swing", "", true);
    private NumberProperty<Double> rangeXZ = new NumberProperty<Double>((Feature)this, "Range", "", 5.0, Double.valueOf(1.0), 6.0);
    private Property<Boolean> strictDirection = new Property<Boolean>(this, "StrictDirection", "", false);
    private Property<Integer> actionShift = new NumberProperty<Integer>((Feature)this, "ActionShift", "", 1, Integer.valueOf(1), 3);
    private NumberProperty<Integer> actionInterval = new NumberProperty<Integer>((Feature)this, this.actionShift, "ActionInterval", "", Integer.valueOf(0), Integer.valueOf(0), 5);
    private Property<Boolean> rayTrace = new Property<Boolean>(this, "RayTrace", "", false);
    private Property<Boolean> doubleHoles = new Property<Boolean>(this, "Double", "", false);
    private Property<Boolean> jumpDisable = new Property<Boolean>(this, "JumpDisable", "", false);
    private Property<Boolean> onlyWebs = new Property<Boolean>(this, "OnlyWebs", "", false);
    private Property<SmartMode> smartMode = new Property<SmartMode>(this, "Smart", "", SmartMode.ALWAYS);
    private NumberProperty<Double> targetRange = new NumberProperty<Double>((Feature)this, "EnemyRange", "", 10.0, Double.valueOf(1.0), 15.0);
    private Property<Boolean> disableWhenNone = new Property<Boolean>(this, "DisableWhenNone", "", false);
    private Property<Boolean> render = new Property<Boolean>(this, "Render", "", false);
    private Property<Color> renderColor = new Property<Color>(this, "RenderColor", "", new Color(229, 11, 137, 181));
    private Map<BlockPos, Long> renderBlocks = new ConcurrentHashMap<BlockPos, Long>();
    private Placement placement = null;
    private int itemSlot;
    ArrayList<BlockPos> blocks;
    private Map<BlockPos, Long> placedBlocks = new ConcurrentHashMap<BlockPos, Long>();
    private int tickCounter = 0;

    @Override
    public void onEnable() {
        if (HoleFill.mc.player == null || HoleFill.mc.world == null) {
            this.toggle();
            return;
        }
        this.blocks = Lists.newArrayList((Iterable)BlockPos.getAllInBox((BlockPos)HoleFill.mc.player.getPosition().add(-((Double)this.rangeXZ.getValue()).doubleValue(), -((Double)this.rangeXZ.getValue()).doubleValue(), -((Double)this.rangeXZ.getValue()).doubleValue()), (BlockPos)HoleFill.mc.player.getPosition().add(((Double)this.rangeXZ.getValue()).doubleValue(), ((Double)this.rangeXZ.getValue()).doubleValue(), ((Double)this.rangeXZ.getValue()).doubleValue())));
        this.tickCounter = (Integer)this.actionInterval.getValue();
    }

    @Listener(priority=ListenerPriority.HIGHER)
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent.Pre event) {
        this.placement = null;
        if (this.jumpDisable.getValue().booleanValue() && HoleFill.mc.player.prevPosY < HoleFill.mc.player.posY) {
            this.toggle();
        }
        if (FeatureManager.getFeatureByClass(PacketFly.class).isEnabled()) {
            return;
        }
        if (this.tickCounter < (Integer)this.actionInterval.getValue()) {
            ++this.tickCounter;
        }
        if (this.tickCounter < (Integer)this.actionInterval.getValue()) {
            return;
        }
        int slot = this.getBlockSlot();
        this.itemSlot = -1;
        if (slot == -1) {
            return;
        }
        this.blocks = Lists.newArrayList((Iterable)BlockPos.getAllInBox((BlockPos)HoleFill.mc.player.getPosition().add(-((Double)this.rangeXZ.getValue()).doubleValue(), -((Double)this.rangeXZ.getValue()).doubleValue(), -((Double)this.rangeXZ.getValue()).doubleValue()), (BlockPos)HoleFill.mc.player.getPosition().add(((Double)this.rangeXZ.getValue()).doubleValue(), ((Double)this.rangeXZ.getValue()).doubleValue(), ((Double)this.rangeXZ.getValue()).doubleValue())));
        int ping = CrystalUtils.ping();
        this.placedBlocks.forEach((pos, time) -> {
            if (System.currentTimeMillis() - time > (long)(ping + 100)) {
                this.placedBlocks.remove(pos);
            }
        });
        if (this.smartMode.getValue() == SmartMode.TARGET && this.getNearestTarget() == null) {
            return;
        }
        BlockPos pos2 = StreamSupport.stream(this.blocks.spliterator(), false).filter(this::isHole).filter(p -> HoleFill.mc.player.getDistance((double)p.getX() + 0.5, (double)p.getY() + 0.5, (double)p.getZ() + 0.5) <= (Double)this.rangeXZ.getValue()).filter(p -> Rotation.canPlaceBlock(p, this.strictDirection.getValue(), this.rayTrace.getValue(), true)).min(Comparator.comparing(e -> Float.valueOf(MathHelper.sqrt((double)HoleFill.mc.player.getDistanceSq(e))))).orElse(null);
        if (pos2 != null) {
            this.placement = Rotation.preparePlacement(pos2, this.rotate.getValue(), false, this.strictDirection.getValue(), this.rayTrace.getValue());
            if (this.placement != null) {
                this.tickCounter = 0;
                this.itemSlot = slot;
                this.renderBlocks.put(pos2, System.currentTimeMillis());
                this.placedBlocks.put(pos2, System.currentTimeMillis());
            }
        } else if (this.disableWhenNone.getValue().booleanValue()) {
            this.toggle();
        }
    }

    @Listener(priority=ListenerPriority.HIGH)
    public void onUpdateWalkingPlayerPost(UpdateWalkingPlayerEvent.Post event) {
        if (this.placement != null && this.itemSlot != -1) {
            boolean changeItem = HoleFill.mc.player.inventory.currentItem != this.itemSlot;
            int startingItem = HoleFill.mc.player.inventory.currentItem;
            if (changeItem) {
                HoleFill.mc.player.inventory.currentItem = this.itemSlot;
                HoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.itemSlot));
            }
            boolean isSprinting = HoleFill.mc.player.isSprinting();
            boolean shouldSneak = BlockUtils.shouldSneakWhileRightClicking(this.placement.getNeighbour());
            if (isSprinting) {
                HoleFill.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)HoleFill.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
            }
            if (shouldSneak) {
                HoleFill.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)HoleFill.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            }
            Vec3d hitVec = new Vec3d((Vec3i)this.placement.getNeighbour()).add(0.5, 0.5, 0.5).add(new Vec3d(this.placement.getOpposite().getDirectionVec()).scale(0.5));
            Rotation.rightClickBlock(this.placement.getNeighbour(), hitVec, EnumHand.MAIN_HAND, this.placement.getOpposite(), true, this.swing.getValue());
            double dX = HoleFill.mc.player.posX - ((IEntityPlayerSP)HoleFill.mc.player).getLastReportedPosX();
            double dY = HoleFill.mc.player.posY - ((IEntityPlayerSP)HoleFill.mc.player).getLastReportedPosY();
            double dZ = HoleFill.mc.player.posZ - ((IEntityPlayerSP)HoleFill.mc.player).getLastReportedPosZ();
            boolean positionChanged = dX * dX + dY * dY + dZ * dZ > 9.0E-4;
            for (int extraBlocks = 0; extraBlocks < this.actionShift.getValue() - 1 && !positionChanged; ++extraBlocks) {
                Placement nextPlacement;
                EntityPlayer nearestTarget = this.getNearestTarget();
                BlockPos pos = StreamSupport.stream(this.blocks.spliterator(), false).filter(this::isHole).min(Comparator.comparing(e -> Float.valueOf(this.smartMode.getValue() != SmartMode.NONE && nearestTarget != null ? MathHelper.sqrt((double)HoleFill.mc.player.getDistanceSq((Entity)nearestTarget)) : MathHelper.sqrt((double)HoleFill.mc.player.getDistanceSq(e))))).orElse(null);
                if (pos == null || !Rotation.canPlaceBlock(pos, this.strictDirection.getValue()) || (nextPlacement = Rotation.preparePlacement(pos, this.rotate.getValue(), true, this.strictDirection.getValue())) == null) break;
                Vec3d nextHitVec = new Vec3d((Vec3i)nextPlacement.getNeighbour()).add(0.5, 0.5, 0.5).add(new Vec3d(nextPlacement.getOpposite().getDirectionVec()).scale(0.5));
                Rotation.rightClickBlock(nextPlacement.getNeighbour(), nextHitVec, EnumHand.MAIN_HAND, nextPlacement.getOpposite(), true, this.swing.getValue());
                this.placedBlocks.put(pos, System.currentTimeMillis());
                this.renderBlocks.put(pos, System.currentTimeMillis());
            }
            if (shouldSneak) {
                HoleFill.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)HoleFill.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            }
            if (isSprinting) {
                HoleFill.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)HoleFill.mc.player, CPacketEntityAction.Action.START_SPRINTING));
            }
            if (changeItem) {
                HoleFill.mc.player.inventory.currentItem = startingItem;
                HoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(startingItem));
            }
        }
    }

    @Listener
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacket() instanceof SPacketBlockChange && this.renderBlocks.containsKey(((SPacketBlockChange)event.getPacket()).getBlockPosition()) && ((SPacketBlockChange)event.getPacket()).getBlockState().getBlock() != Blocks.AIR) {
            this.renderBlocks.remove(((SPacketBlockChange)event.getPacket()).getBlockPosition());
        }
    }

    @Listener
    public void onRender(Render3DEvent event) {
        if (HoleFill.mc.world == null || HoleFill.mc.player == null) {
            return;
        }
        this.renderBlocks.forEach((pos, time) -> {
            if (System.currentTimeMillis() - time > 1000L) {
                this.renderBlocks.remove(pos);
            } else {
                if (!this.render.getValue().booleanValue()) {
                    return;
                }
                RenderUtils3D.draw(pos, true, true, 0.0, 0.0, this.renderColor.getValue());
            }
        });
    }

    private boolean isValidItem(Item item) {
        if (item instanceof ItemBlock) {
            if (this.onlyWebs.getValue().booleanValue()) {
                return ((ItemBlock)item).getBlock() instanceof BlockWeb;
            }
            return true;
        }
        return false;
    }

    private int getBlockSlot() {
        ItemStack stack = HoleFill.mc.player.getHeldItemMainhand();
        if (!stack.isEmpty() && this.isValidItem(stack.getItem())) {
            return HoleFill.mc.player.inventory.currentItem;
        }
        for (int i = 0; i < 9; ++i) {
            stack = HoleFill.mc.player.inventory.getStackInSlot(i);
            if (stack.isEmpty() || !this.isValidItem(stack.getItem())) continue;
            return i;
        }
        return -1;
    }

    private boolean isHole(BlockPos pos) {
        if (this.placedBlocks.containsKey(pos)) {
            return false;
        }
        for (Entity entity : HoleFill.mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(pos))) {
            if (entity instanceof EntityItem || entity instanceof EntityXPOrb || entity instanceof EntityArrow) continue;
            return false;
        }
        if (this.doubleHoles.getValue().booleanValue()) {
            BlockPos twoPos = BlockUtils.validTwoBlockBedrockXZ(pos);
            if (twoPos == null) {
                twoPos = BlockUtils.validTwoBlockObiXZ(pos);
            }
            if (twoPos != null) {
                return true;
            }
        }
        return BlockUtils.isHole(pos);
    }

    private EntityPlayer getNearestTarget() {
        return HoleFill.mc.world.playerEntities.stream().filter(e -> e != HoleFill.mc.player).filter(e -> (double)HoleFill.mc.player.getDistance((Entity)e) < (Double)this.targetRange.getValue()).min(Comparator.comparing(e -> Float.valueOf(HoleFill.mc.player.getDistance((Entity)e)))).orElse(null);
    }

    private static enum SmartMode {
        NONE,
        ALWAYS,
        TARGET;

    }
}

