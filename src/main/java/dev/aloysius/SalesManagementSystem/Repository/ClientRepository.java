package dev.aloysius.SalesManagementSystem.Repository;

import dev.aloysius.SalesManagementSystem.Domains.Client.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Clients, Integer> {

    Optional<Clients> findByEmail(String email);
}
