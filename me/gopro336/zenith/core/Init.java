/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import me.gopro336.zenith.core.InitStage;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface Init {
    public InitStage stage() default InitStage.Pre;

    public Class<?>[] dependencies() default {};
}

