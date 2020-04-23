package com.mp.animals.domain;

import com.mp.haron.stereotype.MongoCollection;
import com.mp.haron.stereotype.MongoField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lizard extends Reptile {
    @MongoField(name = "numberOfTail")
    private Integer numberOfTail;
    @MongoField(name = "lengthOfTongue")
    private Integer lengthOfTongue;
}
