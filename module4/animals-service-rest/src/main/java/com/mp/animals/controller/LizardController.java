package com.mp.animals.controller;

import com.mp.animals.domain.Lizard;
import com.mp.animals.service.AnimalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lizard")
public class LizardController {

    @Autowired
    private AnimalsService animalsService;

    @PostMapping
    public ResponseEntity<Lizard> createLizard(@RequestBody Lizard lizard) {
        Lizard createdLizard = animalsService.createAnimal(lizard);
        return new ResponseEntity<>(createdLizard, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Optional<Lizard> updateDog(@PathVariable("id") String updateLizardId,
                                      @RequestBody Lizard updateLizard) {
        updateLizard.setId(updateLizardId);
        return animalsService.updateAnimal(updateLizard);
    }
}
