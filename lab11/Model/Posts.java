package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  postId;
    @NotNull(message = "title cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String title;
    @NotNull(message = "content cannot be null")
    @Size(min = 50)
    @Column(columnDefinition = "varchar(200) not null")
    private String  content;
//    @NotNull(message = "user id cannot be null")
//    @Column(columnDefinition = "int not null")
    private Integer  userId;
    @NotNull(message = "publication date cannot be not null")
    @Column(columnDefinition = "date not null")
    private LocalDate  publicationDate;
}
