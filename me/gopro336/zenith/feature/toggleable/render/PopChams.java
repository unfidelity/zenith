//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.render;

import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.List;
import me.gopro336.zenith.api.util.math.AnimationUtil;
import me.gopro336.zenith.event.entity.TotemPopEvent;
import me.gopro336.zenith.event.render.Render3DEvent;
import me.gopro336.zenith.event.render.RenderEntityModelEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.toggleable.render.ChamsRw;
import me.gopro336.zenith.property.NumberProperty;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="PopChams", description="", category=Category.RENDER)
public class PopChams
extends Feature {
    private NumberProperty<Float> fadeSpeed = new NumberProperty<Float>((Feature)this, "Speed", "", Float.valueOf(0.2f), Float.valueOf(1.0f), Float.valueOf(3.0f));
    public static List<Ghost> popGhosts = new ArrayList<Ghost>();

    @Override
    @Listener
    public void onRender3D(Render3DEvent event) {
        for (Ghost ghost : popGhosts) {
            if (ghost.alpha > 0.0f) {
                ghost.alpha = AnimationUtil.moveTowards(ghost.alpha, 0.0f, 0.01f + ((Float)this.fadeSpeed.getValue()).floatValue() / 30.0f, 0.1f);
            }
            if (ghost.alpha == 0.0f) {
                popGhosts.remove((Object)ghost);
            }
            RenderEntityModelEvent modelEvent = new RenderEntityModelEvent((ModelBase)ghost.modelPlayer, (Entity)ghost, ghost.limbSwing, ghost.limbSwingAmount, ghost.ticksExisted, ghost.rotationYawHead, ghost.rotationPitch, 1.0f);
            ChamsRw.onRenderLivingEntity(modelEvent);
        }
    }

    @Listener
    public void onTotemPop(TotemPopEvent event) {
        popGhosts.add((Ghost)event.getEntity());
    }

    public class Ghost
    extends EntityPlayer {
        float alpha;
        private final ModelPlayer modelPlayer;

        public Ghost(World worldIn, GameProfile gameProfileIn) {
            super(worldIn, gameProfileIn);
            this.modelPlayer = new ModelPlayer(0.0f, false);
            this.alpha = 180.0f;
        }

        public boolean isSpectator() {
            return false;
        }

        public boolean isCreative() {
            return false;
        }
    }
}

