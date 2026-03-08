package com.backend.repositories;

import com.backend.entities.Report;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportRepository extends MongoRepository<Report, String> {
    List<Report> findAllByOrderByCreatedAtDesc();
}
