package com.mp.animals.controller;

import com.mp.animals.domain.Dog;
import com.mp.animals.service.AnimalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/dogs")
public class DogController {

    @Autowired
    private AnimalsService animalsService;

    @PostMapping
    public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
        Dog createdDog = animalsService.createAnimal(dog);
        return new ResponseEntity<>(createdDog, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Optional<Dog> updateDog(@PathVariable("id") String updateDogId,
                                  @RequestBody Dog updateDog) {
        updateDog.setId(updateDogId);
        return animalsService.updateAnimal(updateDog);
    }
}
