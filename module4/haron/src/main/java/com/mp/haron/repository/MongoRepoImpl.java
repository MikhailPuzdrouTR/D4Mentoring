package com.mp.haron.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mp.haron.convert.MongoDocumentDTOConverter;
import com.mp.haron.util.StereotypeUtil;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.print.Doc;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MongoRepoImpl<T> implements MongoRepository<T> {

    @Autowired
    protected MongoDocumentDTOConverter converter;

    @Autowired
    private MongoDatabase mongoDatabase;

    private Class<T> dtoClassInfo;

    protected MongoCollection<Document> mongoCollection;

    @PostConstruct
    private void init() {
        dtoClassInfo = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];

        StereotypeUtil.verifyIsCollectionType(dtoClassInfo);
        var mongoCollectionInfo = dtoClassInfo.getDeclaredAnnotation(com.mp.haron.stereotype.MongoCollection.class);
        String collectionName = mongoCollectionInfo.collectionName();
        this.mongoCollection = mongoDatabase.getCollection(collectionName);
    }

    @Override
    public <S extends T> S save(S dto) {
        Document newDocument = converter.convertToDocument(dto);
        mongoCollection.insertOne(newDocument);
        return converter.convertDocumentToDto(newDocument);
    }

    @Override
    public List<T> saveBatch(List<T> batch) {
        List<Document> documentsBatch = batch.stream().map(converter::convertToDocument).collect(Collectors.toList());
        mongoCollection.insertMany(documentsBatch);
        return documentsBatch.stream().map(document -> (T) converter.convertDocumentToDto(document)).collect(Collectors.toList());
    }

    @Override
    public List<T> readAll() {
        var iterableResult = mongoCollection.find().iterator();
        return convertIterableCursorToDtoList(iterableResult);
    }

    @Override
    public Optional<T> findById(String entityId) {
        return getDocumentById(entityId).map(doc -> converter.convertDocumentToDto(doc));
    }

    @Override
    public <S extends T> Optional<S> update(S entityForUpdate) {
        var id = StereotypeUtil.getDocumentIfFromDto(entityForUpdate, dtoClassInfo);
        return id.flatMap(this::getDocumentById).map(found -> {
            mongoCollection.replaceOne(new Document("_id", found.getObjectId("_id")), converter.convertToDocument(entityForUpdate), new UpdateOptions().upsert( true ));
            return entityForUpdate;
        });

    }

    private Optional<Document> getDocumentById(String id) {
        try {
            Document found = mongoCollection.find(new Document("_id", new ObjectId(id))).first();
            return Optional.ofNullable(found);
        } catch (IllegalArgumentException ex) {
            // For cases when invalid hexadecimal representation of an ObjectId
            return Optional.empty();
        }
    }

    @Override
    public Optional<T> delete(String deleteEntityId) {
        Optional<T> foundEntity = findById(deleteEntityId);
        foundEntity.ifPresent(entity -> mongoCollection.deleteOne(new Document("_id", deleteEntityId)));
        return foundEntity;
    }

    protected List<T> search(Bson bson) {
        return convertIterableCursorToDtoList(
                mongoCollection.find(bson).iterator()
        );
    }

    protected List<T> convertIterableCursorToDtoList(MongoCursor<Document> iterableResult) {
        List<T> allEntities = new ArrayList<>();
        while (iterableResult.hasNext()) {
            allEntities.add(converter.convertDocumentToDto(iterableResult.next()));
        }
        return allEntities;
    }
}
