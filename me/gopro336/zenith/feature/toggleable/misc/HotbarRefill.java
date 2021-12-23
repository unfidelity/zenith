//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.misc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import me.gopro336.zenith.api.util.time.Timer;
import me.gopro336.zenith.event.world.UpdateEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="HotbarRefill", description="Automatically refills your hotbar", category=Category.MISC)
public class HotbarRefill
extends Feature {
    private Property<Boolean> itemSaver = new Property<Boolean>(this, "ItemSaver", "", false);
    public NumberProperty<Integer> refillThreshold = new NumberProperty<Integer>((Feature)this, "RefillThreshold", "", 1, Integer.valueOf(36), 64);
    public NumberProperty<Integer> delay = new NumberProperty<Integer>((Feature)this, "Delay", "", 1, Integer.valueOf(1), 20);
    private Property<Boolean> crystals = new Property<Boolean>(this, "Crystals", "", true);
    private Property<Boolean> xp = new Property<Boolean>(this, "EXp", "", true);
    private Property<Boolean> food = new Property<Boolean>(this, "Food", "", true);
    private Property<Boolean> others = new Property<Boolean>(this, "Others", "", false);
    public ConcurrentHashMap<ItemStack, Integer> itemsToRefill = new ConcurrentHashMap();
    public static Timer moveTimer = new Timer();
    private int ticks = 0;

    @Listener
    public void onUpdate(UpdateEvent event) {
        if (HotbarRefill.mc.player == null || HotbarRefill.mc.world == null || HotbarRefill.mc.currentScreen instanceof GuiContainer || event.getPhase() == TickEvent.Phase.START) {
            return;
        }
        if (!moveTimer.hasPassed(350.0)) {
            return;
        }
        if (this.itemSaver.getValue().booleanValue()) {
            boolean itemSaved = false;
            EnumHand[] hands = EnumHand.values();
            block4: for (int i = 0; i < hands.length; ++i) {
                EnumHand hand = hands[i];
                ItemStack stack = HotbarRefill.mc.player.getHeldItem(hand);
                if (stack == null || stack.getItem() == null) continue;
                Item item = stack.getItem();
                if (!stack.isItemStackDamageable() || stack.getItemDamage() != item.getMaxDamage(stack)) continue;
                switch (hand) {
                    case MAIN_HAND: {
                        HotbarRefill.mc.playerController.windowClick(HotbarRefill.mc.player.inventoryContainer.windowId, HotbarRefill.mc.player.inventory.currentItem + 36, 0, ClickType.QUICK_MOVE, (EntityPlayer)HotbarRefill.mc.player);
                        itemSaved = true;
                        continue block4;
                    }
                    case OFF_HAND: {
                        HotbarRefill.mc.playerController.windowClick(HotbarRefill.mc.player.inventoryContainer.windowId, 45, 1, ClickType.QUICK_MOVE, (EntityPlayer)HotbarRefill.mc.player);
                        itemSaved = true;
                    }
                }
            }
            if (itemSaved) {
                this.ticks = 0;
                return;
            }
        }
        if (this.ticks > (Integer)this.delay.getValue() * 2) {
            if (!HotbarRefill.mc.player.inventory.getItemStack().isEmpty()) {
                for (int index = 44; index >= 9; --index) {
                    if (!HotbarRefill.mc.player.inventoryContainer.getSlot(index).getStack().isEmpty()) continue;
                    HotbarRefill.mc.playerController.windowClick(0, index, 0, ClickType.PICKUP, (EntityPlayer)HotbarRefill.mc.player);
                    return;
                }
            }
            this.findItemsToRefill();
            this.refillItems();
            this.ticks = 0;
        } else {
            ++this.ticks;
        }
    }

    private void findItemsToRefill() {
        for (int i = 0; i <= 9; ++i) {
            ItemStack stack = HotbarRefill.mc.player.inventory.getStackInSlot(i);
            if (stack.isEmpty() || stack.getItem() == Items.AIR || !stack.isStackable() || stack.getCount() >= stack.getMaxStackSize() || stack.getCount() >= (Integer)this.refillThreshold.getValue() || !(this.others.getValue() != false || stack.getItem() instanceof ItemEndCrystal && this.crystals.getValue() != false || stack.getItem() instanceof ItemFood && this.food.getValue() != false) && (!(stack.getItem() instanceof ItemExpBottle) || !this.xp.getValue().booleanValue())) continue;
            this.itemsToRefill.put(stack, i);
        }
    }

    private boolean isInventoryGood() {
        for (int i = 0; i < 36; ++i) {
            if (HotbarRefill.mc.player.inventoryContainer.getSlot(i).getHasStack()) continue;
            return true;
        }
        return false;
    }

    private void refillItems() {
        for (Map.Entry<ItemStack, Integer> entry : this.itemsToRefill.entrySet()) {
            ItemStack stack = entry.getKey();
            int slotToRefill = entry.getValue();
            if (HotbarRefill.mc.player.inventory.getSlotFor(stack) == -1) continue;
            int refillSlot = -1;
            for (int i = 9; i <= 35; ++i) {
                ItemStack refillStack = HotbarRefill.mc.player.inventory.getStackInSlot(i);
                if (!refillStack.getItem().equals(stack.getItem()) || !refillStack.getDisplayName().equals(stack.getDisplayName()) || refillStack.getItemDamage() != stack.getItemDamage()) continue;
                refillSlot = i;
                break;
            }
            if (refillSlot == -1) continue;
            HotbarRefill.mc.playerController.windowClick(0, refillSlot, 0, ClickType.PICKUP, (EntityPlayer)HotbarRefill.mc.player);
            HotbarRefill.mc.playerController.windowClick(0, slotToRefill < 9 ? slotToRefill + 36 : slotToRefill, 0, ClickType.PICKUP, (EntityPlayer)HotbarRefill.mc.player);
            HotbarRefill.mc.playerController.windowClick(0, refillSlot, 0, ClickType.PICKUP, (EntityPlayer)HotbarRefill.mc.player);
            this.itemsToRefill.remove(stack);
        }
    }
}

