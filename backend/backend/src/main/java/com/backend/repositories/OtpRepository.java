package com.backend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.entities.*;
import java.util.List;

public interface OtpRepository extends MongoRepository<Otp, String> {
    List<Otp> findByCode(String code);
}
