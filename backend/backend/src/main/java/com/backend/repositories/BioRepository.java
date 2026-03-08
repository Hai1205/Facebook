package com.backend.repositories;

import com.backend.entities.Bio;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BioRepository extends MongoRepository<Bio, String> {
    Optional<Bio> findById(String  bioId);
}
