package com.mp.haron.convert;

import com.mp.haron.stereotype.MongoId;
import com.mp.haron.util.ReflectionUtils;
import com.mp.haron.util.StereotypeUtil;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MongoDocumentDTOConverterImpl implements MongoDocumentDTOConverter {

    private static final String TYPE_FIELD = "type__";

    @Autowired
    private StereotypeUtil stereotypeUtil;

    public MongoDocumentDTOConverterImpl() {
    }

    public MongoDocumentDTOConverterImpl(StereotypeUtil stereotypeUtil) {
        this.stereotypeUtil = stereotypeUtil;
    }

    @Override
    public <T> Document convertToDocument(T dto) {
        try {
            return _convertToDocument(dto);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> Document _convertToDocument(T dto) throws IllegalAccessException {
        Class dtoClassInfo = dto.getClass();
        var fields = stereotypeUtil.getFieldInfosFromClass(dtoClassInfo);
        final var document = new Document();
        document.append(TYPE_FIELD, dtoClassInfo.getName());
        stereotypeUtil.getDocumentIfFromDto(dto, dtoClassInfo).ifPresent(id -> document.append("_id", new ObjectId(id)));
        for (FieldInfo field : fields) {
            field.getField().setAccessible(true);
            if (field.isCollection()) {
              if (((Collection) field.getField().get(dto)).isEmpty()) {
                  document.append(field.getName(), field.getField().get(dto));
              } else {
                  var collClassInfo = ((Collection) field.getField().get(dto)).stream().findFirst().get().getClass();
                  if (FieldInfo.builder().fieldType(collClassInfo).build().isObject()) {
                      List<Document> list = new ArrayList<>();
                      for (var v : ((Collection) field.getField().get(dto))) {
                          list.add(_convertToDocument(v));
                      }
                      document.append(field.getName(), list);
                  } else {
                      document.append(field.getName(), field.getField().get(dto));
                  }
              }
            } else if (field.isObject()) {
                document.append(field.getName(), _convertToDocument(field.getField().get(dto)));
            } else {
                document.append(field.getName(), field.getField().get(dto));
            }
        }
        return document;
    }

    @Override
    public <T> T convertDocumentToDto(Document document) {
        try {
            var type = document.get(TYPE_FIELD, String.class);
            Class<?> classInfo = Class.forName(type);
            List<FieldInfo> fieldInfos = stereotypeUtil.getFieldInfosFromClass(classInfo);
            T dto = (T) classInfo.newInstance();
            setIdToDto(document.getObjectId("_id"), dto, classInfo);
            for (var fieldInfo : fieldInfos) {
                var fieldValue = document.get(fieldInfo.getName());
                if (fieldValue == null) {
                    continue;
                }
                fieldInfo.getField().setAccessible(true);
                if (fieldInfo.isCollection()) {
                    if (((Collection) fieldValue).isEmpty()) {
                        fieldInfo.getField().set(dto, createCollectionByType(fieldInfo.getFieldType()));
                    } else {
                        Class<?> collClassInfo = ((Collection) fieldValue).stream().findFirst().get().getClass();
                        if (FieldInfo.builder().fieldType(collClassInfo).build().isObject()) {
                            var collection = createCollectionByType(fieldInfo.getFieldType());
                            ((Collection) fieldValue).stream()
                                    .map(v -> convertDocumentToDto((Document) v))
                                    .forEach(v -> collection.add(v));
                            fieldInfo.getField().set(dto, collection);
                        } else {
                            var collection = createCollectionByType(fieldInfo.getFieldType());
                            collection.addAll(((Collection) fieldValue));
                            fieldInfo.getField().set(dto, collection);
                        }
                    }
                } else if (fieldInfo.isObject()) {
                    fieldInfo.getField().set(dto, convertDocumentToDto((Document) fieldValue));
                } else {
                    fieldInfo.getField().set(dto, fieldValue);
                }
            }
            return dto;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T setIdToDto(ObjectId oId, T dto, Class<?> dtoClassInfo) {
        ReflectionUtils.getAllFieldsFromClass(dtoClassInfo).stream()
                .filter(field -> field.getDeclaredAnnotation(MongoId.class) != null)
                .findFirst()
                .ifPresent(field -> {
                    field.setAccessible(true);
                    try {
                        field.set(dto, oId.toString());
                    } catch (IllegalAccessException e) {
                    }
                });
        return dto;
    }

    private Collection createCollectionByType(Class<? extends Collection> collType) {
        if (Set.class.isAssignableFrom(collType)) {
            return new HashSet();
        }
        if (List.class.isAssignableFrom(collType)) {
            return new ArrayList();
        }
        if (Queue.class.isAssignableFrom(collType)) {
            return new LinkedList();
        }
        return new ArrayList();
    }
}

