package com.backend.controller;

import com.backend.object.LoginRequest;
import com.backend.object.User;
import com.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user){
        try {
            userService.addUser(user);
            return ResponseEntity.status(201).body(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(201).body(Map.of("message", "user has been deleted"));
        }catch (RuntimeException e) {
            return ResponseEntity.status(409).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/auth/login") // Aqui recibia 2 request, pero debe ir englobado en 1
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try{
            String token  = userService.login(loginRequest.getUsername(), loginRequest.getPassword() );

            return ResponseEntity.status(200).body(Map.of("token", token));
           // return ResponseEntity.status(200).body(token);
        }catch (RuntimeException e){
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }
}