package com.mp.animals.service;

import com.mp.animals.domain.Animal;
import com.mp.animals.domain.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HostServiceImpl implements HostService {

    @Autowired
    private AnimalsService animalsService;

    @Override
    public Set<Host> getAllHosts() {
        return animalsService.getAllAnimals().stream().map(Animal::getHost).collect(Collectors.toSet());
    }
}
