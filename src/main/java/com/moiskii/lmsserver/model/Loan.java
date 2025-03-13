package com.moiskii.lmsserver.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "loans")
@Inheritance(strategy = InheritanceType.JOINED)
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "borrowDate", nullable = false)
    private LocalDateTime borrowDate;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "returnDate", nullable = false)
    private LocalDateTime returnDate;

    @JsonIgnore  // Prevent circular reference
    @OneToOne(cascade = {CascadeType.ALL}) // {CascadeType.MERGE}
    private Book book;

    @ManyToOne() //fetch = FetchType.EAGER
    @JoinColumn(name = "member_id")
    private Member member;
}
