//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util;

import java.util.ArrayList;
import java.util.Objects;
import me.gopro336.zenith.asm.mixin.mixins.accessor.IPlayerControllerMP;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;

public class InventoryUtils {
    public static final Minecraft mc = Minecraft.getMinecraft();
    private static boolean switching = false;

    public static void switchToSlot(int slot, Switch switchMode) {
        if (slot != -1 && InventoryUtils.mc.player.inventory.currentItem != slot) {
            switch (switchMode) {
                case NORMAL: {
                    InventoryUtils.mc.player.inventory.currentItem = slot;
                    InventoryUtils.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slot));
                    break;
                }
                case PACKET: {
                    InventoryUtils.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slot));
                }
            }
        }
        InventoryUtils.mc.playerController.updateController();
        ((IPlayerControllerMP)InventoryUtils.mc.playerController).syncCurrentPlayItem();
    }

    public static void switchToSlot(Item item, Switch switchMode) {
        if (InventoryUtils.getItemSlot(item, Inventory.HOTBAR, true) != -1 && InventoryUtils.mc.player.inventory.currentItem != InventoryUtils.getItemSlot(item, Inventory.HOTBAR, true)) {
            InventoryUtils.switchToSlot(InventoryUtils.getItemSlot(item, Inventory.HOTBAR, true), switchMode);
        }
        ((IPlayerControllerMP)InventoryUtils.mc.playerController).syncCurrentPlayItem();
    }

    public static void moveItemToOffhand(Item item, boolean hotbar) {
        InventoryUtils.moveItemToOffhand(InventoryUtils.getItemSlot(item, Inventory.INVENTORY, hotbar));
    }

    public static void moveItemToOffhand(int slot) {
        int returnSlot = -1;
        if (slot == -1) {
            return;
        }
        switching = true;
        InventoryUtils.mc.playerController.windowClick(0, slot < 9 ? slot + 36 : slot, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtils.mc.player);
        InventoryUtils.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtils.mc.player);
        for (int i = 9; i < 45; ++i) {
            if (!InventoryUtils.mc.player.inventory.getStackInSlot(i).isEmpty()) continue;
            returnSlot = i;
            break;
        }
        if (returnSlot != -1) {
            InventoryUtils.mc.playerController.windowClick(0, returnSlot, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtils.mc.player);
        }
        switching = false;
    }

    public static int getItemSlot(Item item, Inventory inventory, boolean hotbar) {
        switch (inventory) {
            case HOTBAR: {
                for (int i = 0; i < 9; ++i) {
                    if (InventoryUtils.mc.player.inventory.getStackInSlot(i).getItem() != item) continue;
                    return i;
                }
                break;
            }
            case INVENTORY: {
                int i;
                int n = i = hotbar ? 9 : 0;
                while (i < 45) {
                    if (InventoryUtils.mc.player.inventory.getStackInSlot(i).getItem() == item) {
                        return i;
                    }
                    ++i;
                }
                break;
            }
        }
        return -1;
    }

    public static int getBlockSlot(Block block, Inventory inventory, boolean hotbar) {
        switch (inventory) {
            case INVENTORY: {
                for (int i = 0; i < 9; ++i) {
                    Item item = InventoryUtils.mc.player.inventory.getStackInSlot(i).getItem();
                    if (!(item instanceof ItemBlock) || !((ItemBlock)item).getBlock().equals(block)) continue;
                    return i;
                }
                break;
            }
            case HOTBAR: {
                int i;
                int n = i = hotbar ? 9 : 0;
                while (i < 45) {
                    Item item = InventoryUtils.mc.player.inventory.getStackInSlot(i).getItem();
                    if (item instanceof ItemBlock && ((ItemBlock)item).getBlock().equals(block)) {
                        return i;
                    }
                    ++i;
                }
                break;
            }
        }
        return -1;
    }

    public static int getItemCount(Item item) {
        return new ArrayList(InventoryUtils.mc.player.inventory.mainInventory).stream().filter(itemStack -> itemStack.getItem().equals(item)).mapToInt(ItemStack::getCount).sum() + new ArrayList(InventoryUtils.mc.player.inventory.offHandInventory).stream().filter(itemStack -> itemStack.getItem().equals(item)).mapToInt(ItemStack::getCount).sum() + new ArrayList(InventoryUtils.mc.player.inventory.armorInventory).stream().filter(itemStack -> itemStack.getItem().equals(item)).mapToInt(ItemStack::getCount).sum();
    }

    public static int getBlockCount(Block block) {
        return new ArrayList(InventoryUtils.mc.player.inventory.mainInventory).stream().filter(itemStack -> itemStack.getItem().equals(Item.getItemFromBlock((Block)block))).mapToInt(ItemStack::getCount).sum() + new ArrayList(InventoryUtils.mc.player.inventory.offHandInventory).stream().filter(itemStack -> itemStack.getItem().equals(Item.getItemFromBlock((Block)block))).mapToInt(ItemStack::getCount).sum();
    }

    public static boolean isHolding32k() {
        for (int i = 0; i < InventoryUtils.mc.player.getHeldItemMainhand().getEnchantmentTagList().tagCount(); ++i) {
            InventoryUtils.mc.player.getHeldItemMainhand().getEnchantmentTagList().getCompoundTagAt(i);
            if (Enchantment.getEnchantmentByID((int)InventoryUtils.mc.player.getHeldItemMainhand().getEnchantmentTagList().getCompoundTagAt(i).getByte("id")) == null || Enchantment.getEnchantmentByID((int)InventoryUtils.mc.player.getHeldItemMainhand().getEnchantmentTagList().getCompoundTagAt(i).getShort("id")) == null || Objects.requireNonNull(Enchantment.getEnchantmentByID((int)InventoryUtils.mc.player.getHeldItemMainhand().getEnchantmentTagList().getCompoundTagAt(i).getShort("id"))).isCurse() || InventoryUtils.mc.player.getHeldItemMainhand().getEnchantmentTagList().getCompoundTagAt(i).getShort("lvl") < 1000) continue;
            return true;
        }
        return false;
    }

    public static boolean isHolding(Item item) {
        return InventoryUtils.mc.player.getHeldItemMainhand().getItem().equals(item) || InventoryUtils.mc.player.getHeldItemOffhand().getItem().equals(item);
    }

    public static boolean isSwitching() {
        return switching;
    }

    public static enum Inventory {
        INVENTORY,
        HOTBAR,
        CRAFTING;

    }

    public static enum Switch {
        NORMAL,
        PACKET,
        NONE;

    }
}

