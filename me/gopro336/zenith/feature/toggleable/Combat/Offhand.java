//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.Combat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import me.gopro336.zenith.api.util.combat.CrystalUtils;
import me.gopro336.zenith.api.util.time.Timer;
import me.gopro336.zenith.event.world.PreTickEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.toggleable.misc.HotbarRefill;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="Offhand", category=Category.COMBAT, description="Makes use of your offhand")
public class Offhand
extends Feature {
    public Property<Boolean> totem = new Property<Boolean>(this, "Totem", "", true);
    public Property<Boolean> gapple = new Property<Boolean>(this, "Gapple", "", true);
    public Property<Boolean> crystal = new Property<Boolean>(this, "Crystal", "", true);
    public NumberProperty<Float> delay = new NumberProperty<Float>((Feature)this, "Delay", "", Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(5.0f));
    public Property<Boolean> hotbarTotem = new Property<Boolean>(this, "HotbarTotem", "", false);
    public NumberProperty<Float> totemHealthThreshold = new NumberProperty<Float>((Feature)this, "TotemHealth", "", Float.valueOf(0.0f), Float.valueOf(5.0f), Float.valueOf(36.0f));
    public Property<Boolean> rightClick = new Property<Boolean>((Feature)this, "RightClickGap", "", true, v -> this.gapple.getValue());
    public Property<CrystalCheck> crystalCheck = new Property<CrystalCheck>(this, "CrystalCheck", "", CrystalCheck.DAMAGE);
    public NumberProperty<Float> crystalRange = new NumberProperty<Float>((Feature)this, "CrystalRange", "", Float.valueOf(1.0f), Float.valueOf(10.0f), Float.valueOf(15.0f), v -> this.crystalCheck.getValue() != CrystalCheck.NONE);
    public Property<Boolean> fallCheck = new Property<Boolean>(this, "FallCheck", "", true);
    public NumberProperty<Float> fallDist = new NumberProperty<Float>((Feature)this, "FallDist", "", Float.valueOf(0.0f), Float.valueOf(15.0f), Float.valueOf(50.0f), v -> this.fallCheck.getValue());
    public Property<Boolean> totemOnElytra = new Property<Boolean>(this, "TotemOnElytra", "", true);
    public Property<Boolean> extraSafe = new Property<Boolean>(this, "ExtraSafe", "", false);
    public Property<Boolean> clearAfter = new Property<Boolean>(this, "ClearAfter", "", true);
    public Property<Boolean> hard = new Property<Boolean>(this, "Hard", "", false);
    public Property<Boolean> notFromHotbar = new Property<Boolean>(this, "NotFromHotbar", "", true);
    public Property<Default> defaultItem = new Property<Default>(this, "DefaultItem", "", Default.TOTEM);
    private final Queue<Integer> clickQueue = new LinkedList<Integer>();
    private Timer timer = new Timer();

    @Listener
    public void onUpdate(PreTickEvent event) {
        if (Offhand.mc.player == null || Offhand.mc.world == null) {
            return;
        }
        if (!(Offhand.mc.currentScreen instanceof GuiContainer) && !(Offhand.mc.currentScreen instanceof GuiInventory)) {
            if (!this.clickQueue.isEmpty()) {
                if (!this.timer.hasPassed(((Float)this.delay.getValue()).floatValue() * 100.0f)) {
                    return;
                }
                int slot = this.clickQueue.poll();
                try {
                    HotbarRefill.moveTimer.reset();
                    this.timer.reset();
                    Offhand.mc.playerController.windowClick(Offhand.mc.player.inventoryContainer.windowId, slot, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else {
                if (!Offhand.mc.player.inventory.getItemStack().isEmpty()) {
                    for (int index = 44; index >= 9; --index) {
                        if (!Offhand.mc.player.inventoryContainer.getSlot(index).getStack().isEmpty()) continue;
                        Offhand.mc.playerController.windowClick(0, index, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
                        return;
                    }
                }
                if (this.totem.getValue().booleanValue()) {
                    if (Offhand.mc.player.getHealth() + Offhand.mc.player.getAbsorptionAmount() <= ((Float)this.totemHealthThreshold.getValue()).floatValue() || this.totemOnElytra.getValue() != false && Offhand.mc.player.isElytraFlying() || this.fallCheck.getValue().booleanValue() && Offhand.mc.player.fallDistance >= ((Float)this.fallDist.getValue()).floatValue() && !Offhand.mc.player.isElytraFlying()) {
                        this.putItemIntoOffhand(Items.TOTEM_OF_UNDYING);
                        return;
                    }
                    if (this.crystalCheck.getValue() == CrystalCheck.RANGE) {
                        EntityEnderCrystal crystal = Offhand.mc.world.loadedEntityList.stream().filter(e -> e instanceof EntityEnderCrystal && Offhand.mc.player.getDistance(e) <= ((Float)this.crystalRange.getValue()).floatValue()).min(Comparator.comparing(c -> Float.valueOf(Offhand.mc.player.getDistance(c)))).orElse(null);
                        if (crystal != null) {
                            this.putItemIntoOffhand(Items.TOTEM_OF_UNDYING);
                            return;
                        }
                    } else if (this.crystalCheck.getValue() == CrystalCheck.DAMAGE) {
                        float damage = 0.0f;
                        List crystalsInRange = Offhand.mc.world.loadedEntityList.stream().filter(e -> e instanceof EntityEnderCrystal).filter(e -> Offhand.mc.player.getDistance(e) <= ((Float)this.crystalRange.getValue()).floatValue()).collect(Collectors.toList());
                        for (Entity entity : crystalsInRange) {
                            damage += CrystalUtils.calculateDamage((EntityEnderCrystal)entity, (Entity)Offhand.mc.player);
                        }
                        if (Offhand.mc.player.getHealth() + Offhand.mc.player.getAbsorptionAmount() - damage <= ((Float)this.totemHealthThreshold.getValue()).floatValue()) {
                            this.putItemIntoOffhand(Items.TOTEM_OF_UNDYING);
                            return;
                        }
                    }
                    if (this.extraSafe.getValue().booleanValue() && this.crystalCheck()) {
                        this.putItemIntoOffhand(Items.TOTEM_OF_UNDYING);
                        return;
                    }
                }
                if (this.gapple.getValue().booleanValue() && this.isSword(Offhand.mc.player.getHeldItemMainhand().getItem())) {
                    if (this.rightClick.getValue().booleanValue() && !Offhand.mc.gameSettings.keyBindUseItem.isKeyDown()) {
                        if (this.clearAfter.getValue().booleanValue()) {
                            this.putItemIntoOffhand(this.defaultItem.getValue().item);
                        }
                        return;
                    }
                    this.putItemIntoOffhand(Items.GOLDEN_APPLE);
                    return;
                }
                if (this.crystal.getValue().booleanValue() && this.clearAfter.getValue().booleanValue()) {
                    this.putItemIntoOffhand(this.defaultItem.getValue().item);
                    return;
                }
                if (this.hard.getValue().booleanValue()) {
                    this.putItemIntoOffhand(this.defaultItem.getValue().item);
                }
            }
        }
    }

    private boolean isSword(Item item) {
        return item == Items.DIAMOND_SWORD || item == Items.IRON_SWORD || item == Items.GOLDEN_SWORD || item == Items.STONE_SWORD || item == Items.WOODEN_SWORD;
    }

    private int findItemSlot(Item item) {
        int i;
        int itemSlot = -1;
        int n = i = this.notFromHotbar.getValue() != false ? 9 : 0;
        while (i < 36) {
            ItemStack stack = Offhand.mc.player.inventory.getStackInSlot(i);
            if (stack != null && stack.getItem() == item) {
                itemSlot = i;
                break;
            }
            ++i;
        }
        return itemSlot;
    }

    private void putItemIntoOffhand(Item item) {
        if (Offhand.mc.player.getHeldItemOffhand().getItem() == item) {
            return;
        }
        int slot = this.findItemSlot(item);
        if (this.hotbarTotem.getValue().booleanValue() && item == Items.TOTEM_OF_UNDYING) {
            for (int i = 0; i < 9; ++i) {
                ItemStack stack = (ItemStack)Offhand.mc.player.inventory.mainInventory.get(i);
                if (stack.getItem() != Items.TOTEM_OF_UNDYING) continue;
                if (Offhand.mc.player.inventory.currentItem != i) {
                    Offhand.mc.player.inventory.currentItem = i;
                }
                return;
            }
        }
        if (slot != -1) {
            if (((Float)this.delay.getValue()).floatValue() > 0.0f) {
                if (this.timer.hasPassed(((Float)this.delay.getValue()).floatValue() * 100.0f)) {
                    Offhand.mc.playerController.windowClick(Offhand.mc.player.inventoryContainer.windowId, slot < 9 ? slot + 36 : slot, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
                    this.timer.reset();
                } else {
                    this.clickQueue.add(slot < 9 ? slot + 36 : slot);
                }
                this.clickQueue.add(45);
                this.clickQueue.add(slot < 9 ? slot + 36 : slot);
            } else {
                this.timer.reset();
                HotbarRefill.moveTimer.reset();
                Offhand.mc.playerController.windowClick(Offhand.mc.player.inventoryContainer.windowId, slot < 9 ? slot + 36 : slot, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
                try {
                    Offhand.mc.playerController.windowClick(Offhand.mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                Offhand.mc.playerController.windowClick(Offhand.mc.player.inventoryContainer.windowId, slot < 9 ? slot + 36 : slot, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
            }
        }
    }

    private boolean crystalCheck() {
        float cumDmg = 0.0f;
        ArrayList<Float> damageValues = new ArrayList<Float>();
        damageValues.add(Float.valueOf(this.calculateDamageAABB(Offhand.mc.player.getPosition().add(1, 0, 0))));
        damageValues.add(Float.valueOf(this.calculateDamageAABB(Offhand.mc.player.getPosition().add(-1, 0, 0))));
        damageValues.add(Float.valueOf(this.calculateDamageAABB(Offhand.mc.player.getPosition().add(0, 0, 1))));
        damageValues.add(Float.valueOf(this.calculateDamageAABB(Offhand.mc.player.getPosition().add(0, 0, -1))));
        damageValues.add(Float.valueOf(this.calculateDamageAABB(Offhand.mc.player.getPosition())));
        Iterator iterator2 = damageValues.iterator();
        while (iterator2.hasNext()) {
            float damage = ((Float)iterator2.next()).floatValue();
            cumDmg += damage;
            if (!(Offhand.mc.player.getHealth() + Offhand.mc.player.getAbsorptionAmount() - damage <= ((Float)this.totemHealthThreshold.getValue()).floatValue())) continue;
            return true;
        }
        return Offhand.mc.player.getHealth() + Offhand.mc.player.getAbsorptionAmount() - cumDmg <= ((Float)this.totemHealthThreshold.getValue()).floatValue();
    }

    private float calculateDamageAABB(BlockPos pos) {
        List crystalsInAABB = Offhand.mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(pos)).stream().filter(e -> e instanceof EntityEnderCrystal).collect(Collectors.toList());
        float totalDamage = 0.0f;
        for (Entity crystal : crystalsInAABB) {
            totalDamage += CrystalUtils.calculateDamage(crystal.posX, crystal.posY, crystal.posZ, (Entity)Offhand.mc.player);
        }
        return totalDamage;
    }

    private static enum Default {
        TOTEM(Items.TOTEM_OF_UNDYING),
        CRYSTAL(Items.END_CRYSTAL),
        GAPPLE(Items.GOLDEN_APPLE),
        AIR(Items.AIR);

        public Item item;

        private Default(Item item) {
            this.item = item;
        }
    }

    private static enum CrystalCheck {
        NONE,
        DAMAGE,
        RANGE;

    }
}

