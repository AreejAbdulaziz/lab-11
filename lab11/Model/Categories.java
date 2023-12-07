package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @NotNull(message = "category name cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String categoryName;
    @NotNull(message = "post id cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer postId;
}
