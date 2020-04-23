package com.mp.animals.domain;

import com.mp.haron.stereotype.MongoField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Host {
    @MongoField(name = "name")
    private String name;
    @MongoField(name = "lastName")
    private String lastName;
    @MongoField(name = "address")
    private String address;
}
