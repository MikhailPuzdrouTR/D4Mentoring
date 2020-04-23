package com.mp.haron.convert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldInfo {
    private Field field;
    private String name;
    private Class fieldType;

    public boolean isObject() {
        var primitive = Set.of(Integer.class, String.class, Double.class, Float.class, Boolean.class, Byte.class);
        return !primitive.contains(fieldType);
    }

    public boolean isCollection() {
        return Collection.class.isAssignableFrom(fieldType);
    }
}
