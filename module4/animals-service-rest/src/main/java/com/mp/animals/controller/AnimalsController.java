package com.mp.animals.controller;

import com.mp.animals.domain.Animal;
import com.mp.animals.domain.Host;
import com.mp.animals.service.AnimalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/animals")
public class AnimalsController {

    @Autowired
    private AnimalsService animalsService;

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalsService.getAllAnimals();
    }

    @GetMapping("/color/{color}")
    public List<Animal> getAnimalsByColor(@PathVariable("color") String color) {
        return animalsService.getAnimalsByColor(color);
    }

    @GetMapping("/breed/{breed}")
    public List<Animal> getAnimalsByBreed(@PathVariable("breed") String breed) {
        return animalsService.getAnimalsByBreed(breed);
    }

    @GetMapping("/favourite-toy/{favouriteToy}")
    public List<Animal> getAnimalsByFavouriteToy(@PathVariable("favouriteToy") String favouriteToy) {
        return animalsService.getAnimalsByFavouriteToy(favouriteToy);
    }

    @GetMapping("/host/name/{lastName}/{name}")
    public List<Animal> getAnimalsByHostName(@PathVariable("lastName") String hostLastName,
                                             @PathVariable("name") String hostName) {
        Host host = Host.builder().lastName(hostLastName).name(hostName).build();
        return animalsService.getAnimalsByHostName(host);
    }

    @GetMapping("/{id}")
    public Optional<Animal> getAnimalById(@PathVariable("id") String id) {
        return animalsService.getAnimalById(id);
    }

    @DeleteMapping("/{id}")
    public Optional<Animal> deleteAnimalById(@PathVariable("id") String deleteAnimalId) {
        return animalsService.deleteAnimalById(deleteAnimalId);
    }
}
