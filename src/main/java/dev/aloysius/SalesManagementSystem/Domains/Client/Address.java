package dev.aloysius.SalesManagementSystem.Domains.Client;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {
    private String country;
    private String province;
    private String streetName;
    private String unitNumber;
}
