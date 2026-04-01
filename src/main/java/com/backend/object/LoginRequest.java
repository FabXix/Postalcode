package com.backend.object;

// Este clase es para que springBoot y el fronted se comuniquen
// Por que en springBoot le estamos mandando 2 request, con esto se arregla
public class LoginRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
