package com.mp.haron.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReflectionUtils {

    public static List<Field> getAllFieldsFromClass(Class<?> metaClass) {
        List<Field> fields = Arrays.stream(metaClass.getDeclaredFields()).collect(Collectors.toList());
        Class<?> metaSupperClass = metaClass.getSuperclass();
        if (metaSupperClass != Object.class) {
            fields.addAll(getAllFieldsFromClass(metaSupperClass));
        }
        return fields;
    }
}
