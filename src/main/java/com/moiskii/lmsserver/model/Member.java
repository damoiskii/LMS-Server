package com.moiskii.lmsserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "members")
@PrimaryKeyJoinColumn(name = "id")
public class Member extends User {
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "dob", nullable = false)
    private LocalDateTime dob;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "allowance", nullable = false)
    private String allowance;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member", fetch = FetchType.LAZY)
    @OrderBy("borrowDate DESC")
    private Set<Loan> bookLoans;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member", fetch = FetchType.LAZY)
    @OrderBy("createdOn DESC")
    private Set<LateFee> lateFees;
}
