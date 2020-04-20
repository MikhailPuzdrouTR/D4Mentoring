package com.mp.animals.domain;

import com.mp.haron.stereotype.MongoCollection;
import com.mp.haron.stereotype.MongoField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Mammals extends Animal {
    @MongoField(name = "hairlineColor")
    private String hairlineColor;
    @MongoField(name = "hairlineLength")
    private String hairlineLength;
}