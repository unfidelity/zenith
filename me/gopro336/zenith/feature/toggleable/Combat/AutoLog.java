//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.Combat;

import java.util.List;
import java.util.stream.Collectors;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.combat.CrystalUtils;
import me.gopro336.zenith.event.world.UpdateEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.FeatureManager;
import me.gopro336.zenith.feature.toggleable.misc.AutoReconnect;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Items;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="AutoLog", category=Category.COMBAT, description="Automaticly disconnects from da server if ur ez")
public class AutoLog
extends Feature {
    private final Property<Mode> mode = new Property<Mode>(this, "Mode", "", Mode.HEALTH);
    private final NumberProperty<Float> health = new NumberProperty<Float>((Feature)this, "Health", "", Float.valueOf(0.0f), Float.valueOf(10.0f), Float.valueOf(22.0f));
    public NumberProperty<Float> crystalRange = new NumberProperty<Float>((Feature)this, "CrystalRange", "", Float.valueOf(1.0f), Float.valueOf(10.0f), Float.valueOf(15.0f));
    private final Property<Boolean> totem = new Property<Boolean>(this, "IgnoreTotem", "", true);
    private Property<Boolean> notify = new Property<Boolean>(this, "Notify", "", false);

    @Listener
    public void onUpdate(UpdateEvent event) {
        if (AutoLog.mc.world == null || AutoLog.mc.player == null) {
            return;
        }
        if (this.mode.getValue() == Mode.HEALTH) {
            if (AutoLog.mc.player.getHealth() <= ((Float)this.health.getValue()).floatValue()) {
                if (this.totem.getValue().booleanValue()) {
                    this.disconnect();
                } else if (!this.hasTotems()) {
                    this.disconnect();
                }
            }
        } else {
            if (!this.totem.getValue().booleanValue() && this.hasTotems()) {
                return;
            }
            float dmg = 0.0f;
            List crystalsInRange = AutoLog.mc.world.loadedEntityList.stream().filter(e -> e instanceof EntityEnderCrystal).filter(e -> AutoLog.mc.player.getDistance(e) <= ((Float)this.crystalRange.getValue()).floatValue()).collect(Collectors.toList());
            for (Entity entity : crystalsInRange) {
                dmg += CrystalUtils.calculateDamage((EntityEnderCrystal)entity, (Entity)AutoLog.mc.player);
            }
            if (AutoLog.mc.player.getHealth() + AutoLog.mc.player.getAbsorptionAmount() <= dmg) {
                this.disconnect();
            }
        }
    }

    private boolean hasTotems() {
        for (int slot = 0; slot < 36; ++slot) {
            if (AutoLog.mc.player.inventory.getStackInSlot(slot).getItem() != Items.TOTEM_OF_UNDYING) continue;
            return true;
        }
        return false;
    }

    private void disconnect() {
        AutoReconnect module = (AutoReconnect)FeatureManager.getFeatureByClass(AutoReconnect.class);
        if (module != null && module.isEnabled()) {
            module.toggle();
        }
        this.toggle();
        if (this.notify.getValue().booleanValue()) {
            Zenith.logger.printToChat("You have AutoLogged!");
        }
        AutoLog.mc.player.inventory.currentItem = 1000;
    }

    private static enum Mode {
        HEALTH,
        PLAYER,
        CRYSTALDMG;

    }
}

