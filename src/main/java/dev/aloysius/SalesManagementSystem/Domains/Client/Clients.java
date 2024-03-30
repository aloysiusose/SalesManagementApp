package dev.aloysius.SalesManagementSystem.Domains.Client;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_sequence")
    @SequenceGenerator(name = "client_id_sequence", allocationSize = 1)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    @Embedded
    private Address address;
    //client should have transactions or sale activity
}
