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
public class Dog extends Mammals {
    @MongoField(name = "breed")
    private String breed;
    @MongoField(name = "countOfFriends")
    private Integer countOfFriends;
    @MongoField(name = "favoriteToy")
    private Toy favoriteToy;
}
