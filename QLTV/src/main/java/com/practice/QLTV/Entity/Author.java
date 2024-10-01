package com.practice.QLTV.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import java.util.Date;

@Entity
@Table(name = "Author")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorID;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String SDT; // Phone number

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate dob; // Date of birth

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
