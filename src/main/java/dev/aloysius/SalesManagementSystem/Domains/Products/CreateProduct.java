package dev.aloysius.SalesManagementSystem.Domains.Products;

import java.math.BigDecimal;
import java.util.Objects;

public record CreateProduct(String name, String description, String category, int quantity, BigDecimal price) {

    public CreateProduct{
        Objects.requireNonNull(name);
        Objects.requireNonNull(description);
        Objects.requireNonNull(category);
        price.min(BigDecimal.ZERO);
        if (quantity <= 0) {
            throw new IllegalStateException();
        }
    }
}
