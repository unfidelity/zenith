/*
 * Decompiled with CFR 0.152.
 */
package org.reflections.util;

import java.lang.reflect.AnnotatedElement;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.reflections.Store;
import org.reflections.util.NameHelper;
import org.reflections.util.QueryFunction;

public interface QueryBuilder
extends NameHelper {
    default public String index() {
        return this.getClass().getSimpleName();
    }

    default public QueryFunction<Store, String> get(String key) {
        return store -> new LinkedHashSet(store.getOrDefault(this.index(), Collections.emptyMap()).getOrDefault(key, Collections.emptySet()));
    }

    default public QueryFunction<Store, String> get(AnnotatedElement element) {
        return this.get(this.toName(element));
    }

    default public QueryFunction<Store, String> get(Collection<String> keys2) {
        return keys2.stream().map(this::get).reduce(QueryFunction::add).get();
    }

    default public QueryFunction<Store, String> getAll(Collection<String> keys2) {
        return QueryFunction.set(keys2).getAll(this::get);
    }

    default public QueryFunction<Store, String> getAllIncluding(String key) {
        return QueryFunction.single(key).add(QueryFunction.single(key).getAll(this::get));
    }

    default public QueryFunction<Store, String> getAllIncluding(Collection<String> keys2) {
        return QueryFunction.set(keys2).add(QueryFunction.set(keys2).getAll(this::get));
    }

    default public QueryFunction<Store, String> of(Collection<String> keys2) {
        return this.getAll(keys2);
    }

    default public QueryFunction<Store, String> of(String key) {
        return this.getAll(Collections.singletonList(key));
    }

    default public QueryFunction<Store, String> of(AnnotatedElement ... elements) {
        return this.getAll(this.toNames(elements));
    }

    default public QueryFunction<Store, String> of(Set<? extends AnnotatedElement> elements) {
        return this.getAll(this.toNames(elements));
    }

    default public QueryFunction<Store, String> with(Collection<String> keys2) {
        return this.of(keys2);
    }

    default public QueryFunction<Store, String> with(String key) {
        return this.of(key);
    }

    default public QueryFunction<Store, String> with(AnnotatedElement ... keys2) {
        return this.of(keys2);
    }

    default public QueryFunction<Store, String> with(Set<? extends AnnotatedElement> keys2) {
        return this.of(keys2);
    }

    default public <T> QueryFunction<Store, T> of(QueryFunction queryFunction) {
        return queryFunction.add(queryFunction.getAll(this::get));
    }
}

