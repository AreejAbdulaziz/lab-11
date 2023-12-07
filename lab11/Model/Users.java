package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @NotNull(message = "user name cannot be null")
    @Size(min = 4)
    @Column(columnDefinition = "varchar(20) unique not null")
    private String userName;
    @NotNull(message = "email cannot be null")
    @Email(message = "write correct email")
    @Column(columnDefinition = "varchar(20) unique not null")
    private String email;
    @NotNull(message = "password cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;
    @NotNull(message = "registration date cannot be null")
    @Column(columnDefinition = "date not null")
    private LocalDate registrationDate;
}
