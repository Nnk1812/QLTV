package com.practice.QLTV.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Book")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookID;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "genreID", nullable = false)
    private Genre genre;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "authorID", nullable = false)
    private Author author;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "publisherID", nullable = false)
    private Publisher publisher;

    @Column(nullable = false)
    private int amount;

}
