/*
 * Decompiled with CFR 0.152.
 */
package org.reflections.util;

import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.reflections.Store;
import org.reflections.util.NameHelper;

public interface QueryFunction<C, T>
extends Function<C, Set<T>>,
NameHelper {
    @Override
    public Set<T> apply(C var1);

    public static <C, T> QueryFunction<Store, T> empty() {
        return ctx -> Collections.emptySet();
    }

    public static <C, T> QueryFunction<Store, T> single(T element) {
        return ctx -> Collections.singleton(element);
    }

    public static <C, T> QueryFunction<Store, T> set(Collection<T> elements) {
        return ctx -> new LinkedHashSet(elements);
    }

    default public QueryFunction<C, T> filter(Predicate<? super T> predicate) {
        return ctx -> this.apply(ctx).stream().filter(predicate).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    default public <R> QueryFunction<C, R> map(Function<? super T, ? extends R> function) {
        return ctx -> this.apply(ctx).stream().map(function).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    default public <R> QueryFunction<C, R> flatMap(Function<T, ? extends Function<C, Set<R>>> function) {
        return ctx -> this.apply(ctx).stream().flatMap((? super T t) -> ((Set)((Function)function.apply(t)).apply(ctx)).stream()).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    default public QueryFunction<C, T> getAll(Function<T, QueryFunction<C, T>> builder) {
        return this.getAll(builder, t -> t);
    }

    default public <R> QueryFunction<C, R> getAll(Function<T, QueryFunction<C, R>> builder, Function<R, T> traverse) {
        return ctx -> {
            ArrayList workKeys = new ArrayList(this.apply(ctx));
            LinkedHashSet result = new LinkedHashSet();
            for (int i = 0; i < workKeys.size(); ++i) {
                Object key = workKeys.get(i);
                Object apply2 = ((QueryFunction)builder.apply(key)).apply(ctx);
                Iterator iterator2 = apply2.iterator();
                while (iterator2.hasNext()) {
                    Object r = iterator2.next();
                    if (!result.add(r)) continue;
                    workKeys.add(traverse.apply(r));
                }
            }
            return result;
        };
    }

    default public <R> QueryFunction<C, T> add(QueryFunction<C, T> function) {
        return ctx -> Stream.of(this.apply(ctx), function.apply(ctx)).flatMap(Collection::stream).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    default public <R> QueryFunction<C, R> as(Class<? extends R> type, ClassLoader ... loaders) {
        return ctx -> {
            Object apply2 = this.apply(ctx);
            return apply2.stream().findFirst().map(arg_0 -> this.lambda$null$11(type, (Set)apply2, loaders, arg_0)).orElse((Set)apply2);
        };
    }

    default public <R> QueryFunction<C, Class<?>> asClass(ClassLoader ... loaders) {
        return ctx -> (Set)this.forNames((Collection<String>)this.apply(ctx), Class.class, loaders);
    }

    default public QueryFunction<C, String> asString() {
        return ctx -> new LinkedHashSet<String>(this.toNames((AnnotatedElement)this.apply(ctx)));
    }

    default public <R> QueryFunction<C, Class<? extends R>> as() {
        return ctx -> new LinkedHashSet(this.apply(ctx));
    }

    private /* synthetic */ Set lambda$null$11(Class type, Set apply2, ClassLoader[] loaders, Object first) {
        return type.isAssignableFrom(first.getClass()) ? apply2 : (first instanceof String ? (Set)this.forNames((Collection<String>)apply2, type, loaders) : (first instanceof AnnotatedElement ? (Set)this.forNames(this.toNames(apply2), type, loaders) : (Set)apply2.stream().map(t -> t).collect(Collectors.toCollection(LinkedHashSet::new))));
    }
}

