package dev.aloysius.SalesManagementSystem.Repository;

import dev.aloysius.SalesManagementSystem.Domains.Sale.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Long> {
}
