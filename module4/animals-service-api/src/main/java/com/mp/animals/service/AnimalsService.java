package com.mp.animals.service;

import com.mp.animals.domain.Animal;
import com.mp.animals.domain.Host;

import java.util.List;
import java.util.Optional;

public interface AnimalsService {

    <T extends Animal> T createAnimal(T newAnimal);

    Optional<Animal> getAnimalById(String animalId);

    List<Animal> getAllAnimals();

    List<Animal> getAnimalsByColor(String color);

    List<Animal> getAnimalsByBreed(String breed);

    List<Animal> getAnimalsByFavouriteToy(String favouriteToy);

    List<Animal> getAnimalsByHostName(Host host);

    <T extends Animal> Optional<T> updateAnimal(T animalForUpdate);

    Optional<Animal> deleteAnimalById(String deleteAnimalId);
}
