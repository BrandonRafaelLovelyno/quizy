package com.brandon.quizy.network.model.request;

public class SignupRequest {
    private final String username;
    private final String email;
    private final String passwordHash;

    public SignupRequest(String username, String email, String passwordHash) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
