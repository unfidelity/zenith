/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.property;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import me.gopro336.zenith.property.Property;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0010\b\u0001\u0010\u0003 \u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00020\u0002B\r\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u00a2\u0006\u0002\u0010\u0006J\u000b\u0010\n\u001a\u00028\u0001\u00a2\u0006\u0002\u0010\bJ$\u0010\u0007\u001a\u00028\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rH\u0086\u0002\u00a2\u0006\u0002\u0010\u000eJ,\u0010\u000f\u001a\u00020\u00102\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u0011\u001a\u00028\u0000H\u0086\u0002\u00a2\u0006\u0002\u0010\u0012R\u0013\u0010\u0005\u001a\u00028\u0001\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0013"}, d2={"Lme/gopro336/zenith/property/PropertyDelegate;", "F", "", "T", "Lme/gopro336/zenith/property/Property;", "value", "(Lme/gopro336/zenith/property/Property;)V", "getValue", "()Lme/gopro336/zenith/property/Property;", "Lme/gopro336/zenith/property/Property;", "getActualValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "newVal", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "zenith"})
public final class PropertyDelegate<F, T extends Property<F>> {
    @NotNull
    private final T value;

    @NotNull
    public final T getActualValue() {
        return this.value;
    }

    @NotNull
    public final F getValue(@Nullable Object thisRef, @NotNull KProperty<?> property) {
        Intrinsics.checkParameterIsNotNull(property, "property");
        Object t = ((Property)this.value).getValue();
        Intrinsics.checkExpressionValueIsNotNull(t, "value.value");
        return (F)t;
    }

    public final void setValue(@Nullable Object thisRef, @NotNull KProperty<?> property, @NotNull F newVal) {
        Intrinsics.checkParameterIsNotNull(property, "property");
        Intrinsics.checkParameterIsNotNull(newVal, "newVal");
        ((Property)this.value).setValue(newVal);
    }

    @NotNull
    public final T getValue() {
        return this.value;
    }

    public PropertyDelegate(@NotNull T value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.value = value;
    }
}

