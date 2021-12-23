//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.util.combat;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.toggleable.Combat.AutoCrystal;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CrystalUtils {
    public static Minecraft mc = Minecraft.getMinecraft();
    private static List<Block> valid = Arrays.asList(Blocks.OBSIDIAN, Blocks.BEDROCK, Blocks.ENDER_CHEST, Blocks.ANVIL);

    public static boolean rayTraceBreak(double x, double y, double z) {
        if (CrystalUtils.mc.world.rayTraceBlocks(new Vec3d(CrystalUtils.mc.player.posX, CrystalUtils.mc.player.posY + (double)CrystalUtils.mc.player.getEyeHeight(), CrystalUtils.mc.player.posZ), new Vec3d(x, y + 1.8, z), false, true, false) == null) {
            return true;
        }
        if (CrystalUtils.mc.world.rayTraceBlocks(new Vec3d(CrystalUtils.mc.player.posX, CrystalUtils.mc.player.posY + (double)CrystalUtils.mc.player.getEyeHeight(), CrystalUtils.mc.player.posZ), new Vec3d(x, y + 1.5, z), false, true, false) == null) {
            return true;
        }
        return CrystalUtils.mc.world.rayTraceBlocks(new Vec3d(CrystalUtils.mc.player.posX, CrystalUtils.mc.player.posY + (double)CrystalUtils.mc.player.getEyeHeight(), CrystalUtils.mc.player.posZ), new Vec3d(x, y, z), false, true, false) == null;
    }

    public static boolean isVisible(Vec3d vec3d) {
        Vec3d eyesPos = new Vec3d(CrystalUtils.mc.player.posX, CrystalUtils.mc.player.getEntityBoundingBox().minY + (double)CrystalUtils.mc.player.getEyeHeight(), CrystalUtils.mc.player.posZ);
        return CrystalUtils.mc.world.rayTraceBlocks(eyesPos, vec3d) == null;
    }

    public static float getBlastReduction(EntityLivingBase entity, float damageInput, Explosion explosion) {
        float damage = damageInput;
        if (entity instanceof EntityPlayer) {
            EntityPlayer ep = (EntityPlayer)entity;
            DamageSource ds = DamageSource.causeExplosionDamage((Explosion)explosion);
            damage = CombatRules.getDamageAfterAbsorb((float)damage, (float)ep.getTotalArmorValue(), (float)((float)ep.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue()));
            int k = 0;
            try {
                k = EnchantmentHelper.getEnchantmentModifierDamage((Iterable)ep.getArmorInventoryList(), (DamageSource)ds);
            }
            catch (Exception exception) {
                // empty catch block
            }
            float f = MathHelper.clamp((float)k, (float)0.0f, (float)20.0f);
            damage *= 1.0f - f / 25.0f;
            if (entity.isPotionActive(MobEffects.RESISTANCE)) {
                damage -= damage / 4.0f;
            }
            damage = Math.max(damage, 0.0f);
            return damage;
        }
        damage = CombatRules.getDamageAfterAbsorb((float)damage, (float)entity.getTotalArmorValue(), (float)((float)entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue()));
        return damage;
    }

    public static float getDamageMultiplied(float damage) {
        int diff = CrystalUtils.mc.world.getDifficulty().getId();
        return damage * (diff == 0 ? 0.0f : (diff == 2 ? 1.0f : (diff == 1 ? 0.5f : 1.5f)));
    }

    public static float calculateDamage(EntityEnderCrystal crystal, Entity entity) {
        return CrystalUtils.calculateDamage(crystal.posX, crystal.posY, crystal.posZ, entity);
    }

    public static float calculateDamage(BlockPos pos, Entity entity) {
        return CrystalUtils.calculateDamage((double)pos.getX() + 0.5, pos.getY() + 1, (double)pos.getZ() + 0.5, entity);
    }

    public static float calculateDamage(double posX, double posY, double posZ, Entity entity) {
        float doubleExplosionSize = 12.0f;
        Vec3d entityPosVec = CrystalUtils.getEntityPosVec(entity, (Integer)AutoCrystal.predictTicks.getValue() > 0 ? (Integer)AutoCrystal.predictTicks.getValue() : 0);
        double distancedsize = entityPosVec.distanceTo(new Vec3d(posX, posY, posZ)) / (double)doubleExplosionSize;
        Vec3d vec3d = new Vec3d(posX, posY, posZ);
        double blockDensity = 0.0;
        try {
            blockDensity = AutoCrystal.terrainIgnore.getValue().booleanValue() ? (double)CrystalUtils.getBlockDensity(vec3d, (Integer)AutoCrystal.predictTicks.getValue() > 0 ? entity.getEntityBoundingBox().offset(CrystalUtils.getMotionVec(entity, (Integer)AutoCrystal.predictTicks.getValue())) : entity.getEntityBoundingBox()) : (double)entity.world.getBlockDensity(vec3d, (Integer)AutoCrystal.predictTicks.getValue() > 0 ? entity.getEntityBoundingBox().offset(CrystalUtils.getMotionVec(entity, (Integer)AutoCrystal.predictTicks.getValue())) : entity.getEntityBoundingBox());
        }
        catch (Exception exception) {
            // empty catch block
        }
        double v = (1.0 - distancedsize) * blockDensity;
        float damage = (int)((v * v + v) / 2.0 * 7.0 * (double)doubleExplosionSize + 1.0);
        double finald = 1.0;
        if (entity instanceof EntityLivingBase) {
            finald = CrystalUtils.getBlastReduction((EntityLivingBase)entity, CrystalUtils.getDamageMultiplied(damage), new Explosion((World)CrystalUtils.mc.world, (Entity)CrystalUtils.mc.player, posX, posY, posZ, 6.0f, false, true));
        }
        return (float)finald;
    }

    public static Vec3d getEntityPosVec(Entity entity, int ticks) {
        return entity.getPositionVector().add(CrystalUtils.getMotionVec(entity, ticks));
    }

    public static Vec3d getMotionVec(Entity entity, int ticks) {
        double dX = entity.posX - entity.prevPosX;
        double dZ = entity.posZ - entity.prevPosZ;
        double entityMotionPosX = 0.0;
        double entityMotionPosZ = 0.0;
        if (AutoCrystal.collision.getValue().booleanValue()) {
            for (int i = 1; i <= ticks && CrystalUtils.mc.world.getBlockState(new BlockPos(entity.posX + dX * (double)i, entity.posY, entity.posZ + dZ * (double)i)).getBlock() instanceof BlockAir; ++i) {
                entityMotionPosX = dX * (double)i;
                entityMotionPosZ = dZ * (double)i;
            }
        } else {
            entityMotionPosX = dX * (double)ticks;
            entityMotionPosZ = dZ * (double)ticks;
        }
        return new Vec3d(entityMotionPosX, 0.0, entityMotionPosZ);
    }

    public static int ping() {
        if (mc.getConnection() == null) {
            return 50;
        }
        if (CrystalUtils.mc.player == null) {
            return 50;
        }
        try {
            return mc.getConnection().getPlayerInfo(CrystalUtils.mc.player.getUniqueID()).getResponseTime();
        }
        catch (NullPointerException nullPointerException) {
            return 50;
        }
    }

    public static int getCrystalSlot() {
        int crystalSlot = -1;
        if (Feature.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) {
            crystalSlot = Feature.mc.player.inventory.currentItem;
        }
        if (crystalSlot == -1) {
            for (int l = 0; l < 9; ++l) {
                if (Feature.mc.player.inventory.getStackInSlot(l).getItem() != Items.END_CRYSTAL) continue;
                crystalSlot = l;
                break;
            }
        }
        return crystalSlot;
    }

    public static int getSwordSlot() {
        int swordSlot = -1;
        if (Feature.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_SWORD) {
            swordSlot = Feature.mc.player.inventory.currentItem;
        }
        if (swordSlot == -1) {
            for (int l = 0; l < 9; ++l) {
                if (Feature.mc.player.inventory.getStackInSlot(l).getItem() != Items.DIAMOND_SWORD) continue;
                swordSlot = l;
                break;
            }
        }
        return swordSlot;
    }

    public static boolean canPlaceCrystal(BlockPos blockPos) {
        BlockPos boost = blockPos.add(0, 1, 0);
        BlockPos boost2 = blockPos.add(0, 2, 0);
        try {
            if (CrystalUtils.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && CrystalUtils.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
            if (CrystalUtils.mc.world.getBlockState(boost).getBlock() != Blocks.AIR || CrystalUtils.mc.world.getBlockState(boost2).getBlock() != Blocks.AIR) {
                return false;
            }
            for (Entity entity : CrystalUtils.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(boost))) {
                if (entity instanceof EntityEnderCrystal) continue;
                return false;
            }
            for (Entity entity : CrystalUtils.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(boost2))) {
                if (entity instanceof EntityEnderCrystal) continue;
                return false;
            }
        }
        catch (Exception ignored) {
            return false;
        }
        return true;
    }

    public static float getBlockDensity(Vec3d vec, AxisAlignedBB bb) {
        double d0 = 1.0 / ((bb.maxX - bb.minX) * 2.0 + 1.0);
        double d1 = 1.0 / ((bb.maxY - bb.minY) * 2.0 + 1.0);
        double d2 = 1.0 / ((bb.maxZ - bb.minZ) * 2.0 + 1.0);
        double d3 = (1.0 - Math.floor(1.0 / d0) * d0) / 2.0;
        double d4 = (1.0 - Math.floor(1.0 / d2) * d2) / 2.0;
        if (d0 >= 0.0 && d1 >= 0.0 && d2 >= 0.0) {
            int j2 = 0;
            int k2 = 0;
            float f = 0.0f;
            while (f <= 1.0f) {
                float f1 = 0.0f;
                while (f1 <= 1.0f) {
                    float f2 = 0.0f;
                    while (f2 <= 1.0f) {
                        double d5 = bb.minX + (bb.maxX - bb.minX) * (double)f;
                        double d6 = bb.minY + (bb.maxY - bb.minY) * (double)f1;
                        double d7 = bb.minZ + (bb.maxZ - bb.minZ) * (double)f2;
                        if (CrystalUtils.rayTraceBlocks(new Vec3d(d5 + d3, d6, d7 + d4), vec) == null) {
                            ++j2;
                        }
                        ++k2;
                        f2 = (float)((double)f2 + d2);
                    }
                    f1 = (float)((double)f1 + d1);
                }
                f = (float)((double)f + d0);
            }
            return (float)j2 / (float)k2;
        }
        return 0.0f;
    }

    @Nullable
    public static RayTraceResult rayTraceBlocks(Vec3d start, Vec3d end) {
        return CrystalUtils.rayTraceBlocks(start, end, false, false, false);
    }

    @Nullable
    public static RayTraceResult rayTraceBlocks(Vec3d vec31, Vec3d vec32, boolean stopOnLiquid, boolean ignoreBlockWithoutBoundingBox, boolean returnLastUncollidableBlock) {
        if (!(Double.isNaN(vec31.x) || Double.isNaN(vec31.y) || Double.isNaN(vec31.z))) {
            if (!(Double.isNaN(vec32.x) || Double.isNaN(vec32.y) || Double.isNaN(vec32.z))) {
                RayTraceResult raytraceresult;
                int j1;
                int i1;
                int i = MathHelper.floor((double)vec32.x);
                int j = MathHelper.floor((double)vec32.y);
                int k = MathHelper.floor((double)vec32.z);
                int l = MathHelper.floor((double)vec31.x);
                BlockPos blockpos = new BlockPos(l, i1 = MathHelper.floor((double)vec31.y), j1 = MathHelper.floor((double)vec31.z));
                IBlockState iblockstate = CrystalUtils.mc.world.getBlockState(blockpos);
                Block block = iblockstate.getBlock();
                if (!valid.contains(block)) {
                    block = Blocks.AIR;
                    iblockstate = Blocks.AIR.getBlockState().getBaseState();
                }
                if ((!ignoreBlockWithoutBoundingBox || iblockstate.getCollisionBoundingBox((IBlockAccess)CrystalUtils.mc.world, blockpos) != Block.NULL_AABB) && block.canCollideCheck(iblockstate, stopOnLiquid) && (raytraceresult = iblockstate.collisionRayTrace((World)CrystalUtils.mc.world, blockpos, vec31, vec32)) != null) {
                    return raytraceresult;
                }
                RayTraceResult raytraceresult2 = null;
                int k1 = 200;
                while (k1-- >= 0) {
                    EnumFacing enumfacing;
                    if (Double.isNaN(vec31.x) || Double.isNaN(vec31.y) || Double.isNaN(vec31.z)) {
                        return null;
                    }
                    if (l == i && i1 == j && j1 == k) {
                        return returnLastUncollidableBlock ? raytraceresult2 : null;
                    }
                    boolean flag2 = true;
                    boolean flag = true;
                    boolean flag1 = true;
                    double d0 = 999.0;
                    double d1 = 999.0;
                    double d2 = 999.0;
                    if (i > l) {
                        d0 = (double)l + 1.0;
                    } else if (i < l) {
                        d0 = (double)l + 0.0;
                    } else {
                        flag2 = false;
                    }
                    if (j > i1) {
                        d1 = (double)i1 + 1.0;
                    } else if (j < i1) {
                        d1 = (double)i1 + 0.0;
                    } else {
                        flag = false;
                    }
                    if (k > j1) {
                        d2 = (double)j1 + 1.0;
                    } else if (k < j1) {
                        d2 = (double)j1 + 0.0;
                    } else {
                        flag1 = false;
                    }
                    double d3 = 999.0;
                    double d4 = 999.0;
                    double d5 = 999.0;
                    double d6 = vec32.x - vec31.x;
                    double d7 = vec32.y - vec31.y;
                    double d8 = vec32.z - vec31.z;
                    if (flag2) {
                        d3 = (d0 - vec31.x) / d6;
                    }
                    if (flag) {
                        d4 = (d1 - vec31.y) / d7;
                    }
                    if (flag1) {
                        d5 = (d2 - vec31.z) / d8;
                    }
                    if (d3 == -0.0) {
                        d3 = -1.0E-4;
                    }
                    if (d4 == -0.0) {
                        d4 = -1.0E-4;
                    }
                    if (d5 == -0.0) {
                        d5 = -1.0E-4;
                    }
                    if (d3 < d4 && d3 < d5) {
                        enumfacing = i > l ? EnumFacing.WEST : EnumFacing.EAST;
                        vec31 = new Vec3d(d0, vec31.y + d7 * d3, vec31.z + d8 * d3);
                    } else if (d4 < d5) {
                        enumfacing = j > i1 ? EnumFacing.DOWN : EnumFacing.UP;
                        vec31 = new Vec3d(vec31.x + d6 * d4, d1, vec31.z + d8 * d4);
                    } else {
                        enumfacing = k > j1 ? EnumFacing.NORTH : EnumFacing.SOUTH;
                        vec31 = new Vec3d(vec31.x + d6 * d5, vec31.y + d7 * d5, d2);
                    }
                    l = MathHelper.floor((double)vec31.x) - (enumfacing == EnumFacing.EAST ? 1 : 0);
                    i1 = MathHelper.floor((double)vec31.y) - (enumfacing == EnumFacing.UP ? 1 : 0);
                    j1 = MathHelper.floor((double)vec31.z) - (enumfacing == EnumFacing.SOUTH ? 1 : 0);
                    blockpos = new BlockPos(l, i1, j1);
                    IBlockState iblockstate1 = CrystalUtils.mc.world.getBlockState(blockpos);
                    Block block1 = iblockstate1.getBlock();
                    if (!valid.contains(block1)) {
                        block1 = Blocks.AIR;
                        iblockstate1 = Blocks.AIR.getBlockState().getBaseState();
                    }
                    if (ignoreBlockWithoutBoundingBox && iblockstate1.getMaterial() != Material.PORTAL && iblockstate1.getCollisionBoundingBox((IBlockAccess)CrystalUtils.mc.world, blockpos) == Block.NULL_AABB) continue;
                    if (block1.canCollideCheck(iblockstate1, stopOnLiquid)) {
                        RayTraceResult raytraceresult1 = iblockstate1.collisionRayTrace((World)CrystalUtils.mc.world, blockpos, vec31, vec32);
                        if (raytraceresult1 == null) continue;
                        return raytraceresult1;
                    }
                    raytraceresult2 = new RayTraceResult(RayTraceResult.Type.MISS, vec31, enumfacing, blockpos);
                }
                return returnLastUncollidableBlock ? raytraceresult2 : null;
            }
            return null;
        }
        return null;
    }
}

