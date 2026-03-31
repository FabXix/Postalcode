package com.backend.service;

import com.backend.object.User;
import com.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public void addUser(User user){
        if(userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("Username already exists");
        }
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already used");
        }
        userRepository.save(user);
    }

    public String login(String username, String password){
        User user = userRepository.findByUsername(username);

        if(user.getPassword().equals(password)){
            return "token-" + user.getId();
        }
        throw new RuntimeException("Invalid username or password");
    }

    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Id not found id: " + id);
        }
        userRepository.deleteById(id);
    }
}
