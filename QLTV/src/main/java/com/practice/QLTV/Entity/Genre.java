package com.practice.QLTV.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Genre")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genreID;

    @Column(nullable = false)
    private String name;

    @Column
    private String address;

    @Column
    private String SDT;

    @Column
    private LocalDate dob;

    @OneToMany(mappedBy = "genre")
    private List<Book> books;
}
