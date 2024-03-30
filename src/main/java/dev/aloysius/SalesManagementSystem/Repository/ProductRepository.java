package dev.aloysius.SalesManagementSystem.Repository;

import dev.aloysius.SalesManagementSystem.Domains.Products.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products, Long> {
    Optional<Products> findByProductName(String name);
}
