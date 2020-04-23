package com.mp.animals.service;

import com.mp.animals.domain.Animal;
import com.mp.animals.domain.Host;
import com.mp.animals.repository.AnimalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalsServiceImpl implements AnimalsService {

    @Autowired
    private AnimalsRepository animalsRepository;

    @Override
    public <T extends Animal> T createAnimal(T newAnimal) {
        return animalsRepository.save(newAnimal);
    }

    @Override
    public Optional<Animal> getAnimalById(String animalId) {
        return animalsRepository.findById(animalId);
    }

    @Override
    public List<Animal> getAllAnimals() {
        return animalsRepository.readAll();
    }

    @Override
    public List<Animal> getAnimalsByColor(String color) {
        return animalsRepository.findAnimalsByColor(color);
    }

    @Override
    public List<Animal> getAnimalsByBreed(String breed) {
        return animalsRepository.findAnimalsByBreed(breed);
    }

    @Override
    public List<Animal> getAnimalsByFavouriteToy(String favouriteToy) {
        return animalsRepository.findAnimalsByFavouriteToy(favouriteToy);
    }

    @Override
    public List<Animal> getAnimalsByHostName(Host host) {
        return animalsRepository.findByHostName(host);
    }

    @Override
    public <T extends Animal> Optional<T> updateAnimal(T animalForUpdate) {
        return animalsRepository.update(animalForUpdate);
    }

    @Override
    public Optional<Animal> deleteAnimalById(String deleteAnimalId) {
        return animalsRepository.delete(deleteAnimalId);
    }
}
