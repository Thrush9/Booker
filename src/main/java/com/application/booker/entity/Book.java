package com.application.booker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    @SequenceGenerator(name = "books_seq", allocationSize = 1)
    private Integer id;

    private String name;

    private String author;

    private String genre;
}
