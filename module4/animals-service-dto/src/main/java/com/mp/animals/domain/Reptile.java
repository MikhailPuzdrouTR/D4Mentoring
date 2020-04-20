package com.mp.animals.domain;

import com.mp.haron.stereotype.MongoCollection;
import com.mp.haron.stereotype.MongoField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Reptile extends Animal {
    @MongoField(name = "scale")
    private String scale;
    @MongoField(name = "scaleColor")
    private String scaleColor;
}

