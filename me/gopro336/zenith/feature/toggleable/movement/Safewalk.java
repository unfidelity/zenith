/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.movement;

import me.gopro336.zenith.event.entity.ShouldWalkOffEdgeEvent;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="SafeWalk", description="Stops you from walking off the edge of a block", category=Category.MOVEMENT)
public class Safewalk
extends Feature {
    @Listener
    public void shouldWalkOfEdge(ShouldWalkOffEdgeEvent event) {
        event.setCanceled(true);
    }
}

