package dev.aloysius.SalesManagementSystem.Domains.Client;

import jakarta.persistence.Embedded;

import java.util.Objects;

public record CreateClient(String firstName,
                           String lastName,
                           String email,
                           String mobile,
                           Address address
                           ) {

    public CreateClient{

        Objects.requireNonNull(firstName());
        Objects.requireNonNull(lastName());
        Objects.requireNonNull(email());
        Objects.requireNonNull(mobile());
    }


}
