package com.guillaumegasnier.education.shell.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class BeanMergeUtil {

    private BeanMergeUtil() {
    }

    /**
     * Copie les propriétés non-nulles de {@code source} vers {@code target} seulement
     * si la propriété correspondante dans {@code target} est actuellement null.
     * Les propriétés listées dans {@code ignoreProperties} sont ignorées.
     */
    public static void mergeWhenTargetNull(Object source, Object target, String... ignoreProperties) {
        if (source == null || target == null) return;

        Set<String> ignore = new HashSet<>();
        if (ignoreProperties != null) ignore.addAll(Arrays.asList(ignoreProperties));

        try {
            PropertyDescriptor[] sourcePds = Introspector.getBeanInfo(source.getClass(), Object.class).getPropertyDescriptors();
            Map<String, PropertyDescriptor> targetPds = Arrays.stream(Introspector.getBeanInfo(target.getClass(), Object.class).getPropertyDescriptors())
                    .collect(Collectors.toMap(PropertyDescriptor::getName, pd -> pd));

            for (PropertyDescriptor spd : sourcePds) {
                String name = spd.getName();
                if (ignore.contains(name)) continue;

                PropertyDescriptor tpd = targetPds.get(name);
                if (tpd == null) continue;

                Method readSrc = spd.getReadMethod();
                Method readTgt = tpd.getReadMethod();
                Method writeTgt = tpd.getWriteMethod();
                if (readSrc == null || readTgt == null || writeTgt == null) continue;

                Object srcVal = readSrc.invoke(source);
                Object tgtVal = readTgt.invoke(target);

                if (tgtVal == null && srcVal != null) {
                    Class<?> paramType = writeTgt.getParameterTypes()[0];
                    if (paramType.isAssignableFrom(srcVal.getClass())) {
                        writeTgt.invoke(target, srcVal);
                    }
                }
            }

        } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Error merging beans", e);
        }
    }
}
