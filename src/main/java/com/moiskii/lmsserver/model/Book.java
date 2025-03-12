package com.moiskii.lmsserver.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "title", length = 300, nullable = false)
    private String title;

    @Column(name = "author", length = 300, nullable = false)
    private String author;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "accession_number", nullable = false, unique = true)
    private String accessionNumber;
}
