package com.backend.repository;

import com.backend.object.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsById(String id);
    User findByUsername(String username);
    User findByEmail(String email);
    void deleteUserById(String id);
}
