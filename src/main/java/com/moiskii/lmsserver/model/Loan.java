package com.moiskii.lmsserver.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
    @DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    @Column(name = "borrowDate", nullable = false)
    private Date borrowDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    @Column(name = "returnDate", nullable = false)
    private Date returnDate;

    @OneToOne(cascade = {CascadeType.ALL}) // {CascadeType.MERGE}
    private Book book;

    @ManyToOne() //fetch = FetchType.EAGER
    @JoinColumn(name = "member_id")
    private Member member;
}
