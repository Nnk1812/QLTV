package com.practice.QLTV.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import java.util.Date;

@Entity
@Table(name = "User")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String passWord;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String SDT; // Phone number

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dob; // Date of birth

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String CCCD; // Citizen ID

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate doc ;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<BorrowBook> borrows;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;

}
