package dev.aloysius.SalesManagementSystem.Repository;

import dev.aloysius.SalesManagementSystem.Domains.ApplicationUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUsers, Integer> {
    Optional<ApplicationUsers> findByEmail(String email);
}
