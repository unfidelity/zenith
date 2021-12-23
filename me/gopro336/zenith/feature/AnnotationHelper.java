/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import me.gopro336.zenith.feature.Category;

@Retention(value=RetentionPolicy.RUNTIME)
public @interface AnnotationHelper {
    public String name() default "No name provided!";

    public String description() default "No description provided!";

    public Category category() default Category.CLIENT;

    public int key() default 0;
}

