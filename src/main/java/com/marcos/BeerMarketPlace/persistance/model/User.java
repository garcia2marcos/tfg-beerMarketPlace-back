package com.marcos.BeerMarketPlace.persistance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min=2, max = 12)
    private String username;

    @NotEmpty
    @Size(min=4 , max = 14)
    private String password;

    @NotEmpty
    @Email
    private String email;
}
