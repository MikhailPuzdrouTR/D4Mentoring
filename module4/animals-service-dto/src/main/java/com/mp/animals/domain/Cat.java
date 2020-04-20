package com.mp.animals.domain;

import com.mp.haron.stereotype.MongoField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cat extends Mammals {
    @MongoField(name = "breed")
    private String breed;
    @MongoField(name = "favouritePlace")
    private String favouritePlace;
    @MongoField(name = "countOfEscapes")
    private Integer countOfEscapes;
}
