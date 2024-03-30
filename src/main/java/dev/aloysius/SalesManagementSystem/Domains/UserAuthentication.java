package dev.aloysius.SalesManagementSystem.Domains;

import java.util.Objects;

public record UserAuthentication(String email, String password) {
    public UserAuthentication{
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
    }
}
