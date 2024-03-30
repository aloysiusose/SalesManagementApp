package dev.aloysius.SalesManagementSystem.Domains;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ApplicationUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", allocationSize = 1)
    private int id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;
    @Column(name = "password")
    private String userPassword;
    @Enumerated(EnumType.STRING)
    private Roles roles;

    public ApplicationUsers(ApplicationUsers users){
        this.id = users.getId();
        this.email = users.getEmail();
        this.userPassword = users.getUserPassword();
        this.roles = users.getRoles();
    }
}
