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
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  commentId;
    @NotNull(message = "post id cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer postId;
    @NotNull(message = "user id cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer userId;
    @NotNull(message = "content cannot be null")
    @Column(columnDefinition = "varchar(200) not null")
    private String content;
    @NotNull(message = "comment date cannot be null")
    @Column(columnDefinition = "date not null")
    private LocalDate commentDate;
}
