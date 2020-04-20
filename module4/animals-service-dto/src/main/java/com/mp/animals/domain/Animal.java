package com.mp.animals.domain;

import com.mp.haron.stereotype.MongoCollection;
import com.mp.haron.stereotype.MongoField;
import com.mp.haron.stereotype.MongoId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MongoCollection(collectionName = "animal")
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Animal {
    @MongoId
    private String id;
    @MongoField(name = "name")
    private String name;
    @MongoField(name = "host")
    private Host host;
}
