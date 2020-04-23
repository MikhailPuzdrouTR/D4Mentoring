package com.mp.animals.repository;

import com.mp.animals.domain.Animal;
import com.mp.animals.domain.Host;
import com.mp.haron.repository.MongoRepository;

import java.util.List;

public interface AnimalsRepository extends MongoRepository<Animal> {

    List<Animal> findAnimalsByColor(String color);

    List<Animal> findAnimalsByBreed(String breed);

    List<Animal> findAnimalsByFavouriteToy(String favouriteToy);

    List<Animal> findByHostName(Host host);
}
