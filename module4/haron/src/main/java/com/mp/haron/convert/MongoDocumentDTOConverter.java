package com.mp.haron.convert;

import org.bson.Document;

public interface MongoDocumentDTOConverter {

    <T> Document convertToDocument(T dto);

    <T> T convertDocumentToDto(Document document);
}
