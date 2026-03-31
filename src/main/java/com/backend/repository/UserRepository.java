package com.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.object.User;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
