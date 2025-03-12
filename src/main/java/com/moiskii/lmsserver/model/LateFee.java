package com.moiskii.lmsserver.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@Table(name = "late_fees")
@Inheritance(strategy = InheritanceType.JOINED)
public class LateFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "created_on", nullable = false)
    private Date createdOn;

    @JsonIgnore  // Prevent circular reference
    @OneToOne(cascade = {CascadeType.ALL}) // {CascadeType.MERGE}
    private Book book;

    @ManyToOne() //fetch = FetchType.EAGER
    @JoinColumn(name = "member_id")
    private Member member;
}
