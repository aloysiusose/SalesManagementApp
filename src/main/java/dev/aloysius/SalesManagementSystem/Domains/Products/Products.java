package dev.aloysius.SalesManagementSystem.Domains.Products;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", allocationSize = 1)
    private long id;
    private String productName;
    private String productDescription;
    private String productCategory;
    private LocalDateTime creationDate;
    private int quantity;
    private BigDecimal price;

    private void addProduct(Products products){
        this.quantity += products.quantity;
    }

}

