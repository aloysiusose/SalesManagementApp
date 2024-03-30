package dev.aloysius.SalesManagementSystem.Domains.Sale;

import dev.aloysius.SalesManagementSystem.Domains.ApplicationUsers;
import dev.aloysius.SalesManagementSystem.Domains.Client.Clients;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales_sequence")
    @SequenceGenerator(name = "sales_sequence", allocationSize = 1)
    private long id;
    private LocalDateTime creationDate = LocalDateTime.now();
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private ApplicationUsers sellers;
    @OneToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Clients clients;

}
