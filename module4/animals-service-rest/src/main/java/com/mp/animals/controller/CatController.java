package com.mp.animals.controller;

import com.mp.animals.domain.Cat;
import com.mp.animals.service.AnimalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cats")
public class CatController {

    @Autowired
    private AnimalsService animalsService;

    @PostMapping
    public ResponseEntity<Cat> createCat(@RequestBody Cat cat) {
        Cat createdCat = animalsService.createAnimal(cat);
        return new ResponseEntity<>(createdCat, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Optional<Cat> updateCat(@PathVariable("id") String updateCatId,
                                   @RequestBody Cat updateCat) {
        updateCat.setId(updateCatId);
        return animalsService.updateAnimal(updateCat);
    }
}
