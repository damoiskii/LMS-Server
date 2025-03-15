package com.moiskii.lmsserver.model;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(exclude = {"loan"})
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

    @JsonIgnore
    @OneToOne(mappedBy = "book", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Loan loan;

    @JsonIgnore
    @OneToOne(mappedBy = "book", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private LateFee lateFee;
}
