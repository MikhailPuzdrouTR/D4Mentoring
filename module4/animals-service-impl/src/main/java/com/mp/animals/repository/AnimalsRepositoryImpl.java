package com.mp.animals.repository;

import com.mp.animals.domain.Animal;
import com.mp.animals.domain.Host;
import com.mp.haron.repository.MongoRepoImpl;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.mongodb.client.model.Filters.or;

@Component
public class AnimalsRepositoryImpl extends MongoRepoImpl<Animal> implements AnimalsRepository {
    @Override
    public List<Animal> findAnimalsByColor(String color) {
        return search(
                or(new Document("hairlineColor", color), new Document("scaleColor", color))
        );
    }

    @Override
    public List<Animal> findAnimalsByBreed(String breed) {
        return search(
                new Document("breed", breed)
        );
    }

    @Override
    public List<Animal> findAnimalsByFavouriteToy(String favouriteToy) {
        return search(
                new Document("favouriteToy", favouriteToy)
        );
    }

    @Override
    public List<Animal> findByHostName(Host host) {
        return search(
                new Document("host.name", host.getName()).append("host.lastName", host.getLastName())
        );
    }
}
