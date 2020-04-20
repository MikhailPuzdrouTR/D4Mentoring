package com.mp.haron.repository;

import java.util.List;
import java.util.Optional;

public interface MongoRepository<T> {
    <S extends T> S save(S dto);

    List<T> saveBatch(List<T> batch);

    List<T> readAll();

    Optional<T> findById(String entityId);

    <S extends T> Optional<S> update(S entityForUpdate);

    Optional<T> delete(String deleteEntityId);
}
