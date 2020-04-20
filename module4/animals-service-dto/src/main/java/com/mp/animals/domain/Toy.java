package com.mp.animals.domain;

import com.mp.haron.stereotype.MongoField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Toy {
    @MongoField(name = "name")
    private String name;
    @MongoField(name = "type")
    private String type;
}
