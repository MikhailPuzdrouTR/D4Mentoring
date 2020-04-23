package com.mp.haron.util;

import com.mp.haron.convert.FieldInfo;
import com.mp.haron.stereotype.MongoCollection;
import com.mp.haron.stereotype.MongoField;
import com.mp.haron.stereotype.MongoId;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StereotypeUtil {

    public static <T> Optional<String> getDocumentIfFromDto(T dto, Class<?> dtoClassInfo) {
        return Arrays.stream(dtoClassInfo.getDeclaredFields())
                .filter(field -> field.getDeclaredAnnotation(MongoId.class) != null)
                .findFirst()
                .flatMap(idField -> {
                    idField.setAccessible(true);
                    try {
                        return Optional.ofNullable(idField.get(dto)).map(Object::toString);
                    } catch (IllegalAccessException e) {
                        return Optional.empty();
                    }
                });
    }

    public static void verifyIsCollectionType(Class classInfo) {
        MongoCollection mongoCollection = (MongoCollection) classInfo.getDeclaredAnnotation(MongoCollection.class);
        if (mongoCollection == null) {
            throw new RuntimeException("Root object should be a collection");
        }
    }

    public static List<FieldInfo> getFieldInfosFromClass(Class classInfo) {
        return ReflectionUtils.getAllFieldsFromClass(classInfo).stream()
                .filter(field -> field.getDeclaredAnnotation(MongoField.class) != null)
                .map(field -> FieldInfo.builder().field(field).fieldType(field.getType()).name(field.getDeclaredAnnotation(MongoField.class).name()).build())
                .collect(Collectors.toList());
    }
}
